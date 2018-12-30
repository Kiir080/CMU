package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import estgf.ipp.pt.cmu.Database.DAO.UserDAO;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.User.User;

public class AddDefaultMealOnUser extends AsyncTask<Meal,Void,Void> {

    private UserDAO userDAO;
    public AddDefaultMealOnUser(UserDAO userDAO) {
        this.userDAO=userDAO;
    }


    @Override
    protected Void doInBackground(Meal... meals) {
        User user = userDAO.getUsers().get(0);
        if(user.getADDED_DEFAULT_MEALS() == null){
            List<Meal> list = new ArrayList<>();
            list.add(meals[0]);
            user.setADDED_DEFAULT_MEALS(list);
        }else{
            user.getADDED_DEFAULT_MEALS().add(meals[0]);
        }
        userDAO.updateUser(user);
        return null;
    }
}
