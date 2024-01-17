package system;

import core.Utils;
import model.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DDB {
    private int n;
    private double lambda;
    private Coordinator coordinator;
    private List<Server> serverList;

    public DDB(int n, double lambda, List<Double> mus, List<Double> ps) {
        this.n = n;
        this.lambda = lambda;
        this.serverList = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            this.serverList.add(new Server(mus.get(i), ps.get(i)));
        this.coordinator = new Coordinator(lambda, n);
    }

    public void simulate(double D) {
        System.out.println("nombre de serveur :" + n);
        System.out.println("******Coordinateur******");
        System.out.println(this.coordinator.getQ());
        this.serverList.forEach(s -> {
            System.out.println("serveur i");
        });
    }

    public void gestionDepart(double tempsService) {
        int i = 0;
        double res = 0;
        double q = 0;
        int nombreRequetes = this.coordinator.getQueue().getRequestQueue().size();
        //
        double QQ = 0;

        while (i < nombreRequetes) {
            q = new Random().nextDouble();
            res = Math.round(q * 100.0) / 100.0;
            //transfert vers un serveur avec proba qi
            for (int s = 0; s < this.serverList.size(); s++) {
                //calcul somme qq
                QQ = 0;
                for (int h = 0; h < s + 1; h++) QQ += this.coordinator.getQ();


                if (res <= QQ) {
                    Request rq = this.coordinator.getQueue().getRequestQueue().poll();
                    rq.setArrivalServTime(rq.getArrivalTime() + tempsService + Utils.expo(this.coordinator.getMu()));
                    break;
                }
            }

            i++;
        }

    }
}
