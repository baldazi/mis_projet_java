package model;

public class Event implements Comparable<Event> {
    private static int counter = 0;
    public final int id; // Identifiant de la requête
    public final EventType type;
    public final double time;

    public Event(int id, EventType type, double time) {
        this.type = type;
        this.time = time;
        this.id = id;
    }

    public Event(EventType type, double time) {
        this(counter++, type, time);
    }

    @Override
    public int compareTo(Event rq) {
        return Double.compare(this.time, rq.time);
    }
}
