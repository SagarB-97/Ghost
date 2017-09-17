package sagarb.ghost;

import android.content.Context;
import android.content.res.AssetManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    ArrayList<String> words;

    @Mock
    Context mockContext;

    @Test
    public void test_id02(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);

        assertThat(dictionaryTest.isWord("Hello"), is(true));
    }
    @Test
    public void test_id11(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);

        assertThat(dictionaryTest.isWord("Exacerbate"),is(true));}
    @Test
    public void test_id12(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);

        assertThat(dictionaryTest.isWord("addju"), is(false));
    }

    @Test
    public void test_id01(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);

        assertEquals(dictionaryTest.getAnyWordStartingWith("shab"),"shabbier");
    }

    @Test
    public void test_id13(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);
        assertEquals(dictionaryTest.getAnyWordStartingWith("vru"), null);
    }

    @Test
    public void test_id07(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);
        vsPlayerTest playerTest = new vsPlayerTest(dictionaryTest);

        assertEquals(playerTest.Challenge("", 0, 1), "Enter a letter first!");
    }
    @Test
    public void test_id09(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);
        vsPlayerTest playerTest = new vsPlayerTest(dictionaryTest);

        assertEquals(playerTest.Challenge("life", 0, 2), "Player 1 Wins!Valid Word Formed");
    }
    @Test
    public void test_id10(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);
        vsPlayerTest playerTest = new vsPlayerTest(dictionaryTest);

        assertEquals(playerTest.Challenge("lifa", 0, 2), "Player 1 Wins!No such word exists");
    }
    @Test
    public void test_id08(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);
        vsPlayerTest playerTest = new vsPlayerTest(dictionaryTest);

        assertEquals(playerTest.Challenge("a", 0, 1), "Words less than four letters can't be challeged");
    }

    @Test
    public void test_id04(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);
        vsComputerTest computerTest = new vsComputerTest(dictionaryTest);

        assertEquals(computerTest.Challenge("arr", 0), "Fragment is less than 4 letters. Can't be challenged. You Lose.");
    }

    @Test
    public void test_id03(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);
        vsComputerTest computerTest = new vsComputerTest(dictionaryTest);

        assertEquals(computerTest.Challenge("", 0), "Enter a letter first!");
    }


    @Test
    public void test_id05(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);
        vsComputerTest computerTest = new vsComputerTest(dictionaryTest);

        assertEquals(computerTest.Challenge("ever", 0), "You Win!Computer Formed a word");
    }

    @Test
    public void test_id06(){
        try {
            readWords();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DictionaryTest dictionaryTest = new DictionaryTest(words);
        vsComputerTest computerTest = new vsComputerTest(dictionaryTest);

        assertEquals(computerTest.Challenge("lifa", 0), "You Win!No such word exists!");
    }
    public void readWords() throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("H:\\MyApps\\Ghost\\app\\src\\test\\java\\sagarb\\ghost\\words.txt"));
        String str;

        ArrayList<String> list = new ArrayList<>();
        while((str = in.readLine()) != null){
            list.add(str);
        }

        words=list;
    }
}