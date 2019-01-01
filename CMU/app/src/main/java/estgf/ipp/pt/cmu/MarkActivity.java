package estgf.ipp.pt.cmu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MarkActivity extends AppCompatActivity {

    private String CHANNEL_ID = "channel";
    private int notificationId;
    private String channel_description = "This is a channel";
    private String channel_name = "Channel";

    private TextView title;
    private CalendarView calendarView;
    private Button launch_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_activity);

        createNotificationChanel();

        title = (TextView) findViewById(R.id.mark_activity);
        calendarView = (CalendarView) findViewById(R.id.mark_activity_calendar);
        launch_btn = (Button) findViewById(R.id.launch_notification);

        verify();

    }
    private void verify () {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                Calendar calendar = Calendar.getInstance();
                if (calendar.get(Calendar.DAY_OF_MONTH) > day && calendar.get(Calendar.MONTH) <= (month + 1)) {
                    Toast.makeText(getApplicationContext(), "Impossível marcar atividade para a data pretendida ", Toast.LENGTH_SHORT).show();
                    verify();
                } else {
                    String selectedDate = day + "/" + (month + 1) + "/" + year;
                    showNotification(selectedDate);
                }
            }
        });
    }

    public void showNotification(final String activityDate) {
        launch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder createNotification = new NotificationCompat.Builder(MarkActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle("Notificação")
                        .setContentText("Atividade marcada para " + activityDate)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
                NotificationManagerCompat showNot = NotificationManagerCompat.from(MarkActivity.this);
                showNot.notify(notificationId, createNotification.build());
            }
        });
    }

    private void dateFromCalendar() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                String date = year + "/" + month + "/" + day;
            }
        });
    }

    private void createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channel_name, importance);
            channel.setDescription(channel_description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
//    Just here if needed
//    public void cancelNotification () {
//        NotificationManagerCompat cancelMessage = NotificationManagerCompat.from(this);
//        cancelMessage.cancel(notificationId);
//    }
}
