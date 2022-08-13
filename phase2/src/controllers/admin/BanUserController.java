package controllers.admin;

import controllers.appWide.RequestController;
import exception.UserIsAdminException;
import exception.UsernameNotFoundException;

import useCases.IAccountManager;

public class BanUserController extends RequestController {
    /**
     * a use case responsible for managing accounts
     */
    final private IAccountManager accountManager;

    /**
     * Constructor for a controller responsible for handling input related to account banning.
     *
     * @param accountManager  a use case responsible for managing accounts
     */
    public BanUserController(IAccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "ban an account";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        try {
            presenter.input();
            presenter.inlinePrint("Enter the username of the account you wish to ban: ");
            String target = presenter.input.nextLine();
            sleeper.sleep(200);
            if (accountManager.ban(target)) {
                presenter.blockPrint("Successfully banned account: " + target + ".");
            } else {
                presenter.blockPrint("Unsuccessful ban, account " + target + " was already banned.");
            }
        } catch (UsernameNotFoundException |  UserIsAdminException e) {
            presenter.blockPrint(e.getMessage());
        }
        return false;
    }

}
