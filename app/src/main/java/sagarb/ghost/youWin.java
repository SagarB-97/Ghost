package sagarb.ghost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class youWin extends AppCompatActivity {

    TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_win);
        status = (TextView)findViewById(R.id.status);

        status.setText(getIntent().getStringExtra("STATUS"));
    }

    public void restart(View v){
        Intent intent = new Intent(this, Choice.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
    }
}
