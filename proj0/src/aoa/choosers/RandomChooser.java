package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile)  {
        // TODO: Fill in/change this constructor.
        List<String> words = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        if (wordLength < 1) {
            throw new IllegalArgumentException("Word length is less than one.");
        }
        if (words.isEmpty()) {
            throw new IllegalStateException("There are no words found of this word length");
        }
        // now choose a word from the word list
        int numWords = words.size();
        int randomlyChosenWordNumber = (int) StdRandom.uniform(numWords);
        chosenWord = words.get(randomlyChosenWordNumber);

        pattern = StringUtils.repeat('-', wordLength);
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        // 1. check if the guess are valid: they are lower letters
        if (!Character.isLowerCase(letter))
            return 0;
        // 2. check if the letter has not been guessed before
        if (pattern.indexOf(letter) != -1)
            return 0;
        // 3. calculate the number of occurrences of the guessed letter in the world
        int count = StringUtils.countMatches(chosenWord, letter);
        // 4. update pattern
        if (count > 0) {
            for (int i=0; i<chosenWord.length(); i++) {
                if (chosenWord.charAt(i) == letter)
                    pattern = pattern.substring(0, i) + letter +
                            pattern.substring(i+1);
            }
        }
        // 5. return the occurance
        return count;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }
}
