package estgf.ipp.pt.cmu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements Menu.OnFragmentSelectedListener {

    private Menu mMenu;
    private MarkActivity mMarkActivity;
    private StartActivity mStartActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.main_container) != null) {
            if (savedInstanceState != null)
                return;

            mMenu = new Menu();
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, mMenu).commit();
        }
    }


    @Override
    public void OnMarkActivity(MarkActivity markActivity) {
        mMarkActivity = (MarkActivity) getSupportFragmentManager().findFragmentById(R.id.b_fragment);
        mMarkActivity = new MarkActivity();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, mMarkActivity);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void OnStartActivity(StartActivity startActivity) {
        mStartActivity = (StartActivity) getSupportFragmentManager().findFragmentById(R.id.c_fragment);
        mStartActivity = new StartActivity();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, mStartActivity);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
