package gateway;

import entities.Event;

import java.util.HashMap;
import java.util.UUID;

public interface IMemento {
    /**
     * Returns the Saved event map stored in the memento
     */
    HashMap<UUID, Event> getSavedEvent();
    /**
     * Stores the Event map into a memento
     *
     * @param event the map that is to be saved in the memento
     */
    void setSavedEvent(HashMap<UUID, Event> event);
}
