package system;

import core.QueueBase;
import core.Utils;
import model.Event;
import model.EventType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DDB {

    private final Coordinator coordinator;
    private final List<Server> servers;
    private double lambda;
    private double simulationTime;

    public DDB(int nb, double lambda, double mu, double[] mus, double[] ps) {
        this.lambda = lambda;
        this.simulationTime = 0;
        this.servers = new ArrayList<>(nb);

        for (int i = 0; i < nb; i++) {
            servers.add(new Server(i, mus[i], ps[i]));
        }

        this.coordinator = new Coordinator(mu, nb);
    }

    private Event popNextEvent() {
        Event e = this.coordinator.peekNextEvent();
        QueueBase q = this.coordinator;

        for(Server s : this.servers) {
            Event f = s.peekNextEvent();

            if(e == null || f != null && f.time < e.time) {
                e = f;
                q = s;
            }
        }

        if(e != null) {
            q.removeEvent(e);
        }

        return e;
    }

    public void simulate(double T) {
        // création de toutes nos arrivées
        double t = 0;
        while(t < T) {
            t += Utils.poisson(this.lambda);
            this.coordinator.addRequest(new Event(Utils.requestCounter++, EventType.ARRIVAL, t, -1));
        }

        System.out.println("Nombre de requêtes: " + this.nbRequêtes());

        QueueBase finalQueue = new QueueBase(Double.NaN);

        Event e;
        while((e = this.popNextEvent()) != null && e.time <= T) {
            this.simulationTime = e.time;
            finalQueue.addRequest(e);

            switch (e.type) {
                case ARRIVAL:
                    if(e.target == -1) {
                        // FIXME: le temps de fin doit prendre en compte la taille de la file

                        // Implémente le traitement de la requête par le coordinateur
                        double endTime = this.simulationTime + Utils.expo(this.coordinator.mu);
                        // Utilisez le vecteur de routage pour rediriger la requête
                        int serveurDestination = coordinator.chooseServer();
                        Event event = new Event(e.id, EventType.ARRIVAL, endTime, serveurDestination);
                        servers.get(serveurDestination).addRequest(event);
                        /* System.out.println("Traitement de la requête " + event.id +

                            " au coordinateur. Redirigée vers le serveur " + serveurDestination +
                            " à l'instant = " + endTime);*/
                    } else {
                        this.servers.get(e.target).handleNextEvent(e, this.simulationTime, this.coordinator);
                    }

                    break;

                case DEPARTURE:
                    break;

                default:
                    throw new UnsupportedOperationException(e.type + " pas implanté");
            }
        }

        System.out.println(Arrays.deepToString(finalQueue.getEventsForEachRequest(this.nbRequêtes() + 1)));
    }

    public int nbEvents(){
        int nb = this.coordinator.getQueue().size();
        for(Server s: this.servers)
            nb += s.getQueue().size();
        return nb;
    }

    public int nbRequêtes() {
        return Utils.requestCounter - 1;
    }
}

