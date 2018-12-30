package estgf.ipp.pt.cmu.Database.Controllers;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import estgf.ipp.pt.cmu.Database.DAO.UserDAO;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.User.User;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;
import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Database.DAO.WeeksDaysDAO;

@Database(entities = {WeeksDays.class,Meal.class,User.class},version = 8)
public abstract class RoomDB extends RoomDatabase {

    public abstract WeeksDaysDAO dao();
    public abstract MealDAO mealDAO();
    public abstract UserDAO userDAO();
    private static  volatile RoomDB INSTANCE;

    public static RoomDB getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized(RoomDB.class){
                if(INSTANCE == null){
                    INSTANCE = android.arch.persistence.room.Room.databaseBuilder(
                            context.getApplicationContext(),RoomDB.class,"database")
                            //.addMigrations(MIGRATION_1_2,MIGRATION_2_3,MIGRATION_3_4)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return  INSTANCE;
    }

}
