package sagarb.ghost;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;
    int ctr=0;
    public SimpleDictionary(InputStream wordListStream) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >=4) {

                words.add(line.trim());
                ctr++;
            }
            //Log.d("word = ",word);
        }
        Log.d("Final Count = ",""+ctr);
    }

    @Override
    public boolean isWord(String word) {
        word = word.toLowerCase();
        return words.contains(word);
    }

    @Override
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

    @Override
    public String getGoodWordStartingWith(String prefix,int began) {
        if(prefix.equals(""))
        {
            Random rd = new Random();
            int id = rd.nextInt(60000)+1;
            return words.get(id);
        }
        else {
            Log.d("Prefix received = ", prefix);
            String selected = null;
            int beg = 0, end = ctr - 1, mid = (beg + end) / 2;
            int start, en;
            while (beg <= end) {

                mid = (beg + end) / 2;
                String midWord = words.get(mid).toString();
                if (midWord.startsWith(prefix)) {
                    if (mid > 0 && words.get(mid - 1).startsWith(prefix)) {
                        end = mid - 1;
                    } else
                        break;
                } else if (prefix.compareTo(midWord) > 0) {
                    beg = mid + 1;
                } else if (prefix.compareTo(midWord) < 0) {
                    end = mid - 1;
                }
            }
            start = mid;


            beg = 0;
            end = ctr - 1;
            mid = (beg + end) / 2;
            while (beg <= end) {
                mid = (beg + end) / 2;
                String midWord = words.get(mid).toString();
                if (midWord.startsWith(prefix)) {
                    if (mid < ctr - 1 && words.get(mid + 1).startsWith(prefix)) {
                        beg = mid + 1;
                    } else
                        break;
                } else if (prefix.compareToIgnoreCase(midWord) > 0) {
                    beg = mid + 1;
                } else if (prefix.compareToIgnoreCase(midWord) < 0) {
                    end = mid - 1;
                }
            }
            en = mid;

            ArrayList<String> rangeString = new ArrayList<>();
            if (!(start < 0 || end > ctr - 1 || start > ctr - 1 || end < 0)) {

                for (int i = start; i <= en; i++)
                    rangeString.add(words.get(i));
                Iterator itr = rangeString.iterator();
                while (itr.hasNext()) {
                    String w = itr.next().toString();
                    if (w.length() % 2 != 0 && began == 1 && w.length()>prefix.length()) {
                        selected = w;
                        break;
                    } else if (w.length() % 2 == 0 && began == 2 && w.length()>prefix.length()) {
                        selected = w;
                        break;
                    }
                }
                if(selected==null)
                {
                    Iterator itr2 = rangeString.iterator();
                    while (itr2.hasNext()) {
                        String w = itr2.next().toString();
                        /*if (w.length() % 2 == 0 && began == 1 && w.length()>prefix.length()) {
                            selected = w;
                            break;
                        } else if (w.length() % 2 != 0 && began == 2 && w.length()>prefix.length()) {
                            selected = w;
                            break;
                        }*/
                        selected=w;
                        break;
                    }
                }
            }
            if(selected!=null)
                Log.d("Word returned = ", selected);
            else
                Log.d("Word returned = ","NULL");
            return selected;
        }
    }
}
