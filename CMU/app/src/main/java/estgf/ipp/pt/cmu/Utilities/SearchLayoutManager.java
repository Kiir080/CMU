package estgf.ipp.pt.cmu.Utilities;

import android.app.Activity;
import android.content.Context;
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
        this.relativeLayout = ((Activity) this.context).findViewById(relativeLayoutId);
        this.errorMessage = ((Activity) this.context).findViewById(errorMessageId);
        this.loadingBar = ((Activity) this.context).findViewById(loadingBarId);
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
