package system;

import core.QueueBase;
import core.Utils;

public class Coordinator extends QueueBase {
    public final int nb;

    public Coordinator(double mu, int nb) {
        super(mu);
        this.nb = nb;
    }

    /**
     * Renvoie l'heure du prochain évènement
     */

    public int chooseServer() {
        // Choix aléatoire d'un serveur parmi les nb serveurs disponibles
        return Utils.generator.nextInt(nb);
    }
}
