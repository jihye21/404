package _4.chatting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import _4.domain.GroupShareDTO;
import _4.service.group.GroupChattingInfoService;
import jakarta.servlet.http.HttpSession;

@Controller
public class WebSocketController {
	@Autowired
	GroupChattingInfoService groupChattingInfoService;
	private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    
    @MessageMapping("/shareEvent/{groupNum}")
    @SendTo("/topic/event/{groupNum}")
    public void  handleEvent(@DestinationVariable String groupNum
    		, GroupShareDTO groupShareDTO) {
        
    	messagingTemplate.convertAndSend("/topic/event/"+groupNum, groupShareDTO);
        
    }
}
