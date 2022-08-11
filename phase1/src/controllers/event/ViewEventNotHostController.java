package controllers.event;

import controllers.appWide.RequestController;
import controllers.appWide.RequestFacade;
import controllers.appWide.ReturnController;
import controllers.comment.AddCommentController;
import controllers.comment.ViewCommentController;
import controllers.like.AddLikeController;
import controllers.like.UnlikeController;
import controllers.like.ViewLikeController;
import dataMapper.DataMapper;
import presenters.EventPresenter;
import useCases.ICommentManager;
import useCases.IEventManager;
import useCases.ILikeManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewEventNotHostController extends RequestController {
    /**
     * a data mapper to store events
     */
    DataMapper eventModel;
    /**
     * a data mapper to store comments
     */
    DataMapper commentModel;
    /**
     * a use case responsible for managing comments
     */
    ICommentManager commentManager;
    /**
     * a use case responsible for managing events
     */
    IEventManager eventManager;
    /**
     * a dataMapper to Store likes
     */
    DataMapper likeModel;
    /**
     * a use case responsible for managing likes
     */
    ILikeManager likeManager;

    /**
     * Constructor for a controller responsible for reading input to view a post.
     *
     * @param eventModel      a data mapper that helps map events into a data structure usable by presenters
     * @param eventManager    a use case responsible for managing events
     * @param commentModel   a data mapper that helps map comments into a data structure usable by presenters
     * @param commentManager a use case responsible for managing comments
     * @param likeManager    a use case responsible for managing likes
     * @param likeModel      a data mapper that helps map likes into a data structures usable by presenters
     */
    public ViewEventNotHostController(DataMapper eventModel, IEventManager eventManager, DataMapper commentModel,
                                   ICommentManager commentManager, ILikeManager likeManager, DataMapper likeModel) {
        this.eventModel = eventModel;
        this.eventManager = eventManager;
        this.commentModel = commentModel;
        this.commentManager = commentManager;
        this.likeManager = likeManager;
        this.likeModel = likeModel;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected String getRequestDescription() {
        return "View events";
    }

    /**
     * @inheritDoc
     */
    @Override
    protected boolean handleRequest(String requester) {
        try {
            ArrayList<HashMap<String, String>> events = eventModel.getModel();
            EventPresenter eventPresenter = new EventPresenter();
            eventPresenter.printEvents(events);
            presenter.input();
            presenter.inlinePrint("Enter the number of the events you wish to view or 0 to return to your profile: ");
            int request = Integer.parseInt(presenter.input.nextLine());
            if (request == 0) {
                return false;
            } else if (request  <= events.size()) {
                int eventNumber = request - 1;
                HashMap<String, String> event = events.get(eventNumber);
                eventPresenter.printEvent(event);
                RequestFacade eventRequests = new RequestFacade(new RequestController[]{
                        new AddCommentController(commentModel, commentManager, requester),
                        new ViewCommentController(commentModel, commentManager),
                        new AddLikeController(likeModel,likeManager,requester),
                        new UnlikeController(likeModel,likeManager,requester),
                        new ViewLikeController(likeModel, likeManager),
                        new ReturnController()
                });
                eventRequests.setRequester(events.get(eventNumber).get("id"));
                eventRequests.presentRequest();
            } else {
                presenter.blockPrint("The number input is invalid.");
                handleRequest(requester);
            }
        } catch (NumberFormatException e) {
            presenter.blockPrint("The number input is invalid.");
            handleRequest(requester);
        }
        return false;
    }
}
