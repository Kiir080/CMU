package estgf.ipp.pt.cmu.Entities.Result;

import java.util.List;

public class IngredientResult  {
    private int id;
    private String name;
    private String unit;
    private int amount;
    private NutrientsListResult nutrition;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public NutrientsListResult getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutrientsListResult nutrition) {
        this.nutrition = nutrition;
    }
}
