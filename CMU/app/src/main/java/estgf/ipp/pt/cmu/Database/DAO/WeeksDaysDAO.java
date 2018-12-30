package estgf.ipp.pt.cmu.Database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface WeeksDaysDAO {
    @Insert(onConflict = REPLACE)
    public long[] insertWeeksDaysDAO(WeeksDays ... weeksDays);

    @Query("SELECT * FROM WeeksDays")
    List<WeeksDays> getWeeksDays();

    @Update
    public void update(WeeksDays weeksDays);
}
