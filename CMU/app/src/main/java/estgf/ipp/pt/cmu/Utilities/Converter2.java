package estgf.ipp.pt.cmu.Utilities;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import estgf.ipp.pt.cmu.Entities.Food.Food;
import estgf.ipp.pt.cmu.Entities.Food.Ingredient;
import estgf.ipp.pt.cmu.Entities.Food.Product;
import estgf.ipp.pt.cmu.Entities.Food.Recipe;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Utilities.typeadapters.RuntimeTypeAdapterFactory;

public class Converter2 {

        private static final RuntimeTypeAdapterFactory<Food> typeFactory = RuntimeTypeAdapterFactory
            .of(Food.class, "type")
            .registerSubtype(Recipe.class, "recipe")
            .registerSubtype(Ingredient.class, "ingredient")
            .registerSubtype(Product.class,"product");

        @TypeConverter
        public static List<Food> fromMeal(String value) {
            if(value == null){
                return Collections.emptyList();
            }
            Type listType = new TypeToken<ArrayList<Food>>() {}.getType();
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory).create();

            return gson.fromJson(value, listType);
        }
        @TypeConverter
        public static String fromList(List<Food> list) {
           // Type listType = new TypeToken<ArrayList<Food>>() {}.getType();
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory).create();
            return gson.toJson(list);
        }

}



