package roman.kononenko.chat_se.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import roman.kononenko.chat_se.dto.UserRequest;
import roman.kononenko.chat_se.entity.ChatMessage;
import roman.kononenko.chat_se.entity.User;
import roman.kononenko.chat_se.repositories.MsgRepository;
import roman.kononenko.chat_se.repositories.UserRepository;

import java.util.List;
import java.util.ListIterator;

@Service
public class ChatService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MsgRepository msgRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }


    public List<ChatMessage> getLastMsgs(){
        final int quantityOfReturnMsgs = 10;

        List<ChatMessage> messages =  msgRepository.findAll();

        messages = messages.subList(messages.size()-quantityOfReturnMsgs, messages.size());

        return messages;
    }

    public String registration(UserRequest userRequest){
        if(!checkIsExistUser(userRequest.getUsername())){
            User user = new User();

            user.setUsername(userRequest.getUsername());
            user.setPassword( new BCryptPasswordEncoder().encode(userRequest.getPassword()));

            userRepository.save(user);
        } else {
            return "exist";
        }
        return "done";
    }

    public String saveMsg(ChatMessage chatMessage){
        msgRepository.save(chatMessage);
        return "accept";
    }

    private boolean checkIsExistUser(String username){
        User findUser = getUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
        if(findUser == null) {
            //User not found
            return false;
        }
        return true;
    }
}
