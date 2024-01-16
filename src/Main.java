import system.DDB;

import java.util.List;

public class Main {
    private static final double D = 10_000;
    public static void main(String[] args) {
        List<Double> mus = List.of(1 / 100d, 1 / 100d, 1 / 200d, 1 / 200d);
        List<Double> ps = List.of(1 / 4d, 1 / 4d, 1 / 4d, 1 / 4d);
        DDB system = new DDB(4, 1/100d, mus, ps);
        system.simulate(D);
    }
}