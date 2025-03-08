package knightworld;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of knight-move holes.
 */
public class KnightWorld {

    private TETile[][] tiles;
    // TODO: Add additional instance variables here
    private int wd, ht, holeSize;
    private Random rand;

    private void initBoard(int width, int height, int holeSize) {
        // store the value of wd, ht, holdSize; initialize the tiles
        // then set the Random seed
        wd = width;
        ht = height;
        this.holeSize = holeSize;
        tiles = new TETile[wd][ht];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Tileset.GRAY_UNLOCKED_DOOR;
            }
        }
        // work on random seed
        long seed = 2873123;
        rand = new Random(seed);
    }

    private void assignHole(int startX, int startY) {
        int endX, endY;
        if (startX + holeSize > wd)
            endX = wd;
        else
            endX = startX + holeSize;
        if (startY + holeSize > ht)
            endY = ht;
        else
            endY = startY + holeSize;
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    private void assignWholeLineHole(int startX, int startY) {
        int offset = holeSize * 5;
        int firstX = startX % offset;
        for (int x = firstX; x < wd; x=x+offset) {
            assignHole(x, startY);
        }
    }

    private void drawAllHole() {
        int startX = rand.nextInt(wd-1);
        int startY = rand.nextInt(ht-1);
        // 1. fill the position with hole
        assignHole(startX, startY);
        assignWholeLineHole(startX, startY);
    }

    public KnightWorld(int width, int height, int holeSize) {
        // TODO: Fill in this constructor and class, adding helper methods and/or classes as necessary to draw the
        //  specified pattern of the given hole size for a window of size width x height. If you're stuck on how to
        //  begin, look at the provided demo code!
        // 1. fill in the board with grid lines
        initBoard(width, height, holeSize);
        drawAllHole();
    }

    /** Returns the tiles associated with this KnightWorld. */
    public TETile[][] getTiles() {
        return tiles;
    }

    public static void main(String[] args) {
        // Change these parameters as necessary
        int width = 50;
        int height = 30;
        int holeSize = 1;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
}
