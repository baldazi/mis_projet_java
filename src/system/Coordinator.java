package system;

import core.Utils;
import model.Request;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class Coordinator {
    private final Queue<Request> queue;
    private final double mu;
    private final double[] q;  // Vecteur q

    public Coordinator(double mu, double[] q) {
        this.queue = new LinkedList<>();
        this.mu = mu;
        this.q = q;
    }

    public void addRequest(Request request) {
        this.queue.add(request);
    }

    public double getNextEventTime(List<Server> servers) {
        double nextArrivalTime = Double.MAX_VALUE;
        if (!queue.isEmpty()) {
            nextArrivalTime = queue.peek().getTime();
        }

        double nextProcessingTime = Double.MAX_VALUE;
        for (int i = 0; i < q.length; i++) {
            if (!servers.get(i).isEmpty()) {
                double processingTime = servers.get(i).getNextEventTime();
                nextProcessingTime = Math.min(nextProcessingTime, processingTime);
            }
        }

        return Math.min(nextArrivalTime, nextProcessingTime);
    }

    public int chooseServer() {
        // Implémentez le choix du serveur de destination en fonction des probabilités
        double randomValue = Utils.generator.nextDouble();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < this.q.length; i++) {
            cumulativeProbability += q[i];
            if (randomValue <= cumulativeProbability) {
                return i;
            }
        }
        // Par défaut, retourne le dernier serveur
        return this.q.length - 1;
    }

    public double getMu() {
        return mu;
    }
}
