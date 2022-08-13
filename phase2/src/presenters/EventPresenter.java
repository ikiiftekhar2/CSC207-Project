package presenters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EventPresenter {
    /**
     * a formatter to format LocalDateTime
     */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    /**
     * Displays an event to user.
     *
     * @param event a post that needs to be displayed to user
     */
    public void printEvent(HashMap<String, String> event) {
        System.out.println("Title: " + event.get("title"));
        System.out.println("Hosted by: " + event.get("host"));
        LocalDateTime newDate = LocalDateTime.parse(event.get("timePosted"));
        System.out.println("Time posted: " + formatter.format(newDate));
        System.out.println("Description: " + event.get("description"));
        String attn = event.get("attendees");
        String inv = event.get("invitees");
        System.out.println("Usernames that are attending");
        attn = attn.substring(1, attn.length() - 1);
        String[] attnstrParts = attn.split(",");
        List<String> attnlistParts = Arrays.asList(attnstrParts);
        attnlistParts.forEach(System.out::println);
        inv = inv.substring(1, inv.length() - 1);
        String[] invstrParts = inv.split(",");
        List<String> invlistParts = Arrays.asList(invstrParts);
        System.out.println("Usernames that have been invited");
        invlistParts.forEach(System.out::println);
        System.out.println("Requirement for invitation:");
        System.out.println(event.get("inviteOnly"));
    }

    /**
     * Displays events to users
     *
     * @param events event that needs to be displayed to users
     */
    public void printEvents(ArrayList<HashMap<String, String>> events) {
        int eventNumber = 1;
        for (HashMap<String, String> event : events) {
            System.out.println("Event " + eventNumber);
            printEvent(event);
            eventNumber++;
        }
    }
}
