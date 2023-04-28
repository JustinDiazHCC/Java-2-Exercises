/**
 * MonteCarloPi.java
 * Driver for Monte Carlo Pi estimation
 */

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
public class MonteCarloPi {

    public static final int NUM_THREADS = 8;
    public static final int NUM_ITERATIONS = 100_000_000_0;

    public static void main(String[] args) {

        System.out.printf("Available Processors: %s\n", Runtime.getRuntime().availableProcessors());

        int iterationsPerThread = NUM_ITERATIONS/NUM_THREADS;
        PiCalculator piCalculator = new PiCalculator(iterationsPerThread);

        Instant startTime = Instant.now();
        piCalculator.getPiEstimate();
        Instant finishTime = Instant.now();

        long timeInMilliseconds = Duration.between(startTime, finishTime).toMillis();
        System.out.printf("Approximation of Pi using %d iterations and %d thread(s) completed in %d milliseconds: %f\n",
                NUM_ITERATIONS, NUM_THREADS, timeInMilliseconds, piCalculator.getPiEstimate());

    }

    /**
     * Uses a Monte Carlo method to estimate Pi
     */
    public static class PiCalculator {

        private final int iterations;

        public PiCalculator(int iterations) {
            this.iterations = iterations;
        }

        public double getPiEstimate() {

                final Random random = new Random();

                int circlePoints = 0;
                int squarePoints = 0;

                for (int i = 0; i < iterations; i++) {
                    double x = random.nextDouble();
                    double y = random.nextDouble();
                    double d = x*x + y*y;

                    if (d <= 1) {
                        circlePoints++;
                    }
                    squarePoints++;
                }

            return 4.0 * ((double)circlePoints / (double)squarePoints);
        }
    }
}
