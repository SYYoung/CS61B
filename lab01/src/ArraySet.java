import java.util.Iterator;

public class ArraySet<T> implements  Iterable<T> {
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    public void add(T x) {
        if (!contains(x)) {
            items[size] = x;
            size += 1;
        }
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() { wizPos = 0;}

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            returnSB.append(items[i]);
            returnSB.append(", ");
        }
        returnSB.append("}");
        return returnSB.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { return true;}
        if (other instanceof ArraySet otherSet) {
            if (this.size != ((ArraySet<?>) other).size) { return false;}
            for (T x : this) {
                if (!otherSet.contains(x)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        ArraySet<Integer> bset = new ArraySet<>();
        bset.add(5);
        bset.add(42);
        bset.add(22);

        for (int i : aset) System.out.println(i);
        Iterator<Integer> aseer = aset.iterator();
        while (aseer.hasNext())
            System.out.println(aseer.next());
        System.out.println(aset);

        if (aset.equals(bset))
            System.out.println("both aset and bset are equal to each other.");
        else
            System.out.println("aset is not same as bset.");


    }
}
