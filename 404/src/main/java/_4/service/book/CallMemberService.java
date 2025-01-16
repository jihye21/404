package _4.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.domain.StoreDTO;
import _4.mapper.BookMapper;
import _4.mapper.MemberMapper;
import _4.mapper.StoreMapper;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Balance;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
public class CallMemberService {
	@Autowired
	BookMapper bookMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	StoreMapper storeMapper;
	
	final DefaultMessageService messageService;
	public CallMemberService() {
		this.messageService = NurigoApp.INSTANCE.initialize("뭘봐", "뭘봐", "https://api.coolsms.co.kr");
	}
	public void execute(String bookNum) {
		String storeNum = storeMapper.waitNumSelectOne(bookNum).getStoreNum();
		String storeName = storeMapper.storeSelectOne(storeNum).getStoreName();
		String memberNum = bookMapper.bookSelectOne(bookNum).getMemNum();
		String memberPhone = memberMapper.memberSelectOne(memberNum).getMemPhone();
		System.out.println(memberPhone);
		String content = "안녕하세요. " + storeName + "입니다. 고객님의 입장까지 2팀 남았습니다. 매장 앞에서 대기해주세요.";
		String storePhone = "010-7146-1970";
		Message message = new Message();
		message.setFrom(storePhone);
		message.setTo(memberPhone);
		message.setText(content);
		SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
		System.out.println(response);
		Balance balance = this.messageService.getBalance();
		System.out.println(balance);
	}
	
}
