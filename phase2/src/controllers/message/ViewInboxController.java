package controllers.message;

import controllers.appWide.RequestController;
import controllers.appWide.RequestFacade;
import controllers.appWide.ReturnController;
import dataMapper.DataMapper;
import useCases.IMessageManager;
import presenters.MessagePresenter;

import java.util.UUID;

public class ViewInboxController extends RequestController {
    /**
     * a use case responsible for managing messages
     */
    IMessageManager messageManager;
    /**
     * a data mapper to store messages
     */
    DataMapper messageModel;

    /**
     * Constructor for a controller responsible for accessing the inbox of a user.
     *
     * @param messageManager use case responsible for managing messages
     */
    public ViewInboxController(DataMapper messageModel, IMessageManager messageManager) {
        this.messageModel = messageModel;
        this.messageManager = messageManager;
    }

    /**
     * inheritDoc
     */
    @Override
    public String getRequestDescription() { return "View Inbox"; }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        messageModel.reset();
        messageModel.addItems(
                messageManager.getInbox(requester),
                new String[]{"sender", "content", "timeSent"}
        );

        MessagePresenter messagePresenter = new MessagePresenter();
        messagePresenter.printMessages(messageModel.getModel());

        RequestFacade viewMessagesFacade = new RequestFacade(
                new RequestController[] {
                        new ReturnController()
                }
        );
        viewMessagesFacade.setRequester(requester);
        viewMessagesFacade.presentRequest();
        return false;
    }
}
