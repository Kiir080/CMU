package estgf.ipp.pt.cmu.old_Stuff;

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

import estgf.ipp.pt.cmu.APIControllers.APIController;
import estgf.ipp.pt.cmu.APIControllers.Routes;
import estgf.ipp.pt.cmu.Entities.Result.Result;
import estgf.ipp.pt.cmu.Entities.Result.ResultAdapter;
import estgf.ipp.pt.cmu.Entities.Result.ResultType;
import estgf.ipp.pt.cmu.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetIngredientResult_old extends AsyncTask<String, Integer, List<Result>> {

    private Routes routes;
    private boolean ready;
    private List<Result> list;
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
    protected List<Result> doInBackground(String... strings) {
        Call<List<Result>> call = routes.autocompleteIngredients(strings[0], 10);
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                list = response.body();

                if (list.size() > 0) {
                    setType(list);
                    if (adapter.isSet()) {
                        Context context = contextWeakReference.get();
                        if (context != null) {
                            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
                         //   adapter.setList((ArrayList<Result>) list);
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
            public void onFailure(Call<List<Result>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return list;
    }

    private void setType(List<Result> list) {
        for (Result pos : list) {
            pos.setType(type);
        }
    }
}

