package estgf.ipp.pt.cmu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class MarkActivity extends Fragment {

    private String CHANNEL_ID = "channel";
    private int notificationId;
    private String channel_description = "This is channel 1";
    private String channel_name = "Channel 1";

    private TextView title;
    private CalendarView calendarView;
    private Button launch_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mark_activity, container, false);
        title = (TextView) view.findViewById(R.id.mark_activity);
        calendarView = (CalendarView) view.findViewById(R.id.mark_activity_calendar);
        launch_btn = (Button) view.findViewById(R.id.launch_notification);
        Notifications(view);
        return view;
    }

    public void Notifications (View view) {
        createNotificationChanel();
        showNotification(view);
    }

    public void showNotification(View view) {

        launch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder createNotification = new NotificationCompat.Builder(getActivity(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle("Notificação")
                        .setContentText("Atividade marcada para ")
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
                NotificationManagerCompat showNotification = NotificationManagerCompat.from(getActivity());
                showNotification.notify(notificationId, createNotification.build());
            }
        });

    }

    public void cancelNotification(View view) {
        NotificationManagerCompat cancelMessage = NotificationManagerCompat.from(getActivity());
        cancelMessage.cancel(notificationId);
    }

    private void createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channel_name, importance);
            channel.setDescription(channel_description);
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}


