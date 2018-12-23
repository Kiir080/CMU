package estgf.ipp.pt.cmu.Querys;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import estgf.ipp.pt.cmu.Entities.Food.Food;
import estgf.ipp.pt.cmu.Entities.Result.Result;

public class GetImages extends AsyncTask<Void,Integer,Void> {

    private Drawable x;
    private Food food;

    public GetImages(Food food){
        this.food=food;
    }

    @Override
    protected Void doInBackground(Void... strings) {
        InputStream is = null;
        try {
            is = (InputStream) new URL(food.getImagePath()).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = Drawable.createFromStream(is, "useless");
        food.setImage(x);
        return null;
    }


}
