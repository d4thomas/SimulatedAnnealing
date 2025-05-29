package solutions;

import core_algorithms.SimulatedAnnealing;
import problems.NQueens;

/**
 * Simulated Annealing solver specialized for the N-Queens problem.
 * Uses a simple linear cooling schedule.
 */
public class SA_NQueens extends SimulatedAnnealing<int[]> {
    // Initial time step
    private final static long INIT_TIME = 1;
    // Initial temperature
    private final static double INIT_TEMP = 1e10;
    // Maximum time (number of iterations)
    private final static long MAX_TIME = 100_000;

    /**
     * Constructs an SA solver for the given N-Queens problem instance.
     *
     * @param problem the N-Queens problem to solve
     */
    public SA_NQueens(NQueens problem) {
        super(INIT_TIME, INIT_TEMP, problem);
    }

    /**
     * Defines a linear cooling schedule.
     * The temperature decreases linearly with time until reaching zero at MAX_TIME.
     *
     * @param time the current time step
     * @param temp the current temperature (not used here)
     * @return the new temperature
     */
    @Override
    public double schedule(long time, double temp) {
        return temp * (1 - time / (double) MAX_TIME);
    }

    /**
     * Entry point: runs the SA solver on a -queen problem of configurable size.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        int numQueens = 16;
        SA_NQueens agent = new SA_NQueens(new NQueens(numQueens));
        agent.search();
    }
}
