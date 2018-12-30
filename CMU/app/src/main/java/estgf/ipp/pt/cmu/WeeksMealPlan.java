package estgf.ipp.pt.cmu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import estgf.ipp.pt.cmu.Database.Controllers.DBController;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetMeals;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetWeeksDays;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.WeeksDays.*;
import estgf.ipp.pt.cmu.Utilities.OnWeeksDaySelectedListener;
import estgf.ipp.pt.cmu.Utilities.StaticHolder;

public class WeeksMealPlan extends AppCompatActivity implements OnWeeksDaySelectedListener,NotifyGetMeals,NotifyGetWeeksDays {

    private WeeksDaysAdapter adapter;
    private Context context=this;
    private List<WeeksDays> list;
    private DBController dbController;
    private int weeksDayID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbController = new DBController(this);


        setContentView(R.layout.activity_weeks_meal_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.adapter= new WeeksDaysAdapter(this,this);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_Weeks_Meal_Plan);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this,UserInformation.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbController.getWeeksDays(this);
    }

    private void generateWeeksDays(Calendar date,List<WeeksDays> list){

        Calendar oneMonthAdded = Calendar.getInstance();
        oneMonthAdded.set(Calendar.DAY_OF_WEEK,1);
        oneMonthAdded.add(Calendar.MONTH,1);
        oneMonthAdded.set(Calendar.DAY_OF_WEEK,7);

        date.set(Calendar.DAY_OF_WEEK,1);

        while(date.compareTo(oneMonthAdded) <= 0){

         list.add(new WeeksDays(date));
         date.add(Calendar.DAY_OF_YEAR,1);
        }



    }

    @Override
    public void onWeeksDaySelected(WeeksDays weeksDay) {
        dbController.getMeals(this, (long) weeksDay.getId());
        StaticHolder.weeksDays=weeksDay;
        this.weeksDayID=weeksDay.getId();
    }

    @Override
    public void OnGetMeals(List<Meal> list) {
        Intent intent = new Intent(context,DailyMeals.class);
        intent.putExtra("weeksDayID",this.weeksDayID);
        intent.putExtra("meals",(ArrayList<Meal>)list);
        startActivity(intent);
    }

    @Override
    public void OnGetWeeksDays(List<WeeksDays> list) {
        if(list == null || list.size()==0){
            this.list = new ArrayList<>();
            Calendar today = Calendar.getInstance();
            generateWeeksDays(today,this.list);

            WeeksDays a[]= new WeeksDays[this.list.size()];
            dbController.insert(this.list.toArray(a));
            dbController.getWeeksDays(this);
        }else{
            this.adapter.addItems(list);
            this.list=list;
            checkIfNeedToGenerateMoreWeeksDays();
        }
    }

    private void checkIfNeedToGenerateMoreWeeksDays() {


        WeeksDays last = list.get(list.size()-1);
        Calendar lastDate = last.getDate(); //Ir buscar a ultiam data gravada na BD
        Calendar today = Calendar.getInstance(); //Ir buscar a data de hoje

        today.set(Calendar.DAY_OF_WEEK,7); //Ir buscar o ultimo dia da semana em que me encontro
        lastDate.add(Calendar.DAY_OF_MONTH,-7); //Retirar uma semana ao ultima data gravada na bd
        //Se a data de hoje for maior que a a ultima data da bd menos uma semana então tenho de adicionar mais weeksdays
        //Basicamente: se faltar uma semana para nao ter mais weeksdays então adiciono mais
        if(today.compareTo(lastDate)>=0){
            List<WeeksDays> temp = new ArrayList<>();
            generateWeeksDays(last.getDate(),temp); //Passar a ultima data na bd (sem ser menos uma semana)
            WeeksDays a[]= new WeeksDays[temp.size()];
            this.dbController.insert(temp.toArray(a));

        }
    }
}
