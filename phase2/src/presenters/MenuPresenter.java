package presenters;

import java.util.ArrayList;

abstract public class MenuPresenter {
    /**
     * Build menu options from the provided requestDescriptions
     *
     * @param requestDescriptions an ArrayList containing the request descriptions to be built as menu options
     * @return a string representation of the menu options
     */
    public abstract String buildMenu(ArrayList<String> requestDescriptions);

    /**
     * Print the menu options to the user and take their input of the desired menu option
     *
     * @param requests a string representation of the menu options available to the user
     * @return a string representing the user's input
     */
    public abstract String printMenu(String requests);
}
