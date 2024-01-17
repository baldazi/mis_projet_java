package model;

public class Request{
    private double id;
    private double arrivalTime;
    private double arrivalServTime;
    private double departureTime;
    private int nbPassage ;

    public Request(double id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.nbPassage = 0;
    }

    public Request() {
        this(0, 0);
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getArrivalServTime() {
        return arrivalServTime;
    }

    public void setArrivalServTime(double arrivalServTime) {
        this.arrivalServTime = arrivalServTime;
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
    }

    public int getNbPassage() {
        return nbPassage;
    }

    public void setNbPassage(int nbPassage) {
        this.nbPassage = nbPassage;
    }

    public void passage(){
        this.nbPassage += 1;
    }
}

