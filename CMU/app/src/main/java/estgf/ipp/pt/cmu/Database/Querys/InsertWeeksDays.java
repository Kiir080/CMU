package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;
import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Database.DAO.WeeksDaysDAO;
import estgf.ipp.pt.cmu.Utilities.StaticHolder;

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
        List<Meal>list= StaticHolder.getDefaultMealList();
        Meal array[] = new Meal[list.size()];

        for (Meal meal:
             list) {
            meal.setIdWeeksDay(id);
        }
        return list.toArray(array);
    }
}
