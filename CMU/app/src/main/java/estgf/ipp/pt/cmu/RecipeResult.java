package estgf.ipp.pt.cmu;


import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import java.io.InputStream;
import java.net.URL;

public class RecipeResult {

    private ResultType type;
    public int id;
    public String title;
    public String image;
    public String name;
    private String baseURL = "https://spoonacular.com/recipeImages/{id}-312x231.jpg";

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

    @Nullable
    public Drawable loadImage() {
        try {
            this.baseURL=this.baseURL.replace("{id}",String.valueOf(this.id));
            InputStream is = (InputStream) new URL(this.baseURL).getContent();
            Drawable d = Drawable.createFromStream(is, "useless");
            return d;
        } catch (Exception e) {
            return null;
        }

    }
    @Override
    public String toString() {
        return getTitle();
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





