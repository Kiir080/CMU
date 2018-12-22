package estgf.ipp.pt.cmu.APIControllers;

import java.util.List;

import estgf.ipp.pt.cmu.Entities.Food.Ingredient;
import estgf.ipp.pt.cmu.Entities.Result.IngredientResult;
import estgf.ipp.pt.cmu.Entities.Result.ProductResult;
import estgf.ipp.pt.cmu.Entities.Result.RecipeResult;
import estgf.ipp.pt.cmu.Entities.Result.Result;
import estgf.ipp.pt.cmu.Entities.Result.ResultList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Routes {

    @Headers({"X-Mashape-Key: bB6yVjQcU7mshGeZbh2nd501GPwvp1pG7eEjsngbv2qLVuX97i"})
    @GET("/recipes/autocomplete")
    Call<List<Result>> autocompleteRecipes(@Query("query") String query, @Query("number") int number);

    @Headers({"X-Mashape-Key: bB6yVjQcU7mshGeZbh2nd501GPwvp1pG7eEjsngbv2qLVuX97i"})
    @GET("food/ingredients/autocomplete")
    Call<List<Result>> autocompleteIngredients(@Query("query") String query, @Query("number") int number);

    @Headers({"X-Mashape-Key: bB6yVjQcU7mshGeZbh2nd501GPwvp1pG7eEjsngbv2qLVuX97i"})
    @GET("/food/products/suggest")
    Call<ResultList> autocompleteproducts(@Query("query") String query, @Query("number") int number);

    @Headers({"X-Mashape-Key: bB6yVjQcU7mshGeZbh2nd501GPwvp1pG7eEjsngbv2qLVuX97i"})
    @GET("/recipes/{id}/information")
    Call<RecipeResult> getRecipeInformation(@Path(value = "id", encoded = true) int id, @Query("includeNutrition") boolean nutrition);

    @Headers({"X-Mashape-Key: bB6yVjQcU7mshGeZbh2nd501GPwvp1pG7eEjsngbv2qLVuX97i"})
    @GET("/food/products/{id}")
    Call<ProductResult> getProductInformation(@Path(value = "id", encoded = true) int id);

    @Headers({"X-Mashape-Key: bB6yVjQcU7mshGeZbh2nd501GPwvp1pG7eEjsngbv2qLVuX97i"})
    @GET("/food/ingredients/{id}/information")
    Call<IngredientResult> getIngredientInformation(@Path(value = "id", encoded = true) int id,@Query("amount") int amount,@Query("unit") String unit);
}
