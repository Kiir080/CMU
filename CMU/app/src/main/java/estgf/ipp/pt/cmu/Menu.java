package estgf.ipp.pt.cmu;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import estgf.ipp.pt.cmu.API.Controllers.GoogleFitController;
import estgf.ipp.pt.cmu.Database.Controllers.DBController;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetUsers;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.User.User;
import estgf.ipp.pt.cmu.Utilities.MaxCaloriesDay;
import estgf.ipp.pt.cmu.Utilities.StaticHolder;

import static java.text.DateFormat.getTimeInstance;

public class Menu extends AppCompatActivity implements NotifyGetUsers, GoogleApiClient.ConnectionCallbacks {
    private GoogleApiClient client;
    private Calendar startTime;
    private Calendar endTime;
    private static final String LOG_TAG = "FitActivity";
    private float total;
    private float expendedCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        Button btnMarkActivity = (Button) findViewById(R.id.mark_activity);
        Button btnStartActivity = (Button) findViewById(R.id.start_activity);
        Button btnMealActivity = (Button) findViewById(R.id.meal_activity);
        FloatingActionButton mapButton = (FloatingActionButton) findViewById(R.id.showMapButton);


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

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        DBController dbController = new DBController(this);
        dbController.getUsers(this);

        FitnessOptions fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .build();

        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    this,
                    System.identityHashCode(this) & 0xFFFF,
                    GoogleSignIn.getLastSignedInAccount(this),
                    fitnessOptions);
        } else {
            connect();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode ==  (System.identityHashCode(this) & 0xFFFF)) {
                //accessGoogleFit();
                connect();
            }
        }
    }

    public void connect(){
        client  = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                .addConnectionCallbacks(this)
                .useDefaultAccount().build();

        client.connect();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.meal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this,UserInformation.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnGetUsers(List<User> userList) {
        if(userList== null || userList.size()==0){
            Intent intent = new Intent(this,Register.class);
            startActivity(intent);
        }else{
            MaxCaloriesDay.maxCaloriesDay=userList.get(0).getMaxCalories();
            if(userList.get(0).getADDED_DEFAULT_MEALS() != null){
                for (Meal meal:
                        userList.get(0).getADDED_DEFAULT_MEALS()) {
                    StaticHolder.addMealToDefault(meal);
                }
            }

        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY,0);
        startTime.set(Calendar.MINUTE,1);
        endTime=Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY,23);
        endTime.set(Calendar.MINUTE,59);



        DataReadRequest readRequest = new DataReadRequest.Builder()
                .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
                .aggregate(DataType.TYPE_CALORIES_EXPENDED, DataType.AGGREGATE_CALORIES_EXPENDED)
                .bucketByActivityType(1, TimeUnit.SECONDS)
                .setTimeRange(startTime.getTimeInMillis(), endTime.getTimeInMillis(), TimeUnit.MILLISECONDS)
                .build();

        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readData(readRequest)
                .addOnSuccessListener(new OnSuccessListener<DataReadResponse>() {
                    @Override
                    public void onSuccess(DataReadResponse dataReadResponse) {
                        for (Bucket bucket : dataReadResponse.getBuckets()) {

                            List<DataSet> dataSetx =  bucket.getDataSets();





                            for (DataSet dataSet : dataSetx) {

                                if(dataSet.getDataType().getName().equals("com.google.step_count.delta"))
                                {


                                    if(dataSet.getDataPoints().size() > 0)
                                    {
                                        // total steps
                                        total = total +  dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();

                                    }

                                }

                            }
                            String bucketActivity = bucket.getActivity();

                                List<DataSet> dataSets = bucket.getDataSets();
                                for (DataSet dataSet : dataSets) {

                                    if(dataSet.getDataType().getName().equals("com.google.calories.expended")) {

                                        for (DataPoint dp : dataSet.getDataPoints()) {

                                            if (dp.getEndTime(TimeUnit.MILLISECONDS) > dp.getStartTime(TimeUnit.MILLISECONDS)) {
                                                for (Field field : dp.getDataType().getFields()) {
                                                    // total calories burned
                                                    expendedCalories = expendedCalories + dp.getValue(field).asFloat();
                                                }

                                            }

                                        }

                                    }

                                }



                        }


                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(LOG_TAG, "onFailure()", e);
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<DataReadResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<DataReadResponse> task) {
                        Log.d(LOG_TAG, "onComplete()");
                    }
                });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
