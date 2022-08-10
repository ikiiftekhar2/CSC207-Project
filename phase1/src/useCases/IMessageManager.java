package useCases;

import java.util.ArrayList;
import entities.Message;
import java.util.UUID;

public interface IMessageManager {
    /**
     * Return the list of messages sent to the given username
     *
     * @param username a string representing the username of a user
     * @return a list of messages received by the account with the provided username
     */
    ArrayList<Message> getInbox(String username);

    /**
     * Add a new message given metadata about the message
     *
     * @param sender the username of the sender of the message
     * @param receiver the username of the receiver of the message
     * @param content the content of the message
     */
    UUID addMessage(String sender, String receiver, String content);

    /**
     * Return a Message entity corresponding to the given id
     *
     * @param id the id of the message to be returned
     * @return the Message entity with an id that matches the provided id
     */
    Message getMessage(UUID id);

    /**
     * Save the current data
     */
    void save();
}
