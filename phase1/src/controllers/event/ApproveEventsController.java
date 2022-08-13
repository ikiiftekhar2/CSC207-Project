package controllers.event;

import controllers.appWide.RequestController;
import dataMapper.DataMapper;
import entities.Event;
import useCases.IAccountManager;
import useCases.IEventManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ApproveEventsController extends RequestController {

    DataMapper eventModel;
    String username;

    /**
     * Constructor for a controller responsible for reading input to sign users up.
     *
     * @param eventModel    a data mapper that helps map events into a data structure usable by presenters
     * @param eventManager  a use case responsible for managing events
     * @param accountManager a use case responsible for managing accounts
     * @param username  a string containing the username of the approvee
     */

    public ApproveEventsController(IAccountManager accountManager, IEventManager eventManager, DataMapper eventModel, String username) {
        this.eventModel = eventModel;
        this.eventManager = eventManager;
        this.accountManager = accountManager;
        this.username = username;
    }
    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "Approve Pending events";
    }
    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        if (accountManager.isAdmin(username)) {
            HashMap<UUID, Event> aprovees = eventManager.getPendingEvents();
            eventManager.approveAll();
            for (Map.Entry<UUID, Event> entry : aprovees.entrySet()) {
                String[] attributes = new String[]{"title", "description", "host", "inviteOnly", "attendees", "invitees", "timePosted", "id"};
                eventModel.addItem(eventManager.getEvent(entry.getKey()), attributes);
            }
            presenter.inlinePrint("Approved all pending events");
        } else {
            presenter.inlinePrint("You do not have permission");
        }

        return false;
    }
}
