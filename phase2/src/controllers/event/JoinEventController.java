package controllers.event;

import controllers.appWide.RequestController;
import dataMapper.DataMapper;
import useCases.IEventManager;

import java.util.UUID;

public class JoinEventController extends RequestController {
    /**
     * a string containing the username of the person who wants to join the event
     */
    String joiner;
    /**
     * a data mapper to store events
     */
    DataMapper eventModel;

    public JoinEventController(DataMapper eventModel, IEventManager eventManager, String joiner){
        this.eventManager = eventManager;
        this.joiner = joiner;
        this.eventModel = eventModel;
    }

    @Override
    public String getRequestDescription() {
        return "Join this Event";
    }

    @Override
    public boolean handleRequest(String requester) {
        Boolean state = eventManager.attendEvent(joiner, UUID.fromString(requester));
        if (state) {
            eventModel.deleteItem("id", requester);
            String[] attributes = new String[]{"title", "description", "host", "inviteOnly", "attendees", "invitees", "timePosted", "id"};
            eventModel.addItem(eventManager.getEvent(UUID.fromString(requester)), attributes);
            presenter.inlinePrint("Successfully Attending the event");
        } else {
            presenter.inlinePrint("You can't join the event");
        }
        return true;
    }
}
