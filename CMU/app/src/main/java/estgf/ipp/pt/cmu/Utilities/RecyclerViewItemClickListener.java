package estgf.ipp.pt.cmu.Utilities;

import android.view.View;

public interface RecyclerViewItemClickListener {

    void onClick(View view, int position);

    void onLongPress(View view, int position);
}
