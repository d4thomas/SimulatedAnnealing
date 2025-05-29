package core_algorithms;

import problems.Problem;

import java.util.Random;

/**
 * Abstract class that implements the general Simulated Annealing algorithm.
 *
 * @param <S> the type representing a state in the problem domain
 */
public abstract class SimulatedAnnealing<S> {
    private long time;
    private double temp;
    private final Problem<S> problem;

    /**
     * Constructs a SimulatedAnnealing solver.
     *
     * @param initTime initial time or iteration count (used in schedule)
     * @param initTemp starting temperature
     * @param p        the problem instance to solve
     */
    public SimulatedAnnealing(long initTime, double initTemp, Problem<S> p) {
        this.time = initTime;
        this.temp = initTemp;
        this.problem = p;
    }

    /**
     * Cooling schedule that determines how temperature is reduced over time.
     * This must be implemented by a subclass.
     *
     * @param time the current time step or iteration
     * @param temp the current temperature
     * @return the new temperature after cooling
     */
    public abstract double schedule(long time, double temp);

    /**
     * Performs the Simulated Annealing search. Iteratively explores the solution
     * space, accepting worse solutions probabilistically based on the temperature.
     *
     * Tracks and returns the best state found during the process.
     *
     * Terminates when the temperature reaches zero.
     */
    public void search() {
        S currentState = problem.getInitState();
        S bestState = currentState;

        while (temp > 0) {
            // update best state seen so far
            if (problem.cost(currentState) < problem.cost(bestState)) {
                bestState = currentState;
            }
            // Generate a neighboring state
            S newState = problem.generateNewState(currentState);
            double deltaE = problem.cost(currentState) - problem.cost(newState);
            // Accept the new state probabilistically
            if (accept(deltaE, temp)) {
                currentState = newState;
            }
            // Cooling and time advancement
            time++;
            temp = schedule(time, temp);
        }

        // Report the best solution found
        System.out.println("Best state found:");
        problem.printState(bestState);
        System.out.println("Best cost found: " + problem.cost(currentState));
    }

    /**
     * Determines whether to accept a new state with higher cost.
     * Always accepts better states. Worse states are accepted with
     * probability proportional to exp(delta/temp).
     *
     * @param delta the difference in cost (positive if new state is better)
     * @param temp  the current temperature
     * @return true if the move is accepted; false otherwise
     */
    public boolean accept(double delta, double temp) {
        if (delta > 0) {
            return true;
        } else {
            double probability = Math.exp(delta / temp);
            assert probability >= 0 && probability <= 1;
            Random r = new Random();
            return probability > r.nextDouble();
        }

    }

}
