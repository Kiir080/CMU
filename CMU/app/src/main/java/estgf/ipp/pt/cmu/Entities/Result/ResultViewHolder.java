package estgf.ipp.pt.cmu.Entities.Result;

        import android.media.Image;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

        import estgf.ipp.pt.cmu.R;

public class ResultViewHolder extends RecyclerView.ViewHolder {


    public TextView textView;

    public ResultViewHolder(@NonNull View itemView) {
        super(itemView);


        this.textView = (TextView) itemView.findViewById(R.id.resultTitle);
    }
}
