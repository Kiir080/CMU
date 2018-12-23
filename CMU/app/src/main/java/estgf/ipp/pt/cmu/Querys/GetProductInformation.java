package estgf.ipp.pt.cmu.Querys;

import estgf.ipp.pt.cmu.APIControllers.APIController;
import estgf.ipp.pt.cmu.APIControllers.Routes;
import estgf.ipp.pt.cmu.Entities.Food.Product;
import estgf.ipp.pt.cmu.Entities.Food.Recipe;
import estgf.ipp.pt.cmu.Entities.Result.ProductResult;
import estgf.ipp.pt.cmu.Entities.Result.RecipeResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProductInformation {

    private Routes routes;
    private ProductResult productResult;
    private Product product;


    public GetProductInformation(Product product) {
        this.routes = APIController.getRoutes();
        this.product = product;

    }


    public void execute() {
        Call<ProductResult> call = routes.getProductInformation(product.getId());
        call.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {
                if (response.body() != null) {
                    productResult = response.body();
                    product.setNutrition(productResult.getNutrition());
                }

            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }

}