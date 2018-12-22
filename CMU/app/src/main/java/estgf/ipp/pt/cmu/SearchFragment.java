package estgf.ipp.pt.cmu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import estgf.ipp.pt.cmu.Entities.Result.ResultAdapter;
import estgf.ipp.pt.cmu.Querys.GetIngredientsResult;
import estgf.ipp.pt.cmu.Querys.GetProductsResult;
import estgf.ipp.pt.cmu.Querys.GetRecipesResult;
import estgf.ipp.pt.cmu.R;
import estgf.ipp.pt.cmu.Utilities.OnResultSelectedListener;
import estgf.ipp.pt.cmu.Utilities.SearchLayoutManager;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.SEARCH_SERVICE;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private ResultAdapter adapter;
    private SearchLayoutManager searchLayoutManager;
    private Context context;
    private OnResultSelectedListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView= inflater.inflate(R.layout.search_fragment_layout,container,false);
        searchView = (SearchView) contentView.findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        searchLayoutManager= new SearchLayoutManager((RelativeLayout)contentView.findViewById(R.id.loadingPanel),(TextView) contentView.findViewById(R.id.ErrorMessage),(ProgressBar) contentView.findViewById(R.id.loadingBar), context);

        this.adapter= new ResultAdapter(context,searchLayoutManager,listener);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        RecyclerView recyclerView = contentView.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return contentView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener=(OnResultSelectedListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException(context.toString()+ "must implement OnCountrySelectedListener");
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        ConnectivityManager connectivityManager	=	(ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
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
        return false;
    }
}
