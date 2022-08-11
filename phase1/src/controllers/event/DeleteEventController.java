package controllers.event;

import controllers.appWide.RequestController;
import dataMapper.DataMapper;
import useCases.IEventManager;

import java.util.UUID;

public class DeleteEventController extends RequestController {
    /**
     * a data mapper that helps map events into a data structure usable by presenters
     */
    DataMapper eventModel;

    /**
     * Constructor for a controller responsible for reading input to sign users up.
     *
     * @param eventModel    a data mapper that helps map events into a data structure usable by presenters
     * @param eventManager  a use case responsible for managing event
     */
    public DeleteEventController(DataMapper eventModel, IEventManager eventManager) {
        this.eventModel = eventModel;
        this.eventManager = eventManager;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "Delete event";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        eventManager.deleteEvent(UUID.fromString(requester));
        eventModel.deleteItem("id", requester);
        presenter.blockPrint("Successfully deleted event");
        return true;
    }
}
