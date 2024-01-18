package system;

import core.Utils;
import model.Request;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Coordinator {
    private final Queue<Request> queue;
    private final double mu;
    private final int nb;

    public Coordinator(double mu, int nb) {
        this.queue = new LinkedList<>();
        this.mu = mu;
        this.nb = nb;
    }

    public void addRequest(Request request) {
        this.queue.add(request);
    }

    public double getNextEventTime(List<Server> servers) {
        double nextArrivalTime = Double.MAX_VALUE;
        if (!queue.isEmpty()) {
            nextArrivalTime = queue.poll().getTime();
        }

        double nextProcessingTime = Double.MAX_VALUE;
        for (Server server : servers) {
            if (!server.isEmpty()) {
                double processingTime = server.getNextEventTime();
                nextProcessingTime = Math.min(nextProcessingTime, processingTime);
            }
        }

        return Math.min(nextArrivalTime, nextProcessingTime);
    }

    public double getMu() {
        return mu;
    }

    public int chooseServer() {
        // Choix aléatoire d'un serveur parmi les nb serveurs disponibles
        return Utils.generator.nextInt(nb);
    }
}
