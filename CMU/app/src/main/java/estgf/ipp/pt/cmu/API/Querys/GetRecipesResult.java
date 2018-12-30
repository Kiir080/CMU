package estgf.ipp.pt.cmu.API.Querys;

import java.util.List;

import estgf.ipp.pt.cmu.API.Controllers.APIController;
import estgf.ipp.pt.cmu.API.Routes;
import estgf.ipp.pt.cmu.Entities.Result.Result;
import estgf.ipp.pt.cmu.Entities.Result.ResultAdapter;
import estgf.ipp.pt.cmu.Entities.Result.ResultType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRecipesResult {

    private Routes routes;
    private List<Result> list;
    private ResultAdapter adapter;
    private final ResultType type = ResultType.Recipe;

    public GetRecipesResult(ResultAdapter adapter) {
        this.adapter = adapter;
        this.routes = APIController.getRoutes();

    }


    public void execute(String query) {
        Call<List<Result>> call = routes.autocompleteRecipes(query, 10);
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
