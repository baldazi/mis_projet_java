package system;

import model.MM1Queue;
import model.Request;

import java.util.Queue;

public class Server {
    private double p;
    private double lambda;
    private double mu;
    private MM1Queue queue;

    public Server(double mu, double p) {
        this.p = p;
        this.lambda = 0;
        this.mu = mu;
        this.queue = new MM1Queue(this.lambda, this.mu);
    }

    public Queue<Request> request() {
        return this.queue.getRequestQueue();
    }
}
