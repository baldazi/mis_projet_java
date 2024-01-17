package system;

import core.Utils;
import model.Request;
import model.RequestType;

import java.util.ArrayList;
import java.util.List;

public class DDB {

    private final Coordinator coordinator;
    private final List<Server> servers;
    private final double lambda;
    private final double simulationTime;

    public DDB(int nb, double lambda, double mu, double[] mus, double[] ps) {
        this.lambda = lambda;
        this.simulationTime = 0;
        this.servers = new ArrayList<>(nb);

        for (int i = 0; i < nb; i++) {
            servers.add(new Server(mus[i], ps[i]));
        }

        double[] qs = new double[nb];
        for (int i = 0; i < nb; i++) {
            qs[i] = 1.0 / i;
        }

        this.coordinator = new Coordinator(mu, qs);
    }

    private void generateArrivalRequest() {
        double arrivalTime = Utils.poisson(this.lambda);
        Request request = new Request(RequestType.ARRIVAL, arrivalTime);
        coordinator.addRequest(request);
        System.out.println("Arrivée d'une requête au coordinateur à la simulationTime = " + arrivalTime);
    }

    public void requestProcess(double t) {
        // Implémentez le traitement de la requête par le coordinateur
        double endTime = t + this.coordinator.getMu();
        // Utilisez le vecteur de routage pour rediriger la requête
        int serveurDestination = coordinator.chooseServer();
        Request request = new Request(RequestType.PROCESSING, endTime);
        servers.get(serveurDestination).addRequest(request);
        System.out.println("Traitement de la requête " + request.getId() +
                " au coordinateur. Redirigée vers le serveur " + serveurDestination +
                " à la simulationTime = " + simulationTime);
    }

    public void simulate(double T) {
        generateArrivalRequest();

        while (this.simulationTime < T) {
            // Trouver le prochain événement
            double nextEventTime = Double.MAX_VALUE;
            for (Server server : servers) {
                if (!server.isEmpty() && server.getNextEventTime() < nextEventTime) {
                    nextEventTime = server.getNextEventTime();
                }
            }

            // Événement dans le coordinateur
            double coordEventTime = coordinator.getNextEventTime(servers);
            if (coordEventTime < nextEventTime) {
                this.requestProcess(coordEventTime);
            } else {
                // Événement dans un serveur
                for (Server server : servers) {
                    if (!server.isEmpty() && server.getNextEventTime() == nextEventTime) {
                        server.requestProcess(nextEventTime);
                    }
                }
            }
        }
    }
}

