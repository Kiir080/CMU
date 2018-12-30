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
import android.widget.TextView;

import estgf.ipp.pt.cmu.Entities.Food.Food;
import estgf.ipp.pt.cmu.Entities.Food.Ingredient;
import estgf.ipp.pt.cmu.Entities.Food.Product;
import estgf.ipp.pt.cmu.Entities.Food.Recipe;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.Result.Result;
import estgf.ipp.pt.cmu.API.Querys.GetImages;
import estgf.ipp.pt.cmu.API.Querys.GetIngredientInformation;
import estgf.ipp.pt.cmu.API.Querys.GetProductInformation;
import estgf.ipp.pt.cmu.API.Querys.GetRecipeInformation;
import estgf.ipp.pt.cmu.Utilities.NotifyGetFoodInformation;
import estgf.ipp.pt.cmu.Utilities.OnFoodSelectedListener;

public class MealFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private Meal meal;
    private FloatingActionButton button;
    private FoodAdapter adapter;
    private OnFoodSelectedListener listener;
    private NotifyGetFoodInformation notifyGetFoodInformation;

    private TextView mealTime;
    private TextView mealName;
    private TextView guidelines;
    private TextView recommendeCalories;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener=(OnFoodSelectedListener) context;
            notifyGetFoodInformation= (NotifyGetFoodInformation) context;
        }catch (ClassCastException ex){
            throw new ClassCastException(context.toString()+ "must implement OnCountrySelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();

        this.adapter= new FoodAdapter(context,listener);

        if(meal.getFoodList().size()!=0){
            this.adapter.addItems(meal.getFoodList());
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.meal_fragment_layout,container,false);
        button = (FloatingActionButton) view.findViewById(R.id.addFoodButton);
        button.setOnClickListener(this);

        mealName = view.findViewById(R.id.textView_Meal_Name);
        mealTime = view.findViewById(R.id.textView_Time_Meal);
        guidelines= view.findViewById(R.id.guideLines);
        recommendeCalories=view.findViewById(R.id.textView_recommended_calories);



        this.recommendeCalories.setText(getString(R.string.recomended_calories,meal.RecommendedCalories()));
        this.guidelines.setText(meal.getGuidelines());
        this.mealTime.setText(getString(R.string.meal_time,meal.getTime()));
        this.mealName.setText(meal.getName());

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        RecyclerView recyclerView = view.findViewById(R.id.foodRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        return view;

    }




    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlaceholder, new SearchFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void updateMeal(Meal meal){
    this.meal=meal;
    }



    public void updateList(Result result){
        Food food = null;
        GetImages getImages= null;
        switch(result.getType()){
            case Ingredient:
                Ingredient ingredient = new Ingredient(result.id,result.name);

                GetIngredientInformation getIngredientInformation = new GetIngredientInformation(ingredient,notifyGetFoodInformation);
                getIngredientInformation.execute();
                getImages= new GetImages(ingredient);
                food=ingredient;
                break;
            case Recipe:
                Recipe recipe = new Recipe(result.id,result.title);
                GetRecipeInformation getRecipeInformation = new GetRecipeInformation(recipe,notifyGetFoodInformation);
                getRecipeInformation.execute();
                getImages = new GetImages(recipe);
                food=recipe;
                break;
            case Product:
                Product product = new Product(result.id,result.title);
                GetProductInformation getProductInformation = new GetProductInformation(product,notifyGetFoodInformation);
                getProductInformation.execute();
                getImages = new GetImages(product);
                food=product;
                break;
        }
        getImages.execute();
        this.adapter.addItem(food);

    }




}
