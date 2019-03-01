package com.example.biggernumbergame.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.biggernumbergame.R;
import com.example.biggernumbergame.helpers.ActivityHelper;

public class GameoverActivity extends AppCompatActivity {

    private TextView text_level;
    private TextView text_correct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        text_level = findViewById(R.id.text_gameover_level);
        text_correct = findViewById(R.id.text_gameover_correct);

        // Load level and correct data from gameplay layout
        int level = getIntent().getIntExtra("level", 0);
        int correctCount = getIntent().getIntExtra("correct", 0);
        text_level.setText(Integer.toString(level));
        text_correct.setText(Integer.toString(correctCount));

    }


    public void onClickMenuButton(View view) {
        ActivityHelper.changeActivity(this, MainMenuActivity.class);
    }

    public void onClickRestartButton(View view) {
        ActivityHelper.changeActivity(this, GameplayActivity.class);
    }
}
