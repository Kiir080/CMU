package estgf.ipp.pt.cmu;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;


public class StartActivity extends Fragment {

    private TextView title;
    private Chronometer chronometer;
    private boolean running;
    private long pauseOffset;
    private Button startrBtn;
    private Button pauseBtn;
    private Button resetBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_activity, container, false);
        title = (TextView) view.findViewById(R.id.start_activity);
        chronometer = (Chronometer) view.findViewById(R.id.activity_timer);
        startrBtn = (Button) view.findViewById(R.id.start_activity_btn);
        pauseBtn = (Button) view.findViewById(R.id.pause_activity_btn);
        resetBtn = (Button) view.findViewById(R.id.reset_activity_btn);
        ChronometerFunctions(view);
        return view;
    }

    public void ChronometerFunctions (View view) {
        startChronometer(view);
        pauseChronometer(view);
        resetChronometer(view);
    }

    public void startChronometer(View view) {
        startrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!running) {
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    chronometer.start();
                    running = true;
                }
            }
        });
    }

    public void pauseChronometer(View view) {
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running) {
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    running = false;
                }

            }
        });

    }

    public void resetChronometer(View view) {
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
            }
        });

    }
}

