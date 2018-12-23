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

import estgf.ipp.pt.cmu.Entities.Food.Food;
import estgf.ipp.pt.cmu.Entities.Food.Ingredient;
import estgf.ipp.pt.cmu.Entities.Food.Product;
import estgf.ipp.pt.cmu.Entities.Food.Recipe;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.Result.IngredientResult;
import estgf.ipp.pt.cmu.Entities.Result.ProductResult;
import estgf.ipp.pt.cmu.Entities.Result.RecipeResult;
import estgf.ipp.pt.cmu.Entities.Result.Result;
import estgf.ipp.pt.cmu.Querys.GetImages;
import estgf.ipp.pt.cmu.Querys.GetIngredientInformation;
import estgf.ipp.pt.cmu.Querys.GetProductInformation;
import estgf.ipp.pt.cmu.Querys.GetRecipeInformation;
import estgf.ipp.pt.cmu.Utilities.OnFoodSelectedListener;
import estgf.ipp.pt.cmu.Utilities.OnResultSelectedListener;

public class MealFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private Meal meal;
    private FloatingActionButton button;
    private FoodAdapter adapter;
    private OnFoodSelectedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener=(OnFoodSelectedListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException(context.toString()+ "must implement OnCountrySelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();

        this.adapter= new FoodAdapter(context,listener);

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
        Food food = null;
        GetImages getImages= null;
        switch(result.getType()){
            case Ingredient:
                Ingredient ingredient = new Ingredient(result.id,result.name);
                GetIngredientInformation getIngredientInformation = new GetIngredientInformation(ingredient);
                getIngredientInformation.execute();
                getImages= new GetImages(ingredient);
                food=ingredient;
                break;
            case Recipe:
                Recipe recipe = new Recipe(result.id,result.title);
                GetRecipeInformation getRecipeInformation = new GetRecipeInformation(recipe);
                getRecipeInformation.execute();
                getImages = new GetImages(recipe);
                food=recipe;
                break;
            case Product:
                Product product = new Product(result.id,result.title);
                GetProductInformation getProductInformation = new GetProductInformation(product);
                getProductInformation.execute();
                getImages = new GetImages(product);
                food=product;
                break;
        }
        getImages.execute();
        this.adapter.addItem(food);

    }


}
