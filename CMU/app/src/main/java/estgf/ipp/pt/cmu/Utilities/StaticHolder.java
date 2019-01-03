package estgf.ipp.pt.cmu.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import estgf.ipp.pt.cmu.Database.Interfaces.NotifyToAddDefaultMealToUser;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyToUpdateWeeksDay;

//Classe com conteudo estático que permite guardar conteudo importante que se tornava dificil passar entre varias atividades ou fragmentos
public class StaticHolder{
    public static Meal meal;
    public static WeeksDays weeksDays;
    public static NotifyToUpdateWeeksDay notifyToUpdateWeeksDay;
    public static NotifyToAddDefaultMealToUser notifyToAddDefaultMealToUser;
    private static List<Meal> DEFAULT_MEALS = generateListOfMeals();
    public static int maxCaloriesDay=0;

    public static void addMealToDefault(Meal meal){
        DEFAULT_MEALS.add(meal);
    }

    public static void addMealDefaultToUpdateOnUser(Meal meal){
        notifyToAddDefaultMealToUser.OnAddDefaultMealToUser(meal);
        DEFAULT_MEALS.add(meal);
    }

    public static int DefaultMealListSize(){
        return DEFAULT_MEALS.size();
    }

    public static List<Meal> getDefaultMealList(){
        return DEFAULT_MEALS;
    }

    private static List<Meal> generateListOfMeals(){
        List<Meal> list= new ArrayList<>();
        Calendar hourBreakfast = Calendar.getInstance();
        hourBreakfast.set(Calendar.HOUR_OF_DAY,8);
        hourBreakfast.set(Calendar.MINUTE,30);

        Calendar hourSnack = Calendar.getInstance();
        hourSnack.set(Calendar.HOUR_OF_DAY,10);
        hourSnack.set(Calendar.MINUTE,30);

        Calendar hourLunch = Calendar.getInstance();
        hourLunch.set(Calendar.HOUR_OF_DAY,13);
        hourLunch.set(Calendar.MINUTE,30);

        Calendar hourAfLunch = Calendar.getInstance();
        hourAfLunch.set(Calendar.HOUR_OF_DAY,16);
        hourAfLunch.set(Calendar.MINUTE,30);

        Calendar hourDinner = Calendar.getInstance();
        hourDinner.set(Calendar.HOUR_OF_DAY,20);
        hourDinner.set(Calendar.MINUTE,0);


        list.add(new Meal("Breakfast",hourBreakfast,"Eat whole grains;\nLean protein;\nLow-fat dairy;\nFruits and vegetables;",0.25f));
        list.add(new Meal("Snack",hourSnack,"Eat something with fiber and protein, also it should have low fat",0.10f));
        list.add(new Meal("Lunch",hourLunch,"Eat Hunger-busting fiber to get energy;\nLean protein;\nSkip soda",0.30f));
        list.add(new Meal("Afternoon Lunch",hourAfLunch,"Eat something with a lot of fiber and protein, also it should have low fat",0.15f));
        list.add(new Meal("Dinner",hourDinner,"Choose a Lean Protein;\nChoose Your Grain or Starchy Vegetable;\nFill in the Blanks with vegetables, a fruit, and a serving of low-fat dairy, along with a protein and a grain",0.20f));
        return list;
    }

}
