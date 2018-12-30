package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;

public class RemoveMeal extends AsyncTask<Meal,Void,Void> {

    private MealDAO mealDAO;
    public RemoveMeal(MealDAO mealDAO) {
        this.mealDAO=mealDAO;
    }


    @Override
    protected Void doInBackground(Meal... meals) {
        this.mealDAO.remove(meals[0]);
        return null;
    }
}
