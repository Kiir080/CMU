package estgf.ipp.pt.cmu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.Result.Result;

public class MealFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private Meal meal;
    private FloatingActionButton button;
    private ResultAdapterTeste adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();

        this.adapter= new ResultAdapterTeste(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.meal_fragment_layout,container,false);
        button = (FloatingActionButton) view.findViewById(R.id.addFoodButton);
        button.setOnClickListener(this);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        RecyclerView recyclerView = view.findViewById(R.id.foodRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        return view;

    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlaceholder, new SearchFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void updateList(Result result){
        this.adapter.addItem(result);
    }
}
