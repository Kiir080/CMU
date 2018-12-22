package estgf.ipp.pt.cmu.Querys;

import android.app.Activity;
import android.content.Context;
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

public class GetIngredientsResult {

    private Routes routes;
    private List<Result> list;
    private ResultAdapter adapter;
    private final ResultType type = ResultType.Ingredient;

    public GetIngredientsResult(ResultAdapter adapter) {
        this.adapter = adapter;
        this.routes = APIController.getRoutes();

    }


    public void execute(String query) {
        Call<List<Result>> call = routes.autocompleteIngredients(query, 10);
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                list = response.body();
                if (list.size() > 0) {
                    setType(list);
                    adapter.addItems(list);
                } else {
                    adapter.emptyResponse();
                }

            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                t.printStackTrace();
                adapter.emptyResponse();
            }

        });
    }


    private void setType(List<Result> list) {
        for (Result pos : list) {
            pos.setType(type);
        }
    }
}