package problems;

import java.util.Arrays;
import java.util.Random;

/**
 * Implementation of the N-Queens problem using integer arrays as states.
 * Each index represents a column, and the value at that index is the row
 * of the queen in that column.
 */
public class NQueens implements Problem<int[]> {
    // Number of queens (and board size)
    private final int N;

    /**
     * Constructs an N-Queens problem instance.
     *
     * @param n the number of queens (and size of the board)
     */
    public NQueens(int n) {
        N = n;
    }

    /**
     * Generates a new state by moving one queen to a different row
     * in the same column. The column and new row are chosen randomly.
     *
     * @param current the current state (queen positions)
     * @return a new state with one queen moved
     */
    @Override
    public int[] generateNewState(int[] current) {
        Random rand = new Random();
        int column = rand.nextInt(N);
        int currentRow = current[column];
        int newRow;

        // Ensure a different row is selected
        do {
            newRow = rand.nextInt(N);
        } while (newRow == currentRow);

        int[] newState = Arrays.copyOf(current, current.length);
        newState[column] = newRow;
        return newState;
    }

    /**
     * Computes the number of pairs of queens that are attacking each other.
     * This includes row and diagonal conflicts.
     *
     * @param state the state to evaluate
     * @return the number of conflicting pairs (lower is better)
     */
    @Override
    public double cost(int[] state) {
        int conflicts = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // checking all the row conflicts
                if (state[i] == state[j]) {
                    conflicts++;
                }
                // check all the diagonal conflicts
                if (Math.abs(state[i] - state[j]) == Math.abs(i - j)) {
                    conflicts++;
                }
            }
        }
        return conflicts;
    }

    /**
     * Returns the initial state with one queen randomly placed in each column.
     *
     * @return a random initial state
     */
    @Override
    public int[] getInitState() {
        Random rand = new Random();
        int[] state = new int[N];
        for (int i = 0; i < N; i++) {
            state[i] = rand.nextInt(N);
        }
        return state;
    }

    /**
     * Prints the current board state in a human-readable format.
     *
     * @param state the state to print
     */
    @Override
    public void printState(int[] state) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (state[col] == row) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }

}