package system;

import core.QueueBase;
import core.Utils;
import model.Event;
import model.EventType;

import java.util.*;

public class Server extends QueueBase {
    public final double p;  // Probabilité de routage vers le coordinateur

    public Server(double mu, double p) {
        super(mu);
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
}
