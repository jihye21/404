package _4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.command.BookCommand;
import _4.domain.BookDTO;
import _4.domain.ThemeDTO;
import _4.mapper.BookMapper;
import _4.mapper.MemberMapper;
import _4.mapper.PurchaseMapper;
import _4.mapper.StoreMapper;
import _4.mapper.ThemeMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import _4.service.book.AfterPayUpdateService;
import _4.service.book.ThemeBookInsertService;
import _4.service.coupon.memberCouponListService;
import _4.service.group.GroupDutchAlarmService;
import _4.service.group.GroupDutchService;
import _4.service.group.GroupListService;
import _4.service.member.MemberPointService;
import _4.service.member.PointUseService;
import _4.service.purchase.IniPayReqService;
import _4.service.purchase.PriceCalcService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	AfterPayUpdateService afterPayUpdateService;
	@Autowired
	PointUseService pointUseService;
	@Autowired
	MemberPointService memberPointService;
	@Autowired
	GroupDutchAlarmService groupDutchAlarmService;
	@Autowired 
	GroupDutchService groupDutchService;
	@Autowired
	GroupListService groupListService;
	@Autowired
	memberCouponListService memberCouponListService;
	@Autowired
	IniPayReqService iniPayReqService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	UserNumService userNumService;
	@Autowired
	ThemeBookInsertService themeBookInsertService;
	@Autowired
	PriceCalcService priceCalcService;
	@Autowired
	PurchaseMapper purchaseMapper;
	@Autowired
	ThemeMapper themeMapper;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	StoreMapper storeMapper;
	@Autowired
	MemberMapper memberMapper;
	
	@PostMapping("themeOrder")
	public String themeOrder(String themeNum, String themeTime, Model model, HttpSession session) {
		ThemeDTO themeDTO = themeMapper.themeSelectOne(themeNum);
		model.addAttribute("themeDTO", themeDTO);
		model.addAttribute("themeTime", themeTime);
		memberCouponListService.execute(session, model);
		groupListService.execute(session, model);
		memberPointService.execute(session, model);
		return "thymeleaf/order/themeOrderPage";
	}
	
	@PostMapping("depositPrice")
	public @ResponseBody Integer depositPrice(@RequestParam("discountedPrice") String discountedPrice, @RequestParam("storeNum") String storeNum, Model model) {
		Integer depositPrice = priceCalcService.execute(discountedPrice, storeNum, model);
		
		return depositPrice;
	}
	
	@PostMapping("payment")
	public String payment(BookCommand bookCommand, Model model, HttpSession session) {
		String memNum = memNum = userNumService.execute(session);
		
		System.out.println("OrderController: " + bookCommand);
		/*
		OrderController: BookCommand(bookNum=null, memNum=mem_100002, 
		groupNum=group_100002, storeNum=null, themeNum=theme_100003, 
		themeTime=10:00, people=1, price=390, depositPrice=1000, finalPrice=null, 
		dutchPrice=500, couponNum=emp_coupon_100001, bookStatus=null, 
		dutchMember=[mem_100001, mem_100002], myDutchPrice=390, memPoint=9600, 
		afterPrice=null, afterDutchPrice=null)
		 */
		
		//예약금이 0원인 경우
		if(bookCommand.getDepositPrice() == 0) {
			bookCommand.setBookStatus("결제완료");
			themeBookInsertService.execute(bookCommand, session);
			return "redirect:/book/memberBookList";
			
		}else if(bookCommand.getPrice() == 0) {
			bookCommand.setBookStatus("결제대기중");
			String bookNum = themeBookInsertService.execute(bookCommand, session);
			
			//포인트 사용 완료
			Integer usedPoint = bookCommand.getMemPoint();
			pointUseService.execute(session, bookNum, usedPoint);
			purchaseMapper.memberPointUpdate(bookNum, memNum);
			purchaseMapper.paymentPointCheck(bookNum, memNum);
			purchaseMapper.pointStatusUpdate(bookNum, memNum);
			
			//쿠폰 적용하기
			if(bookCommand.getCouponNum() != null) {
				purchaseMapper.patmentCouponCheck(bookNum);
			}
			
			bookCommand.setBookStatus("결제완료");
			themeBookInsertService.execute(bookCommand, session);
			return "redirect:/book/memberBookList";
		}
		else {
			bookCommand.setBookStatus("결제대기중");
			String bookNum = themeBookInsertService.execute(bookCommand, session);
			
			BookDTO dto = bookMapper.bookSelectOne(bookNum);
			
			//그룹 결제이면 그룹 더치 금액을 결제하도록 함.
			boolean isGroup = groupDutchService.execute(bookNum, session);
			if(isGroup) {
				dto.setDepositPrice(dto.getDutchPrice());
				
				groupDutchAlarmService.execute(bookNum, bookCommand, session);
				
				//리더의 결제 금액이 0원이면
				if(bookCommand.getMyDutchPrice() == 0) {
					
					//포인트 사용 완료
					Integer usedPoint = bookCommand.getMemPoint();
					pointUseService.execute(session, bookNum, usedPoint);
					purchaseMapper.memberPointUpdate(bookNum, memNum);
					purchaseMapper.paymentPointCheck(bookNum, memNum);
					purchaseMapper.pointStatusUpdate(bookNum, memNum);
					
					//쿠폰 적용하기
					if(bookCommand.getCouponNum() != null) {
						purchaseMapper.patmentCouponCheck(bookNum);
					}
					
					//결제 완료 상태로 변경
					purchaseMapper.groupPaymentCheck(bookNum, memNum);
				}else {
					Integer usedPoint = bookCommand.getMemPoint();
					pointUseService.execute(session, bookNum, usedPoint);
					dto.setDepositPrice(bookCommand.getMyDutchPrice());
					iniPayReqService.execute(dto, model);
				}
				
				//1인 결제
				}else {
				
				//포인트 사용 완료
				Integer usedPoint = bookCommand.getMemPoint();
				pointUseService.execute(session, bookNum, usedPoint);
				purchaseMapper.memberPointUpdate(bookNum, memNum);
				purchaseMapper.paymentPointCheck(bookNum, memNum);
				purchaseMapper.pointStatusUpdate(bookNum, memNum);
				
				//쿠폰 적용하기
				if(bookCommand.getCouponNum() != null) {
					purchaseMapper.patmentCouponCheck(bookNum);
				}
				
				dto.setDepositPrice(bookCommand.getPrice());
				iniPayReqService.execute(dto, model);
			}
			
			return "thymeleaf/purchase/payment";
		}
	}
	
	@PostMapping("afterPayment")
	public String afterPayment(BookCommand bookCommand, Model model, HttpSession session) {
		String memNum = userNumService.execute(session);
		String bookNum = bookCommand.getBookNum();
		
		/*
		 * orderController: BookCommand(bookNum=book_100015, 
		 * memNum=mem_100002, groupNum=on, storeNum=null, 
		 * themeNum=theme_100003, themeTime=10:00, people=null, 
		 * price=null, depositPrice=1000, finalPrice=null, dutchPrice=null, 
		 * couponNum=emp_coupon_100005, bookStatus=null, dutchMember=null, 
		 * myDutchPrice=null, memPoint=9900, afterPrice=710, afterDutchPrice=null)
		 */
		System.out.println("orderController: " + bookCommand);
		
		//그룹 후불 결제인 경우
		boolean isGroup = groupDutchService.execute(bookNum, session);
		if(isGroup) {
			bookCommand.setDutchPrice(bookCommand.getAfterDutchPrice());
			
			//afterDutchPrice에 대한 알림을 dutchMember 에게 보내기
			groupDutchAlarmService.execute(bookNum, bookCommand, session);
			
			//결제 금액이 0원인 경우
			if(bookCommand.getAfterPrice() == 0) {
				//포인트 사용 완료
				Integer usedPoint = bookCommand.getMemPoint();
				pointUseService.execute(session, bookNum, usedPoint);
				purchaseMapper.memberPointUpdate(bookNum, memNum);
				purchaseMapper.paymentPointCheck(bookNum, memNum);
				purchaseMapper.pointStatusUpdate(bookNum, memNum);
				
				//쿠폰 적용하기
				if(bookCommand.getCouponNum() != null) {
					purchaseMapper.patmentCouponCheck(bookNum);
				}
				
				//후불 결제 update 
				afterPayUpdateService.execute(session, bookCommand);
				
				//후불 결제 완료 상태로 udpate
				purchaseMapper.afterPaySuccess(bookNum);
				
				return "redirect:/book/memberBookList";
			}
		}else //결제 금액이 0원인 경우
			if(bookCommand.getAfterPrice() == 0) {
				//포인트 사용 완료
				Integer usedPoint = bookCommand.getMemPoint();
				pointUseService.execute(session, bookNum, usedPoint);
				purchaseMapper.memberPointUpdate(bookNum, memNum);
				purchaseMapper.paymentPointCheck(bookNum, memNum);
				purchaseMapper.pointStatusUpdate(bookNum, memNum);
				
				//쿠폰 적용하기
				if(bookCommand.getCouponNum() != null) {
					purchaseMapper.patmentCouponCheck(bookNum);
				}
				
				//후불 결제 update 
				afterPayUpdateService.execute(session, bookCommand);
				
				//후불 결제 완료 상태로 udpate
				purchaseMapper.afterPaySuccess(bookNum);
				
				return "redirect:/book/memberBookList";
			}
			
		//포인트 사용 완료
		Integer usedPoint = bookCommand.getMemPoint();
		pointUseService.execute(session, bookNum, usedPoint);
		purchaseMapper.paymentPointCheck(bookNum, memNum);
		purchaseMapper.pointStatusUpdate(bookNum, memNum);
		
		//후불 결제 update 
		afterPayUpdateService.execute(session, bookCommand);
		
		//afterPrice를 이니시스로 결제하기
		BookDTO bookDTO = bookMapper.bookSelectOne(bookNum);
		bookDTO.setDepositPrice(bookDTO.getAfterPrice());
		
		iniPayReqService.execute(bookDTO, model);
		
		return "thymeleaf/purchase/payment";
	}
	
	@PostMapping("afterGroupPayment")
	public String afterGroupPayment(BookCommand bookCommand, Model model, HttpSession session) {
		String memNum = userNumService.execute(session);
		
		String bookNum = bookCommand.getBookNum();
		//더치페이 결제
		
		//포인트 사용 완료
		Integer usedPoint = bookCommand.getMemPoint();
		pointUseService.execute(session, bookNum, usedPoint);
		purchaseMapper.paymentPointCheck(bookNum, memNum);
		purchaseMapper.pointStatusUpdate(bookNum, memNum);
		
		//afterPrice를 이니시스로 결제하기
		BookDTO bookDTO = bookMapper.bookSelectOne(bookNum);
		bookDTO.setDepositPrice(bookCommand.getAfterPrice());
		
		if(bookCommand.getAfterPrice() > 0) {
			iniPayReqService.execute(bookDTO, model);
		}
		
		//더치 결제 완료
		purchaseMapper.groupPaymentCheck(bookNum, memNum);
		
		return "thymeleaf/purchase/payment";
	}
}
