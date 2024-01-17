package system;

import model.MM1Queue;

import static core.Utils.generator;

public class Server {
   private double p;
   private double lambda;
   private double mu;
   private MM1Queue queue;
   public Server(double mu, double p){
      this.p = p;
      this.lambda = 0;
      this.mu = mu;
      this.queue = new MM1Queue(this.lambda, this.mu);
   }

   public double getNextArrivalTime() {
      return - (1 / this.lambda) * Math.log(1 - generator.nextDouble());
   }

   public double getNextDepartureTime() {
      return - (1 / this.mu) * Math.log(1 - generator.nextDouble());
   }
}
