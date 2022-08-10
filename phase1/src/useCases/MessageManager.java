package useCases;

import entities.Account;
import entities.Message;
import gateway.IReader;
import gateway.IWriter;

import java.util.*;


public class MessageManager implements IMessageManager {
    /**
     * A mapping of the sender of the message to the message entity
     */
    HashMap<UUID, Message> messages = new HashMap<>();
    /**
     * a gateway responsible for reading objects
     */
    IReader reader;
    /**
     * a gateway responsible for writing objects
     */
    IWriter writer;

    /**
     * Constructor of a use case responsible for managing messages.
     *
     * @param reader a gateway responsible for reading objects
     * @param writer a gateway responsible for writing objects
     */
    public MessageManager(IReader reader, IWriter writer){
        this.reader = reader;
        this.writer = writer;
        messages = reader.read(messages.getClass());
    }

    /**
     * @inheritDoc
     */
    @Override
    public ArrayList<Message> getInbox(String username){
        ArrayList<Message> messages = new ArrayList<>();
        for (UUID id : this.messages.keySet()){
            Message message = this. messages.get(id);
            if (message.getReceiver().equals(username)){
               messages.add(message);
            }
        }
        return messages;
    }

    public Message createMessage(String sender, String receiver, String content){
        return new Message(sender, receiver, content);
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID addMessage(String sender, String receiver, String content){
        Message message = createMessage(sender, receiver, content);
        messages.put(message.getMessageId(), message);
        return message.getMessageId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Message getMessage(UUID id) { return messages.get(id); }

    /**
     * @inheritDoc
     */
    @Override
    public void save() { writer.write(messages);}
}
