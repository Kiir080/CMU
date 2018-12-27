package estgf.ipp.pt.cmu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.WeeksDays.*;
import estgf.ipp.pt.cmu.Utilities.OnWeeksDaySelectedListener;

public class WeeksMealPlan extends AppCompatActivity implements OnWeeksDaySelectedListener {

    private WeeksDaysAdapter adapter;
    private Context context=this;
    private List<WeeksDays> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeks_meal_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = new ArrayList<>();
        generateWeeksDays();
        this.adapter= new WeeksDaysAdapter(this,list,this);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_Weeks_Meal_Plan);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void generateWeeksDays(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar today = Calendar.getInstance();
        Calendar oneMonthAdded = Calendar.getInstance();
        oneMonthAdded.set(Calendar.DAY_OF_WEEK,1);
        oneMonthAdded.add(Calendar.MONTH,1);
        oneMonthAdded.set(Calendar.DAY_OF_WEEK,7);

        today.set(Calendar.DAY_OF_WEEK,1);

        while(today.compareTo(oneMonthAdded) <= 0){

         list.add(new WeeksDays(today));
         today.add(Calendar.DAY_OF_MONTH,1);


        }



    }

    @Override
    public void onWeeksDaySelected(WeeksDays weeksDay) {
        Intent intent = new Intent(context,DailyMeals.class);
        startActivity(intent);
    }
}
