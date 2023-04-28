import java.time.Duration;
import java.time.Instant;
import java.util.Random;

/**
 * Driver for Monte Carlo Pi estimation
 */
public class MonteCarloPi {

    public static final int NUM_THREADS = 8;
    public static final int NUM_ITERATIONS = 2147483647;

    public static void main(String[] args) throws InterruptedException {

        // print available processors
        System.out.printf("Available Processors: %s\n", Runtime.getRuntime().availableProcessors());

        int iterationsPerThread = NUM_ITERATIONS / NUM_THREADS;
        PiCalculator[] piCalcArr = new PiCalculator[NUM_THREADS];

        Instant startTime = Instant.now(); // start timer

        // start each thread in piCalcArr
        for (int i = 0; i < NUM_THREADS; i++) {
            piCalcArr[i] = new PiCalculator(iterationsPerThread);
            piCalcArr[i].start();
        }

        // halt program in loop until every thread terminates
        for (int i = 0; i < NUM_THREADS; i++) {
            while (piCalcArr[i].isAlive()) {
            }
        }

        // accumulator for total estimate in final average pi calculation
        double total = 0.0;
        // add pi estimate from each thread to total
        for (int i = 0; i < NUM_THREADS; i++) {
            total += piCalcArr[i].getPiEstimate();
        }

        // calculate average
        double estimate = total / NUM_THREADS;
        Instant finishTime = Instant.now(); // end timer

        // print results
        long timeInMilliseconds = Duration.between(startTime, finishTime).toMillis();
        System.out.printf("Approximation of Pi using %d iterations and %d thread(s) completed in %d milliseconds: %f\n",
                NUM_ITERATIONS, NUM_THREADS, timeInMilliseconds, estimate);
    }

    /**
     * Uses a Monte Carlo method to estimate Pi
     */
    public static class PiCalculator extends Thread {
        // instance variables
        private final int iterations;
        private int circlePoints;

        // constructor
        public PiCalculator(int iterations) {
            this.iterations = iterations;
            this.circlePoints = 0;
        }

        @Override
        public void run() {
            final Random random = new Random();

            for (int i = 0; i < iterations; i++) {
                double x = random.nextDouble();
                double y = random.nextDouble();
                double d = x * x + y * y;

                if (d <= 1) {
                    circlePoints++;
                }
            }
        }

        public double getPiEstimate() {
            return 4.0 * ((double) circlePoints / iterations);
        }
    }
}
