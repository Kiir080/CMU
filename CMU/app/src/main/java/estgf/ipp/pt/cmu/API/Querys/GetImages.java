package estgf.ipp.pt.cmu.API.Querys;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import estgf.ipp.pt.cmu.Entities.Food.Food;
import estgf.ipp.pt.cmu.R;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class GetImages extends AsyncTask<Void, Integer, Void> {

    private Drawable x;
    private Food food;
    private List<Food> list;
    private Context context;

    public GetImages(Food food, Context context) {
        this.food = food;
        this.context = context;
    }

    public GetImages(List<Food> food, Context context) {
        this.list = food;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... strings) {
        if (list == null) {
            getImage(this.food);
        } else {
            for (Food pos : list
                    ) {
                getImage(pos);
            }
        }
        return null;
    }

    private void getImage(Food food) {

        if (food.getImage() == null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                InputStream is = null;
                try {
                    is = (InputStream) new URL(food.getImagePath()).getContent();
                    x = Drawable.createFromStream(is, "useless");
                    food.setImage(x);

                } catch (IOException e) {
                    Resources resources = context.getResources();
                    x = resources.getDrawable(R.drawable.no_connection);
                    food.setImage(x);
                }

            } else {

                Resources resources = context.getResources();
                x = resources.getDrawable(R.drawable.no_connection);
                food.setImage(x);
            }
        }
    }
}
