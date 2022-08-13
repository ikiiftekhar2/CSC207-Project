package controllers.event;

import controllers.appWide.RequestController;
import entities.Event;
import useCases.IEventManager;

import java.util.*;


public class PendingEventController extends RequestController {

    IEventManager eventManager;

    /**
     * Constructor for a controller responsible for reading input to sign users up.
     * @param eventManager  a use case responsible for managing events
     */

    public PendingEventController(IEventManager eventManager) {
        this.eventManager = eventManager;
    }
    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "View Pending Events";
    }
    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        HashMap<UUID, Event> eventMap = this.eventManager.getPendingEvents();
        for (Map.Entry<UUID, Event> entry : eventMap.entrySet()) {
            presenter.inlinePrint("\n");
            presenter.inlinePrint("Title: " + entry.getValue().getTitle() + "\n");
            presenter.inlinePrint("Description: " + entry.getValue().getContent() + "\n");
            presenter.inlinePrint("Invitation requirement: " + entry.getValue().getInviteOnly() + "\n");
            HashSet<String> invitees = entry.getValue().getInvitees();
            presenter.inlinePrint("Invitees: "  + "\n");
            for (String invitee : invitees) {
                presenter.inlinePrint(invitee + "\n");
            }
        }
        return false;
    }
}
