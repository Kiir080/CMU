package estgf.ipp.pt.cmu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import estgf.ipp.pt.cmu.Entities.Result.Result;
import estgf.ipp.pt.cmu.Utilities.OnResultSelectedListener;

public class MealActivity extends AppCompatActivity  implements OnResultSelectedListener {
    private MealFragment mealFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        if(findViewById(R.id.fragmentPlaceholder)!=null){
            if(savedInstanceState != null)
                return;
        }

        mealFragment= new MealFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentPlaceholder,mealFragment).addToBackStack("root").commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResultSelected(Result result) {
        mealFragment.updateList(result);
    }
}
