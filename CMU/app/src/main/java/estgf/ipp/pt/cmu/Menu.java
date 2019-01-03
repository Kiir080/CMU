package estgf.ipp.pt.cmu;

import android.app.Activity;
import android.app.PendingIntent;
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
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
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
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import estgf.ipp.pt.cmu.Database.Controllers.DBController;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyGetUsers;
import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Entities.User.User;
import estgf.ipp.pt.cmu.Utilities.MaxCaloriesDay;
import estgf.ipp.pt.cmu.Utilities.StaticHolder;

import static java.text.DateFormat.getTimeInstance;

public class Menu extends AppCompatActivity implements NotifyGetUsers, GoogleApiClient.ConnectionCallbacks {
    private final int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 0x1001;
    private GoogleApiClient client;
    private Calendar startTime;
    private Calendar endTime;
    private static final String TAG = "googleFit";
    private float total;
    private float expendedCalories;
    private Context context;
    private  GoogleSignInOptionsExtension fitnessOptions;

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

        initializeGoogleFit();

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

public void initializeGoogleFit(){
    this.fitnessOptions =FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .build();

    checkPermitions();
}

    public void checkPermitions(){
        if(!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
        GoogleSignIn.requestPermissions(
                this, // your activity instance
                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
                GoogleSignIn.getLastSignedInAccount(this),
                fitnessOptions);
    } else {
        accessGoogleFit();
    }}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == this.GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
                Log.i(TAG, "accessing...");
                accessGoogleFit();
            }
        }
    }

    private void accessGoogleFit() {
        client = new GoogleApiClient.Builder(this)
                            .addApi(Fitness.HISTORY_API)
                            .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                            .addConnectionCallbacks(this)
                            .build();
        client.connect();
    }







    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Fitness.getRecordingClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .subscribe(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(TAG, "Successfully subscribed!");
                        PendingResult<DailyTotalResult> dailyTotalResultPendingResult = Fitness.HistoryApi.readDailyTotal(client, DataType.TYPE_STEP_COUNT_DELTA );
                        dailyTotalResultPendingResult.setResultCallback(new ResultCallback<DailyTotalResult>() {
                            @Override
                            public void onResult(@NonNull DailyTotalResult dailyTotalResult) {
                                showDataSet(dailyTotalResult.getTotal());

                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "There was a problem subscribing.");
                    }
                });

        Fitness.getRecordingClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .subscribe(DataType.AGGREGATE_STEP_COUNT_DELTA)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(TAG, "Successfully subscribed!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "There was a problem subscribing.");
                    }
                });

//        teste  teste = new teste(client);
//        teste.execute();



    }


    private void showDataSet(DataSet dataSet) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        DateFormat timeFormat = DateFormat.getTimeInstance();
        for (DataPoint dp : dataSet.getDataPoints()) {
            Log.i("History", "Data point:");
            Log.i("History", "\tType: " + dp.getDataType().getName());
            Log.i("History", "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)) + " " + timeFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            Log.i("History", "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)) + " " + timeFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            for (Field field : dp.getDataType().getFields()) {
                Log.i("History", "\tField: " + field.getName() +
                        " Value: " + dp.getValue(field));
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
