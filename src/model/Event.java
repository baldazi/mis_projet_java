package model;

import java.util.Comparator;
import java.util.Objects;

public class Event implements Comparable<Event> {
    public final int id; // Identifiant de la requête
    public final EventType type;
    public final double time;
    /**
     * Représente le n° du serveur ; {@code -1} pour le coordinateur.
     */
    public final int target;

    public Event(int id, EventType type, double time, int target) {
        this.type = type;
        this.time = time;
        this.id = id;
        this.target = target;
    }

    @Override
    public int compareTo(Event rq) {
        // compare le temps puis l'id de requête si temps égal
        return Comparator.<Event>comparingDouble(e -> e.time).thenComparingInt(e -> e.id).compare(this, rq);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Event other) {
            return this.compareTo(other) == 0;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, id);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", type=" + type +
                ", time=" + time +
                ", origin=" + target +
                '}';
    }
}
