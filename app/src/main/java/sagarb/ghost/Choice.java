package sagarb.ghost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }

    public void computer(View v){
        Intent intent = new Intent(this, vsComputer.class);
        startActivity(intent);
    }
    public void player(View v){
        Intent intent = new Intent(this, vsPlayer.class);
        startActivity(intent);
    }
}
