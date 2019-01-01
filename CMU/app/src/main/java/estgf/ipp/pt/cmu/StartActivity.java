package estgf.ipp.pt.cmu;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private TextView title;
    private Chronometer chronometer;
    private boolean running;
    private long pauseOffset;
    private Button startBtn;
    private Button pauseBtn;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        title = (TextView) findViewById(R.id.iniciar_atividade);
        chronometer = (Chronometer) findViewById(R.id.activity_timer);
        startBtn = (Button) findViewById(R.id.start_activity_btn);
        pauseBtn = (Button) findViewById(R.id.pause_activity_btn);
        resetBtn = (Button) findViewById(R.id.reset_activity_btn);

    }

    public void chronometerStart(View view) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void chronometerPause(View view) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void chronometerReset(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}
