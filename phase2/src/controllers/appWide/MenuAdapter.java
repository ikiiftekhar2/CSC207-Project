package controllers.appWide;

import presenters.MenuPresenter;
import presenters.TextMenuPresenter;

import java.util.ArrayList;

public class MenuAdapter implements IMenuAdapter{
    /**
     * a menu presenter responsible for building and printing menu options
     */
    MenuPresenter menuPresenter = new TextMenuPresenter();

    /**
     * @inheritDoc
     */
    public String buildMenu(RequestController[] requestControllers){
        ArrayList<String> requestDescriptions = new ArrayList<>();
        for (RequestController controller : requestControllers){
            requestDescriptions.add(controller.getRequestDescription());
        }
        return menuPresenter.buildMenu(requestDescriptions);
    }

    /**
     * @inheritDoc
     */
    public String printMenu(String requests) {
        return menuPresenter.printMenu(requests);
    }
}
