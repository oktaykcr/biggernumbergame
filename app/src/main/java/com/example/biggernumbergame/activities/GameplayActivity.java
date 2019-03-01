package com.example.biggernumbergame.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.biggernumbergame.R;
import com.example.biggernumbergame.helpers.AlertDialogHelper;
import com.example.biggernumbergame.services.GameplayService;

public class GameplayActivity extends AppCompatActivity {

    private GridLayout gameplay_layout;
    private TextView text_time;
    private TextView text_correct;
    private TextView text_level;

    private GameplayService gameplayService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        gameplayService = new GameplayService();

        gameplay_layout = findViewById(R.id.gameplay_layout);
        text_time = findViewById(R.id.text_time);
        text_correct = findViewById(R.id.text_correct);
        text_level = findViewById(R.id.text_level);

        updateLevelText(gameplayService.getLevel());
        gameplayService.getButtonsFromGameplayLayout(gameplay_layout);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        gameplayService.startGame(text_time);
    }

    @Override
    public void onBackPressed() {
        AlertDialogHelper.showExitDialog(this);
    }

    public void onClickButton(View view) {
        Button button = (Button) view;
        Integer buttonNumber = Integer.valueOf(button.getText().toString());
        button.setVisibility(View.INVISIBLE);
        gameplayService.checkButtonNumber(this, buttonNumber);
        updateCorrectText(gameplayService.getCorrectCount());
        updateLevelText(gameplayService.getLevel());
    }

    private void updateCorrectText(int correct) {
        text_correct.setText(Integer.toString(correct));
    }

    private  void updateLevelText(int level) {
        text_level.setText(Integer.toString(level));
    }

}
