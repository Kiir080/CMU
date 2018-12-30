package estgf.ipp.pt.cmu.Database.Interfaces;

import java.util.List;

import estgf.ipp.pt.cmu.Entities.User.User;

public interface NotifyGetUsers {
    public void OnGetUsers(List<User> userList);
}
