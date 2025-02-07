package hashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {


    @Override
    public Set<K> keySet() {
        //return Set.of();
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        //return null;
        throw new UnsupportedOperationException();
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private int numBuckets;
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        int defaultCap = 16;
        double defaultLoadFactor = 0.75;
        this(defaultCap, defaultLoadFactor);
    }

    public MyHashMap(int initialCapacity) {
        double defaultLoadFactor = 0.75;
        this(initialCapacity, defaultLoadFactor);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        buckets = createTable(initialCapacity);
        size = 0;
        numBuckets = initialCapacity;
        this.loadFactor = loadFactor;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        //return null;
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table;
        table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    private Node getNode(K key) {
        int hashVal = key.hashCode();
        int whichBucket = Math.floorMod(hashVal, numBuckets);
        for (Node node : buckets[whichBucket]) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    private boolean exceedLoadFactor() {
       return  (((double) size / numBuckets) > loadFactor);
    }

    private void resizeTable() {
        int newTableSize = numBuckets * 2;
        Collection<Node>[] newBucket = createTable(newTableSize);
        Collection<Node>[] oldBucket = buckets;
        buckets = newBucket;
        size = 0;
        int oldNumBuckets = numBuckets;
        numBuckets = newTableSize;
        for (int i = 0; i < oldNumBuckets; i++) {
            for (Node node : oldBucket[i]) {
                putNewNode(node.key, node.value);
                size++;
            }
        }
        oldBucket = null;
    }

    private void putNewNode(K key, V value) {
        Node node = new Node(key, value);
        int hashVal = node.key.hashCode();
        int whichBucket = Math.floorMod(hashVal, numBuckets);
        buckets[whichBucket].add(node);
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void put(K key, V value) {
        // check such key exists or not
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
        }
        else {
            // check if the table reaches the loadFactor. if yes, resize the table
            // and copy current nodes to the new table
            if (exceedLoadFactor())
                resizeTable();
            node = new Node(key, value);
            int hashVal = node.key.hashCode();
            int whichBucket = Math.floorMod(hashVal, numBuckets);
            buckets[whichBucket].add(node);
            size++;
        }
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        if (node == null)
            return null;
        else
            return node.value;
    }

    @Override
    public boolean containsKey(K key) {
        Node node = getNode(key);
        return (node != null);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < numBuckets; i++) {
            buckets[i].clear();
        }
        size = 0;
    }

}
