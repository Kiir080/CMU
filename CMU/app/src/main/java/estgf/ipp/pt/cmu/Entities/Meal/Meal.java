package estgf.ipp.pt.cmu.Entities.Meal;

import java.util.List;

import estgf.ipp.pt.cmu.Entities.Food.Food;

public class Meal {

    public String Name;
    private List<Food> foodList;

    public Meal(String name){
        this.Name=name;

    }


}
