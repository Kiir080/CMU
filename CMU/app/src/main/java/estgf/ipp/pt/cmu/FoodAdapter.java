package estgf.ipp.pt.cmu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import estgf.ipp.pt.cmu.Entities.Food.Food;
import estgf.ipp.pt.cmu.Entities.Food.Ingredient;
import estgf.ipp.pt.cmu.Entities.Food.Product;
import estgf.ipp.pt.cmu.Entities.Food.Recipe;
import estgf.ipp.pt.cmu.Entities.Result.Result;
import estgf.ipp.pt.cmu.Entities.Result.ResultType;
import estgf.ipp.pt.cmu.Entities.Result.ResultViewHolder;
import estgf.ipp.pt.cmu.Utilities.OnFoodSelectedListener;
import estgf.ipp.pt.cmu.Utilities.RecyclerViewItemClickListener;

public class FoodAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private Context context;
    private ArrayList<Food> list;
    private OnFoodSelectedListener listener;

    public FoodAdapter(Context context, OnFoodSelectedListener listener ) {
        this.context = context;
        this.list = new ArrayList<>();
        this.listener=listener;


    }
    public FoodAdapter(Context context, List<Food> list, OnFoodSelectedListener listener ) {
        this.context = context;
        this.list = (ArrayList<Food>) list;
        this.listener=listener;


    }



    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View resultView = inflater.inflate(R.layout.row_layout_food_meal, viewGroup, false);




        return new ResultViewHolder(resultView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder resultViewHolder, int i) {
        Food temp = list.get(i);

        TextView textView = resultViewHolder.textView;

        if (temp instanceof Ingredient) {
            Ingredient ingredient = (Ingredient) temp;
            textView.setText(ingredient.getName());

        } else if(temp instanceof Recipe){
            Recipe recipe = (Recipe) temp;
            textView.setText(recipe.getTitle());
        } else if(temp instanceof Product){
            Product product = (Product) temp;
            textView.setText(product.getTitle());
        }

        resultViewHolder.setRecyclerViewItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                FragmentManager x = ((AppCompatActivity) context).getSupportFragmentManager();
                Food temp =list.get(position);

                if (temp instanceof Ingredient ) {
                    listener.onFoodSelected((Ingredient)temp);
                } else if(temp instanceof Recipe){
                    listener.onFoodSelected((Recipe)temp);
                } else if(temp instanceof Product){
                    listener.onFoodSelected((Product)temp);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Food food) {
        if (food!= null) {
            list.add(food);
            notifyDataSetChanged();
        }

    }

    public void addItems(List<Food> food){
        list.addAll(food);
        notifyDataSetChanged();
    }

}
