package controllers.account;

import controllers.appWide.RequestController;
import controllers.appWide.RequestFacade;
import controllers.appWide.ReturnController;
import controllers.event.*;
import dataMapper.DataMapper;
import gateway.EventTimeSorter;
import presenters.EventPresenter;
import useCases.ICommentManager;
import useCases.IEventManager;
import useCases.ILikeManager;

public class ViewSelfEvents extends RequestController{
    /**
     * a use case responsible for managing events
     */
    IEventManager eventManager;
    /**
     * a use case responsible for managing comments
     */
    ICommentManager commentManager;
    /**
     * a data mapper responsible for mapping events into a data structure usable by the presenters
     */
    DataMapper eventModel = new DataMapper();
    /**
     *  a data mapper responsible for mapping comments into a data structure usable by the presenters
     */
    DataMapper commentModel = new DataMapper();
    /**
     * a dataMapper to Store likes
     */
    DataMapper likeModel = new DataMapper();
    /**
     * a use case responsible for managing likes
     */
    ILikeManager likeManager;

    /**
     * Constructor for a controller responsible for handling input related to viewing a user's own profile.
     *
     * @param eventManager  a use case responsible for managing posts
     */
    public ViewSelfEvents(IEventManager eventManager, ICommentManager commentManager, ILikeManager likeManager) {
        this.eventManager = eventManager;
        this.commentManager = commentManager;
        this.likeManager = likeManager;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "View Events you Hosted";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        eventManager.setEventSorter(new EventTimeSorter());
        eventModel.reset();
        eventModel.addItems(
                eventManager.getEventsHostedBy(requester),
                new String[]{"title", "description", "host", "inviteOnly", "attendees", "invitees", "timePosted", "id"}
        );
        EventPresenter eventPresenter = new EventPresenter();
        eventPresenter.printEvents(eventModel.getModel());
        RequestFacade profileFacade = new RequestFacade(
                new RequestController[] {
                        new AddEventController(eventModel, eventManager),
                        new PendingEventController(eventManager),
                        new ViewEventHostController(eventModel, eventManager, commentModel, commentManager, likeManager, likeModel),
                        new ReturnController()
                }
        );
        profileFacade.setRequester(requester);
        profileFacade.presentRequest();
        return false;
    }
}
