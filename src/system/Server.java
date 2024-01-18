package system;

import core.Utils;
import model.Request;
import model.RequestType;

import java.util.LinkedList;
import java.util.Queue;

public class Server {

    private Queue<Request> queue;
    private double mu;
    private double p;  // Probabilité de routage vers le coordinateur

    public Server(double mu, double p) {
        this.queue = new LinkedList<>();
        this.mu = mu;
        this.p = p;
    }

    public void requestProcess(double simulationTime) {
        if (!queue.isEmpty()) {
            Request request = queue.poll();
            double endTimeService = simulationTime + mu;
            request.setEndTimeService(endTimeService);
            // Utilisez la probabilité de routage pour rediriger la requête
            if (Utils.generator.nextDouble() <= this.p) {
                // La requête va vers le coordinateur
                Request rq = new Request(RequestType.END_PROCESSING, endTimeService);
                queue.add(rq);
                System.out.println("La requête " + request.getId() + " retourne dans le coordinateur à l'instant " + simulationTime);
            } else {
                // La requete sort du système
                //todo
                System.out.println("La requête " + request.getId() + " a quitté le système à l'instant " + simulationTime);
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
        return isEmpty() ? Double.MAX_VALUE : queue.peek().getTime();
    }
}
