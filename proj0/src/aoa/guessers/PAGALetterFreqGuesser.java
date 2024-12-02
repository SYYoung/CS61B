package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMap = new TreeMap<>();
        Map<Character, Integer> sortedFreqMap = new HashMap<>();
        List<String> keepWords = LFGHelper.EliminateWordsThatNotMatchPattern(pattern,guesses,words);
        List<String> matchedWords = LFGHelper.keepOnlyWordsThatMatchPattern(pattern,
                                        guesses, keepWords);
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
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
