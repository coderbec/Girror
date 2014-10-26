package becmartin.com.appdiction;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by sexybexy on 10/25/14.
 */
public class SettingSimpleActivity extends Activity {


    AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_settings);


        Button btnSave = (Button)findViewById(R.id.btnPreferences);
        btnSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateNotification();

            }
        });
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        setOneTimeAlarm();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    public void setOneTimeAlarm() {
        Intent intent = new Intent(this, TimeAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);
        am.set(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), pendingIntent);
    }

    public void setRepeatingAlarm(int interval) {
        Intent intent = new Intent(this, TimeAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                (interval), pendingIntent);
    }


    public void generateNotification(){

        Context context = SettingSimpleActivity.this
                .getApplicationContext();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(NOTIFICATION_SERVICE);

        Notification updateComplete = new Notification();
        updateComplete.icon = android.R.drawable.stat_notify_sync;
        updateComplete.tickerText = context
                .getText(R.string.notification_title);
        updateComplete.when = System.currentTimeMillis();

        Intent notificationIntent = new Intent(context,
                MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);

        String contentTitle = context.getText(R.string.notification_title)
                .toString();

        String contentText;
//        if (!result) {
//            Log.w(DEBUG_TAG, "XML download and parse had errors");
//            contentText = context.getText(R.string.notification_info_fail)
//                    .toString();
//        } else {
            contentText = context.getText(
                    R.string.notification_info_success).toString();
        //}
        updateComplete.setLatestEventInfo(context, contentTitle,
                contentText, contentIntent);

        notificationManager.notify(100, updateComplete);
    }
}