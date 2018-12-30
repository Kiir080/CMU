package estgf.ipp.pt.cmu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import estgf.ipp.pt.cmu.Entities.Food.Recipe;


public class RecipeFragment extends Fragment {

    private Context context;
    private TextView name;
    private TextView vegetarian;
    private TextView vegan;
    private TextView diaryFree;
    private TextView glutenFree;
    private TextView cal;
    private TextView carbs;
    private TextView prot;
    private TextView fat;
    private Recipe recipe;
    private ImageView imageView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView= inflater.inflate(R.layout.recipe_fragment_layout,container,false);
        this.name=(TextView) contentView.findViewById(R.id.recipeTitle);
        this.vegetarian =(TextView) contentView.findViewById(R.id.Vegetarian);
        this.vegan =(TextView) contentView.findViewById(R.id.Vegan);
        this.diaryFree =(TextView) contentView.findViewById(R.id.diaryFree);
        this.glutenFree =(TextView) contentView.findViewById(R.id.glutenFree);
        this.cal=(TextView) contentView.findViewById(R.id.ingredientCal);
        this.carbs=(TextView) contentView.findViewById(R.id.ingredientCarbs);
        this.prot=(TextView) contentView.findViewById(R.id.ingredientProt);
        this.fat=(TextView) contentView.findViewById(R.id.ingredientFat);
        this.imageView=(ImageView) contentView.findViewById(R.id.recipeImage);
        mapData();
        return contentView;
    }


    private void mapData(){
       if(recipe.isDairyFree()){
           this.diaryFree.setVisibility(View.VISIBLE);
       }
       if(recipe.isGlutenFree()){
           this.glutenFree.setVisibility(View.VISIBLE);
       }
       if(recipe.isVegetarian()){
           this.vegetarian.setVisibility(View.VISIBLE);
       }
       if(recipe.isVegan()){
           this.vegan.setVisibility(View.VISIBLE);
       }
        name.setText(recipe.getTitle());
        cal.setText(getString(R.string.calories,recipe.getNutrition().getCalories()));
        carbs.setText(getString(R.string.carbs,recipe.getNutrition().getCarbs()));
        prot.setText(getString(R.string.protein,recipe.getNutrition().getProtein()));
        fat.setText(getString(R.string.fat,recipe.getNutrition().getFat()));
        imageView.setImageDrawable(recipe.getImage());

    }

    public void addData(Recipe recipe){
        this.recipe =recipe;
    }

}
