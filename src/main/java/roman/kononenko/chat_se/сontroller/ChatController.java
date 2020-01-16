package roman.kononenko.chat_se.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import roman.kononenko.chat_se.dto.UserRequest;
import roman.kononenko.chat_se.entity.ChatMessage;
import roman.kononenko.chat_se.service.ChatService;

import javax.validation.Valid;

@Controller
public class ChatController {

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

        return chatMessage;
    }

    @RequestMapping("/")
    public String start(){
        return "index.html";
    }

    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

    @RequestMapping("/signup")
    public String registration(){
        return "registration.html";
    }


//    @Autowired
//    private ChatService chatService;
//
//    @PostMapping("/registration")
//    public String registration(@Valid @RequestBody UserRequest userRequest){
//        return chatService.registration(userRequest);}

}
