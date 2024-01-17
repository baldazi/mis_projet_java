package model;

public class Request implements Comparable<Request> {

    private int id;
    private RequestType type;
    private double time;

    public Request(RequestType type, double time) {
        this.type = type;
        this.time = time;
        this.id = 0;
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
}
