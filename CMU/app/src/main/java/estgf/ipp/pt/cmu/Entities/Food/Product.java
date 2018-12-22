package estgf.ipp.pt.cmu.Entities.Food;

import java.io.Serializable;

public class Product extends Food implements Serializable {
    private String title;
    private String servingSize;



    public Product(int id, String title, String servingSize, Nutrition nutrition,String imagePath) {
        super(id,nutrition,imagePath);
        this.title = title;
        this.servingSize = servingSize;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }


}
