package sagarb.ghost;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Dell on 24-03-2017.
 */

public class DictionaryTest {
    private ArrayList<String> words;

    public DictionaryTest(ArrayList<String> w){
        this.words=w;
    }

    public boolean isWord(String word) {
        word = word.toLowerCase();
        return words.contains(word);
    }
    public String getAnyWordStartingWith(String prefix) {
        if(prefix.equals(""))
        {
            Random rd = new Random();
            int id = rd.nextInt(60000)+1;
            return words.get(id);
        }
        else
        {
            Iterator iterator = words.iterator();
            while(iterator.hasNext())
            {
                String w = iterator.next().toString();
                if(w.length()>=4 && w.startsWith(prefix))
                    return w;
            }
        }
        return null;
    }

}
