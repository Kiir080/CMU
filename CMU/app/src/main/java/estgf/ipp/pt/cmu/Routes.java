package estgf.ipp.pt.cmu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Routes {

    @Headers({"X-Mashape-Key: P5DCpMxKSkmshSWsMQXyUv0eZsbMp18RwEijsn1Zc4Sg5BVzSz","X-Mashape-Host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"})
    @GET("/recipes/autocomplete")
    Call<List<RecipeResult>> autocompleteRecipes(@Query("query") String query,@Query("number") int number);

    @Headers({"X-Mashape-Key: P5DCpMxKSkmshSWsMQXyUv0eZsbMp18RwEijsn1Zc4Sg5BVzSz","X-Mashape-Host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"})
    @GET("food/ingredients/autocomplete")
    Call<List<RecipeResult>> autocompleteIngredients(@Query("query") String query,@Query("number") int number);

    @Headers({"X-Mashape-Key: P5DCpMxKSkmshSWsMQXyUv0eZsbMp18RwEijsn1Zc4Sg5BVzSz","X-Mashape-Host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"})
    @GET("/food/menuItems/suggest")
    Call<List<RecipeResult>> autocompleteproducts(@Query("query") String query,@Query("number") int number);
}
