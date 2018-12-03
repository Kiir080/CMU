package pt.ipp.estg.cmu_projeto;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Menu extends Fragment implements View.OnClickListener{

    private OnFragmentSelectedListener mListener;

    public interface OnFragmentSelectedListener {
        void OnMarkActivity(MarkActivity markActivity);
        void OnStartActivity(StartActivity startActivity);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnPersonSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mContentView = inflater.inflate(R.layout.menu_layout, container, false);

        Button btnMarkActivity = (Button) mContentView.findViewById(R.id.mark_activity);
        btnMarkActivity.setOnClickListener(this);

        Button btnStartActivity = (Button) mContentView.findViewById(R.id.start_activity);
        btnStartActivity.setOnClickListener(this);

        return mContentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mark_activity:
                mListener.OnMarkActivity(new MarkActivity());
                break;
            case R.id.start_activity:
                mListener.OnStartActivity(new StartActivity());
                break;
        }
    }
}
