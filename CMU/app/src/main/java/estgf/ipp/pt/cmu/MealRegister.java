package estgf.ipp.pt.cmu;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.Calendar;

import estgf.ipp.pt.cmu.Database.Controllers.DBController;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyInsertMeal;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Utilities.StaticHolder;

import static estgf.ipp.pt.cmu.MealRegister.TimePickerFragment.timeCal;

public class MealRegister extends AppCompatActivity implements View.OnClickListener,NotifyInsertMeal {

    private int id;
    private Button button;
    private EditText name;
    private EditText recomendedCalories;
    private EditText guidelines;
    private DBController dbController;
    private boolean addToAllWeeksDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_register);

        id = getIntent().getIntExtra("weeksDayID",-1);
        dbController = new DBController(this);
        button = (Button) findViewById(R.id.mealRegisterButton);
        button.setOnClickListener(this);
        name = (EditText) findViewById(R.id.mealName);
        recomendedCalories = (EditText) findViewById(R.id.mealRecommendCalories);
        guidelines =(EditText) findViewById(R.id.mealGuidelines);



    }

    @Override
    public void onClick(View v) {
        if(!name.getText().toString().matches("")
                && timeCal!=null
                && !recomendedCalories.getText().toString().matches("")
                && !guidelines.getText().toString().matches("")) {

            if(Float.parseFloat(recomendedCalories.getText().toString()) < 1f && Float.parseFloat(recomendedCalories.getText().toString())>0 ){
                Meal meal = new Meal(name.getText().toString(),timeCal,guidelines.getText().toString(),Float.parseFloat(recomendedCalories.getText().toString()),id);
                if(addToAllWeeksDays){
                    dbController.addMealToallWeeksDays(meal);
                    StaticHolder.addMealDefaultToUpdateOnUser(meal);
                    finish();
                }else{
                    dbController.insert(meal,this);
                }
            }else{
                recomendedCalories.setError("Recommended Calories must be between 0 and 1!");
            }

        }else{
            if(name.getText().toString().matches(""))
                name.setError( "Name is required!" );
            else if(recomendedCalories.getText().toString().matches(""))
                recomendedCalories.setError("Recommended Calories is required!");
            else if(timeCal == null){
                View contextView = findViewById(R.id.mealRegisterButton);
                Snackbar.make(contextView,getString(R.string.time_required),Snackbar.LENGTH_INDEFINITE).show();
            }

        }
    }

    public void showTimePickerDialog(View v){
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void OnInsertMeal() {
       /* Intent intent = new Intent(this,DailyMeals.class);
        intent.putExtra("weeksDayID",id);
        startActivity(intent);*/
       finish();
    }

    public void onCheckboxClicked(View view) {
        addToAllWeeksDays=true;
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        public static Calendar timeCal;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            timeCal= Calendar.getInstance();
            timeCal.set(Calendar.HOUR_OF_DAY,hourOfDay);
            timeCal.set(Calendar.MINUTE,minute);
        }
    }
}
