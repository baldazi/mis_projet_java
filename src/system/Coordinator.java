package system;

public class Coordinator {
    private double lambda;
    private double q;

    private double n;
    public Coordinator(double lambda, int n) {
        this.lambda = lambda;
        this.n = n;
        this.q = 1/n;
    }
}
