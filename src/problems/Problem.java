package problems;

/**
 * Interface representing a generic optimization problem
 * that can be solved using local search techniques like
 * Simulated Annealing.
 *
 * @param <S> the type representing a state in the problem
 *            domain
 */
public interface Problem<S> {
    /**
     * Generates a new neighboring state from the given
     * current state.
     * This should implement some local move logic
     * (e.g., random swap).
     *
     * @param current the current state
     * @return a randomly generated neighboring state
     */
    public S generateNewState(S current);

    /**
     * Computes the cost of a given state.
     * Lower cost is assumed to be better
     * (e.g., number of conflicts in N-Queens).
     *
     * @param state the state to evaluate
     * @return the cost of the state
     */
    public double cost(S state);

    /**
     * Returns the initial state of the problem.
     *
     * @return the initial state
     */
    public S getInitState();

    /**
     * Prints a readable representation of the given state.
     *
     * @param state the state to print
     */
    public void printState(S state);
}