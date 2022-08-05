package controllers.admin;

import controllers.appWide.RequestController;
import exception.UsernameExistsException;
import useCases.IAccountManager;

public class CreateAdminController extends RequestController {
    /**
     * Constructor for a controller responsible for handling input related to create new admin accounts.
     *
     * @param accountManager  a use case responsible for managing accounts
     */
    public CreateAdminController(IAccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getRequestDescription() {
        return "make a new admin account";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean handleRequest(String requester) {
        try {
            presenter.input();
            presenter.inlinePrint("Enter the username of the admin account to be created: ");
            String username = presenter.input.nextLine();
            presenter.inlinePrint("Enter the password of the admin account to be created: ");
            String password = presenter.input.nextLine();
            accountManager.createAdmin(username, password);
            presenter.blockPrint("Successfully created admin " + username + ".");
        } catch (UsernameExistsException e) {
            presenter.blockPrint(e.getMessage());
        }
        return false;
    }
}
