package estgf.ipp.pt.cmu;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import estgf.ipp.pt.cmu.Entities.Food.Ingredient;

public class IngredientFragment extends Fragment {

    private Context context;
    private TextView name;
    private TextView amount;
    private TextView unit;
    private TextView cal;
    private TextView carbs;
    private TextView prot;
    private TextView fat;
    private Ingredient ingredient;
    private ImageView imageView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView= inflater.inflate(R.layout.ingredient_fragment_layout,container,false);
        this.name=(TextView) contentView.findViewById(R.id.ingredientName);
        this.amount=(TextView) contentView.findViewById(R.id.ingredientAmount);
        this.unit=(TextView) contentView.findViewById(R.id.ingredientUnit);
        this.cal=(TextView) contentView.findViewById(R.id.ingredientCal);
        this.carbs=(TextView) contentView.findViewById(R.id.ingredientCarbs);
        this.prot=(TextView) contentView.findViewById(R.id.ingredientProt);
        this.fat=(TextView) contentView.findViewById(R.id.ingredientFat);
        this.imageView=(ImageView) contentView.findViewById(R.id.ingredientImage);
        mapData();
        return contentView;
    }


    private void mapData(){
        name.setText(ingredient.getName());
        amount.setText(String.valueOf(ingredient.getAmount()));
        unit.setText(ingredient.getUnit());
        cal.setText(String.valueOf(ingredient.getNutrition().getCalories()));
        carbs.setText(String.valueOf(ingredient.getNutrition().getCarbs()));
        prot.setText(String.valueOf(ingredient.getNutrition().getProtein()));
        fat.setText(String.valueOf(ingredient.getNutrition().getFat()));
        imageView.setImageDrawable(ingredient.getImage());
    }

    public void addData(Ingredient ingredient){
        this.ingredient=ingredient;

    }



}
