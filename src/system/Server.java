package system;

import core.Utils;
import model.Event;
import model.EventType;

import java.util.*;

public class Server {
    private final List<Event> queue;
    public final double mu;
    public final double p;  // Probabilité de routage vers le coordinateur

    public Server(double mu, double p) {
        this.queue = new ArrayList<>();
        this.mu = mu;
        this.p = p;
    }

    /**
     *
     * @param t le temps actuel dans la simulation
     */
    public void handleNextEvent(double t, Coordinator c) {
        Event e = this.popNextEvent();

        if(e.type != EventType.ARRIVAL) {
            throw new UnsupportedOperationException(e.type + " pas implanté");
        }

        double departure = t + Utils.expo(this.mu);
        if(Utils.generator.nextDouble() <= this.p) {
            Event f = new Event(e.id, EventType.ARRIVAL, departure);
            c.addRequest(f);
            System.out.println("La requête " + e.id + " retourne dans le coordinateur à l'instant " + departure);
        }
        else {
            Event f = new Event(e.id, EventType.DEPARTURE, departure);
            this.addRequest(f);
            System.out.println("La requête " + e.id + " a quitté le système à l'instant " + departure);
        }
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
