package roman.kononenko.chat_se.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.kononenko.chat_se.entity.ChatMessage;

import java.util.List;

@Repository
public interface MsgRepository extends JpaRepository<ChatMessage, Long> {
    //List<ChatMessage> find
}
