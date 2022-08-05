package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Event implements Serializable {
    /**
     * the title of an event
     */
    String title;
    /**
     * the description of an event
     */
    String description;
    /**
     * the username of the user who created the event
     */
    String author;
    /**
     * the requirement if the event requires invitation
     */
    Boolean inviteOnly;
    /**
     * the time in which the event was created
     */
    LocalDateTime timePosted;
    /**
     * the id of the event
     */
    UUID id;

    public Event(String title, String description, String author, Boolean inviteOnly) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.timePosted = LocalDateTime.now();
        id = UUID.randomUUID();
        this.inviteOnly = inviteOnly;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public UUID getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String description) {
        this.description = description;
    }

    public LocalDateTime getTimePosted() {
        return timePosted;
    }
}