import system.DDB;

public class Main {

    private static final double P = 1000;
    public static void main(String[] args) {
        // Paramètres de simulation
        int nbServers = 3;
        double lambda = 0.0095;
        double mu = 0.01;
        double[] mus = {0.1, 0.1, 0.2};
        double[] ps = {1.0 / 3, 1.0 / 3, 1.0 / 3};

        // Création du système de gestion de requêtes
        DDB system = new DDB(nbServers, lambda, mu, mus, ps);

        // Exécution de la simulation
        system.simulate(P);
    }
}
