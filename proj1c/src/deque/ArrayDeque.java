package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private final int RFACTOR = 2;
    private final int DFACTOR = 2;
    private final float USAGE = 0.25F;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        // the first one will start at the middle of array so that it can expand on both way
        /* if a new item is added at first, it will be added at items[nextFirst]. */
        /* if a new item is added at last, it will be added at items[nextLast] */
        nextFirst = items.length/2;
        nextLast = items.length/2 + 1;
    }

    private boolean isQueueFull() {
       return (size == items.length);
    }

    private boolean isQueueTooSpare() {
        return (size < items.length * USAGE);
    }

    private void resizeUp(int capacity) {
        T[] a = (T []) new Object[items.length * 2];
        /* the old array will copy to the new array, starting from index: original length /2,
        then copy the whole array. the last index should be: original_length/2 + original_length - 1
         */
        int new_start = size / 2;
        int old_start = nextFirst + 1;
        for (int i=0; i <size; i++) {
            a[new_start + i] = items[(old_start + i) % size];
        }
        items = a;
        /* update nextFirst, nextLast and size */
        nextFirst = new_start - 1;
        nextLast = new_start + size;
    }

    @Override
    public void addFirst(T x) {
        if (isQueueFull())
           resizeUp(size * RFACTOR);
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (isQueueFull())
            resizeUp(size * RFACTOR);
        items[nextLast] = x;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        for (int i=0; i<size; i++) {
            returnList.add(items[(nextFirst + 1 + i) % items.length]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    public void resizeDown() {
        T[] a = (T []) new Object[items.length / DFACTOR];
        /* the old array will copy to the new array, starting from index: original length /2,
        then copy the whole array. the last index should be: original_length/2 + original_length - 1
         */
        int new_start = 0;
        int old_start = nextFirst + 1;
        for (int i=0; i <size; i++) {
            a[new_start + i] = items[(old_start + i) % items.length];
        }
        items = a;
        /* update nextFirst, nextLast and size */
        nextFirst = (new_start - 1 + a.length) % a.length;
        nextLast = size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty())
            return null;
        /* if the queue is too sparse, scale down first */
        if (isQueueTooSpare())
            resizeDown();
        int firstIndex = (nextFirst + 1 + items.length) % items.length;
        T firstItem = items[firstIndex];
        /* update the internal variables */
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size--;

        return firstItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty())
            return null;
        /* check if the queue is too sparse, scale down first */
        if (isQueueTooSpare())
            resizeDown();

        int lastIndex = (nextLast - 1 + items.length) % items.length;
        T lastItem = items[lastIndex];
        /* update internal variables */
        items[lastIndex] = null;
        nextLast = lastIndex;
        size--;

        return lastItem;
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index > items.length))
            return null;
        return (items[(nextFirst + 1 + index) % items.length]);
    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayDeque oas) {
            if (oas.size != this.size)
                return false;

            for (int i = 0; i < this.size; i++) {
                if (this.get(i) != oas.get(i))
                    return false;
            }

            return true;
        }
        /* if the other object is not ArrayQueue, return false */
        return false;
    }

    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    /* implement the iterator */
    private class ArrayDequeIterator implements Iterator<T> {
        int curr; /* this points to current pointer position */

        public ArrayDequeIterator() {
            curr = 0;
        }

        @Override
        public boolean hasNext() {
            return (curr != size);
        }

        @Override
        public T next() {
            T currItem = get(curr);
            curr += 1;
            return currItem;
        }
    }
}
