package estgf.ipp.pt.cmu;

public class IngredientResult {

    String baseURL = "https://spoonacular.com/cdn/ingredients_100x100/{name}.jpg";

    public String image;
    private ResultType type;
    public String name;

    public ResultType getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ResultType type) {
        this.type = type;
    }
}


