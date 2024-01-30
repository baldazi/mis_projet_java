package core;

import model.Event;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
import java.util.function.Function;

public class Utils {

    public static int requestCounter = 0;

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

    public static void saveData(double[] data, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < data.length; i++) {
                if (data[i] != 0) {
                    writer.write(String.format(Locale.US, "%6d%20.8f%n", i, data[i]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeDataFileTTrait(String filename, LinkedList<Event> data) {

    }
}
