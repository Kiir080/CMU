package estgf.ipp.pt.cmu;

public class ProductResult extends Result {

    private int id;

    public ProductResult(String title, int id) {
        super(title);
        this.id=id;
        String baseURL = "https://spoonacular.com/productImages/{id}-312x231.jpg";
        super.setImage(baseURL.replace("{id}",String.valueOf(this.id)));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
