package com.minigame.survey.pad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.ArrayList;

import becmartin.com.appdiction.R;

/**
 * Created by jonathanliono on 26/10/14.
 */
public abstract class PadGameActivity extends Activity {

    private ArrayList<Button> buttons;
    private Button startButton;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.fragment_game_pad);
        this.startButton = (Button)findViewById(R.id.minigame_pad_start);
        this.buttons = new ArrayList<Button>();
        buttons.add((Button)findViewById(R.id.minigame_pad_button1));
        buttons.add((Button)findViewById(R.id.minigame_pad_button2));
        buttons.add((Button)findViewById(R.id.minigame_pad_button3));
        buttons.add((Button)findViewById(R.id.minigame_pad_button4));
        buttons.add((Button)findViewById(R.id.minigame_pad_button5));
        buttons.add((Button)findViewById(R.id.minigame_pad_button6));
        buttons.add((Button)findViewById(R.id.minigame_pad_button7));
        buttons.add((Button)findViewById(R.id.minigame_pad_button8));
        buttons.add((Button)findViewById(R.id.minigame_pad_button9));
        this.tableLayout = (TableLayout)findViewById(R.id.minigame_pad_table);
    }

    public Button getStartButton(){
        return this.startButton;
    }

    public Button getButton(int pos)
    {
        return this.buttons.get(pos);
    }

    public void startGame()
    {
        this.startButton.setVisibility(View.GONE);
        this.tableLayout.setVisibility(View.VISIBLE);
    }

    public abstract void onGameFinish(double[] results);
}
