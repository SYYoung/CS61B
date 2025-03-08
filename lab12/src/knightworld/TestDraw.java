package knightworld;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import java.awt.Color;

public class TestDraw {

    public static void main(String[] args) {
        int width = 30;
        int height = 30;
        int[][] grid = new int[width][height];

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        //StdDraw.setScale(0, width);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = 0;
            }
        }

        StdDraw.setPenColor(Color.gray);
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++) {
                StdDraw.filledSquare(x, y, 1);

                //StdDraw.square(x, y, 1);
            }
        }
        StdDraw.setPenColor(Color.black);
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++) {
                StdDraw.square(x, y, 1);

                //StdDraw.square(x, y, 1);
            }
        }



    }
}
