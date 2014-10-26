package becmartin.com.appdiction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 */
public class SurveyFragment extends Fragment {

    public static final String TAG = SurveyFragment.class.getSimpleName();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_survey,
                container, false);

        //new survey button
        Button newSurveyButton = (Button)rootView.findViewById(R.id.btnNewSurvey);
        newSurveyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewSurveyActivity.class);
                startActivity(intent);
            }
        });

        //view statistics button
        Button viewStatsButton = (Button)rootView.findViewById(R.id.btnViewStats);
        viewStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StatsActivity.class);
                startActivity(intent);
            }
        });

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
