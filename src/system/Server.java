package system;

import core.QueueBase;
import core.Utils;
import model.Event;
import model.EventType;

import java.util.*;

public class Server extends QueueBase {
    /**
     * Probabilité de routage vers le coordinateur.
     */
    public final double p;

    /**
     * N° du serveur.
     */
    public final int i;

    public Server(int i, double mu, double p) {
        super(mu);
        this.p = p;
        this.i = i;
    }

    /**
     *
     * @param t le temps actuel dans la simulation
     */
    public void handleNextEvent(Event e, double t, Coordinator c) {
        switch (e.type) {
            case ARRIVAL:
                double departure = t + Utils.expo(this.mu);
                if (Utils.generator.nextDouble() <= this.p) {
                    Event f = new Event(e.id, EventType.ARRIVAL, departure, -1);
                    c.addRequest(f);
                    System.out.println("La requête " + e.id + " retourne dans le coordinateur à l'instant " + departure);
                } else {
                    Event f = new Event(e.id, EventType.DEPARTURE, departure, this.i);
                    this.addRequest(f);
                    System.out.println("La requête " + e.id + " a quitté le système à l'instant " + departure);
                }

                break;

            case DEPARTURE:
                break;

            default:
                throw new UnsupportedOperationException(e.type + " pas implanté");
        }
    }
}
