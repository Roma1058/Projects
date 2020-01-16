package roman.kononenko.chat_se.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(schema = "chat_schema")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String sender;
    private MessageType type;

    public enum MessageType {
        CHAT, LEAVE, JOIN
    }
}
