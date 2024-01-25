package model;

public enum EventType {
    /**
     * Le client arrive dans une file d'attente.
     */
    ARRIVAL,

    /**
     * Le client a été traité et sort de la file d'attente.
     */
    DEPARTURE
}
