package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    private List<String> keepOnlyWordsThatMatchPattern(String pattern) {
        // go through each word in the word list. 1. check the length, 2. check the pattern
        List<String> matchedWord = new ArrayList<>();
        for (String w : words) {
            if (w.length() == pattern.length()) {
                boolean match = true;
                for (int i=0; i < pattern.length(); i++) {
                    char c = pattern.charAt(i);
                    if (Character.isLetter(c)) {
                        if (w.charAt(i) != c) {
                            match = false;
                            break;
                        }
                    }
                }
                if (match)
                    matchedWord.add(w);
            }
        }
        return matchedWord;
    }

    private Map<Character, Integer> getFreqMapThatMatchesPattern(List<String> matchedWords) {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMap = new TreeMap<>();
        for (String s: matchedWords) {
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

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMap = new TreeMap<>();
        Map<Character, Integer> sortedFreqMap = new HashMap<>();
        List<String> matchedWords = keepOnlyWordsThatMatchPattern(pattern);
        freqMap = getFreqMapThatMatchesPattern(matchedWords);
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
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}