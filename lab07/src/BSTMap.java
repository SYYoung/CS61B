import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private BSTNode root;
    private HashSet<K> setOfKey;

    // define the node structure
    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;
        BSTNode root;

        private BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    public BSTMap() {
       root = null;
       size = 0;
       setOfKey = new HashSet<>();
    }

    @Override
    public void put(K key, V value) {
        if (!containsKey(key))
            size++;
        root =  putHelper(root, key, value);
        setOfKey.add(key);
    }

    private BSTNode putHelper(BSTNode node, K key, V value) {
        if (node == null)
            return new BSTNode(key, value);
        int d = key.compareTo(node.key); // check if key >= node.key
        if (d < 0) // key < node.key
            node.left = putHelper(node.left, key, value);
        if (d > 0) // key > node.key
            node.right = putHelper(node.right, key, value);
        if (d == 0) // key = node.key
            node.value = value;
        return node;
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(BSTNode node, K key) {
        if (node == null)
            return null;
        int d = key.compareTo(node.key); // check if key >= node.key
        if (d == 0) // both key and node.key are equal
            return node.value;
        else if (d < 0) // key < node.key
            return getHelper(node.left, key);
        else // key > node.key
            return getHelper(node.right, key);
    }

    @Override
    public boolean containsKey(K key) {
        return setOfKey.contains(key);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
        setOfKey.clear();
    }

    @Override
    public Set keySet() {
        // no need to implement
        throw new UnsupportedOperationException();
    }


    @Override
    public V remove(K key) {
        // no need to implement
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        // no need to implement
        throw new UnsupportedOperationException();
    }

    private void printInOrderHelper(BSTNode node) {
        // print left , then the node, then the right
        if (node == null) return;
        printInOrderHelper(node.left);
        System.out.println("key: " + node.key + ", value: " + node.value);
        printInOrderHelper(node.right);
    }

    public void printInOrder() {
        printInOrderHelper(root);
    }

}
