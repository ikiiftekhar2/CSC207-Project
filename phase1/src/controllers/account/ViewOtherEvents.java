package controllers.account;

import controllers.appWide.RequestController;
import controllers.appWide.RequestFacade;
import controllers.appWide.ReturnController;
import controllers.event.ViewEventNotHostController;
import dataMapper.DataMapper;
import entities.Event;
import gateway.EventTimeSorter;
import gateway.IEventSorter;
import presenters.EventPresenter;
import useCases.IAccountManager;
import useCases.ICommentManager;
import useCases.IEventManager;
import useCases.ILikeManager;

import java.util.ArrayList;
import java.util.HashSet;

public class ViewOtherEvents extends RequestController {
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
    public ViewOtherEvents(IEventManager eventManager,IAccountManager accountManager, ICommentManager commentManager, ILikeManager likeManager) {
        this.eventManager = eventManager;
        this.commentManager = commentManager;
        this.likeManager = likeManager;
        this.accountManager = accountManager;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "View Events Others Hosted";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        IEventSorter eventSorter = new EventTimeSorter();
        eventManager.setEventSorter(eventSorter);
        HashSet<String> followees = accountManager.getFolloweesOf(requester);
        ArrayList<Event> eventsList = new ArrayList<>();
        for (String followee : followees) { eventsList.addAll(eventManager.getEventsHostedBy(followee));}
        eventModel.reset();
        eventModel.addItems(
                eventSorter.sort(eventsList),
                new String[]{"title", "queries", "description", "host", "inviteOnly", "attendees", "invitees", "timePosted", "id"}
        );
        EventPresenter eventPresenter = new EventPresenter();
        eventPresenter.printEvents(eventModel.getModel());
        RequestFacade otherEventFacade = new RequestFacade(
                new RequestController[] {
                        new ViewEventNotHostController(eventModel, eventManager, commentModel, commentManager, likeManager, likeModel),
                        new ReturnController()
                }
        );
        otherEventFacade.setRequester(requester);
        otherEventFacade.presentRequest();
        return false;
    }
}
