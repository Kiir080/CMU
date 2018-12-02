package estgf.ipp.pt.cmu.Entities.WeeksDays;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import estgf.ipp.pt.cmu.R;

public class WeeksDaysViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public TextView ConsumedCalories;
    public WeeksDaysViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textView = (TextView) itemView.findViewById(R.id.textView_row_layout_weeks_meal_plan);
        this.ConsumedCalories = (TextView) itemView.findViewById(R.id.textView_Consumed_Calories);

    }
}
