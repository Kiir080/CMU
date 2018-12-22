package estgf.ipp.pt.cmu.Entities.Result;

public class RecipeResult {
    private int id;
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private String diaryFree;
    private String title;
    private String image;
    private NutrientsListResult nutrition;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NutrientsListResult getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutrientsListResult nutrition) {
        this.nutrition = nutrition;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public String getDiaryFree() {
        return diaryFree;
    }

    public void setDiaryFree(String diaryFree) {
        this.diaryFree = diaryFree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
