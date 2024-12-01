import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> charMap = new HashMap<>();
        Character letter = 'a';
        Integer count = 1;
        int total = 26;
        for (int i=0; i<total; i++) {
            charMap.put(letter, count);
            letter++;
            count++;
        }
        return charMap;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> squareMap = new HashMap<>();

        if (nums.isEmpty())
            return squareMap;
        for (Integer elem : nums) {
            squareMap.put(elem, elem*elem);
        }
        return squareMap;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        if (words.isEmpty())
            return wordCountMap;

        for (String s : words) {
            if (wordCountMap.containsKey(s)) {
                Integer val = wordCountMap.get(s);
                val++;
                wordCountMap.put(s, val);
            }
            else {
                wordCountMap.put(s, 1);
            }
        }
        return wordCountMap;
    }
}
