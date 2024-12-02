package aoa.guessers;

import java.util.*;

public class LFGHelper {

    public static Map<Character, Integer> getFreqMapThatMatchesPattern(List<String> matchedWords) {
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

    public static Map<Character, Integer> sortedFrequencyMap(Map<Character, Integer> originalMap) {
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

    public static List<String> keepOnlyWordsThatMatchPattern(String pattern,
                                                             List<String> words) {
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

    public static List<String> keepOnlyWordsThatMatchPattern(String pattern,
                                                             List<Character> guess,
                                                             List<String> words) {
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
                    } else if (guess.contains(w.charAt(i))) {
                        match = false;
                        break;
                    }
                }
                if (match)
                    matchedWord.add(w);
            }
        }
        return matchedWord;
    }

    public static List<String> EliminateWordsThatNotMatchPattern(String pattern,
                                                                 List<Character> guesses,
                                                                 List<String> words)
    {
        // find which letter(s) should be excluded
        List<Character> excludeCharList = new ArrayList<>();
        for (char c: guesses) {
            if (pattern.indexOf(c) == -1) {
                excludeCharList.add(c);
            }
        }

        List<String> matchedWord = new ArrayList<>();
        for (String w : words) {
            boolean include = true;
            for (char c: excludeCharList) {
                if (w.indexOf(c) != -1) {
                    include = false;
                    break;
                }
            }
            if (include) {
                matchedWord.add(w);
            }
        }
        return matchedWord;
    }

}
