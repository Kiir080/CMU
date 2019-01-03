package estgf.ipp.pt.cmu.Entities.Meal;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import estgf.ipp.pt.cmu.Entities.Food.Food;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;
import estgf.ipp.pt.cmu.Utilities.Converter2;
import estgf.ipp.pt.cmu.Utilities.StaticHolder;

@Entity( foreignKeys = @ForeignKey(entity = WeeksDays.class,
        parentColumns = "id",
        childColumns = "idWeeksDay"),indices = {@Index("idWeeksDay")})
public class Meal implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private long idWeeksDay;
    private String Name;
    @TypeConverters(Converter2.class)
    private List<Food> foodList;
    private int caloriesEaten;
    @Ignore
    private Calendar timeCal;
    private String time;

    private Float recommendedCalories;
    private String guidelines;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdWeeksDay() {
        return idWeeksDay;
    }

    public void setIdWeeksDay(long idWeeksDay) {
        this.idWeeksDay = idWeeksDay;
    }

    public Meal(){

    }

    public Meal(String name, Calendar time, String guidelines, Float recommendedCalories,long idWeeksDay){
        this.idWeeksDay=idWeeksDay;
        this.Name=name;
        this.timeCal=time;
        foodList=new ArrayList<>();
        DateFormat format = new SimpleDateFormat("HH:mm",Locale.ENGLISH);
        this.time= format.format(timeCal.getTime());
        this.guidelines=guidelines;
        this.recommendedCalories=recommendedCalories;
    }

    @Ignore
    public Meal(String name, Calendar time, String guidelines, Float recommendedCalories){
        this.Name=name;
        this.timeCal=time;
        foodList=new ArrayList<>();
        DateFormat format = new SimpleDateFormat("HH:mm",Locale.ENGLISH);
        this.time= format.format(timeCal.getTime());
        this.guidelines=guidelines;
        this.recommendedCalories=recommendedCalories;
    }

    public Float getRecommendedCalories() {
        return recommendedCalories;
    }

    public String RecommendedCalories() {
        int calories = (int) (StaticHolder.maxCaloriesDay* recommendedCalories);
        return String.valueOf(calories);
    }

    public void setRecommendedCalories(Float recommendedCalories) {
        this.recommendedCalories = recommendedCalories;
    }

    public String getGuidelines() {
        return guidelines;
    }

    public void setGuidelines(String guidelines) {
        this.guidelines = guidelines;
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

    public void calculateCaloriesEaten(){
        int count =0;
        for (Food pos:
             foodList) {
            count += pos.getNutrition().getCalories();
        }

        this.caloriesEaten=count;
    }
}
