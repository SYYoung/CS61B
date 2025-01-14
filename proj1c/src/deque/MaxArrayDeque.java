package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> defaultComparator;

    public MaxArrayDeque(Comparator<T> c) {
        defaultComparator = c;
    }

    public T max() {
        /* use the default comparator to find out the max in the queue */
        /* if the queue is empty, return null */
        return max(defaultComparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty())
            return null;
        int maxInd = 0;
        T maxItem = get(maxInd);
        for (int i = 1; i < size(); i++) {
            T curItem = get(i);
            if (c.compare(curItem, maxItem) > 0) {
                maxInd = i;
                maxItem = curItem;
            }
        }
        return maxItem;
    }
}
