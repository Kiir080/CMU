package estgf.ipp.pt.cmu.API.Querys;

import estgf.ipp.pt.cmu.API.Controllers.APIController;
import estgf.ipp.pt.cmu.API.Routes;
import estgf.ipp.pt.cmu.Database.Controllers.DBController;
import estgf.ipp.pt.cmu.Entities.Food.Recipe;
import estgf.ipp.pt.cmu.Entities.Result.RecipeResult;
import estgf.ipp.pt.cmu.SaveCurrentMeal;
import estgf.ipp.pt.cmu.Utilities.NotifyGetFoodInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRecipeInformation {

    private final NotifyGetFoodInformation notifyGetFoodInformation;
    private Routes routes;
    private RecipeResult recipeResult;
    private Recipe recipe;


    public GetRecipeInformation(Recipe recipe, NotifyGetFoodInformation notifyGetFoodInformation) {
        this.routes = APIController.getRoutes();
        this.recipe = recipe;
        this.notifyGetFoodInformation=notifyGetFoodInformation;

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

                    notifyGetFoodInformation.OnGetFoodInformation(recipe);
                }

            }

            @Override
            public void onFailure(Call<RecipeResult> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }

}