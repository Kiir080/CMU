package estgf.ipp.pt.cmu.Entities.WeeksDays;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import estgf.ipp.pt.cmu.R;
import estgf.ipp.pt.cmu.Utilities.OnResultSelectedListener;
import estgf.ipp.pt.cmu.Utilities.OnWeeksDaySelectedListener;
import estgf.ipp.pt.cmu.Utilities.RecyclerViewItemClickListener;

public class WeeksDaysAdapter extends RecyclerView.Adapter<WeeksDaysViewHolder> {


    private Context context;
    private ArrayList<WeeksDays> list;
    private OnWeeksDaySelectedListener listener;

    public WeeksDaysAdapter(Context context,OnWeeksDaySelectedListener listener){
        this.context=context;
        this.list= new ArrayList<>();
        this.listener=listener;

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
        WeeksDays temp = list.get(i);
        weeksDaysViewHolder.textView.setText(temp.getDayOfWeek());
        weeksDaysViewHolder.date.setText(temp.getDateFormat());
        weeksDaysViewHolder.ConsumedCalories.setText(context.getString(R.string.caloriesX,temp.getCaloriesEaten()));

        weeksDaysViewHolder.setRecyclerViewItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                listener.onWeeksDaySelected(list.get(position));

            }

            @Override
            public void onLongPress(View view, int position) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<WeeksDays> list){
        if(this.list.size()!=0){
            this.list.clear();
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
