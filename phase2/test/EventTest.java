import entities.Event;
import gateway.*;
import org.junit.Test;
import useCases.EventManager;
import useCases.IEventManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.*;

public class EventTest {

    @Test
    public void eventTestCreation() {
        HashMap<UUID, Event> curr = new HashMap<>();
        IMemento memento = new EventsMemento(curr);
        final String eventDataFileDirectory = "data/eventData.txt";
        IReader reader5 = new Reader(eventDataFileDirectory);
        IWriter writer5 = new Writer(eventDataFileDirectory);
        IEventManager eventManager = new EventManager(reader5, writer5, memento);
        HashSet<String > invitees = new HashSet<>();
        invitees.add("Ken");
        eventManager.addEvent("Test Title", "Test Description", "Author", false, invitees);
        assertEquals(eventManager.getPendingEvents().size(), 1);
    }

    @Test
    public void eventApprovalTest() {
        HashMap<UUID, Event> curr = new HashMap<>();
        IMemento memento = new EventsMemento(curr);
        final String eventDataFileDirectory = "data/eventData.txt";
        IReader reader5 = new Reader(eventDataFileDirectory);
        IWriter writer5 = new Writer(eventDataFileDirectory);
        IEventManager eventManager = new EventManager(reader5, writer5, memento);
        HashSet<String > invitees = new HashSet<>();
        invitees.add("Ken");
        eventManager.addEvent("Test Title", "Test Description", "Author", false, invitees);
        eventManager.approveAll();
        assertEquals(eventManager.getPendingEvents().size(), 1);
        assertEquals(eventManager.getEventMap().size(), 1);
    }

    @Test
    public void eventChangeInviteTest() {
        HashMap<UUID, Event> curr = new HashMap<>();
        IMemento memento = new EventsMemento(curr);
        final String eventDataFileDirectory = "data/eventData.txt";
        IReader reader5 = new Reader(eventDataFileDirectory);
        IWriter writer5 = new Writer(eventDataFileDirectory);
        IEventManager eventManager = new EventManager(reader5, writer5, memento);
        HashSet<String > invitees = new HashSet<>();
        invitees.add("Ken");
        UUID id  = eventManager.addEvent("Test Title", "Test Description", "Author", false, invitees);
        eventManager.approveAll();
        Event event = eventManager.getEvent(id);
        event.setInviteOnly(true);
        assertTrue(event.getInviteOnly());
    }

    @Test
    public void eventCannotJoinNonInvitee() {
        HashMap<UUID, Event> curr = new HashMap<>();
        IMemento memento = new EventsMemento(curr);
        final String eventDataFileDirectory = "data/eventData.txt";
        IReader reader5 = new Reader(eventDataFileDirectory);
        IWriter writer5 = new Writer(eventDataFileDirectory);
        IEventManager eventManager = new EventManager(reader5, writer5, memento);
        HashSet<String > invitees = new HashSet<>();
        invitees.add("Ken");
        int b = 1;
        int c  = 0;
        UUID id  = eventManager.addEvent("Test Title", "Test Description", "Author", true, invitees);
        eventManager.approveAll();
        Event event = eventManager.getEvent(id);
        assertFalse(eventManager.attendEvent("McDonald", id));
        assertFalse(event.getAttendees().contains("McDonald"));
    }

    public void eventCanJoinNonInvitee() {
        HashMap<UUID, Event> curr = new HashMap<>();
        IMemento memento = new EventsMemento(curr);
        final String eventDataFileDirectory = "data/eventData.txt";
        IReader reader5 = new Reader(eventDataFileDirectory);
        IWriter writer5 = new Writer(eventDataFileDirectory);
        IEventManager eventManager = new EventManager(reader5, writer5, memento);
        HashSet<String > invitees = new HashSet<>();
        invitees.add("Ken");
        invitees.add("Jason Durelo");
        UUID id  = eventManager.addEvent("Test Title", "Test Description", "Author", true, invitees);
        eventManager.approveAll();
        Event event = eventManager.getEvent(id);
        assertTrue(eventManager.attendEvent("Jason Durelo", id));
        assertTrue(event.getAttendees().contains("Jason Durelo"));
    }
}
