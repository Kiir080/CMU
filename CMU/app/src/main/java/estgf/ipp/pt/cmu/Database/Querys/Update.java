package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;

public class Update extends AsyncTask<Meal, Void, Void> {
    private final MealDAO dao;

    public Update(MealDAO dao) {
        this.dao = dao;
    }


    @Override
    protected Void doInBackground(Meal... meals) {
        dao.update(meals[0]);
        return null;
    }
}
