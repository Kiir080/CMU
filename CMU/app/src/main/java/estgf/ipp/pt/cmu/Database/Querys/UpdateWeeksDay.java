package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Database.DAO.WeeksDaysDAO;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;

public class UpdateWeeksDay extends AsyncTask<WeeksDays,Void,Void> {
    private final WeeksDaysDAO weeksDaysDAO;
    private final  MealDAO mealDAO;

    public UpdateWeeksDay(WeeksDaysDAO weeksDaysDAO, MealDAO mealDAO) {
        this.weeksDaysDAO= weeksDaysDAO;
        this.mealDAO=mealDAO;
    }


    @Override
    protected Void doInBackground(WeeksDays... weeksDays) {

        int calories = mealDAO.getTotalCaloriesEaten(weeksDays[0].getId());
        weeksDays[0].setCaloriesEaten(calories);
        weeksDaysDAO.update(weeksDays[0]);
        return null;
    }
}
