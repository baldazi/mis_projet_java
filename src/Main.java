import system.DDB;

public class Main {

    private static final double P = 1_0;
    public static void main(String[] args) {
        // Paramètres de simulation
        int nbServers = 3;
        double lambda = 0.0095;
        double mu = 0.01;
        double[] mus = {0.1, 0.1, 0.2};
        double[] routagesP = {1.0 / 3, 1.0 / 3, 1.0 / 3};
        double tempsSimulation = 1000;

        // Création du système de gestion de requêtes
        DDB systeme = new DDB(nbServers, lambda, mu, mus, routagesP);

        // Exécution de la simulation
        systeme.simulate(P);
    }
}
