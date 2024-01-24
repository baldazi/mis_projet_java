package core;

import model.Event;

import java.util.LinkedList;
import java.util.Random;

public class Utils {

    public static int eventCounter = 0;

    public static final Random generator = new Random();

    public static double poisson(double lambda) {
        double x = 0;
        double p = Math.exp(-lambda);
        double s = p;
        double u = generator.nextDouble();

        while (u > s) {
            x = x + 1;
            p = p * lambda / x;
            s = s + p;
        }
        return x;
    }

    public static double expo(double lambda) {
        double x = generator.nextDouble();

        while (x == 0) {              // tant que x n'est pas une proba on refait un random
            x = generator.nextDouble();
        }
        return (-Math.log(1 - x) / lambda);
    }


    public static void writeDataFileTTrait(String filename, LinkedList<Event> data) {

    }
}
