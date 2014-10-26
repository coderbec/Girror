package becmartin.com.appdiction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;


public class StatsActivity extends Activity {

    TextView ytdResponses;
    TextView lastSevenDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        findYearTotal();
        lastSevenDays();

        lastSevenDays = (TextView) findViewById(R.id.lastSevenDays);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stats, menu);
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

    /*
 * count of quizzes in liftime  by curent user
 */

    // need to populate myUser with currently authenticated user
    public void findYearTotal(){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Quiz");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
       // query.include("user");

        query.countInBackground(new CountCallback() {
            public void done(int count, ParseException e) {
                if (e == null) {
                    // The count request succeeded. Log the count
                    Log.d("score", "myUser has attempted " + count + " quizzes in total.");
                    ytdResponses = (TextView) findViewById(R.id.YTDResponses);
                    ytdResponses.setText("" + count);
                } else {

                    Log.e("error", e.getMessage());
                    // The request failed
                }
            }
        });

    }

    public void lastSevenDays(){
        /*
            * count of quizzes in last 7 days by curent user
         */

        // need to populate myUser with currently authenticated user
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Quiz");
        query.whereEqualTo("user", ParseUser.getCurrentUser());

        // calculate date 7 days ago
        Date dateThreshold = new Date(System.currentTimeMillis() - (7L * 24 * 3600 * 1000));

        query.whereGreaterThan("createdAt", dateThreshold);

        query.countInBackground(new CountCallback() {
            public void done(int count, ParseException e) {
                if (e == null) {
                    // The count request succeeded. Log the count
                    Log.d("score", "myUser has attempted " + count + " quizzes in total.");
                    lastSevenDays = (TextView) findViewById(R.id.lastSevenDays);
                    lastSevenDays.setText("" + count);
                } else {
                    // The request failed
                }
            }
        });
    }

}
