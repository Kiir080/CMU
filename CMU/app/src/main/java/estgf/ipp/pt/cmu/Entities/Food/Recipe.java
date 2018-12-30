package estgf.ipp.pt.cmu.Entities.Food;

import java.io.Serializable;


public class Recipe extends Food implements Serializable {

    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private String title;


    private static String baseUrl="https://spoonacular.com/recipeImages/{id}-312x231.jpg";

    public Recipe(int id,String title){
        super(id,null,baseUrl.replace("{id}",Integer.toString(id)),"recipe");
        this.title = title;
    }

    public Recipe(int id, Nutrition nutrition, String imagePath, boolean vegetarian, boolean vegan, boolean glutenFree, boolean diaryFree, String title) {
        super(id, nutrition, imagePath,"recipe");
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.title = title;
        this.dairyFree=diaryFree;
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

    public boolean isDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
