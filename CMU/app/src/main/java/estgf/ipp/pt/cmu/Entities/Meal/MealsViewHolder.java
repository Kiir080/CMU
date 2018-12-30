package estgf.ipp.pt.cmu.Entities.Meal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import estgf.ipp.pt.cmu.R;
import estgf.ipp.pt.cmu.Utilities.RecyclerViewItemClickListener;

public class MealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView Meal;
    public TextView Time;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;


    public MealsViewHolder(@NonNull View itemView) {
        super(itemView);

        this.Meal=(TextView) itemView.findViewById(R.id.textView_Meal);
        this.Time = (TextView) itemView.findViewById(R.id.textView_Time);
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
