package estgf.ipp.pt.cmu.API.Querys;

import estgf.ipp.pt.cmu.API.Controllers.APIController;
import estgf.ipp.pt.cmu.API.Routes;
import estgf.ipp.pt.cmu.Entities.Food.Product;
import estgf.ipp.pt.cmu.Entities.Result.ProductResult;
import estgf.ipp.pt.cmu.Utilities.NotifyGetFoodInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProductInformation {

    private final NotifyGetFoodInformation notifyGetFoodInformation;
    private Routes routes;
    private ProductResult productResult;
    private Product product;


    public GetProductInformation(Product product, NotifyGetFoodInformation notifyGetFoodInformation) {
        this.routes = APIController.getRoutes();
        this.product = product;
        this.notifyGetFoodInformation=notifyGetFoodInformation;

    }


    public void execute() {
        Call<ProductResult> call = routes.getProductInformation(product.getId());
        call.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {
                if (response.body() != null) {
                    productResult = response.body();
                    product.setNutrition(productResult.getNutrition());
                    notifyGetFoodInformation.OnGetFoodInformation(product);
                }

            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }

}