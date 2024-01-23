package system;

import core.Utils;
import model.Event;

import java.util.*;

public class Coordinator {
    private final List<Event> queue;
    public final double mu;
    public final int nb;

    public Coordinator(double mu, int nb) {
        this.queue = new ArrayList<>();
        this.mu = mu;
        this.nb = nb;
    }

    public void addRequest(Event event) {
        int i = Collections.binarySearch(this.queue, event);

        this.queue.add(-i - 1, event);
    }

    /**
     * Renvoie l'heure du prochain évènement
     */
    public Event getNextEvent(List<Server> servers) {
        Event e = null;

        if(!this.queue.isEmpty()) {
            e = this.queue.get(0);
        }

        for(Server s : servers) {
            Event f = s.peekNextEvent();

            if(e == null || (f != null && f.getTime() < e.getTime())) {
                e = f;
            }
        }

        return e;
    }

    public int chooseServer() {
        // Choix aléatoire d'un serveur parmi les nb serveurs disponibles
        return Utils.generator.nextInt(nb);
    }

    public List<Event> getQueue() {
        return Collections.unmodifiableList(queue);
    }
}
