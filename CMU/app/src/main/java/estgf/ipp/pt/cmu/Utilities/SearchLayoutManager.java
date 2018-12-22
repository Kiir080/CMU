package estgf.ipp.pt.cmu.Utilities;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchLayoutManager {


    private RelativeLayout relativeLayout;
    private ProgressBar loadingBar;
    private TextView errorMessage;
    private Context context;



    public SearchLayoutManager(int relativeLayoutId, int errorMessageId, int loadingBarId,Context context) {
        this.context=context;
        View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        this.relativeLayout = rootView.findViewById(relativeLayoutId);
        this.errorMessage = ((Activity) context).findViewById(errorMessageId);
        this.loadingBar = ((Activity) context).findViewById(loadingBarId);
    }

    public SearchLayoutManager(RelativeLayout relativeLayout, TextView errorMessage, ProgressBar loadingBar,Context context) {
        this.context=context;
        this.relativeLayout = relativeLayout;
        this.errorMessage = errorMessage;
        this.loadingBar = loadingBar;
    }

    public void MakeLoadingPanelGone(){
        relativeLayout.setVisibility(View.GONE);
    }

    public void MakeLoadingPanelVisible(){
        errorMessage.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.VISIBLE);
    }

    public void DisplayErrorMessage(String message){
        errorMessage.setText(message);
        errorMessage.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);
    }
}
