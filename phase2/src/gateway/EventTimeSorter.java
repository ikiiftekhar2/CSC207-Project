package gateway;

import entities.Event;
import entities.Post;

import java.util.ArrayList;
import java.util.Comparator;

public class EventTimeSorter implements IEventSorter{

    @Override
    public ArrayList<Event> sort(ArrayList<Event> events) {
        events.sort(new EventTimeSorter.EventTimeComparator());
        return events;
    }

    private class EventTimeComparator implements Comparator<Event> {
        @Override
        public int compare(Event p1, Event p2) {
            if (p1.getTimePosted().isEqual(p2.getTimePosted())) {
                return 0;
            } else if (p1.getTimePosted().isAfter(p2.getTimePosted())) {
                return -1;
            }
            return 1;
        }

    }
}
