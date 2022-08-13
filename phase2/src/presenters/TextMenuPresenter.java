package presenters;

import java.util.ArrayList;
import java.util.Scanner;
public class TextMenuPresenter extends MenuPresenter {
    /**
     * @inheritDoc
     */
    @Override
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
     * @inheritDoc
     */
    @Override
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
