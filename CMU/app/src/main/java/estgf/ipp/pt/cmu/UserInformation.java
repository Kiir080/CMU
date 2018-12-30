package estgf.ipp.pt.cmu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import estgf.ipp.pt.cmu.Database.Controllers.DBController;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetUsers;
import estgf.ipp.pt.cmu.Entities.User.User;
import estgf.ipp.pt.cmu.R;

public class UserInformation extends AppCompatActivity implements NotifyGetUsers {

    private User user;
    private TextView name;
    private TextView gender;
    private TextView age;
    private TextView weight;
    private TextView height;
    private TextView maxCalories;
    private TextView activity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        DBController dbController = new DBController(this);
        dbController.getUsers(this);

        name = (TextView) findViewById(R.id.user_name);
        age = (TextView) findViewById(R.id.user_age);
        gender = (TextView) findViewById(R.id.user_gender);
        weight = (TextView) findViewById(R.id.user_weight);
        height = (TextView) findViewById(R.id.user_height);
        maxCalories = (TextView) findViewById(R.id.user_maxCalories);
        activity = (TextView) findViewById(R.id.user_activity);


    }

    @Override
    public void OnGetUsers(List<User> userList) {
        if(userList== null || userList.size()==0){
            Intent intent = new Intent(this,Register.class);
            startActivity(intent);
        }else{
            user = userList.get(0);

            name.setText(getString(R.string.username_1_s,user.getUsername()));
            activity.setText(getString(R.string.type_of_activity_1_s,user.getActivity()));
            age.setText(getString(R.string.age_1_d,user.getAge()));
            gender.setText(getString(R.string.gender_1_s,user.getGender()));
            weight.setText(getString(R.string.weight_1_d,user.getWeight()));
            height.setText(getString(R.string.height_1_f,user.getHeight()));
            maxCalories.setText(getString(R.string.max_daily_calories_1_d,user.getMaxCalories()));
        }
    }
}
