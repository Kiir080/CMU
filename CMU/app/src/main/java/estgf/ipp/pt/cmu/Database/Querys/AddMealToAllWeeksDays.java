package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import java.util.List;

import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Database.DAO.WeeksDaysDAO;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;

public class AddMealToAllWeeksDays extends AsyncTask<Meal, Void, Void> {

    private MealDAO mealDAO;
    private WeeksDaysDAO weeksDaysDAO;

    public AddMealToAllWeeksDays(MealDAO mealDAO, WeeksDaysDAO weeksDaysDAO) {
        this.weeksDaysDAO = weeksDaysDAO;
        this.mealDAO = mealDAO;
    }

    @Override
    protected Void doInBackground(Meal... meals) {

        List<WeeksDays> list = this.weeksDaysDAO.getWeeksDays();
        for (WeeksDays pos :
                list) {
            meals[0].setIdWeeksDay(pos.getId());
            this.mealDAO.insert(meals[0]);
        }
        return null;
    }
}
