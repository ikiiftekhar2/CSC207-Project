package entities;

import entities.Account;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Message implements Serializable {
    /**
     * The sender of the message
     */
    String sender;
    /**
     * The receiver of the message
     */
    String receiver;
    /**
     * A string containing the content of the message
     */
    String content;
    /**
     * the id of the message
     */
    UUID id;
    /**
     * the time the message was sent
     */
    LocalDateTime timeSent;

    public Message(String sender, String receiver, String content){
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timeSent = LocalDateTime.now();
        id = UUID.randomUUID();
    }

    public String getSender() { return sender; }
    public String getReceiver() { return receiver; }

    public String getMessage() { return content; }

    public UUID getMessageId() { return id; }

    public LocalDateTime getTimeSent() { return timeSent; }
}
