package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import estgf.ipp.pt.cmu.Database.DAO.UserDAO;
import estgf.ipp.pt.cmu.Entities.User.User;

public class UpdateUser extends AsyncTask<User,Void,Void> {

    private UserDAO userDAO;
    public UpdateUser(UserDAO userDAO) {
        this.userDAO=userDAO;
    }

    @Override
    protected Void doInBackground(User... users) {
        this.userDAO.updateUser(users[0]);
        return null;
    }
}
