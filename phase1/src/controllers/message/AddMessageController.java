package controllers.message;

import controllers.appWide.RequestController;
import dataMapper.DataMapper;
import useCases.IMessageManager;
import useCases.IAccountManager;
import java.util.UUID;

public class AddMessageController extends RequestController{

    /**
     * a data mapper responsible for mapping messages into a data structure usable by the presenters
     */
    DataMapper messageModel;

    /**
     * a use case responsible for managing messages
     */
    IMessageManager messageManager;

    /**
     * a string representing the sender of the message
     */
    String sender;

    /**
     * Constructor for a controller responsible for reading input to create and send a new message
     *
     * @param messageModel the DataMapper to be used by this controller
     * @param messageManager a use case responsible for managing messages
     * @param sender a string representing the username of the sender of this message
     */
    public AddMessageController(DataMapper messageModel, IMessageManager messageManager, String sender){
        this.messageModel = messageModel;
        this.messageManager = messageManager;
        this.sender = sender;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() { return "Send a message"; }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        presenter.input();
        presenter.inlinePrint("Recipient: ");
        String receiver = presenter.input.nextLine();
        presenter.inlinePrint("Message: ");
        String content = presenter.input.nextLine();
        sleeper.sleep(200);
        UUID messageId = messageManager.addMessage(sender, receiver, content);
        String[] attributes = new String[]{ "sender", "receiver", "content", "id"};
        messageModel.addItem(messageManager.getMessage(messageId), attributes);
        presenter.blockPrint("Message successfully sent");
        return false;
    }
}
