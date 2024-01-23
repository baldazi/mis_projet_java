package model;

public enum EventType {
    /**
     * Le client arrive dans une file d'attente.
     */
    ARRIVAL,

    /**
     * Le client est traité par le serveur/le coordinateur.
     */
    PROCESSING,

    /**
     * Le client a été traité et sort de la file d'attente.
     */
    DEPARTURE
}
