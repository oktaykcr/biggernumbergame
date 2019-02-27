package com.example.biggernumbergame.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.biggernumbergame.R;
import com.example.biggernumbergame.helpers.ActivityHelper;
import com.example.biggernumbergame.helpers.AlertDialogHelper;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public void onBackPressed() {
        AlertDialogHelper.showExitDialog(this);
    }

    public void changeActivity(View view) {
        ActivityHelper.changeActivity(this, GameplayActivity.class);
    }

    public void exitGame(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
