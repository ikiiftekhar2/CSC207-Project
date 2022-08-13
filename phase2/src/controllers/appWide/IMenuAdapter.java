package controllers.appWide;

public interface IMenuAdapter {
    /**
     * Builds menu options to be displayed
     * @param requestControllers the requestControllers that have to be built as menu options
     * @return a string representing all the menu options consisting of the provided requestControllers
     */
    String buildMenu(RequestController[] requestControllers);

    /**
     * Prints the built menu options to the user
     * @param requests a string representing all the available menu options
     * @return a string representing the menu option selected by the user
     */
    String printMenu(String requests);
}
