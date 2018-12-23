package estgf.ipp.pt.cmu.Entities.Food;

import java.io.Serializable;

public class Product extends Food implements Serializable {
    private String title;

    private static String baseUrl="https://spoonacular.com/productImages/{id}-312x231.jpg";

    public Product(int id,String title){
        super(id,null,baseUrl.replace("{id}",Integer.toString(id)));
        this.title = title;
    }

    public Product(int id, String title, Nutrition nutrition,String imagePath) {
        super(id,nutrition,imagePath);
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
