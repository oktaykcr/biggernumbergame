package com.example.biggernumbergame.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.biggernumbergame.R;
import com.example.biggernumbergame.helpers.AlertDialogHelper;
import com.example.biggernumbergame.helpers.MathHelper;

public class GameplayActivity extends AppCompatActivity {

    private RelativeLayout gameplay_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        gameplay_layout = findViewById(R.id.gameplay_layout);
    }

    /*
        To get gameplay layout width and height after ui fully loaded,
        otherwise getWidth and getHeight methods returns 0
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        spawnButtonOnRelativeLayout("1");
        spawnButtonOnRelativeLayout("2");
        spawnButtonOnRelativeLayout("3");
        spawnButtonOnRelativeLayout("4");
    }


    @Override
    public void onBackPressed() {
        AlertDialogHelper.showExitDialog(this);
    }

    private void spawnButtonOnRelativeLayout(String text) {
        float layout_spawn_end_x = gameplay_layout.getWidth() - 300f;
        float layout_spawn_end_y = gameplay_layout.getHeight() - 300f;

        float layout_spawn_start_x = 25f;
        float layout_spawn_start_y = 25f;

        RelativeLayout.LayoutParams Params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        Params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        Params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        Params.leftMargin = MathHelper.generateRandomNumber((int)layout_spawn_end_x, (int) layout_spawn_start_x);
        Params.topMargin = MathHelper.generateRandomNumber((int)layout_spawn_end_y, (int) layout_spawn_start_y);

        Button button = new Button(this);
        button.setText(text);

        gameplay_layout.addView(button, Params);

    }
}
