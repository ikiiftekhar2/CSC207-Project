package controllers.event;

import controllers.appWide.RequestController;
import dataMapper.DataMapper;
import useCases.IEventManager;

import java.util.HashSet;
import java.util.UUID;

public class AddEventController extends RequestController {
    DataMapper eventModel;

    /**
     * Constructor for a controller responsible for reading input to sign users up.
     *
     * @param eventModel    a data mapper that helps map events into a data structure usable by presenters
     * @param eventManager  a use case responsible for managing events
     */
    public AddEventController(DataMapper eventModel, IEventManager eventManager) {
        this.eventModel = eventModel;
        this.eventManager = eventManager;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "Create an event";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        presenter.input();
        presenter.inlinePrint("Title: ");
        String title = presenter.input.nextLine();
        presenter.inlinePrint("Description: ");
        String description = presenter.input.nextLine();
        boolean inviteOnly = false;
        boolean flag = true;
        HashSet<String> invitees = new HashSet<>();
        presenter.inlinePrint("Do you want the event to be Invite-Only?");
        String invitationRequirement = presenter.input.nextLine();
        if ((invitationRequirement.equals("yes") || invitationRequirement.equals("Yes") || invitationRequirement.equals("YES"))) {
            inviteOnly = true;
        }
        presenter.inlinePrint("Enter the usernames of people you want to invite to the event, type 0 to stop");
        presenter.inlinePrint("  ");
        while (flag) {
            String response = presenter.input.nextLine();
            if (response.equals("0")){
                flag = false;
            } else {
                invitees.add(response);
            }
        }
        UUID eventId = eventManager.addEvent(title,description, requester, inviteOnly, invitees);
        String[] attributes = new String[]{"title", "queries", "description", "host", "inviteOnly", "attendees", "invitees", "timePosted", "id"};
        eventModel.addItem(eventManager.getEvent(eventId), attributes);
        presenter.blockPrint("Event successfully created!");
        return false;
    }
}
