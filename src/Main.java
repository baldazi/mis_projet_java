import system.DDB;
import java.util.Scanner;
public class Main {

    private static final double P = 100;
    public static void main(String[] args) {
        //
        DDB system;
        int nbServers;
        double lambda;
        double mu = 0.1;

        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        System.out.println("choisir:");
        System.out.println("1=test 1, 2 = test 2, 3=personnalisé : ");

        int choix = scan.nextInt();  // Read user input
        if (choix == 1) {
            // Paramètres de simulation
            nbServers = 4;
            lambda = 0.01;
            double[] mus = {0.1, 0.1, 0.2, 0.2};
            double[] ps = {0.25, 0.25, 0.25, 0.25};

            // Création du système de gestion de requêtes
            system = new DDB(nbServers, lambda, mu, mus, ps);
        }else if(choix == 2) {
            // Paramètres de simulation
            nbServers = 3;
            lambda = 0.0095;
            double[] mus = {0.1, 0.1, 0.2};
            double[] ps = {1.0 / 3, 1.0 / 3, 1.0 / 3};

            // Création du système de gestion de requêtes
            system = new DDB(nbServers, lambda, mu, mus, ps);
        }else{
            System.out.println("nombre de serveurs : ");
            nbServers = scan.nextInt();
            System.out.println("nombre de serveurs rapide: ");
            int nbFast = scan.nextInt();
            System.out.println("taux d'arrivé dans le système : ");
            lambda = scan.nextDouble();
            System.out.println("vecteur de probabilité de routage des serveurs: ");
            double p = scan.nextDouble();
            double[] ps = new double[nbServers];
            double[] mus = new double[nbServers];
            for (int i=0; i<nbServers; i++){
                ps[i] = p;
                mus[i] = 0.2;
            }
            for (int i=0; i<nbFast; i++){
                mus[i] = 0.1;
            }
            // Création du système de gestion de requêtes
            system = new DDB(nbServers, lambda, mu, mus, ps);
        }

        // Exécution de la simulation
        system.simulate(P);

        scan.close();
    }
}
