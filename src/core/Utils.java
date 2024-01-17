package core;

import model.Request;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Utils {
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

        while (x == 0) {
            x = generator.nextDouble();
        }
        return (-Math.log(1 - x) / lambda);
    }


    public static void writeDataFileTTrait(String filename, List<Request> data) {

        try {
            FileWriter fw = new FileWriter(filename);

            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getArrivalTime() != 0)
                fw.write(data.get(i).getDepartureTime() - data.get(i).getArrivalTime() + " " + i + "\n");
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

