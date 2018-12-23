package estgf.ipp.pt.cmu.Querys;

import java.util.List;

import estgf.ipp.pt.cmu.APIControllers.APIController;
import estgf.ipp.pt.cmu.APIControllers.Routes;
import estgf.ipp.pt.cmu.Entities.Food.Ingredient;
import estgf.ipp.pt.cmu.Entities.Food.Recipe;
import estgf.ipp.pt.cmu.Entities.Result.IngredientResult;
import estgf.ipp.pt.cmu.Entities.Result.RecipeResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRecipeInformation {

    private Routes routes;
    private RecipeResult recipeResult;
    private Recipe recipe;


    public GetRecipeInformation(Recipe recipe) {
        this.routes = APIController.getRoutes();
        this.recipe = recipe;

    }


    public void execute() {
        Call<RecipeResult> call = routes.getRecipeInformation(recipe.getId(),true);
        call.enqueue(new Callback<RecipeResult>() {
            @Override
            public void onResponse(Call<RecipeResult> call, Response<RecipeResult> response) {
                if (response.body() != null) {
                    recipeResult = response.body();
                    recipe.setNutrition(recipeResult.getNutrition().getNutrients());
                    recipe.setDairyFree(recipeResult.isDairyFree());
                    recipe.setGlutenFree(recipeResult.isGlutenFree());
                    recipe.setVegan(recipeResult.isVegan());
                    recipe.setVegetarian(recipeResult.isVegetarian());
                }

            }

            @Override
            public void onFailure(Call<RecipeResult> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }

}