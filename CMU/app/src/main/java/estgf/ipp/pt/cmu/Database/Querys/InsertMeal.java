package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Database.DAO.MealDAO;

public class InsertMeal extends AsyncTask<Meal,Void,Void> {
    private final MealDAO dao;

    public InsertMeal(MealDAO dao) {
        this.dao=dao;
    }

    @Override
    protected Void doInBackground(Meal... meals) {
        dao.insert(meals);
        return null;
    }
}
