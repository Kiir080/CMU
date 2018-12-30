package estgf.ipp.pt.cmu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import estgf.ipp.pt.cmu.Database.Controllers.DBController;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetMeal;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetMeals;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyRemoveMeal;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.Meal.MealsAdapter;
import estgf.ipp.pt.cmu.Utilities.DeleteDialog;
import estgf.ipp.pt.cmu.Utilities.OnMealSelectedListener;

public class DailyMeals extends AppCompatActivity implements OnMealSelectedListener,NotifyGetMeal,NotifyGetMeals,NotifyRemoveMeal {

    private MealsAdapter adapter;
    private Context context = this;
    private DBController dbController;
    private int weeksDayID;
    private Meal temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_meals);

        dbController = new DBController(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // ArrayList<Meal> list = (ArrayList<Meal>)getIntent().getSerializableExtra("meals");
         weeksDayID = getIntent().getIntExtra("weeksDayID",-1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(context,MealRegister.class);
              intent.putExtra("weeksDayID",weeksDayID);
              context.startActivity(intent);
            }
        });

        this.adapter = new MealsAdapter(this,this);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_Daily_Meals);

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
        this.dbController.getMeals(this,(long)weeksDayID);
    }

    @Override
    public void onMealSelected(Meal meal) {
        DBController dbController = new DBController(this);
        dbController.getMeal(this,meal.getId());
    }

    @Override
    public void onMealLongPressed(Meal meal) {
        temp=meal;
        DeleteDialog deleteDialog = new DeleteDialog();
        deleteDialog.setListener(this);
        deleteDialog.show(getSupportFragmentManager(),"delete_dialog");
    }

    @Override
    public void GetMeal(Meal meal) {
        Intent intent = new Intent(context, MealActivity.class);
        intent.putExtra("meal",meal);
        startActivity(intent);
    }

    @Override
    public void OnGetMeals(List<Meal> list) {
        adapter.setItems(list);
    }

    @Override
    public void OnRemoveMeal() {
        this.dbController.removeMeal(temp);
        this.adapter.removeItem(temp);
    }
}
