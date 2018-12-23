package estgf.ipp.pt.cmu.Entities.Result;


import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import java.io.InputStream;
import java.net.URL;

public class Result {

    private ResultType type;
    public int id;
    public String title;
    public String image;
    public String name;
    private String baseURLRecipe = "https://spoonacular.com/recipeImages/{id}-312x231.jpg";
    private String baseURLIngredients = "https://spoonacular.com/cdn/ingredients_100x100/{name}.jpg";
    private String baseURLProdcuts ="https://spoonacular.com/productImages/{id}-312x231.jpg";

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



    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(ResultType type) {
        this.type = type;
    }

     public ResultType getType() {
        return type;
    }

}





