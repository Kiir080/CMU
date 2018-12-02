package estgf.ipp.pt.cmu.Entities.Meal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import estgf.ipp.pt.cmu.R;


public class MealsAdapter extends RecyclerView.Adapter<MealsViewHolder>  {

    private Context context;
    private ArrayList<Meal> list;

    public MealsAdapter(Context context, List<Meal> list){
        this.context=context;
        this.list= (ArrayList<Meal>) list;
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
        mealsViewHolder.Meal.setText(temp.Name);
        mealsViewHolder.Time.setText("12:30");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
