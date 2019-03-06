package com.example.biggernumbergame.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.biggernumbergame.R;
import com.example.biggernumbergame.helpers.ActivityHelper;
import com.example.biggernumbergame.helpers.AlertDialogHelper;
import com.example.biggernumbergame.services.StorageService;

public class MainMenuActivity extends AppCompatActivity {

    private StorageService storageService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        storageService = new StorageService();

        // play fade in animation on create activity
        overridePendingTransition(R.anim.anim_fadein, R.anim.anim_fadeout);
    }

    @Override
    public void onBackPressed() {
        AlertDialogHelper.showExitDialog(this);
    }

    public void onClickStart(View view) {
        ActivityHelper.changeActivity(this, GameplayActivity.class);
    }

    public void onClickHighScore(View view) {
        StringBuilder builder = storageService.readFile(this, "high_score.txt");
        String[] best = builder.toString().split(",");
        Log.i("HIGH_SCORE", "Level: " + best[0] + " Correct: " + best[1]);
        Toast.makeText(this, "Level: " + best[0] + " Correct: " + best[1], Toast.LENGTH_SHORT).show();
    }

    public void onClickExit(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
