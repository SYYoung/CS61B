import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Exception;
// TODO: Add any other necessary imports.

public class Percolation {
    // TODO: Add any necessary instance variables.
    private WeightedQuickUnionUF wuf;
    private int[][] grid;
    private int totalOpen;
    private int dimension;
    // values for OPEN, BLOCKED and FULL
    private final int OPEN = 1;
    private final int BLOCKED = 0;
    private final int FULL = 2;
    // virtual top node and virtual bottom node

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N <= 0)
            throw new IllegalArgumentException();
        dimension = N;
        wuf = new WeightedQuickUnionUF(N * N);
        grid = new int[N][N];
        // initialize all the grids as BLOCKED
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                grid[i][j] = BLOCKED;
        totalOpen = 0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (!isInputValid(row, col))
            throw new IndexOutOfBoundsException();
        grid[row][col] = OPEN;
        connect2Adj(row, col);
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return (grid[row][col] == OPEN);
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return totalOpen;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    private boolean isInputValid(int r, int c) {
        boolean valid = true;
        if ((r < 0 ) || (r >= dimension))
            valid =  false;
        else if ((c < 0) || (c >= dimension))
            valid =  false;
        return valid;
    }

    private int rc21d(int r, int c) {
        // since the input to UF is 1d, we need to convert the 2d coord to 1d
        return (r * dimension + c);
    }

    private int[] oneD2rc(int ind) {
        // convert the index from UF back to row and column
        int[] rc = new int[2];
        rc[0] = ind / dimension;
        rc[1] = ind % dimension;
        return rc;
    }

    private void connect2Adj(int r, int c) {
        // check if the adjacent is also open. If yes, connect them
        int[][] offset = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < 4; i++) {
            int adjR = r + offset[i][0];
            int adjC = c + offset[i][1];
            if (isInputValid(adjR, adjC) && isOpen(adjR, adjC)) {
                wuf.union(rc21d(r, c), rc21d(adjR, adjC));
            }
        }
    }

    public static void main(String[] args) {
        Percolation per = new Percolation(5);
        per.open(3, 4);
        per.open(2,4);
        boolean connect = per.wuf.connected(per.rc21d(3,4), per.rc21d(2,4));
    }
}
