package estgf.ipp.pt.cmu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import java.util.List;

import estgf.ipp.pt.cmu.Database.Controllers.DBController;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetUsers;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.User.User;
import estgf.ipp.pt.cmu.Utilities.MaxCaloriesDay;
import estgf.ipp.pt.cmu.Utilities.StaticHolder;

public class MainActivity extends FragmentActivity implements Menu.OnFragmentSelectedListener,NotifyGetUsers {

    private Menu mMenu;
    private MarkActivity mMarkActivity;
    private StartActivity mStartActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBController dbController = new DBController(this);
        dbController.getUsers(this);

        if (findViewById(R.id.main_container) != null) {
            if (savedInstanceState != null)
                return;

            mMenu = new Menu();
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, mMenu).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
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
    public void OnMarkActivity(MarkActivity markActivity) {
        mMarkActivity = (MarkActivity) getSupportFragmentManager().findFragmentById(R.id.b_fragment);
        mMarkActivity = new MarkActivity();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, mMarkActivity);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void OnStartActivity(StartActivity startActivity) {
        mStartActivity = (StartActivity) getSupportFragmentManager().findFragmentById(R.id.c_fragment);
        mStartActivity = new StartActivity();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, mStartActivity);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void OnGetUsers(List<User> userList) {
        if(userList== null || userList.size()==0){
          Intent intent = new Intent(this,Register.class);
          startActivity(intent);
        }else{
            MaxCaloriesDay.maxCaloriesDay=userList.get(0).getMaxCalories();
            if(userList.get(0).getADDED_DEFAULT_MEALS() != null){
                for (Meal meal:
                        userList.get(0).getADDED_DEFAULT_MEALS()) {
                    StaticHolder.addMealToDefault(meal);
                }
            }

        }
    }
}
