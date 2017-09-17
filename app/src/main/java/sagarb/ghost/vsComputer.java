package sagarb.ghost;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class vsComputer extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private TextView Word,Label;
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    private String curWord="";
    int gameend=0;
    int began;

    final Animation in = new AlphaAnimation(0.0f, 1.0f);
    final Animation out = new AlphaAnimation(1.0f, 0.0f);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ghost);
        Word=(TextView)findViewById(R.id.ghostText);
        Label=(TextView)findViewById(R.id.gameStatus);

        in.setDuration(1000);
        out.setDuration(500);

        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Label.setText(COMPUTER_TURN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Word.setText(curWord);
                Word.startAnimation(in);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Label.setText(USER_TURN);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new SimpleDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        curWord="";
        gameend=0;
        userTurn = random.nextBoolean();
        if (userTurn) {
            began=1;
            Label.setText(USER_TURN);
        } else {
            began=2;
            Label.setText(COMPUTER_TURN);
            computerTurn();
        }
    }
    public void reset(View v)
    {
        userTurn = random.nextBoolean();
        if (userTurn) {
            began=1;
            Label.setText(USER_TURN);
        } else {
            began=2;
            Label.setText(COMPUTER_TURN);
            computerTurn();
        }
        curWord="";
        gameend=0;
        Word.setText(curWord);
    }

    private void computerTurn() {
        if(curWord.length()>=4 && dictionary.isWord(curWord))
        {
            Label.setText("You formed a word!\nComputer Wins!");
            Intent intent = new Intent(this, compWin.class);
            intent.putExtra("STATUS","You formed a word!\nComputer Wins!");
            startActivity(intent);
        }
        else
        {
            String next = dictionary.getAnyWordStartingWith(curWord);
            if(next==null)
            {
                Label.setText("Computer Challenged you!\nNo possible word exists!");
                Intent intent = new Intent(this, compWin.class);
                intent.putExtra("STATUS","Computer Challenged you!\nNo possible word exists!");
                startActivity(intent);
            }
            else
            {
                curWord+=next.charAt(curWord.length());
                Word.startAnimation(out);
            }
        }
    }

    @Override
    public boolean onKeyUp(int keycode,KeyEvent event)
    {
        if(gameend==0) {
            char key = (char) event.getUnicodeChar();
            if (Character.isAlphabetic(key)) {
                curWord += key;
                Word.setText(curWord);
                computerTurn();
                return true;
            } else {
                return super.onKeyUp(keycode, event);
            }
        }
        else{
            Toast.makeText(this, "Click on Reset to Reset Game", Toast.LENGTH_SHORT).show();
            return super.onKeyUp(keycode,event);
        }
    }

    public void Challenge(View v) {
        if(curWord.length()==0)
            Toast.makeText(this, "Enter a letter first!", Toast.LENGTH_SHORT).show();
        else {
            if (gameend == 0) {
                if (curWord.length() >= 4 && dictionary.isWord(curWord)) {
                    Intent intent = new Intent(this, youWin.class);
                    intent.putExtra("STATUS", "You Win!\nComputer Formed a word");
                    startActivity(intent);
                } else {
                    String possibleWord = dictionary.getAnyWordStartingWith(curWord);
                    if (possibleWord != null) {
                        Intent intent = new Intent(this, compWin.class);
                        intent.putExtra("STATUS", "You Lose!\nThere is a Possible word : \n" + possibleWord.toUpperCase());
                        startActivity(intent);
                    } else {
                        Label.setText("You Win! No such word exists!!");
                        Intent intent = new Intent(this, youWin.class);
                        intent.putExtra("STATUS", "You Win!\nNo such word exists!");
                        startActivity(intent);
                    }
                }
            }
        }
    }
}
