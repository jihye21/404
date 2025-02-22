package _4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _4.command.BookCommand;
import _4.command.GroupCommand;
import _4.domain.BookDTO;
import _4.domain.GroupDTO;
import _4.domain.PaymentDTO;
import _4.domain.ReviewDTO;
import _4.mapper.BookMapper;
import _4.mapper.PurchaseMapper;
import _4.mapper.ReviewMapper;
import _4.mapper.service.AutoNumService;
import _4.mapper.service.UserNumService;
import _4.service.coupon.memberCouponListService;
import _4.service.group.GroupAlarmCount;
import _4.service.group.GroupAlarmListService;
import _4.service.group.GroupBookInfoService;
import _4.service.group.GroupDetailService;
import _4.service.group.GroupDutchAlarmCount;
import _4.service.group.GroupDutchAlarmListService;
import _4.service.group.GroupDutchAlarmService;
import _4.service.group.GroupEnterService;
import _4.service.group.GroupListService;
import _4.service.group.GroupMemberSearchService;
import _4.service.group.GroupPaymentHistoryListService;
import _4.service.group.GroupQuitService;
import _4.service.group.GroupRegistService;
import _4.service.group.MemberDutchPaymentCheckService;
import _4.service.member.MemberPointService;
import _4.service.member.PointUseService;
import _4.service.purchase.IniPayReqService;
import _4.service.review.ReviewGroupMemberListService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("group")
public class GroupController {
	@Autowired
	PointUseService pointUseService;
	@Autowired
	GroupBookInfoService groupBookInfoService;
	@Autowired
	MemberPointService memberPointService;
	@Autowired
	memberCouponListService memberCouponListService;
	@Autowired
	MemberDutchPaymentCheckService memberDutchPaymentCheckService;
	@Autowired
	GroupPaymentHistoryListService groupPaymentHistoryListService;
	@Autowired
	IniPayReqService iniPayReqService;
	@Autowired
	GroupDutchAlarmListService groupDutchAlarmListService;
	@Autowired
	GroupDutchAlarmCount groupDutchAlarmCount;
	@Autowired
	ReviewMapper reviewMapper;
	@Autowired
	UserNumService userNumService;
	@Autowired
	BookMapper bookMapper;
	@Autowired
	GroupDetailService groupDetailService;
	@Autowired
	GroupQuitService groupQuitService;
	@Autowired
	GroupEnterService groupEnterService;
	@Autowired
	GroupAlarmListService groupAlarmListService;
	@Autowired
	GroupAlarmCount groupAlarmCount;
	@Autowired
	GroupListService groupListService;
	@Autowired
	GroupRegistService groupRegistService;
	@Autowired
	GroupMemberSearchService groupMemberSearchService;
	@Autowired
	ReviewGroupMemberListService reviewGroupMemberListService;
	@Autowired
	PurchaseMapper purchaseMapper;
	@GetMapping("groupList")
	public String groupList(HttpSession session, Model model) {
		groupListService.execute(session, model);
		groupAlarmListService.execute(session, model);
		groupDutchAlarmListService.execute(session, model);
		return "thymeleaf/group/groupList";
	}
	
	@PostMapping("GroupMemberSearch")
	public @ResponseBody List<GroupDTO> GroupMemberSearch(@RequestParam("memName")String memName
			, HttpSession session, Model model) {
		List<GroupDTO> groupList = groupMemberSearchService.execute(memName, session);
		
		return groupList;
	}
	
	@PostMapping("GroupForm")
	public String GroupForm(GroupCommand groupCommand, HttpSession session) {
		groupRegistService.execute(groupCommand, session);
		return "redirect:/group/groupList";
	}
	
	@PostMapping("alarmCount")
	public @ResponseBody Integer alarmCount(HttpSession session) {
		Integer alarmCount = groupAlarmCount.execute(session);
		Integer payAlarmCount = groupDutchAlarmCount.execute(session);
		alarmCount += payAlarmCount;
		return alarmCount;
	}
	
	@PostMapping("groupEnter")
	public @ResponseBody void groupEnter(@RequestParam ("groupNum") String groupNum, HttpSession session) {
		groupEnterService.execute(groupNum, session);
	}
	
	@PostMapping("groupQuit")
	public @ResponseBody void groupQuit(@RequestParam ("groupNum") String groupNum, HttpSession session) {
		groupQuitService.execute(groupNum, session);
	}
	
	@GetMapping("groupDetail")
	public String groupDetail(@RequestParam String groupNum, Model model, HttpSession session) {
		//그룹 회원 목록 가져오기
		groupDetailService.execute(groupNum, model);
		
		//예약 리스트 가져오기
		String memberNum = userNumService.execute(session);
		List<BookDTO> list = bookMapper.bookGroupSelectAllWithMember(memberNum, groupNum);
		model.addAttribute("list", list);
		
		groupPaymentHistoryListService.execute(groupNum, model);
		memberDutchPaymentCheckService.execute(groupNum, session, model);
		memberPointService.execute(session, model);
		model.addAttribute("groupNum", groupNum);
		
		return "thymeleaf/group/groupDetail";
	}
	
	@GetMapping("groupBookDetail")
	public String groupBookDetail(String bookNum, Model model, HttpSession session) {
		String memNum = userNumService.execute(session);
		
		BookDTO dto = bookMapper.bookSelectOne(bookNum);
		List<ReviewDTO> reviewDTO = reviewMapper.groupReviewSelectOneWithBookNum(bookNum);
		
		boolean reviewNull = true;
		for(ReviewDTO reviewMem : reviewDTO) {
			if(reviewMem.getMemNum().equals(memNum)) {
				reviewNull = false;
			}
		}
		
		model.addAttribute("reviewNull", reviewNull);
		model.addAttribute("dto", dto);
		model.addAttribute("reviewDTO", reviewDTO);

		reviewGroupMemberListService.execute(bookNum, model, session);
		
		return "thymeleaf/group/groupBookDetail";
	}
	
	@PostMapping("groupDutchPay")
	public String groupDutchPayEnter(GroupCommand groupCommand, Model model, HttpSession session, BookCommand bookCommand) {
		
		BookDTO dto = new BookDTO();
		
		String memNum = memNum = userNumService.execute(session);
		String bookNum = groupCommand.getBookNum();
		dto = bookMapper.bookSelectOne(bookNum);
		/*
		dto.setDepositPrice(groupCommand.getMyDutchPrice());
		iniPayReqService.execute(dto, model);
		*/
		if(groupCommand.getMyDutchPrice() == 0) {

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
			
			//더치 결제 완료
			purchaseMapper.groupPaymentCheck(bookNum, memNum);
			
			return "redirect:/group/groupList";
		}
		else {
			
			dto = bookMapper.bookSelectOne(bookNum);
				
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
				
			dto.setDepositPrice(bookCommand.getMyDutchPrice());
			iniPayReqService.execute(dto, model);
			
			//더치 결제 완료
			purchaseMapper.groupPaymentCheck(bookNum, memNum);
			}
		
		return "thymeleaf/purchase/payment";
	}
	
	@PostMapping("groupMemberPayment")
	public String groupMemberPayment(Model model, HttpSession session
			, GroupCommand groupCommand) {
		groupBookInfoService.execute(groupCommand, model, session);
		memberCouponListService.execute(session, model);
		memberPointService.execute(session, model);
		return "thymeleaf/group/groupMemberPayment";
	}
	
}
