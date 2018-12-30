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
    private transient byte[] imageByteArray;
    private transient Bitmap image;
    public String type1;


    public Food(int id, Nutrition nutrition, String imagePath, String type) {
        this.id = id;
        this.nutrition = nutrition;
        this.imagePath = imagePath;
       // this.type1 = type;

    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type) {
        this.type1 = type;
    }

    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
    }


    public Bitmap getImage() {
        if(image == null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(this.imageByteArray, 0, this.imageByteArray.length);
            this.image = bitmap;
        }

        return image;
    }

    public void setImage(Drawable image) {
        //this.image = image;
        Bitmap bitmap = ((BitmapDrawable)image).getBitmap();
        this.image=bitmap;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        this.imageByteArray = stream.toByteArray();
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


  /*  @Nullable
    public Drawable loadImage() {
        try {
            InputStream is = (InputStream) new URL(this.baseURLRecipe).getContent();
            Drawable d = Drawable.createFromStream(is, "useless");
            return d;
        } catch (Exception e) {
            return null;
        }

    }*/
}
