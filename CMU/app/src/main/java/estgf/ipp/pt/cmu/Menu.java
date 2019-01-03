package estgf.ipp.pt.cmu;

import android.content.Context;
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
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import estgf.ipp.pt.cmu.Database.Controllers.DBController;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetUsers;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.User.User;
import estgf.ipp.pt.cmu.Utilities.StaticHolder;

import static java.text.DateFormat.getTimeInstance;

public class Menu extends AppCompatActivity implements NotifyGetUsers, GoogleApiClient.ConnectionCallbacks {
    private GoogleApiClient client;
    private Calendar startTime;
    private Calendar endTime;
    private static final String LOG_TAG = "FitActivity";
    private float total;
    private float expendedCalories;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
context=this;
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


        FitnessOptions fitnessOptions =
                FitnessOptions.builder()
                        .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE,FitnessOptions.ACCESS_WRITE)
                        .addDataType(DataType.TYPE_STEP_COUNT_DELTA,FitnessOptions.ACCESS_WRITE)
                        .build();

        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    this,
                    System.identityHashCode(this) & 0xFFFF,
                    GoogleSignIn.getLastSignedInAccount(this),
                    fitnessOptions);
        } else {
            //connect();
            subscribe();
        }

    }



    public void subscribe() {
        Fitness.getRecordingClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .subscribe(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.i(LOG_TAG, "Successfully subscribed!");
                                    readData();

                                } else {
                                    Log.w(LOG_TAG, "There was a problem subscribing.", task.getException());
                                }
                            }
                        });
    }


    private void readData() {
        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(
                        new OnSuccessListener<DataSet>() {
                            @Override
                            public void onSuccess(DataSet dataSet) {

                                long total =
                                        dataSet.isEmpty()
                                                ? 0
                                                : dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                                Log.i(LOG_TAG, "Total steps: " + total);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(LOG_TAG, "There was a problem getting the step count.", e);
                            }
                        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode ==  (System.identityHashCode(this) & 0xFFFF)) {
                //connect();
                subscribe();
            }
        }
    }

    public void connect(){
        client  = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
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
            StaticHolder.maxCaloriesDay=userList.get(0).getMaxCalories();
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

        Fitness.getRecordingClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .subscribe(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(LOG_TAG, "Successfully subscribed!");

                        Fitness.getHistoryClient(context, GoogleSignIn.getLastSignedInAccount(context)).readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA).addOnSuccessListener(new OnSuccessListener<DataSet>() {
                            @Override
                            public void onSuccess(DataSet dataSet) {
                                dumpDataSet(dataSet);
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(LOG_TAG, "There was a problem subscribing.");
                    }
                });




    }



    private static void dumpDataSet(DataSet dataSet) {
        Log.i(LOG_TAG, "Data returned for Data type: " + dataSet.getDataType().getName());
        DateFormat dateFormat = getTimeInstance();

        for (DataPoint dp : dataSet.getDataPoints()) {
            Log.i(LOG_TAG, "Data point:");
            Log.i(LOG_TAG, "\tType: " + dp.getDataType().getName());
            Log.i(LOG_TAG, "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            Log.i(LOG_TAG, "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)));
            for (Field field : dp.getDataType().getFields()) {
                Log.i(LOG_TAG, "\tField: " + field.getName() + " Value: " + dp.getValue(field));
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }


}
