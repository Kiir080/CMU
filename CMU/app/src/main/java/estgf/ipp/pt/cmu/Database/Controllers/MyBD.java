package estgf.ipp.pt.cmu.Database.Controllers;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.User.User;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;
import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Database.DAO.WeeksDaysDAO;


@Database(entities = {WeeksDays.class,Meal.class,User.class},version = 1)
public abstract class MyBD extends RoomDatabase {
    public abstract WeeksDaysDAO dao();
    public abstract MealDAO mealDAO();

}