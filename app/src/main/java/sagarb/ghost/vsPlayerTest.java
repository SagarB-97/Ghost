package sagarb.ghost;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Dell on 24-03-2017.
 */

public class vsPlayerTest {

    DictionaryTest dictionary;

    public vsPlayerTest(DictionaryTest dictionaryTest){
        dictionary=dictionaryTest;
    }
    public String Challenge(String curWord, int gameend, int player)
    {
        if(curWord.length()==0)
            return "Enter a letter first!";
        else {
            if (gameend == 0) {
                if (curWord.length() >= 4 && dictionary.isWord(curWord)) {
                    if (player == 1) {
                        return "Player 2 Wins!Valid Word Formed";
                    } else {
                        return "Player 1 Wins!Valid Word Formed";
                    }
                }
                else {
                    String possibleWord = dictionary.getAnyWordStartingWith(curWord);
                    if (possibleWord != null) {
                        return "Player " + player + " Wins!There is a possible word : " + possibleWord.toUpperCase();
                    } else {
                        if (player == 1) {
                            return "Player 2 Wins!No such word exists";
                        } else {
                            return "Player 1 Wins!No such word exists";
                        }
                    }
                }
            } else {
                return "Click on Reset to Reset Game";
            }
        }
    }
}
