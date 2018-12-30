package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetMeal;
import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;

public class GetMeal extends AsyncTask<Integer,Void,Meal> {

    private final MealDAO mealDAO;
    private final NotifyGetMeal notifyGetMeal;

    public GetMeal(MealDAO mealDAO, NotifyGetMeal notifyGetMeal) {
        this.mealDAO=mealDAO;
        this.notifyGetMeal=notifyGetMeal;
    }

    @Override
    protected Meal doInBackground(Integer... integers) {
        return mealDAO.getMeal(integers[0]);
    }

    @Override
    protected void onPostExecute(Meal meal) {
        notifyGetMeal.GetMeal(meal);
    }
}
