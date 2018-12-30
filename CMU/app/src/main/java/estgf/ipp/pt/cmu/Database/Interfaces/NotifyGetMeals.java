package estgf.ipp.pt.cmu.Database.Interfaces;

import java.util.List;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;

public interface NotifyGetMeals {
    public void OnGetMeals(List<Meal> list);
}
