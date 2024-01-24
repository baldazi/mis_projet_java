package system;

import core.Utils;
import model.Event;
import model.EventType;

import java.util.ArrayList;
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
            servers.add(new Server(mus[i], ps[i]));
        }

        this.coordinator = new Coordinator(mu, nb);
    }

    private void generateArrivalRequest() {
        double arrivalTime = Utils.poisson(this.lambda)+this.simulationTime;
        Event event = new Event(EventType.ARRIVAL, arrivalTime);
        coordinator.addRequest(event);
        System.out.println("Arrivée d'une requête au coordinateur à l'instant " + arrivalTime);
    }

    public void requestProcess(double t) {
        // Implémente le traitement de la requête par le coordinateur
        double endTime = t + Utils.expo(this.coordinator.mu);
        // Utilisez le vecteur de routage pour rediriger la requête
        int serveurDestination = coordinator.chooseServer();
        Event event = new Event(EventType.PROCESSING, endTime);
        servers.get(serveurDestination).addRequest(event);
        System.out.println("Traitement de la requête " + event.id +

                " au coordinateur. Redirigée vers le serveur " + serveurDestination +
                " à l'instant = " + endTime);
    }

    public void simulate(double T) {
        generateArrivalRequest();

        while (this.simulationTime < T) {
            // Trouve la prochaine arrivée
            double nextEventTime = Double.MAX_VALUE;
            for (Server server : servers) {
                if (!server.isEmpty() && server.getNextArrivalTime() < nextEventTime) {
                    nextEventTime = server.getNextArrivalTime();
                }
            }

            // Date du prochain événement dans le coordinateur
            double coordEventTime = coordinator.getNextEventTime(servers);
            if (coordEventTime < nextEventTime) {
                this.requestProcess(coordEventTime);
            } else {
                // Événement dans un serveur
                for (Server server : servers) {
                    if (!server.isEmpty() && server.getNextArrivalTime() == nextEventTime) {
                        server.handleNextEvent(nextEventTime);
                    }
                }

                // Générer une nouvelle requête si nécessaire
                if (nextEventTime > this.simulationTime) {
                    generateArrivalRequest();
                }
            }

            System.out.println("nombre de requetes : " + this.nbRequest());

            this.simulationTime = Math.min(coordEventTime, nextEventTime);
        }
    }

    public int nbRequest(){
        int nb = this.coordinator.getQueue().size();
        for(Server s: this.servers)
            nb += s.getQueue().size();
        return nb;
    }

}

