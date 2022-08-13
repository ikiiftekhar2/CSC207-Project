package presenters;

import java.util.ArrayList;
import java.util.Scanner;
public class MenuPresenter {
    /**
     * Build menu options from the provided requestDescriptions
     *
     * @param requestDescriptions an ArrayList containing the request descriptions to be built as menu options
     * @return a string representation of the menu options
     */
    public String buildMenu(ArrayList<String> requestDescriptions){
        int requestNumber = 0;
        StringBuilder requestsBuilder = new StringBuilder();
        for (String request : requestDescriptions){
            requestsBuilder.append(requestNumber);
            requestsBuilder.append(" - ");
            requestsBuilder.append(request);
            requestsBuilder.append("\n");
            requestNumber++;
        }
        return requestsBuilder.toString();
    }

    /**
     * Print the menu options to the user and take their input of the desired menu option
     *
     * @param requests a string representation of the menu options available to the user
     * @return a string representing the user's input
     */
    public String printMenu(String requests){
        System.out.println("");
        System.out.println(requests);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your request: ");
        String request = scanner.nextLine();
        System.out.println("");
        return request;
    }
}
