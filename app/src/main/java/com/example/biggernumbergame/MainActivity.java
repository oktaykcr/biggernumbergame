package com.example.biggernumbergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.biggernumbergame.helpers.AlertDialogHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        AlertDialogHelper.showExitDialog(this);
    }

}