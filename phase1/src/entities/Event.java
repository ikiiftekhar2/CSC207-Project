package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Event implements Serializable {
    /**
     * the title of an event
     */
    String title;
    /**
     * the queries of the event
     */
    HashMap<UUID, QuestionAnswer> queries;
    /**
     * the description of an event
     */
    String description;
    /**
     * the username of the user who created the event
     */
    String host;
    /**
     * the requirement if the event requires invitation
     */
    Boolean inviteOnly;
    /**
     * the attendees of the event
     */
    HashMap<String, Boolean> attendees;
    /**
     * the time in which the event was created
     */
    LocalDateTime timePosted;
    /**
     * the id of the event
     */
    UUID id;

    public Event(String title, String description, String host, Boolean inviteOnly, HashMap<String, Boolean> attendees) {
        this.title = title;
        this.description = description;
        this.host = host;
        this.timePosted = LocalDateTime.now();
        id = UUID.randomUUID();
        this.inviteOnly = inviteOnly;
        this.attendees = attendees;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInviteOnly(Boolean inviteOnly) {
        this.inviteOnly = inviteOnly;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQueries(String question, String username) {
        QuestionAnswer data = new QuestionAnswer(question, username);
        this.queries.put(data.getId(), data);
    }

    public void setAttendees(String username, Boolean anttending) {
        this.attendees.put(username, anttending);
    }

    public LocalDateTime getTimePosted() {
        return timePosted;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return description;
    }

    public String getHost() {
        return host;
    }

    public UUID getId() {
        return id;
    }

    public Boolean getInviteOnly() {
        return inviteOnly;
    }

    public HashMap<UUID, QuestionAnswer> getQueries() {
        return queries;
    }

    public HashMap<String, Boolean> getAttendees() {
        return attendees;
    }
}