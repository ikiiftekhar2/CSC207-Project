package gateway;

import entities.Event;

import java.util.ArrayList;

public interface IEventSorter {
    /**
     * returns a sorted array list of a posts.
     *
     * @param events an arraylist of posts
     * @return a sorted arraylist of posts.
     */
    ArrayList<Event> sort(ArrayList<Event> events);
}