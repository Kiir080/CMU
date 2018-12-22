package estgf.ipp.pt.cmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.RelativeLayout;

import estgf.ipp.pt.cmu.Entities.Result.ResultAdapter;
import estgf.ipp.pt.cmu.Querys.GetIngredientsResult;
import estgf.ipp.pt.cmu.Querys.GetProductsResult;
import estgf.ipp.pt.cmu.Querys.GetRecipesResult;
import estgf.ipp.pt.cmu.old_Stuff.GetResults;


public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private ResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);

        this.adapter= new ResultAdapter(this);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));




    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if(adapter.getItemCount() > 0){
            adapter.cleanList();
        }
        GetResults recipes = new GetResults(this,adapter);
       // GetResults ingredients = new GetResults(this,adapter);
        // GetResults products = new GetResults(this,adapter);
      //  ingredients.execute(s,"Ingredients");
        GetIngredientsResult t= new GetIngredientsResult(this,adapter);
        GetRecipesResult x= new GetRecipesResult(this,adapter);
        GetProductsResult y= new GetProductsResult(this,adapter);

        t.execute(s);
        x.execute(s);
        y.execute(s);

        RelativeLayout temp = (RelativeLayout) findViewById(R.id.loadingPanel);
        temp.setVisibility(View.VISIBLE);

     //   recipes.execute(s,"Recipes");
       // products.execute(s,"Products");
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return true;
    }
}
