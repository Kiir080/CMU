package estgf.ipp.pt.cmu;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
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

public class GetIngredientResult_old extends AsyncTask<String, Integer, List<RecipeResult>> {

    private Routes routes;
    private boolean ready;
    private List<RecipeResult> list;
    private WeakReference<Context> contextWeakReference;
    private ResultAdapter adapter;
    private ResultType type;


    public GetIngredientResult_old(Context context, ResultAdapter adapter) {
        this.contextWeakReference = new WeakReference<>(context);
        this.adapter = adapter;

    }

    @Override
    protected void onPreExecute() {
        routes = APIController.getRoutes();
        ready = false;
    }

    @Override
    protected List<RecipeResult> doInBackground(String... strings) {
        Call<List<RecipeResult>> call = routes.autocompleteIngredients(strings[0], 10);
        call.enqueue(new Callback<List<RecipeResult>>() {
            @Override
            public void onResponse(Call<List<RecipeResult>> call, Response<List<RecipeResult>> response) {
                list = response.body();

                if (list.size() > 0) {
                    setType(list);
                    if (!adapter.isSet()) {
                        Context context = contextWeakReference.get();
                        if (context != null) {
                            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
                            adapter.setList((ArrayList<RecipeResult>) list);
                            RecyclerView recyclerView = ((Activity) context).findViewById(R.id.recyclerView);
                            recyclerView.setAdapter(adapter);
                            recyclerView.addItemDecoration(itemDecoration);

                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
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

        return list;
    }

    private void setType(List<RecipeResult> list) {
        for (RecipeResult pos : list) {
            pos.setType(type);
        }
    }
}

