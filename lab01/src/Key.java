import org.checkerframework.checker.units.qual.K;

import java.util.Collections;

public class Key implements Comparable<Key> {
    int value;

    public Key(int val) {
        value = val;
    }

    @Override
    public int compareTo(Key otherKey) {
        return this.value - otherKey.value;
    }

    public boolean isBigger(Key otherKey) {
        if (this.compareTo(otherKey) > 0) return true;
        return false;
    }

    public static void main(String[] args) {
        Key key1 = new Key(10);
        Key key2 = new Key(20);
        boolean bigger = key1.isBigger(key2);
    }
}
