package estgf.ipp.pt.cmu.Database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;


import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MealDAO {

    @Insert(onConflict = REPLACE)
    public void insert(Meal... meals);

    @Query("SELECT * FROM Meal")
    public List<Meal> getMeals();

    @Query("SELECT * FROM Meal WHERE idWeeksDay == :id")
    public List<Meal> getMeals(long id);

    @Update
    public void update(Meal meal);

    @Query("SELECT * FROM Meal WHERE id == :id")
    public Meal getMeal(int id);

    @Query("SELECT SUM(caloriesEaten) as total FROM Meal WHERE idWeeksDay == :id")
    public int getTotalCaloriesEaten(int id);

    @Delete
    public void remove(Meal meal);
}
