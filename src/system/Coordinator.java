package system;

import model.MM1Queue;

public class Coordinator {
    private double lambda;
    private double q;
    private MM1Queue queue;
    private double mu;
    private double n;

    public Coordinator(double lambda, int n) {
        this.lambda = lambda;
        this.mu = 0;
        this.queue = new MM1Queue(this.lambda, this.mu);
        this.n = n;
        this.q = 1d/n;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public double getQ() {
        return q;
    }

    public void setQ(double q) {
        this.q = q;
    }

    public MM1Queue getQueue() {
        return queue;
    }

    public void setQueue(MM1Queue queue) {
        this.queue = queue;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }
}
