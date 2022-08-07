package useCases;

import entities.Event;
import gateway.IEventSorter;
import gateway.IReader;
import gateway.IWriter;

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
     * Constructor of a use case responsible for managing events.
     *
     * @param reader a gateway responsible for reading objects
     * @param writer a gateway responsible for writing objects
     */
    public EventManager(IReader reader, IWriter writer) {
        this.reader = reader;
        this.writer = writer;
        events = reader.read(events.getClass());
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
        events.put(event.getId(), event);
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


    @Override
    public void setEventSorter(IEventSorter eventSorter) {
        this.eventSorter = eventSorter;
    }

}

