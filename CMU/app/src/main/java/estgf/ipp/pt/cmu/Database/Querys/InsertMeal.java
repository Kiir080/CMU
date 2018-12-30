package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import estgf.ipp.pt.cmu.Database.Interfaces.NotifyInsertMeal;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Database.DAO.MealDAO;

public class InsertMeal extends AsyncTask<Meal,Void,Void> {
    private final MealDAO dao;
    private NotifyInsertMeal notifyInsertMeal;

    public InsertMeal(MealDAO dao) {
        this.dao = dao;
    }

    public InsertMeal(MealDAO dao, NotifyInsertMeal notifyInsertMeal) {
        this.dao=dao;
        this.notifyInsertMeal=notifyInsertMeal;
    }

    @Override
    protected Void doInBackground(Meal... meals) {
        dao.insert(meals);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(notifyInsertMeal!= null){
            notifyInsertMeal.OnInsertMeal();
        }
    }
}
