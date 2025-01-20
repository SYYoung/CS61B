import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Exception;
// TODO: Add any other necessary imports.

public class Percolation {
    // TODO: Add any necessary instance variables.
    private WeightedQuickUnionUF wuf;
    private int[][] grid;
    private int totalOpen;
    private int dimension;
    // virtual top node and virtual bottom node
    private int virtualTop;
    private int virtualBot;
    // values for OPEN, BLOCKED and FULL
    private final int OPEN = 1;
    private final int BLOCKED = 0;
    private final int FULL = 2;


    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N <= 0)
            throw new IllegalArgumentException();
        dimension = N;
        wuf = new WeightedQuickUnionUF(N * N + 2);
        grid = new int[N][N];
        // initialize all the grids as BLOCKED
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                grid[i][j] = BLOCKED;
        totalOpen = 0;
        // set up virtualTop and virtualBot
        virtualTop = N * N;
        virtualBot = N * N + 1;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (!isInputValid(row, col))
            throw new IndexOutOfBoundsException();
        if (isOpen(row, col))
            return;
        grid[row][col] = OPEN;
        connect2Adj(row, col);
        connect2VirtualTop(row, col);
        totalOpen++;
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return (grid[row][col] == OPEN);
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (wuf.connected(rc21d(row, col), virtualTop))
            return true;
        else
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

    private void connect2VirtualTop(int row, int col) {
        if (row != 0)
            return;
        wuf.union(rc21d(row, col), virtualTop);
    }


    public static void main(String[] args) {
        Percolation per = new Percolation(5);
        int r1, r2, c1, c2;
        r1 = 3; c1 = 4; r2 = 2; c2 = 4;
        per.open(r1, c1);
        per.open(r2, c2);
        boolean connect = per.wuf.connected(14,19);
        r1=2; c1 = 2; r2 = 2; c2 = 3;
        per.open(r1, c1);
        per.open(r2, c2);
        // now (12,1) should be connected. also (13,1) should be connected
        connect = per.wuf.connected(12, 13);
        connect = per.wuf.connected(13, 14);
        r1 = 0; c1 = 2; r2 = 1; c2 = 2;
        per.open(r1, c1);
        per.open(r2, c2);
        connect = per.wuf.connected(2, 7);
        connect = per.wuf.connected(7, 12);
        r1 = 2; c1 = 2;
        per.open(r1, c1);
        boolean fullOrNot = per.isFull(r1, c1); // should be full
        fullOrNot = per.isFull(3, 4);
    }
}
