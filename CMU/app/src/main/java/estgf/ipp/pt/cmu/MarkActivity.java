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
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import estgf.ipp.pt.cmu.Service.ScheduleClient;


public class MarkActivity extends AppCompatActivity {

    private ScheduleClient scheduleClient;
    private DatePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_activity);

//        Criaçãp de um novo serviço de cliente  e ligação da atividade a este serviço
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();

//        Referência para a nossa escolha de data
        picker = (DatePicker) findViewById(R.id.mark_activity_calendar);
//        launch_btn = (Button) findViewById(R.id.launch_notification);

//        verify();

    }

    /**
     * Método onClick definido no XML mark_activity
     */
    public void onDateSelectedButtonClick(View v) {
        // Get the date from our datepicker
        int day = picker.getDayOfMonth();
        int month = picker.getMonth();
        int year = picker.getYear();
//        Criação de um calendário para que possamos escolher a data da nossa atividade
        Calendar selectedDate = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        selectedDate.set(year, month, day);
        selectedDate.set(Calendar.HOUR_OF_DAY, 0);
        selectedDate.set(Calendar.MINUTE, 0);
        selectedDate.set(Calendar.SECOND, 0);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);


        if (today.compareTo(selectedDate) <= 0) {
//        Pedir ao serviço para que lance um alarm para a data que o utilizador marcou a atividade, que por sua vez a aplicação fala com o "cliente" e esta pede informação ao serviço
            scheduleClient.setAlarmForNotification(selectedDate);
//         Notificação para que o utilizador saiba para quando marcou a atividade
            Toast.makeText(this, "Notificação marcada para " + day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Impossível marcar data", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStop() {
//        Este método permite que quando a aplicaão seja fechada, a notificação feche também
        if (scheduleClient != null)
            scheduleClient.doUnbindService();
        super.onStop();
    }


}
