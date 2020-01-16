package roman.kononenko.chat_se.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roman.kononenko.chat_se.dto.UserRequest;
import roman.kononenko.chat_se.entity.ChatMessage;
import roman.kononenko.chat_se.service.ChatService;
import org.springframework.messaging.handler.annotation.Payload;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin /// ????????????????????????
public class ServiceController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/register")
    public String registrat(@Valid @RequestBody UserRequest userRequest){
        return chatService.registration(userRequest);}


    @RequestMapping("/saveMsg")
    public String savingMessage(@Valid @RequestBody ChatMessage chatMessage) {
        return chatService.saveMsg(chatMessage);
    }

    @RequestMapping("/hello")
    public String test(){
        return "Hello test rest";
    }

    @GetMapping("/getLastMsgs")
    public List<ChatMessage> lastMsgs() {return chatService.getLastMsgs();}
}
