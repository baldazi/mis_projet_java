package model;

import java.util.*;

public class MM1Queue {

    private double lambda;
    private double mu;
    private Queue<Request> requestQueue;

    public MM1Queue(double lambda, double mu) {
        this.lambda = lambda;
        this.mu = mu;
        this.requestQueue = new PriorityQueue<>();
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public Queue<Request> getRequestQueue() {
        return requestQueue;
    }

    public void addRequest(Request request) {
        this.requestQueue.add(request);
    }
}
