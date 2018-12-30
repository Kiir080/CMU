package estgf.ipp.pt.cmu.Database.Controllers;

import android.content.Context;

import java.util.List;

import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetMeals;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetWeeksDays;
import estgf.ipp.pt.cmu.Database.Querys.ClearDB;
import estgf.ipp.pt.cmu.Database.Querys.GetMeals;
import estgf.ipp.pt.cmu.Database.Querys.GetWeeksDays;
import estgf.ipp.pt.cmu.Database.Querys.InsertMeal;
import estgf.ipp.pt.cmu.Database.Querys.InsertWeeksDays;
import estgf.ipp.pt.cmu.Database.Querys.Update;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;
import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Database.DAO.WeeksDaysDAO;

public class DBController {

    private RoomDB roomDB;

    private WeeksDaysDAO weeksDaysDAO;
    private MealDAO mealDAO;

    public DBController(Context context){
        this.roomDB=RoomDB.getDatabase(context);
        weeksDaysDAO =roomDB.dao();
        mealDAO=roomDB.mealDAO();

    }


    public void insert(Meal... meal){
        new InsertMeal(mealDAO).execute(meal);
    }

    public void insert(WeeksDays ... weeksDays){
        new InsertWeeksDays(weeksDaysDAO,mealDAO).execute(weeksDays);
    }

    public List<WeeksDays> getWeeksDays(NotifyGetWeeksDays notifyGetWeeksDays){
        new GetWeeksDays(weeksDaysDAO,notifyGetWeeksDays).execute();
        return null;
    }

    public List<Meal> getMeals(NotifyGetMeals notifyGetMeals, Long id){
        new GetMeals(mealDAO,notifyGetMeals).execute(id);
        return null;
    }

    public Meal getMeal(NotifyGetMeal notifyGetMeal, int id){
        new GetMeal(mealDAO,notifyGetMeal).execute(id);
        return null;
    }

    public void clearTables(){
        new ClearDB(roomDB).execute();
    }

    public void update(Meal meal){
        new Update(mealDAO).execute(meal);
    }
}
