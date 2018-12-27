package estgf.ipp.pt.cmu.Entities.Meal;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import estgf.ipp.pt.cmu.Entities.Food.Food;

public class Meal implements Serializable {

    private String Name;
    private List<Food> foodList;
    private int caloriesEaten;
    private Calendar timeCal;
    private String time;

    public Meal(String name,Calendar time){
        this.Name=name;
        this.timeCal=time;
        foodList=new ArrayList<>();
        DateFormat format = new SimpleDateFormat("HH:mm",Locale.ENGLISH);
        this.time= format.format(timeCal.getTime());
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public int getCaloriesEaten() {
        return caloriesEaten;
    }

    public void setCaloriesEaten(int caloriesEaten) {
        this.caloriesEaten = caloriesEaten;
    }
}
