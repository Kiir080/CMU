package estgf.ipp.pt.cmu.Entities.WeeksDays;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import estgf.ipp.pt.cmu.R;

public class WeeksDaysAdapter extends RecyclerView.Adapter<WeeksDaysViewHolder> {


    private Context context;
    private ArrayList<String> list;

    public WeeksDaysAdapter(Context context){
        this.context=context;
        this.list= new ArrayList<String>();
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Saturday");
        list.add("Sunday");
    }

    @NonNull
    @Override
    public WeeksDaysViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context= viewGroup.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);

        View resultView= inflater.inflate(R.layout.row_layout_weeks_meal_plan,viewGroup,false);

        return new WeeksDaysViewHolder(resultView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeksDaysViewHolder weeksDaysViewHolder, int i) {
        String temp = list.get(i);
        weeksDaysViewHolder.textView.setText(temp);
        weeksDaysViewHolder.ConsumedCalories.setText("Calories: 1000");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
