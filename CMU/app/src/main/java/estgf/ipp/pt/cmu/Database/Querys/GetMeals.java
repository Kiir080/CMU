package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import java.util.List;

import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetMeals;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Database.DAO.MealDAO;

public class GetMeals extends AsyncTask<Long, Void, List<Meal>> {


    private final MealDAO mealDAO;
    private List<Meal> list;
    private NotifyGetMeals notifyGetMeals;

    public GetMeals(
            MealDAO mealDAO,NotifyGetMeals notifyGetMeals) {

        this.mealDAO = mealDAO;
        this.notifyGetMeals=notifyGetMeals;
    }


    @Override
    protected List<Meal> doInBackground(Long... longs) {
        if(longs==null || longs.length==0){
            return mealDAO.getMeals();
        }else{
            return mealDAO.getMeals(longs[0]);
        }

    }

    @Override
    protected void onPostExecute(List<Meal> meals) {
        notifyGetMeals.OnGetMeals(meals);
    }
}
