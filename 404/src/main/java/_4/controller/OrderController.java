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
import _4.mapper.StoreMapper;
import _4.mapper.ThemeMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import _4.service.book.ThemeBookInsertService;
import _4.service.coupon.memberCouponListService;
import _4.service.group.GroupDutchAlarmService;
import _4.service.group.GroupDutchService;
import _4.service.group.GroupListService;
import _4.service.purchase.IniPayReqService;
import _4.service.purchase.PriceCalcService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("order")
public class OrderController {
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
		return "thymeleaf/order/themeOrderPage";
	}
	
	@PostMapping("depositPrice")
	public @ResponseBody Integer depositPrice(@RequestParam("discountedPrice") String discountedPrice, @RequestParam("storeNum") String storeNum, Model model) {
		Integer depositPrice = priceCalcService.execute(discountedPrice, storeNum, model);
		System.out.println("음");
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
			}
			
			iniPayReqService.execute(dto, model);
			return "thymeleaf/purchase/payment";
		}
	}

}
