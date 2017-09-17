package sagarb.ghost;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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


public class vsPlayer extends AppCompatActivity {
    private static final String P2_TURN = "Player 1 Turn";
    private static final String P1_TURN = "Player 2 Turn";
    private TextView Word,Label;
    private GhostDictionary dictionary;
    private Random random = new Random();
    private boolean p1turn = false;
    private String curWord="";
    int gameend=0;
    int began;
    int player;

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

        curWord="";
        gameend=0;
        p1turn = random.nextBoolean();
        if (p1turn) {
            began=1;
            player=1;
            Label.setText(P1_TURN);
        } else {
            began=2;
            player=2;
            Label.setText(P2_TURN);
        }

        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new SimpleDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstancestate)
    {
        savedInstancestate.putString("Current_Word",curWord);
        savedInstancestate.putInt("Game_Status",gameend);
        super.onSaveInstanceState(savedInstancestate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param
     * @return true
     */
    public void reset(View v)
    {
        p1turn = random.nextBoolean();
        if (p1turn) {
            began=1;
            Label.setText(P1_TURN);
        } else {
            began=2;
            Label.setText(P2_TURN);
        }
        curWord="";
        gameend=0;
        Word.setText(curWord);
    }

    @Override
    public boolean onKeyUp(int keycode,KeyEvent event)
    {
        if(gameend==0) {
            char key = (char) event.getUnicodeChar();
            if (Character.isAlphabetic(key)) {
                curWord += key;
                Word.setText(curWord);
                if(player==1) {
                    Label.setText(P2_TURN);
                    player=2;
                }
                else{
                    Label.setText(P1_TURN);
                    player=1;
                }
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

    public void Challenge(View v)
    {
        if(curWord.length()==0)
            Toast.makeText(this, "Enter a letter first!", Toast.LENGTH_SHORT).show();
        else {
            if (gameend == 0) {
                if (curWord.length() >= 4 && dictionary.isWord(curWord)) {
                    if (player == 1) {
                        Label.setText("Player 2 Wins!\nValid Word Formed");

                        Intent intent = new Intent(this, playerWin.class);
                        intent.putExtra("STATUS", "Player 2 Wins!\nValid Word Formed");
                        startActivity(intent);
                    } else {
                        Label.setText("Player 1 Wins!\nValid Word Formed");

                        Intent intent = new Intent(this, playerWin.class);
                        intent.putExtra("STATUS", "Player 1 Wins!\nValid Word Formed");
                        startActivity(intent);
                    }
                    gameend = 1;
                } else {
                    String possibleWord = dictionary.getAnyWordStartingWith(curWord);
                    if (possibleWord != null) {
                        Label.setText("Player " + player + " Wins!\nThere is a possible word");
                        Word.setText(possibleWord);
                        gameend = 1;

                        Intent intent = new Intent(this, playerWin.class);
                        intent.putExtra("STATUS", "Player " + player + " Wins!\nThere is a possible word : " + possibleWord.toUpperCase());
                        startActivity(intent);


                    } else {
                        if (player == 1) {
                            Label.setText("Player 2 Wins!\nNo such word exists");

                            Intent intent = new Intent(this, playerWin.class);
                            intent.putExtra("STATUS", "Player 2 Wins!\nNo such word exists");
                            startActivity(intent);
                        } else {
                            Label.setText("Player 1 Wins!\nNo such word exists");

                            Intent intent = new Intent(this, playerWin.class);
                            intent.putExtra("STATUS", "Player 1 Wins!\nNo such word exists");
                            startActivity(intent);
                        }
                        gameend = 1;
                    }
                }
            } else {
                Toast.makeText(this, "Click on Reset to Reset Game", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
