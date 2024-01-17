package model;

public class Request implements Comparable<Request> {

    private static int counter = 0;
    private int id; // Identifiant de la requête
    private RequestType type;
    private double time;
    private double endTimeService;  // Nouvelle propriété pour stocker la fin du service

    public Request(RequestType type, double time) {
        this.type = type;
        this.time = time;
        this.endTimeService = 0;
        this.id = counter++;
    }

    public RequestType getType() {
        return type;
    }

    public double getTime() {
        return time;
    }

    @Override
    public int compareTo(Request rq) {
        return Double.compare(this.time, rq.time);
    }

    public void setEndTimeService(double endTimeService) {
        this.endTimeService = endTimeService;
    }

    public int getId() {
        return id;
    }

    public double getEndTimeService() {
        return endTimeService;
    }
}
