package controllers.account;

import controllers.appWide.RequestController;
import exception.UserNotFollowedException;
import exception.UsernameNotFoundException;
import useCases.IAccountManager;

public class UnfollowController extends RequestController {
    /**
     * a use case responsible for managing accounts
     */
    IAccountManager accountManager;

    /**
     * Constructor for a controller responsible for handling input related to user unfollowing.
     *
     * @param accountManager  a use case responsible for managing accounts
     */
    public UnfollowController(IAccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "Unfollow an account";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        try {
            presenter.input();
            presenter.blockPrint("Enter the username of the account you wish to unfollow: ");
            String target = presenter.input.nextLine();
            sleeper.sleep(200);
            accountManager.unfollow(requester, target);
            presenter.blockPrint("Successfully unfollowed user: " + target);
        } catch (UsernameNotFoundException | UserNotFollowedException e) {
            presenter.blockPrint(e.getMessage());
        }
        return false;
    }
}
