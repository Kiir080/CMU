package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Utilities.StaticHolder;

public class UpdateMeal extends AsyncTask<Meal, Void, Void> {
    private final MealDAO dao;

    public UpdateMeal(MealDAO dao) {
        this.dao = dao;
    }


    @Override
    protected Void doInBackground(Meal... meals) {
        dao.update(meals[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        StaticHolder.notifyToUpdateWeeksDay.notifyToUpdate();
    }
}
