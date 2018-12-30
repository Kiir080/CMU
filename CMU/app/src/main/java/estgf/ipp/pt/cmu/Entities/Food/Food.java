package estgf.ipp.pt.cmu.Entities.Food;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;

import estgf.ipp.pt.cmu.Entities.Result.NutrientResult;
import estgf.ipp.pt.cmu.Entities.Result.NutritionResult;

public class Food implements Serializable {
    private int id;
    private Nutrition nutrition;
    private String imagePath;
    private transient Drawable image;



    public Food(int id, Nutrition nutrition, String imagePath, String type) {
        this.id = id;
        this.nutrition = nutrition;
        this.imagePath = imagePath;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;

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

    public void setNutrition(NutritionResult nutrition) {
        this.nutrition = new Nutrition(nutrition.getCalories(),
                Float.valueOf(nutrition.getFat().replace("g","")),
                        Float.valueOf(nutrition.getProtein().replace("g","")),
                                Float.valueOf(nutrition.getCarbs().replace("g","")));
    }

    public void setNutrition(List<NutrientResult> nutrients) {
        float calories=0;
        float fat=0;
        float protein=0;
        float carbs=0;
        int allSet = 0;
        for (NutrientResult pos : nutrients) {
            if (allSet == 4) {
                break;
            } else {
                switch (pos.getTitle()) {
                    case "Calories":
                        calories = pos.getAmount();
                        allSet++;
                        break;
                    case "Fat":
                        fat = pos.getAmount();
                        allSet++;
                        break;
                    case "Protein":
                        protein = pos.getAmount();
                        allSet++;
                        break;
                    case "Carbohydrates":
                        carbs = pos.getPercentOfDailyNeeds();
                        allSet++;
                        break;
                }
            }
        }

        this.nutrition= new Nutrition(calories,fat,protein,carbs);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



}
