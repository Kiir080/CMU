package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import java.util.List;

import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetWeeksDays;
import estgf.ipp.pt.cmu.Entities.WeeksDays.WeeksDays;
import estgf.ipp.pt.cmu.Database.DAO.WeeksDaysDAO;

public class GetWeeksDays extends AsyncTask<Void,Void,List<WeeksDays>> {

    private final WeeksDaysDAO dao;
    private NotifyGetWeeksDays notifyGetWeeksDays;

    public GetWeeksDays(WeeksDaysDAO dao,NotifyGetWeeksDays notifyGetWeeksDays){
        this.dao=dao;
        this.notifyGetWeeksDays=notifyGetWeeksDays;
    }


    @Override
    protected List<WeeksDays> doInBackground(Void... voids) {
        return dao.getWeeksDays();
    }

    @Override
    protected void onPostExecute(List<WeeksDays> weeksDays) {
        notifyGetWeeksDays.OnGetWeeksDays(weeksDays);
    }
}
