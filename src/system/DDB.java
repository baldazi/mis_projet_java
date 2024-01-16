package system;

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
}
