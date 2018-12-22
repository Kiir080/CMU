package estgf.ipp.pt.cmu;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import estgf.ipp.pt.cmu.Entities.Result.ResultAdapter;
import estgf.ipp.pt.cmu.Querys.GetIngredientsResult;
import estgf.ipp.pt.cmu.Querys.GetProductsResult;
import estgf.ipp.pt.cmu.Querys.GetRecipesResult;
import estgf.ipp.pt.cmu.Utilities.SearchLayoutManager;


public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private ResultAdapter adapter;
    private SearchLayoutManager searchLayoutManager;

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
        searchLayoutManager= new SearchLayoutManager(R.id.loadingPanel, R.id.ErrorMessage, R.id.loadingBar, this);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {

        ConnectivityManager connectivityManager	=	(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){
            if(adapter.getItemCount() > 0){
                adapter.cleanList();
            }

            GetIngredientsResult ingredientsResult= new GetIngredientsResult(adapter);
            GetRecipesResult recipesResult= new GetRecipesResult(adapter);
            GetProductsResult productsResult= new GetProductsResult(adapter);

            ingredientsResult.execute(s);
            recipesResult.execute(s);
            productsResult.execute(s);
            searchLayoutManager.MakeLoadingPanelVisible();
        }else{
            searchLayoutManager.DisplayErrorMessage("Check your Internet Connection !!");
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return true;
    }
}
