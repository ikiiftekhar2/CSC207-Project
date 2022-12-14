package controllers.landing;

import controllers.appWide.RequestController;
import useCases.*;

public class QuitController extends RequestController {
    /**
     * a use case responsible for managing accounts
     */
    IAccountManager accountManager;
    /**
     * a use case responsible for managing posts
     */
    IPostManager postManager;
    /**
     * a use case responsible for managing comments
     */
    ICommentManager commentManager;
    /**
     * a use case responsible for managing likes
     */
    ILikeManager likeManager;

    IEventManager eventManager;

    /**
     * Constructor for a controller responsible for reading input to log users out.
     *
     * @param accountManager a use case responsible for managing accounts
     * @param postManager    a use case responsible for managing posts
     * @param likeManager    a use case responsible for managing likes
     * @param eventManager a use case responsible for managing events
     */
    public QuitController(IAccountManager accountManager, IPostManager postManager, ICommentManager commentManager, ILikeManager likeManager, IEventManager eventManager) {
        this.accountManager = accountManager;
        this.postManager = postManager;
        this.commentManager = commentManager;
        this.likeManager = likeManager;
        this.eventManager = eventManager;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "Quit the app";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        accountManager.save();
        postManager.save();
        commentManager.save();
        likeManager.save();
        eventManager.save();
        return true;
    }
}