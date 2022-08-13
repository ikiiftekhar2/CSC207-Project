package controllers.event;

import controllers.appWide.RequestController;
import dataMapper.DataMapper;
import entities.Event;
import useCases.IEventManager;

import java.util.UUID;

public class ChangeInviteRequirementController extends RequestController {
    /**
     * a data mapper to store events
     */
    DataMapper eventModel;

    public ChangeInviteRequirementController(DataMapper eventModel, IEventManager eventManager){
        this.eventManager = eventManager;
        this.eventModel = eventModel;
    }
    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "Change Invite Only requirement";
    }
    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        presenter.inlinePrint("Current Status of Invitation Requirement was set as ");
        Boolean result = eventManager.getEvent(UUID.fromString(requester)).getInviteOnly();
        presenter.inlinePrint(result.toString());
        eventManager.getEvent(UUID.fromString(requester)).setInviteOnly(!result);
        eventModel.deleteItem("id", requester);
        String[] attributes = new String[]{"title", "description", "host", "inviteOnly", "attendees", "invitees", "timePosted", "id"};
        eventModel.addItem(eventManager.getEvent(UUID.fromString(requester)), attributes);
        presenter.inlinePrint("\n Successfully Changed to " + !result);

        return true;
    }
}
