package estgf.ipp.pt.cmu.Entities.Result;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import estgf.ipp.pt.cmu.Entities.Food.Nutrition;

public class ProductResult {

    private int id;
    private String title;
    private NutritionResult nutrition;
    private List<String> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NutritionResult getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutritionResult nutrition) {
        this.nutrition = nutrition;
    }


    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
