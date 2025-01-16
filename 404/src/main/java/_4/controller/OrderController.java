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
import _4.mapper.PurchaseMapper;
import _4.mapper.StoreMapper;
import _4.mapper.ThemeMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import _4.service.book.ThemeBookInsertService;
import _4.service.coupon.memberCouponListService;
import _4.service.group.GroupDutchAlarmService;
import _4.service.group.GroupDutchService;
import _4.service.group.GroupListService;
import _4.service.member.MemberPointService;
import _4.service.purchase.IniPayReqService;
import _4.service.purchase.PriceCalcService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("order")
public class OrderController {
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
		if(bookCommand.getDepositPrice() == 0) {
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
					String memNum = userNumService.execute(session);
					//결제 완료 상태로 변경
					purchaseMapper.groupPaymentCheck(bookNum, memNum);
				}else {
					dto.setDepositPrice(bookCommand.getMyDutchPrice());
					iniPayReqService.execute(dto, model);
				}
			}
			
			dto.setDepositPrice(bookCommand.getDepositPrice());
			iniPayReqService.execute(dto, model);
			
			return "thymeleaf/purchase/payment";
		}
	}
	
	@PostMapping("afterPayment")
	public String afterPayment(BookCommand bookCommand, Model model, HttpSession session) {
		//그룹 후불 결제인 경우
		boolean isGroup = groupDutchService.execute(bookNum, session);
		if(isGroup) {
			
		}
		/*
		
		if(groupNum.equals("group_")) {
			//그룹 더치페이 인원 수 가져오기
			Integer memberCount = groupMapper.groupDutchMemberCount(groupNum);
			
			//finalPrice = 원가 + 추가 결제 금액
			//finalPrice - 예약금 / 인원 수 
			
			//더치페이 알림을 받은 그룹원만 가져와서 후불 결제 알림 보내기
			//dutchPrice에 후불 결제 금액 담기?
		}
		*/
		
		//1인 후불 결제인 경우 바로 보내기
		return "redirect:/group/groupList";
	}
}
