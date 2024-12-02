package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMap = new TreeMap<>();
        for (String s: words) {
            for (int i=0; i<s.length(); i++) {
                Character c = s.charAt(i);
                if (freqMap.containsKey(c)) {
                    freqMap.put(c, freqMap.get(c)+1);
                } else {
                    freqMap.put(c, 1);
                }
            }
        }
        return freqMap;
    }

    private Map<Character, Integer> sortedFrequencyMap(Map<Character, Integer> originalMap) {
        List<Map.Entry<Character, Integer>> list =
                new LinkedList<Map.Entry<Character, Integer>>(originalMap.entrySet());
        // sort the list
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2) {
                return (o1.getValue().compareTo(o2.getValue()) * -1);
            }
        });
        // put data from sorted list to a new hashmap
        HashMap<Character, Integer> temp = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character, Integer> aa: list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMap = new TreeMap<>();
        Map<Character, Integer> sortedFreqMap = new HashMap<>();
        freqMap = getFrequencyMap();
        sortedFreqMap = sortedFrequencyMap(freqMap);
        if (freqMap.isEmpty())
            return '?';
        // get the keys from the sorted map
        List<Character> keyList = new ArrayList<Character>(sortedFreqMap.keySet());
        // get the char from the keyList
        char answer = '?';
        for (Character c : keyList) {
            if (!guesses.contains(c)) {
                answer = c;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
