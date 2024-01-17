package system;

import model.Request;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Coordinator {

    private Queue<Request> queue;
    private double mu;
    private double[] q;  // Vecteur q

    public Coordinator(double mu, double[] q) {
        this.queue = new LinkedList<>();
        this.mu = mu;
        this.q = q;
    }

    public void addRequest(Request request) {
        this.queue.add(request);
    }

    public double getNextEventTime() {
        // Implémentez la logique pour obtenir le prochain temps d'événement dans le coordinateur
        // ...
        return 0d;
    }

    public int choisirServeurDestination() {
        // Implémentez le choix du serveur de destination en fonction des probabilités
        double randomValue = new Random().nextDouble();
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
