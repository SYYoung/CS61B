import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        if (L.isEmpty())
            return 0;
        else {
            int total = 0;
            for (int i=0; i<L.size(); i++) {
                total += L.get(i);
            }
            return total;
        }
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> evenList = new ArrayList<>();
        if (L.isEmpty())
            return null;
        for (int i=0; i<L.size(); i++) {
            int num = L.get(i);
            if (num % 2 == 0)
                evenList.add(num);
        }
        return evenList;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        List<Integer> commonList = new ArrayList<>();

        // Build set 1 and set 2
        if (L1.isEmpty() || L2.isEmpty())
            return commonList;
        for (int elem : L1)
            set1.add(elem);
        for (int elem: L2)
            set2.add(elem);

        for (int elem1: set1) {
            if (set2.contains(elem1))
                commonList.add(elem1);
        }
        return commonList;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        if (words.isEmpty())
            return 0;
        int total = 0;
        for (String s : words) {
            for (int i=0; i<s.length(); i++) {
                if (c == s.charAt(i))
                    total++;
            }
        }
        return total;
    }
}
