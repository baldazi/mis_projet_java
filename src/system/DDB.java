package system;

import core.Utils;
import model.Request;

import java.util.ArrayList;
import java.util.List;

public class DDB {
    private int n;
    private double lambda;
    private Coordinator coordinator;
    private List<Server> serverList;

    public DDB(int n, double lambda, List<Double> mus, List<Double> ps){
        this.n = n;
        this.lambda = lambda;
        this.serverList  = new ArrayList<>(n);
        for (int i=0;i<n ;i++)
            this.serverList.add(new Server(mus.get(i), ps.get(i)));
        this.coordinator = new Coordinator(lambda, n);
    }
    public void simulate(double D) {
        System.out.println("nombre de serveur :" + n);
        this.serverList.forEach(s-> {
            System.out.println("serveur i");
        });
    }

    public void gestionDepart(List<Request> requests) {
        int i = 0;
        double res = 0;
        double q = 0;
        int nombreRequetes = requests.size();
        //
        double QQ = 0;

        while (i < nombreRequetes) {
            q = Utils.generator.nextDouble();
            res = Math.round(q * 100.0) / 100.0;
            //transfert vers un serveur avec proba qi
            for (int s = 0; s < this.serverList.size(); s++) {
                //calcul somme qq
                QQ = 0;
                for (int h = 0; h < s + 1; h++) QQ += this.coordinator.getQ();
            }

            i++;
        }

    }
}
