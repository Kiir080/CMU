package estgf.ipp.pt.cmu.Querys;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import estgf.ipp.pt.cmu.Entities.Result.Result;

public class GetImages extends AsyncTask<Result,Integer,Drawable[]> {


    @Override
    protected Drawable[] doInBackground(Result... recipeResults) {
        Drawable temp[]= new Drawable[recipeResults.length];
        for(int i =0; i< recipeResults.length;i++){
            temp[i]=recipeResults[i].loadImage();
        }

        return temp;
    }


}
