package com.minigame.survey.pad;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by jonathanliono on 26/10/14.
 */
public class PadGameActivityTest extends PadGameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                new PadGame(8, PadGameActivityTest.this).startGame();
                return null;
            }
        }.execute();
    }

    @Override
    public void onGameFinish(double[] results) {
        StringBuffer sb = new StringBuffer();
        sb.append("Result: ");
        for(int i = 0; i < results.length; i++){
            if(i != 0)
            {
                sb.append(", ");
            }
            sb.append(results[i]);
        }
        Log.d("Girror", sb.toString());
    }
}
