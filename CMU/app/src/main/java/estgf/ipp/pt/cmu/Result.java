package estgf.ipp.pt.cmu;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import java.io.InputStream;
import java.net.URL;

public class Result{
    private String title;
    private Drawable image;
    private String baseURL;

    public Result(String title){
        this.title=title;
    }

    public Drawable getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setImage(String baseURL) {
        this.baseURL=baseURL;
        this.image = loadImage();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    private Drawable loadImage() {
        try {
            InputStream is = (InputStream) new URL(this.baseURL).getContent();
            Drawable d = Drawable.createFromStream(is,"useless");
            return d;
        } catch (Exception e) {
            return null;
        }

    }
}
