package estgf.ipp.pt.cmu.old_Stuff;

import android.content.Context;
import android.os.AsyncTask;


import java.lang.ref.WeakReference;
import java.util.List;


import estgf.ipp.pt.cmu.APIControllers.APIController;
import estgf.ipp.pt.cmu.APIControllers.Routes;
import estgf.ipp.pt.cmu.Entities.Result.Result;
import estgf.ipp.pt.cmu.Entities.Result.ResultAdapter;
import estgf.ipp.pt.cmu.Entities.Result.ResultType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetResults extends AsyncTask<String,Integer,List<Result>> {

    private Routes test;
    private boolean ready;
    private List<Result> list;
    private WeakReference<Context> contextWeakReference;
    private ResultAdapter adapter;
    private ResultType type;


    public GetResults(Context context,ResultAdapter adapter){
        this.contextWeakReference=new WeakReference<>(context);
        this.adapter=adapter;

    }

    @Override
    protected void onPreExecute() {
        test = APIController.getRoutes();
        ready=false;
    }

    @Override
    protected List<Result> doInBackground(String... strings) {
        Call<List<Result>> call = null;
    if(strings[1].equals("Recipes")){
        type=ResultType.Recipe;
       call = test.autocompleteRecipes(strings[0],10);
    } else if (strings[1].equals("Ingredients")) {
        type=ResultType.Ingredient;
        call = test.autocompleteIngredients(strings[0],10);
    }
    /* else if(strings[1].equals("Products")){
        type=ResultType.Product;
        call=test.autocompleteproducts(strings[0],10);
    }*/

        call.enqueue(new Callback<List<Result>>() {

            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                list = response.body();
                ready = true;


            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                ready = true;
                t.printStackTrace();
            }
        });

        while(!ready);

        return list;
    }

   /* @Override
    protected void onPostExecute(List<Result> recipeResults) {
        if(list.size()>0) {
            setType(list);
            if (!this.adapter.isSet()) {
                Context context = contextWeakReference.get();
                if (context != null) {

                    RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
                    adapter.setList((ArrayList<Result>) list);
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
*/
    private void setType(List<Result> list) {
        for ( Result pos :list) {
            pos.setType(type);
        }
    }
}
