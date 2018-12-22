package estgf.ipp.pt.cmu.Entities.Food;

import java.io.Serializable;

public class Food implements Serializable {
    private int id;
    private Nutrition nutrition;
    private String imagePath;

    public Food(int id,Nutrition nutrition,String imagePath) {
        this.id = id;
        this.nutrition=nutrition;
        this.imagePath=imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
