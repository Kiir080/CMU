package estgf.ipp.pt.cmu.Entities.Result;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import estgf.ipp.pt.cmu.R;
import estgf.ipp.pt.cmu.Utilities.RecyclerViewItemClickListener;

public class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public TextView textView;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public ResultViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textView = (TextView) itemView.findViewById(R.id.resultTitle);
        itemView.setOnClickListener(this);
    }

    public void setRecyclerViewItemClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    @Override
    public void onClick(View v) {
        recyclerViewItemClickListener.onClick(v,getAdapterPosition());
    }
}
