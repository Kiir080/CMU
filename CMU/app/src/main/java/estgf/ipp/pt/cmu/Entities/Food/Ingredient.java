package estgf.ipp.pt.cmu.Entities.Food;

import java.io.Serializable;
import java.util.List;

public class Ingredient extends Food implements Serializable {

    private String name;
    private String unit;
    private int amount;


    public Ingredient(int id, String name, String unit, int amount, Nutrition nutrition,String imagePath) {
        super(id,nutrition,imagePath);
        this.name = name;
        this.unit = unit;
        this.amount = amount;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
