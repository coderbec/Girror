package com.minigame.survey.pad;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;
import java.util.Random;

/**
 * Created by jonathanliono on 26/10/14.
 */
public class PadGame {
    private int maxSteps;
    private Random randomEngine;
    private PadGameActivity gameActivity;
    private double[] responseResults;

    public PadGame(int steps, PadGameActivity activity)
    {
        this.maxSteps = steps;
        this.responseResults = new double[maxSteps];
        this.gameActivity = activity;
        this.randomEngine = new Random(50);
    }

    public void startGame(){
        Button startButton = this.gameActivity.getStartButton();
        Log.d("Girror", startButton.toString());
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        // this will make start button disappear and display number pad.
                        gameActivity.startGame();

                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                // start test logic.
                                gameActivity.onGameFinish(startTest());
                                return null;
                            }
                        }.execute();
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        return null;
                    }
                }.execute();
            }
        });
    }

    public double[] startTest(){
        for(int i = 0; i < this.maxSteps; i++){
            final boolean[] start = {true};
            final int step = i;
            final long recordStartTime = new Date().getTime();
            int next = this.getNextRandomNumber();
            final Button button = this.gameActivity.getButton(next);
            gameActivity.runOnUiThread(new Runnable(){

                @Override
                public void run()
                {
                    button.setBackgroundColor(Color.RED);
                }
            });

            button.setOnClickListener(new View.OnClickListener(){
                                          @Override
                                          public void onClick(View view) {
                                              long recordFinishTime = new Date().getTime();
                                              Long difference = recordFinishTime - recordStartTime;
                                              double differenceDbl = difference.doubleValue() / 1000;
                                              responseResults[step] = differenceDbl;
                                              start[0] = false;

                                              gameActivity.runOnUiThread(new Runnable(){

                                                  @Override
                                                  public void run()
                                                  {
                                                      button.setBackgroundResource(android.R.drawable.btn_default);
                                                      // button.setBackgroundColor(Color.GRAY);
                                                      button.setOnClickListener(null);
                                                  }
                                              });
                                          }
                                      }
            );

            while (start[0])
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
            }
        }

        return this.responseResults;
    }

    private int getNextRandomNumber(){
        return this.randomEngine.nextInt(8);
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public void setMaxSteps(int maxSteps) {
        this.maxSteps = maxSteps;
    }
}
