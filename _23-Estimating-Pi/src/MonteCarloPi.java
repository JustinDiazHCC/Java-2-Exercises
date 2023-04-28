/**
 * MonteCarloPi.java
 * Driver for Monte Carlo Pi estimation
 */

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
public class MonteCarloPi {

    public static final int NUM_THREADS = 4;
    public static final int NUM_ITERATIONS = 100_000_000;
    public static int iterationsPerThread = NUM_ITERATIONS/NUM_THREADS;

    public static PiCalculator piCalculator = new PiCalculator();

    public static void main(String[] args) {

        System.out.printf("Available Processors: %s\n", Runtime.getRuntime().availableProcessors());

        Double piEstimate;

        Instant startTime = Instant.now();
        piEstimate = piCalculator.getPiEstimate();
        Instant finishTime = Instant.now();

        long timeInMilliseconds = Duration.between(startTime, finishTime).toMillis();
        System.out.printf("Approximation of Pi using %d iterations and %d thread(s) completed in %d milliseconds: %f\n",
                NUM_ITERATIONS, NUM_THREADS, timeInMilliseconds, piEstimate);

    }

    /**
     * Uses a Monte Carlo method to estimate Pi
     */
    public static class PiCalculator {

        protected int circlePoints;
        protected int squarePoints;

        public PiCalculator() {
            this.circlePoints = 0;
            this.squarePoints = 0;
        }

        public static double getPiEstimate() {

            for (int i = 0; i < NUM_THREADS; i++) {
                MultithreadingMonteCarlo multithread = new MultithreadingMonteCarlo();
                Thread thread = new Thread(multithread);
                thread.start();
            }

            return 4.0 * ((double)piCalculator.circlePoints / (double)piCalculator.squarePoints);
        }
    }

    /**
     * Implements multithreading
     */
    public static class MultithreadingMonteCarlo implements Runnable{
        @Override
        public void run () {
            final Random random = new Random();

            for (int i = 0; i < iterationsPerThread; i++) {
                double x = random.nextDouble();
                double y = random.nextDouble();
                double d = x*x + y*y;

                if (d <= 1) {
                    piCalculator.circlePoints++;
                }
                piCalculator.squarePoints++;
            }
        }
    }
}
