package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import java.util.Calendar;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;
import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Database.DAO.WeeksDaysDAO;

public class InsertWeeksDays extends AsyncTask<WeeksDays,Void,Void> {

    private final WeeksDaysDAO dao;
    private final MealDAO mealDAO;

    public InsertWeeksDays(WeeksDaysDAO dao,
    MealDAO mealDAO){
        this.dao=dao;
        this.mealDAO=mealDAO;
    }


    @Override
    protected Void doInBackground(WeeksDays... weeksDays) {
        long[] inserted = dao.insertWeeksDaysDAO(weeksDays);

       for(int i=0; i< inserted.length;i++){
           mealDAO.insert(generateListOfMeals(inserted[i]));
       }
        return null;
    }

    private Meal[] generateListOfMeals(long id){
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

        Meal array[] = new Meal[5];
        array[0]=new Meal("Breakfast",hourBreakfast,"Eat whole grains;\nLean protein;\nLow-fat dairy;\nFruits and vegetables;",0.25f,id);
        array[1]=new Meal("Snack",hourSnack,"Eat something with fiber and protein, also it should have low fat",0.10f,id);
        array[2]=new Meal("Lunch",hourLunch,"Eat Hunger-busting fiber to get energy;\nLean protein;\nSkip soda",0.30f,id);
        array[3]=new Meal("Afternoon Lunch",hourAfLunch,"Eat something with a lot of fiber and protein, also it should have low fat",0.15f,id);
        array[4]=new Meal("Dinner",hourDinner,"Choose a Lean Protein;\nChoose Your Grain or Starchy Vegetable;\nFill in the Blanks with vegetables, a fruit, and a serving of low-fat dairy, along with a protein and a grain",0.20f,id);
        return array;
    }
}
