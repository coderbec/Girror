package becmartin.com.appdiction;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NewSurveyActivity extends Activity implements GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener  {

    private LocationRequest locationRequest;
      private LocationClient locationClient;
    private ParseGeoPoint geoPoint;
    String questionText = "How happy are you right now";
    int value = 5;
    Date dateobj;
    ParseUser currentUser;
    private SeekBar happySad;
    private SeekBar alert;
    private SeekBar enthusiastic;
    private SeekBar nervous;
    private SeekBar frustrated;
    Quiz Quiz = new Quiz();

    private RadioButton alone;
    private RadioButton withOther;
    private CheckBox checkBoxSpouse;
    private CheckBox checkBoxChildren;
    private CheckBox checkBoxOtherFamily;
    private CheckBox checkBoxColleagues;
    private CheckBox checkBoxFriends;
    private CheckBox checkBoxOther;
    private Spinner spinnerGambling;
    private RadioButton radioAlcoholYes;
    private RadioButton radioAlcoholNo;

    private LocationClient myLocationClient;

    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        myLocationClient = new LocationClient(getApplicationContext(), this, this);
// once we have the reference to the client, connect it
        if(myLocationClient != null)
            myLocationClient.connect();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_survey);

        DateFormat dateFormat = new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss.SSS'Z'");
        //get current date time with Date()
        dateobj = new Date();

        currentUser = ParseUser.getCurrentUser();

        Button sendButton = (Button)findViewById(R.id.btnSubmit);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Quiz();
                Recording();
            }
        });

        /*happy_score: Number
    frustrated_score: Number
    alert_score: Number
    enthusiasm_score: Number
    nervous_score: Number
    quiz: Quiz*/


       //android:id="@+id/seekBarHappySad"
       happySad = (SeekBar) findViewById(R.id.seekBarHappySad);

        //android:id="@+id/seekBarAlert"
        alert = (SeekBar) findViewById(R.id.seekBarAlert);

        //android:id="@+id/seekBarEnthusiastic"
        enthusiastic = (SeekBar) findViewById(R.id.seekBarEnthusiastic);

      //  android:id="@+id/seekBarNervous"
        nervous = (SeekBar) findViewById(R.id.seekBarNervous);

       // android:id="@+id/seekBarFrustrated"
        frustrated = (SeekBar) findViewById(R.id.seekBarFrustrated);

        alone = (RadioButton) findViewById(R.id.radioAlone);
        withOther = (RadioButton) findViewById(R.id.radioWithOther);
        checkBoxSpouse = (CheckBox) findViewById(R.id.checkBox1);
        checkBoxChildren = (CheckBox) findViewById(R.id.checkBox2);
        checkBoxOtherFamily = (CheckBox) findViewById(R.id.checkBox3);
        checkBoxColleagues = (CheckBox) findViewById(R.id.checkBox4);
        checkBoxFriends = (CheckBox) findViewById(R.id.checkBox5);
        checkBoxOther = (CheckBox) findViewById(R.id.checkBox6);
        spinnerGambling = (Spinner) findViewById(R.id.spinnerGambling);
        radioAlcoholYes = (RadioButton) findViewById(R.id.radioYes);
        radioAlcoholNo = (RadioButton) findViewById(R.id.radioNo);

        }

    private void Quiz() {

        Location location = myLocationClient.getLastLocation();
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        geoPoint = new ParseGeoPoint(latitude, longitude);

        // 1
        //Quiz = new Quiz();
        Quiz.setLocation(geoPoint);
        Quiz.setStartTime(dateobj);
        Quiz.setUser(currentUser);


        // 2
        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(true);
        Quiz.setACL(acl);



        // 3
        Quiz.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {


                if (e == null) {
                    // Success!
                    Toast.makeText(getApplicationContext(), "Survey Results saved",
                            Toast.LENGTH_LONG).show();
                } else {
                    Log.e("Error", e.getMessage());
                }


            }
        });




    }

    private void Recording() {



        // 1
        Recording recording = new Recording();
        recording.setHappyScore(happySad.getProgress());
        recording.setAlertScore(alert.getProgress());
        recording.setEnthusiasmScore(enthusiastic.getProgress());
        recording.setFrustratedScore(frustrated.getProgress());
        recording.setNervousScore(nervous.getProgress());
        recording.setQuiz(Quiz);
        recording.setAlcohol(returnAlcohol());
        recording.setWhatDoing(returnWhatDoing());
        recording.setOther(checkBoxOther.isChecked());
        recording.setFriends(checkBoxFriends.isChecked());
        recording.setColleagues(checkBoxColleagues.isChecked());
        recording.setOtherFamily(checkBoxOtherFamily.isChecked());
        recording.setChildren(checkBoxChildren.isChecked());
        recording.setPartner(checkBoxSpouse.isChecked());
        recording.setAlone(alone.isChecked());

        // 2
       // ParseACL acl = new ParseACL();
        //acl.setPublicReadAccess(true);
        //acl.setPublicWriteAccess(true);
        //recording.setACL(acl);



        // 3
        recording.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {
                    // Success!
                    Toast.makeText(getApplicationContext(), "Recording Results saved",
                            Toast.LENGTH_LONG).show();
                } else {
                    Log.e("Error", e.getMessage());
                }


            }
        });


    }


    private String returnWhatDoing() {

        return String.valueOf(spinnerGambling.getSelectedItem());
    }

    private Boolean returnAlcohol() {
        if(radioAlcoholYes.isChecked()){
            return true;
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_survey, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {

    }


    @Override
    public void onConnected(Bundle bundle) {
        myLocationClient.requestLocationUpdates( REQUEST, this);

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }



    public static class ErrorDialogFragment extends DialogFragment {
        private Dialog mDialog;

        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }

        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }

    private boolean servicesConnected() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (ConnectionResult.SUCCESS == resultCode) {
            return true;
        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 0);
            if (dialog != null) {
               // ErrorDialogFragment errorFragment = new ErrorDialogFragment();
               // errorFragment.setDialog(dialog);
               // errorFragment.show(this, this.APPTAG);
            }
            return false;
        }
    }




}