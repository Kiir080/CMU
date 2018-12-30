package estgf.ipp.pt.cmu.Database.Controllers;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;
import estgf.ipp.pt.cmu.Database.DAO.MealDAO;
import estgf.ipp.pt.cmu.Database.DAO.WeeksDaysDAO;

@Database(entities = {WeeksDays.class,Meal.class},version = 6)
public abstract class RoomDB extends RoomDatabase {

    public abstract WeeksDaysDAO dao();
    public abstract MealDAO mealDAO();
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

    @VisibleForTesting
    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };


    @VisibleForTesting
    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    @VisibleForTesting
    static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
}
