package gateway;

import entities.Event;

import java.util.HashMap;
import java.util.UUID;

public class EventsMemento implements IMemento{
    /**
     * Stores the hashmap of the events
     */
    private HashMap<UUID, Event> events;

    public EventsMemento(HashMap<UUID, Event> event) {
        this.events = event;
    }
    /**
     * @inheritDoc
     */
    public HashMap<UUID, Event> getSavedEvent() {
        return events;
    }
    /**
     * @inheritDoc
     */
    public void setSavedEvent(HashMap<UUID, Event> event) {
        this.events = event;
    }
}
