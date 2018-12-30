package estgf.ipp.pt.cmu.Entities.User;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.List;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Utilities.Converter;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String username;
    private int age;
    private String gender;
    private int maxCalories;
    private String activity;
    private Float height;
    private int weight;
    @TypeConverters(Converter.class)
    private List<Meal> ADDED_DEFAULT_MEALS;

    @Ignore
    public User(){
        this.username="default";
    }

    public User(@NonNull String username, int age, String gender, int maxCalories, String activity, Float height, int weight) {
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.maxCalories = maxCalories;
        this.activity = activity;
        this.height = height;
        this.weight = weight;
    }

    public String getUsername() {

        return username;
    }

    public List<Meal> getADDED_DEFAULT_MEALS() {
        return ADDED_DEFAULT_MEALS;
    }

    public void setADDED_DEFAULT_MEALS(List<Meal> ADDED_DEFAULT_MEALS) {
        this.ADDED_DEFAULT_MEALS = ADDED_DEFAULT_MEALS;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(int maxCalories) {
        this.maxCalories = maxCalories;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
