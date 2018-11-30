package estgf.ipp.pt.cmu;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

public class GetImages extends AsyncTask<RecipeResult,Integer,Drawable[]> {


    @Override
    protected Drawable[] doInBackground(RecipeResult... recipeResults) {
        Drawable temp[]= new Drawable[recipeResults.length];
        for(int i =0; i< recipeResults.length;i++){
            temp[i]=recipeResults[i].loadImage();
        }

        return temp;
    }


}
