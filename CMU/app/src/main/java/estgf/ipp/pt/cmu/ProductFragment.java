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

import estgf.ipp.pt.cmu.Entities.Food.Product;


public class ProductFragment extends Fragment {
    private Context context;
    private TextView name;
    private TextView cal;
    private TextView carbs;
    private TextView prot;
    private TextView fat;
    private Product product;
    private ImageView imageView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView= inflater.inflate(R.layout.product_fragment_layout,container,false);
        this.name=(TextView) contentView.findViewById(R.id.productTitle);
        this.cal=(TextView) contentView.findViewById(R.id.ingredientCal);
        this.carbs=(TextView) contentView.findViewById(R.id.ingredientCarbs);
        this.prot=(TextView) contentView.findViewById(R.id.ingredientProt);
        this.fat=(TextView) contentView.findViewById(R.id.ingredientFat);
        this.imageView=(ImageView) contentView.findViewById(R.id.productImage);
        mapData();
        return contentView;
    }


    private void mapData(){
        name.setText(product.getTitle());
        cal.setText(getString(R.string.calories,product.getNutrition().getCalories()));
        carbs.setText(getString(R.string.carbs,product.getNutrition().getCarbs()));
        prot.setText(getString(R.string.protein,product.getNutrition().getProtein()));
        fat.setText(getString(R.string.fat,product.getNutrition().getFat()));
        imageView.setImageDrawable(product.getImage());

    }

    public void addData(Product product){
        this.product =product;

    }
}
