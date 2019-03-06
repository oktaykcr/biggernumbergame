package com.example.biggernumbergame.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.biggernumbergame.R;
import com.example.biggernumbergame.helpers.ActivityHelper;
import com.example.biggernumbergame.services.StorageService;

public class GameoverActivity extends AppCompatActivity {

    private TextView text_level;
    private TextView text_correct;
    private TextView text_highscore;

    private StorageService storageService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        storageService = new StorageService();

        text_level = findViewById(R.id.text_gameover_level);
        text_correct = findViewById(R.id.text_gameover_correct);
        text_highscore = findViewById(R.id.text_gameover_highscore);

        // Load level and correct data from gameplay layout
        int level = getIntent().getIntExtra("level", 0);
        int correctCount = getIntent().getIntExtra("correct", 0);
        text_level.setText(Integer.toString(level));
        text_correct.setText(Integer.toString(correctCount));

        String[] best = checkHighScore(level, correctCount);
        text_highscore.setText("Level: " + best[0] + ", Correct: " + best[1]);
    }

    public void onClickMenuButton(View view) {
        ActivityHelper.changeActivity(this, MainMenuActivity.class);
    }

    public void onClickRestartButton(View view) {
        ActivityHelper.changeActivity(this, GameplayActivity.class);
    }

    private String[] checkHighScore(int currentLevel, int currentCorrect) {
        StringBuilder builder = storageService.readFile(this, "high_score.txt");
        String[] best = builder.toString().split(",");
        Log.i("HIGH_SCORE", "Level: " + best[0] + " Correct: " + best[1]);
        if( currentLevel > Integer.parseInt(best[0]) || (currentLevel == Integer.parseInt(best[0]) && currentCorrect > Integer.parseInt(best[1]))) {
            best[0] = String.valueOf(currentLevel);
            best[1] = String.valueOf(currentCorrect);
            String newHighScore = best[0] + "," + best[1];
            storageService.writeFile(this, "high_score.txt", newHighScore);
            Log.i("HIGH_SCORE", " New High Score: Level: " + best[0] + " Correct: " + best[1]);
        }
        return best;
    }
}
