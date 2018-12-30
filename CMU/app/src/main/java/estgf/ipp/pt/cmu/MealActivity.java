package estgf.ipp.pt.cmu;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import estgf.ipp.pt.cmu.Database.Controllers.DBController;
import estgf.ipp.pt.cmu.Entities.Food.Food;
import estgf.ipp.pt.cmu.Entities.Food.Ingredient;
import estgf.ipp.pt.cmu.Entities.Food.Product;
import estgf.ipp.pt.cmu.Entities.Food.Recipe;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.Result.Result;
import estgf.ipp.pt.cmu.Utilities.NotifyGetFoodInformation;
import estgf.ipp.pt.cmu.Utilities.OnFoodAdded;
import estgf.ipp.pt.cmu.Utilities.OnFoodSelectedListener;
import estgf.ipp.pt.cmu.Utilities.OnResultSelectedListener;

public class MealActivity extends AppCompatActivity  implements OnResultSelectedListener, OnFoodSelectedListener , NotifyGetFoodInformation {
    private MealFragment mealFragment;
    private IngredientFragment ingredientFragment;
    private ProductFragment productFragment;
    private RecipeFragment recipeFragment;
    private Meal meal;
    private OnFoodAdded onFoodAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        if(findViewById(R.id.fragmentPlaceholder)!=null){
            if(savedInstanceState != null)
                return;
        }
        meal = (Meal) getIntent().getSerializableExtra("meal");


        mealFragment= new MealFragment();
        mealFragment.updateMeal(meal);
        onFoodAdded= mealFragment.getListener();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentPlaceholder,mealFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this,UserInformation.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResultSelected(Result result) {

        mealFragment.updateList(result);
    }

    @Override
    public void onFoodSelected(Ingredient ingredient) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ingredientFragment= new IngredientFragment();
        ingredientFragment.addData(ingredient);
        fragmentTransaction.replace(R.id.fragmentPlaceholder,ingredientFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onFoodSelected(Product product) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        productFragment= new ProductFragment();
        productFragment.addData(product);
        fragmentTransaction.replace(R.id.fragmentPlaceholder,productFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onFoodSelected(Recipe recipe) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        recipeFragment= new RecipeFragment();
        recipeFragment.addData(recipe);
        fragmentTransaction.replace(R.id.fragmentPlaceholder,recipeFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onRemoveFood(Food food) {
        this.meal.getFoodList().remove(food);
        this.meal.calculateCaloriesEaten();
        DBController dbController = new DBController(this);
        dbController.updateMeal(this.meal);
        onFoodAdded.notifySumCalories(this.meal.getCaloriesEaten());
    }


    @Override
    public void OnGetFoodInformation(Food food) {
        this.meal.getFoodList().add(food);
        this.meal.calculateCaloriesEaten();
        DBController dbController = new DBController(this);
        dbController.updateMeal(this.meal);
        onFoodAdded.notifySumCalories(this.meal.getCaloriesEaten());
    }

}
