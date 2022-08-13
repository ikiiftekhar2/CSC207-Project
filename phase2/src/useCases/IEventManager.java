package useCases;

import entities.Event;
import gateway.IEventSorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public interface IEventManager {
    /**
     * Return a list of events hosted by the account with the provided username.
     *
     * @param username a string representing a username of a user.
     * @return a list of events written by an account with the provided username
     */
    ArrayList<Event> getEventsHostedBy(String username);

    /**
     * Delete all events hosted by the account with the provided username.
     *
     * @param username a string representing a username of a user.
     */
    void deleteEventsHostedBy(String username);

    /**
     * Add a new event given metadata about the event.
     *
     * @param title   the title of the event
     * @param description the description of the event
     * @param host  the username of the account that is hosting the event
     * @param inviteOnly the requirement of the event needing invitation
     * @param invitees the set of usernames who are invited to the event
     * @return the id of the newly added event
     */
    UUID addEvent(String title, String description, String host, Boolean inviteOnly, HashSet<String> invitees);

    /**
     * Delete an event based on the id of the event.
     *
     * @param id the id of the event to be deleted.
     */
    void deleteEvent(UUID id);

    /**
     * Return an event based on the id of the event.
     *
     * @param id the id of the event to be returned.
     * @return the event with an id that matches the provided id. .
     */
    Event getEvent(UUID id);

    /**
     * Saves the current data.
     */
    void save();


    /**
     * Returns the Mapping of UUID and Event
     */
    HashMap<UUID,Event> getEventMap();


    /**
     * Set the eventSorter to be used
     *
     * @param eventSorter an IPostSorter strategy for sorting comments
     */
    void setEventSorter(IEventSorter eventSorter);
    /**
     * Return the state if the user can attend the event or not
     *
     * @param username the username of the attendee
     * @param id the id of the event the user wants to attend
     */
    Boolean attendEvent(String username, UUID id);
    /**
     * Returns the event map of the events that require admin approval
     */
    HashMap<UUID, Event> getPendingEvents();
    /**
     * Returns the state of the state after all the pending events have been added to
     * the central event list
     */
    Boolean approveAll();
}
