package estgf.ipp.pt.cmu.Database.Querys;

import android.os.AsyncTask;

import estgf.ipp.pt.cmu.Database.Controllers.RoomDB;

public class ClearDB extends AsyncTask<Void,Void,Void> {
    private RoomDB roomDB;

    public ClearDB(RoomDB roomDB) {
        this.roomDB = roomDB;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        roomDB.clearAllTables();
        return null;
    }
}
