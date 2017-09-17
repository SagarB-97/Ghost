package sagarb.ghost;

import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Dell on 24-03-2017.
 */

public class vsComputerTest {


    DictionaryTest dictionary;

    public vsComputerTest(DictionaryTest dictionaryTest){
        dictionary=dictionaryTest;
    }

    public String Challenge(String curWord, int gameend) {
        if(curWord.length()==0)
            return "Enter a letter first!";
        else {
            if (gameend == 0) {
                if (curWord.length() >= 4 && dictionary.isWord(curWord)) {
                    return "You Win!Computer Formed a word";
                } else {
                    String possibleWord = dictionary.getAnyWordStartingWith(curWord);
                    if (possibleWord != null) {
                        return "You Lose!There is a Possible word : " + possibleWord.toUpperCase();
                    } else {
                        return "You Win!No such word exists!";
                    }
                }
            }
        }
        return null;
    }
}
