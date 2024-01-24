package core;

import model.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueueBase {
    private final List<Event> queue;
    public final double mu;

    public QueueBase(double mu) {
        this.queue = new ArrayList<>();
        this.mu = mu;
    }

     /*
        Ici la queue est commune au serveur et au coordinateur,
        donc faut mettre ça dans une classe à part
     */

    public void addRequest(Event event) {
        int i = Collections.binarySearch(this.queue, event);

        this.queue.add(-i - 1, event);
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public Event popNextEvent() {
        return isEmpty() ? null : queue.remove(0);
    }

    public List<Event> getEventsForRequest(int req) {
        List<Event> l = new ArrayList<>();
        for(Event e : this.queue) {
            if(e.id == req) {
                l.add(e);
            }
        }

        return l;
    }

    public List<Event> getQueue() {
        return Collections.unmodifiableList(queue);
    }
}
