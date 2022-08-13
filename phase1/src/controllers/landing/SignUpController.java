package controllers.landing;

import controllers.appWide.RequestController;
import controllers.appWide.RequestFacade;
import controllers.account.UnfollowController;
import controllers.account.ViewSelfProfileController;
import controllers.account.*;
import controllers.search.SearchPostByTitleController;
import controllers.search.SearchUserByUsernameController;
import exception.InvalidUsernameException;
import exception.UsernameExistsException;
import gateway.ISearch;
import searchHandler.SearchByUsernameRegular;
import useCases.*;


public class SignUpController extends RequestController {
    /**
     * a use case responsible for managing accounts
     */
    private IAccountManager accountManager;
    /**
     * a request facade containing request controllers for likes
     */
    ILikeManager likeManager;
    /**
     * a request facade containing request controllers for regular accounts
     */
    private RequestFacade accountRequestFacade;
    /**
     * a request facade containing request controllers for comments
     */
    private ICommentManager commentManager;
    /**
     * a request facade containing request controllers for events
     */
    private IEventManager eventManager;
    /**
     * a use case responsible for managing messages
     */
    private IMessageManager messageManager;
    /**
     * Constructor for a controller responsible for reading input to sign users up.
     *
     * @param accountManager a use case responsible for managing accounts
     * @param postManager    a use case responsible for managing posts
     * @param commentManager a use case responsible for managing comments
     * @param likeManager a use case responsible for managing likes
     * @param messageManager a use case responsible for managing messages
     * @param eventManager a use case responsible for managing events
     */
    public SignUpController(IAccountManager accountManager, IPostManager postManager, ICommentManager commentManager, ILikeManager likeManager, IMessageManager messageManager,IEventManager eventManager) {
        this.accountManager = accountManager;
        this.commentManager = commentManager;
        this.messageManager = messageManager;
        ISearch searchForRegularUsers = new SearchByUsernameRegular(accountManager);
        accountRequestFacade = new RequestFacade(new RequestController[]{
            new ViewHistoryController(accountManager),
            new DeleteSelfController(accountManager, postManager),
            new FollowController(accountManager),
            new UnfollowController(accountManager),
            new ViewFollowerController(accountManager),
            new ViewFollowingController(accountManager),
                new ViewOtherEvents(eventManager, accountManager, commentManager, likeManager),
                new ViewSelfEvents(eventManager, commentManager, likeManager),
            new ViewSelfProfileController(postManager, commentManager, likeManager, messageManager),
            new ViewFeedController(postManager, accountManager, commentManager, likeManager),
                new ViewProfileController(accountManager, postManager, commentManager, likeManager, messageManager),
                new SearchPostByTitleController(postManager),
                new SearchUserByUsernameController(accountManager, searchForRegularUsers),
            new LogoutController(),
        });
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "Sign up for an account";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        presenter.input();
        presenter.inlinePrint("Enter your username: ");
        String username = presenter.input.nextLine();
        presenter.inlinePrint("Enter your password: ");
        String password = presenter.input.nextLine();
        try {
            accountManager.signUp(username, password);
            presenter.blockPrint("Successfully signed up.");
            sleeper.sleep(200);
            accountRequestFacade.setRequester(username);
            accountRequestFacade.presentRequest();
        } catch (UsernameExistsException | InvalidUsernameException e){
            presenter.blockPrint(e.getMessage());
        }
        presenter.blockPrint("");
        return false;
    }
}

