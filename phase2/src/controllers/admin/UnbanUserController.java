package controllers.admin;

import controllers.appWide.RequestController;
import exception.UserIsAdminException;
import exception.UsernameNotFoundException;
import useCases.IAccountManager;

public class UnbanUserController extends RequestController {
    /**
     * Constructor for a controller responsible for handling input related to unbanning an account.
     *
     * @param accountManager  a use case responsible for managing accounts
     */
    public UnbanUserController(IAccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "unban an account";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        try {
            presenter.input();
            presenter.inlinePrint("Enter the username of the account to unban: ");
            String target = presenter.input.nextLine();
            sleeper.sleep(200);
            if (accountManager.unban(target)) {
                presenter.blockPrint("Successfully unbanned account " + target + ".");
            } else {
                presenter.blockPrint("Unsuccessful ban, account " + target + " was not banned.");
            }
        } catch (UsernameNotFoundException | UserIsAdminException e) {
            presenter.blockPrint(e.getMessage());
        }
        return false;
    }
}
