package system;

import model.Request;
import model.RequestType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DDB {

    private Coordinator coordinator;
    private List<Server> servers;
    private double lambda;
    private double simulationTime;

    public DDB(int nb, double lambda, double mu, double[] mus, double[] ps) {
        this.lambda = lambda;
        this.simulationTime = 0;
        this.servers = new ArrayList<>();

        for (int i = 0; i < nb; i++) {
            servers.add(new Server(lambda, mus[i], ps[i]));
        }
        double[] qs = new double[nb];
        for (int i = 0; i < nb; i++) {
            qs[i] = 1.0 / i;
        }
        this.coordinator = new Coordinator(mu, qs);
    }

    private void genererArriveeRequete() {
        double tempsArrivee = genererTempsArrivee();
        coordinator.addRequest(new Request(RequestType.ARRIVAL, tempsArrivee));
    }

    private double genererTempsArrivee() {
        return -Math.log(1 - new Random().nextDouble()) / lambda;
    }

    public void traiterRequete(double simulationTime) {
        // Implémentez le traitement de la requête par le coordinateur
        double endTime = simulationTime + this.coordinator.getMu();
        // Utilisez le vecteur de routage pour rediriger la requête
        int serveurDestination = coordinator.choisirServeurDestination();
        servers.get(serveurDestination).addRequest(new Request(RequestType.PROCESSING, endTime));
    }

    public void simulate(double simulationTime) {
        genererArriveeRequete();

        while (this.simulationTime <= simulationTime) {
            // Trouver le prochain événement
            double nextEventTime = Double.MAX_VALUE;
            for (Server server : servers) {
                if (!server.isEmpty() && server.getNextEventTime() < nextEventTime) {
                    nextEventTime = server.getNextEventTime();
                }
            }

            // Événement dans le coordinateur
            double coordEventTime = coordinator.getNextEventTime();
            if (coordEventTime < nextEventTime) {
                this.traiterRequete(coordEventTime);
            } else {
                // Événement dans un serveur
                for (Server server : servers) {
                    if (!server.isEmpty() && server.getNextEventTime() == nextEventTime) {
                        server.traiterRequete(nextEventTime);
                    }
                }
            }
        }
    }
}

