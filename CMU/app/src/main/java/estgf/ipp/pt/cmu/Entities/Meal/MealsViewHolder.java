package estgf.ipp.pt.cmu.Entities.Meal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import estgf.ipp.pt.cmu.R;

public class MealsViewHolder extends RecyclerView.ViewHolder{

    public TextView Meal;
    public TextView Time;


    public MealsViewHolder(@NonNull View itemView) {
        super(itemView);

        this.Meal=(TextView) itemView.findViewById(R.id.textView_Meal);
        this.Time = (TextView) itemView.findViewById(R.id.textView_Time);

    }
}
