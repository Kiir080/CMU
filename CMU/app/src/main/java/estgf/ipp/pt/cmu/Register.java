package estgf.ipp.pt.cmu;

import android.content.Context;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import estgf.ipp.pt.cmu.Entities.User;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    private User user;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context=this;

        Spinner genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        Spinner activitySpinner = (Spinner) findViewById(R.id.activitySpinner);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> activityAdapter = ArrayAdapter.createFromResource(this,
                R.array.activity_array, android.R.layout.simple_spinner_item);

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpinner.setAdapter(genderAdapter);
        activitySpinner.setAdapter(activityAdapter);

        genderSpinner.setOnItemSelectedListener(this);
        activitySpinner.setOnItemSelectedListener(this);

        user= new User();

        Button button = (Button) findViewById(R.id.registerButton);
        button.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.genderSpinner){
            user.setGender(parent.getItemAtPosition(position).toString());
        }else{
            user.setActivity(parent.getItemAtPosition(position).toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.registerButton){
            EditText username= (EditText)  findViewById(R.id.username);
            EditText age = (EditText)  findViewById(R.id.userAge);
            EditText weight = (EditText)  findViewById(R.id.weight);
            EditText height = (EditText)  findViewById(R.id.height);
            if(!username.getText().toString().matches("")
                    && !age.getText().toString().matches("")
                    && !weight.getText().toString().matches("")
                    && !height.getText().toString().matches("")){

                user.setUsername(username.getText().toString());
                user.setAge(Integer.parseInt(age.getText().toString()));
                user.setHeight(Float.parseFloat(height.getText().toString()));
                user.setWeight(Integer.parseInt(weight.getText().toString()));

                calculateMaxDailyCalories(user);

                View contextView = findViewById(R.id.registerButton);

                Snackbar.make(contextView,getString(R.string.calculated_calories,user.getMaxCalories()),Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,Menu.class);
                        context.startActivity(intent);
                    }
                }).show();

            }
        }
    }

    private void calculateMaxDailyCalories(User user){
        float activity=1;

        switch (user.getGender()){
            case "Male":
                switch(user.getActivity()){
                    case "Sedentary":
                        break;
                    case "Low Active":
                        activity=1.12f;
                        break;
                    case "Active":
                        activity=1.27f;
                        break;
                    case "Very Active":
                        activity=1.54f;
                        break;

                }
                user.setMaxCalories((int) (864 - 9.72f * user.getAge() + activity *(14.2f * user.getWeight() + 503 * user.getHeight())));
                break;
            case "Female":
                switch(user.getActivity()){
                    case "Sedentary":
                        break;
                    case "Low Active":
                        activity=1.14f;
                        break;
                    case "Active":
                        activity=1.27f;
                        break;
                    case "Very Active":
                        activity=1.45f;
                        break;

                }
                user.setMaxCalories((int) (387 - 7.31f * user.getAge() + activity * (10.9f * user.getWeight() +  660.7f * user.getHeight())));
                break;
        }
    }
}
