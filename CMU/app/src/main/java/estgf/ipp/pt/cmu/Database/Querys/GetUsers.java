package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import java.util.List;

import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetUsers;
import estgf.ipp.pt.cmu.Database.DAO.UserDAO;
import estgf.ipp.pt.cmu.Entities.User.User;

public class GetUsers extends AsyncTask<Void,Void,List<User>> {
    private final UserDAO userDAO;
    private final NotifyGetUsers notifyGetUsers;

    public GetUsers(UserDAO userDAO, NotifyGetUsers notifyGetUsers) {
        this.userDAO=userDAO;
        this.notifyGetUsers = notifyGetUsers;
    }

    @Override
    protected List<User> doInBackground(Void... voids) {
        return this.userDAO.getUsers();
    }

    @Override
    protected void onPostExecute(List<User> users) {
        this.notifyGetUsers.OnGetUsers(users);
    }
}
