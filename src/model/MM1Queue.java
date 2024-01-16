package model;

import java.util.*;

public class MM1Queue {

    private double lambda;
    private double mu;
    private Queue<Event> eventQueue;
    private List<Double> arrivalTimesList;
    private List<Double> departureTimesList;
    private Random generator;

    public MM1Queue(double lambda, double mu) {
        this.lambda = lambda;
        this.mu = mu;
        this.generator = new Random();
        this.eventQueue = new PriorityQueue<>();
        this.arrivalTimesList = new ArrayList<>();
        this.departureTimesList = new ArrayList<>();
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

    public Queue<Event> getEventQueue() {
        return eventQueue;
    }

    public void setEventQueue(Queue<Event> eventQueue) {
        this.eventQueue = eventQueue;
    }

    public List<Double> getArrivalTimesList() {
        return arrivalTimesList;
    }

    public void setArrivalTimesList(List<Double> arrivalTimesList) {
        this.arrivalTimesList = arrivalTimesList;
    }

    public List<Double> getDepartureTimesList() {
        return departureTimesList;
    }

    public void setDepartureTimesList(List<Double> departureTimesList) {
        this.departureTimesList = departureTimesList;
    }

    public Random getGenerator() {
        return generator;
    }


}
