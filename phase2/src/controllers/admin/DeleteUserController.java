package controllers.admin;

import controllers.appWide.RequestController;
import exception.UserIsAdminException;
import exception.UsernameNotFoundException;
import useCases.IAccountManager;
import useCases.IPostManager;

public class DeleteUserController extends RequestController {
    /**
     * Constructor for a controller responsible for handling input related to deleting others' account.
     *
     * @param accountManager  a use case responsible for managing accounts
     * @param postManager     a use case responsible for managing posts
     */
    public DeleteUserController(IAccountManager accountManager, IPostManager postManager) {
        this.accountManager = accountManager;
        this.postManager = postManager;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "Delete an account";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        try {
            presenter.input();
            presenter.inlinePrint("Enter the username of the account you wish to delete: ");
            String target = presenter.input.nextLine();
            accountManager.deleteUser(target);
            postManager.deletePostsWrittenBy(target);
            presenter.blockPrint("Successfully deleted user: " + target);
        } catch (UsernameNotFoundException | UserIsAdminException e) {
           presenter.blockPrint(e.getMessage());
        }
        return false;
    }
}
