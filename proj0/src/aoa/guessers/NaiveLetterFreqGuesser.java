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
        Map<Character, Integer> freqMap = new TreeMap<Character,Integer>();
        freqMap = LFGHelper.getFreqMapThatMatchesPattern(words);
        return (freqMap);
    }


    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMap = new TreeMap<>();
        Map<Character, Integer> sortedFreqMap = new HashMap<>();
        freqMap = LFGHelper.getFreqMapThatMatchesPattern(words);
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
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
