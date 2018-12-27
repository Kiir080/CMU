package estgf.ipp.pt.cmu.Entities.WeeksDays;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;

public class WeeksDays {
    private Calendar date;
    private List<Meal> meals;
    private int caloriesConsumed;
    private int caloriesEaten;
    private String dayOfWeek;
    private String dateFormat;

    public WeeksDays(Calendar date) {
        this.date = date;
        this.meals= new ArrayList<>();
        initializeDate();
        generateListOfMeals();



    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public int getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(int caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

    public int getCaloriesEaten() {
        return caloriesEaten;
    }

    public void setCaloriesEaten(int caloriesEaten) {
        this.caloriesEaten = caloriesEaten;
    }

    private void generateListOfMeals(){
        Calendar hourBreakfast = Calendar.getInstance();
        hourBreakfast.set(Calendar.HOUR_OF_DAY,8);
        hourBreakfast.set(Calendar.MINUTE,30);

        Calendar hourSnack = Calendar.getInstance();
        hourSnack.set(Calendar.HOUR_OF_DAY,10);
        hourSnack.set(Calendar.MINUTE,30);

        Calendar hourLunch = Calendar.getInstance();
        hourLunch.set(Calendar.HOUR_OF_DAY,13);
        hourLunch.set(Calendar.MINUTE,30);

        Calendar hourAfLunch = Calendar.getInstance();
        hourAfLunch.set(Calendar.HOUR_OF_DAY,16);
        hourAfLunch.set(Calendar.MINUTE,30);

        Calendar hourDinner = Calendar.getInstance();
        hourDinner.set(Calendar.HOUR_OF_DAY,20);
        hourDinner.set(Calendar.MINUTE,0);


        meals.add(new Meal("Breakfast",hourBreakfast));
        meals.add(new Meal("Snack",hourSnack));
        meals.add(new Meal("Lunch",hourLunch));
        meals.add(new Meal("Afternoon Lunch",hourAfLunch));
        meals.add(new Meal("Dinner",hourDinner));
    }

    private void initializeDate(){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat= format.format(date.getTime());
        switch(date.get(Calendar.DAY_OF_WEEK)){
            case 1:
                dayOfWeek="Sunday";
                break;
            case 2:
                dayOfWeek="Monday";
                break;
            case 3:
                dayOfWeek="Tuesday";
                break;
            case 4:
                dayOfWeek="Wednesday";
                break;
            case 5:
                dayOfWeek="Thursday";
                break;
            case 6:
                dayOfWeek="Friday";
                break;
            case 7:
                dayOfWeek="Saturday";
                break;
        }
    }
}
