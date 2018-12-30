package estgf.ipp.pt.cmu.API.Querys;

import android.content.Context;

import java.util.List;

import estgf.ipp.pt.cmu.API.Controllers.APIController;
import estgf.ipp.pt.cmu.API.Routes;
import estgf.ipp.pt.cmu.Entities.Food.Ingredient;
import estgf.ipp.pt.cmu.Entities.Result.IngredientResult;
import estgf.ipp.pt.cmu.Utilities.NotifyGetFoodInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetIngredientInformation {

    private final NotifyGetFoodInformation notifyGetFoodInformation;
    private Context context;
    private Routes routes;
    private IngredientResult ingredientResult;
    private Ingredient ingredient;


    public GetIngredientInformation(Ingredient ingredient, NotifyGetFoodInformation notifyGetFoodInformation) {
        this.routes = APIController.getRoutes();
        this.ingredient = ingredient;
        this.notifyGetFoodInformation=notifyGetFoodInformation;


    }


    public void execute() {
        Call<List<IngredientResult>> call = routes.getIngredientInformation(1,ingredient.getName(),true);
        call.enqueue(new Callback<List<IngredientResult>>() {
            @Override
            public void onResponse(Call<List<IngredientResult>> call, Response<List<IngredientResult>> response) {
                if (response.body().size() > 0) {
                    ingredientResult = response.body().get(0);
                    ingredient.setAmount(ingredientResult.getAmount());
                    ingredient.setUnit(ingredientResult.getUnit());
                    ingredient.setNutrition(ingredientResult.getNutrition().getNutrients());
                    ingredient.setId(ingredientResult.getId());

                    notifyGetFoodInformation.OnGetFoodInformation(ingredient);
                }

            }

            @Override
            public void onFailure(Call<List<IngredientResult>> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }

}