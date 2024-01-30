package core;

import model.Event;
import model.EventType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class QueueBase {
    /**
     * Structure permettant de stoker les événements.
     */
    private final List<Event> queue;
    public final double mu;

    public QueueBase(double mu) {
        this.queue = new ArrayList<>();
        this.mu = mu;
    }

     /**
        *Ici la queue est commune au serveur et au coordinateur,
        *donc Nous mettons ça dans une classe à part
     */

    public void addRequest(Event event) {
        int i = Collections.binarySearch(this.queue, event);
        if(i >= 0) {
            throw new IllegalArgumentException("Evènement dupliqué: " + event + " " + this.queue.get(i));
        }

        this.queue.add(-i - 1, event);
    }

    public void removeEvent(Event e) {
        int i = Collections.binarySearch(this.queue, e);
        if(i >= 0) {
            this.queue.remove(i);
        }
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public Event popNextEvent() {
        return isEmpty() ? null : queue.remove(0);
    }

    public Event peekNextEvent() {
        return isEmpty() ? null : queue.get(0);
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

    /**
     * Renvoie tous les évènements `e` tels que `e.id == i <=> e appartient à tab[i]`.
     * En d'autres mots, `tab[i]` contient tous les évènements de la requête n° `i`.
     *
     * @param requestNumber
     * @return
     */
    public Event[][] getEventsForEachRequest(int requestNumber) {
        List<Event>[] events = new ArrayList[requestNumber];
        for(int i = 0; i < events.length; ++i) {
            events[i] = new ArrayList<>(3);
        }

        for(Event e : this.queue) {
            events[e.id].add(e);
            // ~~~~~~~~~ l
        }

        return Arrays.stream(events).map(e -> e.toArray(Event[]::new)).toArray(Event[][]::new);
    }

    public double[] eachRequestTime(int requestNumber){
        Event[][] events = this.getEventsForEachRequest(requestNumber);
        double[] result = new double[requestNumber];
        for (int i=0; i<requestNumber; i++){
            for (Event event : events[i]) {
                result[i] += event.time;
            }
        }
        return result;
    }

    public Stream<Event> getEventsForType(EventType type) {
        return this.queue.stream().filter(e -> e.type == type);
    }

    public List<Event> getQueue() {
        return Collections.unmodifiableList(queue);
    }
}
