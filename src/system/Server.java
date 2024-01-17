package system;

import model.Request;
import model.RequestType;

import java.util.LinkedList;
import java.util.Queue;

public class Server {

    private Queue<Request> queue;
    private double lambda;
    private double mu;
    private double p;  // Probabilité de routage vers le coordinateur

    public Server(double lambda, double mu, double p) {
        this.queue = new LinkedList<>();
        this.lambda = lambda;
        this.mu = mu;
        this.p = p;
    }

    // ... (autres méthodes)

    public void traiterRequete(double simulationTime) {
        if (!queue.isEmpty()) {
            Request request = queue.poll();
            double endTimeService = simulationTime + mu;
            request.setEndTimeService(endTimeService);
            // Utilisez la probabilité de routage pour rediriger la requête
            if (Math.random() <= this.p) {
                // La requête va vers le coordinateur
                queue.add(new Request(RequestType.END_PROCESSING, endTimeService));
            } else {
                // La requête reste dans le serveur
                // ...
            }
        }
    }

    public void addRequest(Request request) {
        this.queue.add(request);
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public double getNextEventTime() {
        return this.queue.poll().getTime();
    }
}
