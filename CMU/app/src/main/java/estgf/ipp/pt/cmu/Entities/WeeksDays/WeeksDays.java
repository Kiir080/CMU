package estgf.ipp.pt.cmu.Entities.WeeksDays;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
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
}
