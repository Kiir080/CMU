package estgf.ipp.pt.cmu.APIControllers;

import java.util.List;

import estgf.ipp.pt.cmu.Entities.Result.Result;
import estgf.ipp.pt.cmu.Entities.Result.ResultList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Routes {

    @Headers({"X-Mashape-Key: P5DCpMxKSkmshSWsMQXyUv0eZsbMp18RwEijsn1Zc4Sg5BVzSz","X-Mashape-Host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"})
    @GET("/recipes/autocomplete")
    Call<List<Result>> autocompleteRecipes(@Query("query") String query, @Query("number") int number);

    @Headers({"X-Mashape-Key: P5DCpMxKSkmshSWsMQXyUv0eZsbMp18RwEijsn1Zc4Sg5BVzSz","X-Mashape-Host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"})
    @GET("food/ingredients/autocomplete")
    Call<List<Result>> autocompleteIngredients(@Query("query") String query, @Query("number") int number);

    @Headers({"X-Mashape-Key: P5DCpMxKSkmshSWsMQXyUv0eZsbMp18RwEijsn1Zc4Sg5BVzSz","X-Mashape-Host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"})
    @GET("/food/menuItems/suggest")
    Call<ResultList> autocompleteproducts(@Query("query") String query, @Query("number") int number);
}
