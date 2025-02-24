import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TrieSet {
    private TrieNode root;

    private class TrieNode {
        private HashMap<Character, TrieNode> children;
        private boolean isKey;

        public TrieNode() {
            children = new HashMap<>();
            isKey = false;
        }
    }

    public TrieSet() {
        root = new TrieNode();
    }

    private TrieNode searchNode(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null)
                return null;
            current = node;
        }
        return current;
    }
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null)
                return false;
            current = node;
        }
        return current.isKey;
    }

    public void insert(String word) {
        if (search(word))
            return;

        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.computeIfAbsent(ch, k -> new TrieNode());
            current = node;
        }
        current.isKey = true;
    }

    private void colHelp(String s, List<String> x, TrieNode n) {
        if (n.isKey)
            x.add(s);
        for (char c: n.children.keySet())
            colHelp(s+c, x, n.children.get(c));
    }

    public List<String> getAllWord() {
        List<String> x = new LinkedList<>();
        TrieNode current = root;

        colHelp("", x, root);
        return x;
    }

    public List<String> keysWithPrefix(String s) {
        // find the node corresponding to the string
        List<String> x = new LinkedList<>();
        TrieNode alpha = searchNode(s);
        if (alpha != null)
            colHelp(s, x, alpha);
        return x;
    }

    public String longestPrefixOf(String word) {
        TrieNode current = root;
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null)
                break;
            result.append(ch);
            current = node;
        }
        return result.toString();
    }


    public static void main(String[] args) {
        TrieSet trie = new TrieSet();
        trie.insert("hello");
        trie.insert("world");
        trie.insert("he");
        trie.insert("help");

        System.out.println(trie.search("hello"));
        System.out.println(trie.search("world"));
        System.out.println(trie.search("hi"));
        System.out.println(trie.search("hell"));

        List<String> list1 = trie.getAllWord();
        System.out.println(list1);

        list1 = trie.keysWithPrefix("hel");
        System.out.println(list1);

        trie = new TrieSet();
        String[] inputS = new String[]{"sad", "awls", "same", "a", "sam", "sap"};
        for (String s : inputS)
            trie.insert(s);

        list1 = trie.getAllWord();
        System.out.println(list1);

        list1 = trie.keysWithPrefix("sam");
        System.out.println(list1);

        String longest = trie.longestPrefixOf("sample");
    }


    /*
    collect(): collecting Trie
    public List<> collect() {
        List<> x = new LinkedList<String>();
        for (char c: root.next.keys())
            colHelp("", x, root.nex.get(c);
        return x;
     }

     public void colHelp(String s, List<String> x, Node n) {
        if (n.isKey())
            x.add(s);
        for (char c: n.next.keys())
            colHelp(s+c, x, n.next.get(c));
     }

     // keys with prefix
     //  assume the node alpha corresponding to the string
     public void keysWithPrefix(String s) {
        // find the node corresponding to the string
        List<> x = new LinkedList<String>();
        for (char c: alpha.next.keys())
            colHelp(s+c, x, alpha.next.get(c);
     }

    public void betterAutoComplete(String s) {
        // each node stores it own value, as well as the value of its
        // substring.
        // use a priorityQ to get the best subtree
        // consider the highest score of its children
        // then keep on getting until it fulfills the number required
    }
     */
}
