package estgf.ipp.pt.cmu.Database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import estgf.ipp.pt.cmu.Entities.User.User;

@Dao
public interface UserDAO {
    @Insert
    public void insertUser(User user);

    @Query("SELECT * FROM User")
    public List<User> getUsers();

    @Update
    public void updateUser(User user);
}
