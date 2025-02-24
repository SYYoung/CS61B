public class DataIndexedCharMap<V>  {
    private V[] items;

    public DataIndexedCharMap(int R) {
        items = (V[]) new Object[R];
    }

    public void put(char c, V val) {
        items[c] = val;
    }

    public V get(char c) {
        return items[c];
    }

    public static void main(String[] args) {
        DataIndexedCharMap<Integer> test = new DataIndexedCharMap<>(130);
        test.put('c', 10);
        test.put('a', 5);

        int val = test.get('a');
    }
}
