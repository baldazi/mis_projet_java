package system;

import model.Request;
import model.RequestType;

import java.util.PriorityQueue;
import java.util.Random;

public class DDB {

    private int nbServers;
    private double tauxArrivee;
    private double tempsServiceCoord;
    private double[] tempsServiceServeurs;
    private double[] probabilitesRoutage;

    private PriorityQueue<Request> fileRequests;
    private double tempsSimulation;

    public DDB(int nombreServeurs, double tauxArrivee,
               double tempsServiceCoord, double[] tempsServiceServeurs,
               double[] probabilitesRoutage, double tempsSimulation) {
        this.nbServers = nombreServeurs;
        this.tauxArrivee = tauxArrivee;
        this.tempsServiceCoord = tempsServiceCoord;
        this.tempsServiceServeurs = tempsServiceServeurs;
        this.probabilitesRoutage = probabilitesRoutage;
        this.tempsSimulation = tempsSimulation;
        this.fileRequests = new PriorityQueue<>();
    }

    private void genererArriveeRequete() {
        double tempsArrivee = genererTempsArrivee();
        fileRequests.add(new Request(RequestType.ARRIVAL, tempsArrivee));
    }

    private void gererArriveeRequete(Request request) {
        genererArriveeRequete();
        int serveurDestination = choisirServeurDestination();
        fileRequests.add(new Request(RequestType.PROCESSING, tempsSimulation + tempsServiceServeurs[serveurDestination]));
    }

    private void gererDebutTraitementServeur(Request request) {
        double tempsServiceCoord = genererTempsServiceCoord();
        fileRequests.add(new Request(RequestType.END_PROCESSING, tempsSimulation + tempsServiceCoord));
    }

    private void gererFinTraitementCoord(Request request) {
        // Implémentez la redirection des requêtes en fonction des probabilités
        System.out.println("Redirection des requêtes (à implémenter)");
    }

    private int choisirServeurDestination() {
        // Implémentez le choix du serveur de destination en fonction des probabilités
        return 0;
    }

    private double genererTempsArrivee() {
        return -Math.log(1 - new Random().nextDouble()) / tauxArrivee;
    }

    private double genererTempsServiceCoord() {
        return -Math.log(1 - new Random().nextDouble()) / tempsServiceCoord;
    }

    public void simuler() {
        genererArriveeRequete();

        while (!fileRequests.isEmpty() && fileRequests.peek().getTime() <= tempsSimulation) {
            Request request = fileRequests.poll();
            tempsSimulation = request.getTime();

            switch (request.getType()) {
                case ARRIVAL:
                    gererArriveeRequete(request);
                    break;
                case PROCESSING:
                    gererDebutTraitementServeur(request);
                    break;
                case END_PROCESSING:
                    gererFinTraitementCoord(request);
                    break;
                // Ajoutez d'autres types d'événements selon le besoin
            }
        }
    }
}