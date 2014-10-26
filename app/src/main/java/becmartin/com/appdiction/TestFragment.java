package becmartin.com.appdiction;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by sexybexy on 10/25/14.
 */
public class TestFragment extends Fragment{

    public static final String TAG = SurveyFragment.class.getSimpleName();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test,
                container, false);


        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
