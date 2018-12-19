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
import estgf.ipp.pt.cmu.Entities.Result.ResultList;
import estgf.ipp.pt.cmu.Entities.Result.ResultType;
import estgf.ipp.pt.cmu.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProductsResult {

    private Routes routes;
    private List<Result> list;
    private WeakReference<Context> contextWeakReference;
    private ResultAdapter adapter;
    private final ResultType type = ResultType.Product;

    public GetProductsResult(Context context, ResultAdapter adapter) {
        this.contextWeakReference = new WeakReference<>(context);
        this.adapter = adapter;
        this.routes = APIController.getRoutes();

    }


    public void execute(String query) {
        Call<ResultList> call = routes.autocompleteproducts(query, 10);
        call.enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(Call<ResultList> call, Response<ResultList> response) {
                ResultList temp = response.body();

                if (temp != null && temp.getResult().size() >0) {
                    list= temp.getResult();
                    setType(list);
                    if (!adapter.isSet()) {
                        Context context = contextWeakReference.get();
                        if (context != null) {
                            adapter.setList((ArrayList<Result>) list);
                            RelativeLayout relativeLayout = ((Activity) context).findViewById(R.id.loadingPanel);
                            relativeLayout.setVisibility(View.GONE);
                        }
                    } else {
                        adapter.addItems(list);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultList> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }


    private void setType(List<Result> list) {
        for (Result pos : list) {
            pos.setType(type);
        }
    }

}
