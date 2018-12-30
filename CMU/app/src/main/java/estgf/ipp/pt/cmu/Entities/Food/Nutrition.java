package estgf.ipp.pt.cmu.Entities.Food;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.widget.Switch;

import java.io.Serializable;
import java.util.List;

import estgf.ipp.pt.cmu.Entities.Result.NutrientResult;

@Entity
public class Nutrition implements Serializable {
    private float calories;
    private float fat;
    private float protein;
    private float carbs;
    private int allSet = 0;


    public Nutrition(float calories, float fat, float protein, float carbs) {
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
    }


    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }
}
