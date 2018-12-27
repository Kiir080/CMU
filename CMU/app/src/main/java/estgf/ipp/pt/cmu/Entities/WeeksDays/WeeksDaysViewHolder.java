package estgf.ipp.pt.cmu.Entities.WeeksDays;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import estgf.ipp.pt.cmu.R;
import estgf.ipp.pt.cmu.Utilities.RecyclerViewItemClickListener;

public class WeeksDaysViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

    public TextView textView;
    public TextView date;
    public TextView ConsumedCalories;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public WeeksDaysViewHolder(@NonNull View itemView) {
        super(itemView);
        this.date=(TextView) itemView.findViewById(R.id.dateFormat);
        this.textView = (TextView) itemView.findViewById(R.id.textView_row_layout_weeks_meal_plan);
        this.ConsumedCalories = (TextView) itemView.findViewById(R.id.textView_Consumed_Calories);
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
