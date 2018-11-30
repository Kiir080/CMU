package estgf.ipp.pt.cmu;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetIngredientsResult {

    private Routes routes;
    private List<RecipeResult> list;
    private WeakReference<Context> contextWeakReference;
    private ResultAdapter adapter;
    private final ResultType type=ResultType.Ingredient;

    public GetIngredientsResult(Context context, ResultAdapter adapter){
        this.contextWeakReference=new WeakReference<>(context);
        this.adapter=adapter;
        this.routes=APIController.getRoutes();

    }


    public void execute(String query){
        Call<List<RecipeResult>> call = routes.autocompleteIngredients(query, 10);
        call.enqueue(new Callback<List<RecipeResult>>() {
            @Override
            public void onResponse(Call<List<RecipeResult>> call, Response<List<RecipeResult>> response) {
                list = response.body();
                if (list.size() > 0) {
                    setType(list);
                    if (!adapter.isSet()) {
                        Context context = contextWeakReference.get();
                        if (context != null) {
                            adapter.setList((ArrayList<RecipeResult>) list);
                            RelativeLayout relativeLayout = ((Activity) context).findViewById(R.id.loadingPanel);
                            relativeLayout.setVisibility(View.GONE);
                        }
                    } else {
                        adapter.addItems(list);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RecipeResult>> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }


    private void setType(List<RecipeResult> list) {
        for ( RecipeResult pos :list) {
            pos.setType(type);
        }
    }
}