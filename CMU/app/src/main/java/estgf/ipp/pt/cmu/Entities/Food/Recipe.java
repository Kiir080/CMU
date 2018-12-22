package estgf.ipp.pt.cmu.Entities.Food;

import java.io.Serializable;

public class Recipe extends Food implements Serializable {

    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private String diaryFree;
    private String title;

    public Recipe(int id, Nutrition nutrition, String imagePath, boolean vegetarian, boolean vegan, boolean glutenFree, String diaryFree, String title) {
        super(id, nutrition, imagePath);
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.diaryFree = diaryFree;
        this.title = title;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public String getDiaryFree() {
        return diaryFree;
    }

    public void setDiaryFree(String diaryFree) {
        this.diaryFree = diaryFree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
