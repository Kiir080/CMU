package estgf.ipp.pt.cmu.Entities.Food;

import java.io.Serializable;

public class Nutrition implements Serializable {
    private int calories;
    private String fat;
    private String protein;
    private String carbs;

    public Nutrition(int calories, String fat, String protein, String carbs) {
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }
}
