package estgf.ipp.pt.cmu.Utilities;

import estgf.ipp.pt.cmu.Entities.Food.Food;
import estgf.ipp.pt.cmu.Entities.Food.Ingredient;
import estgf.ipp.pt.cmu.Entities.Food.Product;
import estgf.ipp.pt.cmu.Entities.Food.Recipe;

public interface OnFoodSelectedListener {
    public void onFoodSelected(Ingredient ingredient);
    public void onFoodSelected(Product product);
    public void onFoodSelected(Recipe recipe);
    public void onRemoveFood(Food food);


}
