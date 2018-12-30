package estgf.ipp.pt.cmu.Entities.Meal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import estgf.ipp.pt.cmu.R;
import estgf.ipp.pt.cmu.SaveCurrentMeal;
import estgf.ipp.pt.cmu.Utilities.OnMealSelectedListener;
import estgf.ipp.pt.cmu.Utilities.RecyclerViewItemClickListener;


public class MealsAdapter extends RecyclerView.Adapter<MealsViewHolder>  {

    private Context context;
    private ArrayList<Meal> list;
    private OnMealSelectedListener listener;

    public MealsAdapter(Context context, List<Meal> list,OnMealSelectedListener listener){
        this.context=context;
        this.list= (ArrayList<Meal>) list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context= viewGroup.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);

        View resultView= inflater.inflate(R.layout.row_layout_daily_meals,viewGroup,false);
        return new MealsViewHolder(resultView);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder mealsViewHolder, int i) {
        Meal temp = list.get(i);
        mealsViewHolder.Meal.setText(temp.getName());
        mealsViewHolder.Time.setText(temp.getTime());

        mealsViewHolder.setRecyclerViewItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                listener.onMealSelected(list.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
