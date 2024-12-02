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

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMap = new TreeMap<>();
        Map<Character, Integer> sortedFreqMap = new HashMap<>();
        List<String> matchedWords = keepOnlyWordsThatMatchPattern(pattern);
        freqMap = LFGHelper.getFreqMapThatMatchesPattern(matchedWords);
        sortedFreqMap = LFGHelper.sortedFrequencyMap(freqMap);
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