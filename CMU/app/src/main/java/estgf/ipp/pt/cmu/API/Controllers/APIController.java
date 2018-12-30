package estgf.ipp.pt.cmu.API.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import estgf.ipp.pt.cmu.API.Routes;
import estgf.ipp.pt.cmu.Utilities.GsonHelper;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIController  {

    private static Retrofit retrofit;
    private static Gson gson;
    private static final String BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/";
    private static Routes routes;

    public static Routes getRoutes() {

        if(retrofit == null && gson == null){
            gson = new GsonBuilder()
                    .setLenient()
                    .registerTypeHierarchyAdapter(byte[].class,new GsonHelper.ByteArrayToBase64TypeAdapter())
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            routes = retrofit.create(Routes.class);

        }

        return routes;

    }


}