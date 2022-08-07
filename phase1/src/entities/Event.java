package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
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
    HashSet<String> attendees;
    /**
     * the attendees of the event
     */
    HashSet<String> invitees; /// add invitees to attendees with observer pattern
    /**
     * the time in which the event was created
     */
    LocalDateTime timePosted;
    /**
     * the id of the event
     */
    UUID id;

    public Event(String title, String description, String host, Boolean inviteOnly, HashSet<String> invitees) {
        this.title = title;
        this.description = description;
        this.host = host;
        this.timePosted = LocalDateTime.now();
        id = UUID.randomUUID();
        this.inviteOnly = inviteOnly;
        this.invitees = invitees;
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

    public void setAttendees(String username) {
        this.attendees.add(username);
    }

    public void setInvitees(HashSet<String> invitees) {
        this.invitees = invitees;
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

    public HashSet<String> getAttendees() {
        return attendees;
    }
}