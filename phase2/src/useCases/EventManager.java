package useCases;

import entities.Event;
import gateway.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class EventManager implements IEventManager{
    /**
     * a mapping of id of the post to the events entity
     */
    HashMap<UUID, Event> events = new HashMap<>();
    /**
     * a gateway responsible for reading objects
     */
    IReader reader;
    /**
     * a gateway responsible for writing objects
     */
    IWriter writer;
    /**
     *  a sorter that sorts an arraylist of events
     */
    IEventSorter eventSorter;
    /**
     *  an event memento Object
     */
    IMemento memento;

    /**
     * Constructor of a use case responsible for managing events.
     *
     * @param reader a gateway responsible for reading objects
     * @param writer a gateway responsible for writing objects
     */
    public EventManager(IReader reader, IWriter writer, IMemento memento) {
        this.reader = reader;
        this.writer = writer;
        events = reader.read(events.getClass());
        this.memento = memento;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ArrayList<Event> getEventsHostedBy(String username) {
        ArrayList<Event> events = new ArrayList<>();
        for (UUID id : this.events.keySet()) {
            Event event = this.events.get(id);
            if (event.getHost().equals(username)) {
                events.add(event);
            }
        }
        this.eventSorter.sort(events);
        return events;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void deleteEventsHostedBy(String username) {
        for (Event event : getEventsHostedBy(username)) {
            deleteEvent(event.getId());
        }
    }

    private Event createEvent(String title, String description, String host, Boolean inviteOnly, HashSet<String> invitees) {
        return new Event(title, description, host, inviteOnly, invitees);
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID addEvent(String title, String description, String host, Boolean inviteOnly, HashSet<String> invitees) {
        Event event = createEvent(title, description, host, inviteOnly, invitees);
        HashMap<UUID, Event> newEvent = memento.getSavedEvent();
        newEvent.put(event.getId(), event);
        memento.setSavedEvent(newEvent);
        return event.getId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void deleteEvent(UUID id) {
        events.remove(id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Event getEvent(UUID id) {
        return events.get(id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void save() {
        writer.write(events);
    }

    /**
     * @inheritDoc
     */
    @Override
    public HashMap<UUID, Event> getEventMap(){
        return events;
    }
    /**
     * @inheritDoc
     */
    @Override
    public Boolean attendEvent(String username, UUID id){
        Event event = getEvent(id);
        if (event.getInviteOnly()) {
            if (event.getInvitees().contains(username)) {
                event.addAttendees(username);
                return true;
            }
            return false;
        } else {
            event.addAttendees(username);
            return true;
        }
    }
    /**
     * @inheritDoc
     */
    @Override
    public void setEventSorter(IEventSorter eventSorter) {
        this.eventSorter = eventSorter;
    }
    /**
     * @inheritDoc
     */
    public HashMap<UUID, Event> getPendingEvents() {
        return memento.getSavedEvent();
    }
    /**
     * @inheritDoc
     */
    public Boolean approveAll() {
        HashMap<UUID, Event> curr = memento.getSavedEvent();
        events.putAll(curr);
        return true;
    }
}

