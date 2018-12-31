package estgf.ipp.pt.cmu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        Button btnMarkActivity = (Button) findViewById(R.id.mark_activity);
        Button btnStartActivity = (Button) findViewById(R.id.start_activity);
        Button btnMealActivity = (Button) findViewById(R.id.meal_activity);

        btnMarkActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, MarkActivity.class);
                startActivity(intent);
            }
        });

        btnStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, StartActivity.class);
                startActivity(intent);
            }
        });

        btnMealActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, WeeksMealPlan.class);
                startActivity(intent);
            }
        });

    }

}
