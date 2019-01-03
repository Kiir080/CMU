package estgf.ipp.pt.cmu.Utilities;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;

//Converter para Lista de Meal's
public class Converter {

        @TypeConverter
        public static List<Meal> fromMeal(String value) {
            if(value == null){
                return Collections.emptyList();
            }
            Type listType = new TypeToken<ArrayList<Meal>>() {}.getType();
            return new Gson().fromJson(value, listType);
        }
        @TypeConverter
        public static String fromList(List<Meal> list) {
            Gson gson = new Gson();
            return gson.toJson(list);
        }

}
