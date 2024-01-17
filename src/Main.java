import system.DDB;

public class Main {
    public static void main(String[] args) {
        // Paramètres de simulation
        int nbServers = 3;
        double mu = 0.0095;
        double tempsServiceCoord = 0.01;
        double[] tempsServiceServeurs = {0.1, 0.1, 0.2};
        double[] probabilitesRoutage = {1.0 / 3, 1.0 / 3, 1.0 / 3};
        double tempsSimulation = 1000;

        // Création du système de gestion de requêtes
        DDB systeme = new DDB(nbServers, mu,
                tempsServiceCoord, tempsServiceServeurs, probabilitesRoutage, tempsSimulation);

        // Exécution de la simulation
        systeme.simuler();
    }
}
