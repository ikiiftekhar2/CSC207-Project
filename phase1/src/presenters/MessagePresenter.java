package presenters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MessagePresenter {
    /**
     * a formatter to format LocalDateTime
     */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Display a message to the user
     *
     * @param message a message to be displayed to the user
     */

    public void printMessage(HashMap<String, String> message) {
        System.out.println(message.get("sender") + ": " + message.get("content"));
        LocalDateTime newDate = LocalDateTime.parse(message.get("timeSent"));
        System.out.println("Time sent: " + formatter.format(newDate));
        System.out.println();
    }

    /**
     * Displays messages to users
     *
     * @param messages messages that need to be displayed to users
     */
    public void printMessages(ArrayList<HashMap<String, String>> messages) {
        for (HashMap<String, String> message : messages) {
            printMessage(message);
        }
    }
}
