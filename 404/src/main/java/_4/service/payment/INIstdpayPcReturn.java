package _4.service.payment;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
import com.inicis.std.util.SignatureUtil;

import _4.domain.BookDTO;
import _4.domain.PaymentDTO;
import _4.mapper.BookMapper;
import _4.mapper.MemberMapper;
import _4.mapper.PurchaseMapper;
import _4.mapper.service.UserNumService;
import _4.service.group.GroupDutchAlarmService;
import _4.service.group.GroupDutchService;
import _4.service.member.PointUseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class INIstdpayPcReturn {
	@Autowired
	PointUseService pointUseService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	GroupDutchAlarmService groupDutchAlarmService;
	@Autowired 
	GroupDutchService groupDutchService;
	@Autowired
	PurchaseMapper purchaseMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	BookMapper bookMapper;
	public void execute(HttpServletRequest request, HttpSession session) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try{
			//#############################
			// 인증결과 파라미터 일괄 수신
			//#############################
			request.setCharacterEncoding("UTF-8");

			Map<String,String> paramMap = new Hashtable<String,String>();

			Enumeration elems = request.getParameterNames();

			String temp = "";

			while(elems.hasMoreElements())
			{
				temp = (String) elems.nextElement();
				paramMap.put(temp, request.getParameter(temp));
			}
			
			System.out.println("paramMap : "+ paramMap.toString());
			
			
			if("0000".equals(paramMap.get("resultCode"))){

				System.out.println("####인증성공/승인요청####");

				//############################################
				// 1.전문 필드 값 설정(***가맹점 개발수정***)
				//############################################
				
				String mid 		= paramMap.get("mid");
				String timestamp= SignatureUtil.getTimestamp();
				String charset 	= "UTF-8";
				String format 	= "JSON";
				String authToken= paramMap.get("authToken");
				String authUrl	= paramMap.get("authUrl");
				String netCancel= paramMap.get("netCancelUrl");	
				String merchantData = paramMap.get("merchantData");
				
				//#####################
				// 2.signature 생성
				//#####################
				Map<String, String> signParam = new HashMap<String, String>();

				signParam.put("authToken",	authToken);		// 필수
				signParam.put("timestamp",	timestamp);		// 필수

				// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
				String signature = SignatureUtil.makeSignature(signParam);


				//#####################
				// 3.API 요청 전문 생성
				//#####################
				Map<String, String> authMap = new Hashtable<String, String>();

				authMap.put("mid"			,mid);			// 필수
				authMap.put("authToken"		,authToken);	// 필수
				authMap.put("signature"		,signature);	// 필수
				authMap.put("timestamp"		,timestamp);	// 필수
				authMap.put("charset"		,charset);		// default=UTF-8
				authMap.put("format"		,format);	    // default=XML


				HttpUtil httpUtil = new HttpUtil();

				
				try{
					//#####################
					// 4.API 통신 시작
					//#####################

					String authResultString = "";

					authResultString = httpUtil.processHTTP(authMap, authUrl);
					
					//############################################################
					//5.API 통신결과 처리(***가맹점 개발수정***)
					//############################################################
					
					String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ","").replace("\n", "").replace("}", "").replace("{", "");
					
								
					resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱
					
					
				  // 수신결과를 파싱후 resultCode가 "0000"이면 승인성공 이외 실패

				  //throw new Exception("강제 Exception");
					PaymentDTO dto = new PaymentDTO();
					dto.setApplDate(resultMap.get("applDate"));
					dto.setApplTime(resultMap.get("applTime"));
					dto.setCardNum(resultMap.get("CARD_Num"));
					dto.setConfirmNumber(resultMap.get("applNum"));
					dto.setPayMethod(resultMap.get("payMethod"));
					dto.setPurchaseNum(resultMap.get("MOID"));
					dto.setResultMessage(resultMap.get("resultMsg"));
					dto.setTid(resultMap.get("tid"));
					dto.setTotalPrice(resultMap.get("TotPrice"));
					System.out.println("주문번호 : " + dto.getPurchaseNum());
					purchaseMapper.paymentInsert(dto);
					
					String memNum = userNumService.execute(session);
					String bookNum = dto.getPurchaseNum();

					//그룹 결제이면 그룹 더치 금액을 결제하도록 함.
					boolean isGroup = groupDutchService.execute(bookNum, session);
					if(isGroup) {
						
						BookDTO bookDTO = bookMapper.bookSelectOne(bookNum);
						String bookStatus = bookDTO.getBookStatus();
						
						//리더 결제 완료
						purchaseMapper.groupPaymentCheck(bookNum, memNum);
						
						//후불 결제인지 확인하기 book_status == "후불결제대기"이면 
						if(bookStatus.equals("후불결제대기")) {
							
							purchaseMapper.groupPaymentCheck(bookNum, memNum);
							purchaseMapper.patmentCouponCheck(dto.getPurchaseNum());
							purchaseMapper.memberPointUpdate(bookNum, memNum);
							purchaseMapper.paymentPointCheck(bookNum, memNum);
							purchaseMapper.pointStatusUpdate(bookNum, memNum);
						
							String groupPaySuccess = purchaseMapper.groupPaySuccess(bookNum);
							//모든 그룹원이 결제가 되었는지 확인하기
							if(!"미결제".equals(groupPaySuccess)) {
								//모든 그룹원이 결제가 되었다면 후불결제 완료 상태로 변경하기
								purchaseMapper.afterPaySuccess(bookNum);
							}
						}else {
							String groupPaySuccess = purchaseMapper.groupPaySuccess(bookNum);

							//모든 그룹원이 결제가 되었는지 확인하기
							if(groupPaySuccess == null) {
							purchaseMapper.paymentCheck(dto.getPurchaseNum());
							
							//theme_time != '종일권'이면 시간제 테마 예약 완료로 update 
							purchaseMapper.themeTimeBookStatusUpdate(dto.getPurchaseNum());
							}else {
								
								//그룹원들의 결제 되지 않았다면
								purchaseMapper.patmentCouponCheck(dto.getPurchaseNum());
								purchaseMapper.memberPointUpdate(bookNum, memNum);
								purchaseMapper.paymentPointCheck(bookNum, memNum);
								purchaseMapper.pointStatusUpdate(bookNum, memNum);
							}
							
							
						}
					}else {
						//1인 결제인 경우
						
						BookDTO bookDTO = bookMapper.bookSelectOne(bookNum);
						String bookStatus = bookDTO.getBookStatus();
						//후불 결제인지 확인하기 book_status == "후불결제대기"이면 
						if(bookStatus.equals("후불결제대기")) {
							//후불결제완료 상태로 변경하기
							purchaseMapper.afterPaySuccess(bookNum);
							
							purchaseMapper.patmentCouponCheck(dto.getPurchaseNum());
							//포인트 update
							purchaseMapper.memberPointUpdate(bookNum, memNum);
							purchaseMapper.paymentPointCheck(bookNum, memNum);
							purchaseMapper.pointStatusUpdate(bookNum, memNum);
						}
						else{
							purchaseMapper.paymentCheck(dto.getPurchaseNum());
							//theme_time != '종일권'이면 시간제 테마 예약 완료로 update 
							purchaseMapper.themeTimeBookStatusUpdate(dto.getPurchaseNum());
						}
					}
					
				} catch (Exception ex) {
					//####################################
					// 실패시 처리(***가맹점 개발수정***)
					//####################################

					//---- db 저장 실패시 등 예외처리----//
					System.out.println(ex);

					//#####################
					// 망취소 API
					//#####################
					String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);	// 망취소 요청 API url(고정, 임의 세팅 금지)

					System.out.println("## 망취소 API 결과 ##");

					// 취소 결과 확인
					System.out.println(netcancelResultString);
					ex.printStackTrace();
				}

			}else{
				resultMap.put("resultCode", paramMap.get("resultCode"));
				resultMap.put("resultMsg", paramMap.get("resultMsg"));
			}
		}catch(Exception e){

			System.out.println(e);
		}
	}
}
