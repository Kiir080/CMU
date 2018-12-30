package estgf.ipp.pt.cmu.Database.Controllers;

import android.content.Context;

import java.util.List;

import estgf.ipp.pt.cmu.Database.DAO.UserDAO;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetMeal;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetMeals;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetUsers;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetWeeksDays;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyInsertMeal;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyInsertUser;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyToAddDefaultMealToUser;

import estgf.ipp.pt.cmu.Database.Querys.AddDefaultMealOnUser;
import estgf.ipp.pt.cmu.Database.Querys.ClearDB;
import estgf.ipp.pt.cmu.Database.Querys.GetMeal;
import estgf.ipp.pt.cmu.Database.Querys.GetMeals;
import estgf.ipp.pt.cmu.Database.Querys.GetUsers;
import estgf.ipp.pt.cmu.Database.Querys.GetWeeksDays;
import estgf.ipp.pt.cmu.Database.Querys.InsertMeal;
import estgf.ipp.pt.cmu.Database.Querys.InsertUser;
import estgf.ipp.pt.cmu.Database.Querys.InsertWeeksDays;
import estgf.ipp.pt.cmu.Database.Querys.RemoveMeal;
import estgf.ipp.pt.cmu.Database.Querys.UpdateMeal;
import estgf.ipp.pt.cmu.Database.Querys.UpdateUser;
import estgf.ipp.pt.cmu.Database.Querys.UpdateWeeksDay;
import estgf.ipp.pt.cmu.Database.Querys.AddMealToAllWeeksDays;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.User.User;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;
import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Database.DAO.WeeksDaysDAO;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyToUpdateWeeksDay;
import estgf.ipp.pt.cmu.Utilities.StaticHolder;

public class DBController implements NotifyToUpdateWeeksDay,NotifyToAddDefaultMealToUser {

    private RoomDB roomDB;

    private WeeksDaysDAO weeksDaysDAO;
    private MealDAO mealDAO;
    private UserDAO userDAO;

    public DBController(Context context){
        this.roomDB=RoomDB.getDatabase(context);
        weeksDaysDAO =roomDB.dao();
        mealDAO=roomDB.mealDAO();
        this.userDAO=roomDB.userDAO();
        StaticHolder.notifyToUpdateWeeksDay=this;
        StaticHolder.notifyToAddDefaultMealToUser=this;
    }


    public void insert(Meal... meal){
        new InsertMeal(mealDAO).execute(meal);
    }

    public void insert(WeeksDays ... weeksDays){
        new InsertWeeksDays(weeksDaysDAO,mealDAO).execute(weeksDays);
    }

    public void insert(User user, NotifyInsertUser notifyInsertUser){
        new InsertUser(userDAO,notifyInsertUser).execute(user);
    }

    public void insert(Meal meal, NotifyInsertMeal notifyInsertMeal){
        new InsertMeal(mealDAO,notifyInsertMeal).execute(meal);
    }

    public List<WeeksDays> getWeeksDays(NotifyGetWeeksDays notifyGetWeeksDays){
        new GetWeeksDays(weeksDaysDAO,notifyGetWeeksDays).execute();
        return null;
    }

    public List<Meal> getMeals(NotifyGetMeals notifyGetMeals, Long id){
        new GetMeals(mealDAO,notifyGetMeals).execute(id);
        return null;
    }

    public List<User> getUsers(NotifyGetUsers notifyGetUsers){
        new GetUsers(userDAO,notifyGetUsers).execute();
        return null;
    }

    public Meal getMeal(NotifyGetMeal notifyGetMeal, int id){
        new GetMeal(mealDAO,notifyGetMeal).execute(id);
        return null;
    }

    public void clearTables(){
        new ClearDB(roomDB).execute();
    }

    public void updateMeal(Meal meal){
        new UpdateMeal(mealDAO).execute(meal);
    }

    public void updateWeeksDays(WeeksDays weeksDays){
        new UpdateWeeksDay(weeksDaysDAO,mealDAO).execute(weeksDays);
    }

    public void updateUser(User user){
        new UpdateUser(userDAO).execute(user);
    }

    public void addMealToallWeeksDays(Meal meal){
        new AddMealToAllWeeksDays(mealDAO,weeksDaysDAO).execute(meal);
    }

    @Override
    public void notifyToUpdate() {
        updateWeeksDays(StaticHolder.weeksDays);
    }

    public void removeMeal(Meal meal){
        new RemoveMeal(mealDAO).execute(meal);
    }

    @Override
    public void OnAddDefaultMealToUser(Meal meal) {
        new AddDefaultMealOnUser(userDAO).execute(meal);
    }
}
