package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import estgf.ipp.pt.cmu.Database.DAO.UserDAO;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyInsertUser;
import estgf.ipp.pt.cmu.Entities.User.User;

public class InsertUser extends AsyncTask<User,Void,Void> {
    private final UserDAO userDAO;
    private NotifyInsertUser notifyInsertUser;

    public InsertUser(UserDAO userDAO, NotifyInsertUser notifyInsertUser) {
        this.userDAO=userDAO;
        this.notifyInsertUser=notifyInsertUser;
    }

    @Override
    protected Void doInBackground(User... users) {
        this.userDAO.insertUser(users[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        notifyInsertUser.OnInsertUser();
    }
}
