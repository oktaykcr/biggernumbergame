package com.example.biggernumbergame.services;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.biggernumbergame.helpers.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class GameplayService {

    private List<Button> spawnedButtons = new ArrayList<>();
    private CountDownTimer countDownTimer;
    private boolean gameover = false;
    private int level = 1;
    private int correctCount = 0;


    public void startGame(final TextView text_time) {
        prepareLevel(level);
        countDownTimer = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                text_time.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                setGameover(true);
                text_time.setText("Finish!");
            }
        }.start();
    }

    public void prepareLevel(int level) {

        switch (level) {
            case 1:
                for(int i = 0 ; i < 2 ; i++) {
                    spawnButton(MathHelper.generateRandomNumber(0, 7),
                            10, 0);
                }
                break;
            case 2:
                for(int i = 0 ; i < 3 ; i++) {
                    spawnButton(MathHelper.generateRandomNumber(0, 7),
                            20, 0);
                }
                break;
            case 3:
                for(int i = 0 ; i < 3 ; i++) {
                    spawnButton(MathHelper.generateRandomNumber(0, 7),
                            30, 0);
                }
                break;
            case 4:
                for(int i = 0 ; i < 4 ; i++) {
                    spawnButton(MathHelper.generateRandomNumber(0, 7),
                            40, 0);
                }
                break;
            case 5:
                for(int i = 0 ; i < 4 ; i++) {
                    spawnButton(MathHelper.generateRandomNumber(0, 7),
                            40, -40);
                }
                break;
            case 6:
                for(int i = 0 ; i < 4 ; i++) {
                    spawnButton(MathHelper.generateRandomNumber(0, 7),
                            60, -60);
                }
                break;
            case 7:
                for(int i = 0 ; i < 4 ; i++) {
                    spawnButton(MathHelper.generateRandomNumber(0, 7),
                            100, -100);
                }
                break;
            case 8:
                for(int i = 0 ; i < 5 ; i++) {
                    spawnButton(MathHelper.generateRandomNumber(0, 7),
                            100, -100);
                }
                break;
            case 9:
                for(int i = 0 ; i < 6 ; i++) {
                    spawnButton(MathHelper.generateRandomNumber(0, 7),
                            100, -100);
                }
                break;
            case 10:
                for(int i = 0 ; i < 8 ; i++) {
                    spawnButton(MathHelper.generateRandomNumber(0, 7),
                            100, -100);
                }
                break;
        }
    }

    public void increaseLevel() {
        resetButtons();
        correctCount = 0;
        if(level < 10) {
            this.level += 1;
            prepareLevel(this.level);
        }
    }

    /**
     * set visibility of buttons to INVISIBLE for spawn again
     */
    private void resetButtons() {
        for( Button button : spawnedButtons) {
            button.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * if the button is invisible so it can be spawned on the layout
     * @param index list index of buttons
     * @param upper generated number
     * @param lower generated number
     */
    private void spawnButton(int index, int upper, int lower) {
        while(true){
            if(spawnedButtons.get(index).getVisibility() == View.INVISIBLE) {
                spawnedButtons.get(index).setVisibility(View.VISIBLE);
                int randomInt = MathHelper.generateRandomNumber(upper, lower);
                spawnedButtons.get(index).setText(String.valueOf(randomInt));
                break;
            }
        }
    }

    public void getButtonsFromGameplayLayout(GridLayout gameplay_layout) {

        for(int i = 0 ; i < gameplay_layout.getChildCount() ; i++) {
            Button button = (Button) gameplay_layout.getChildAt(i);
            button.setVisibility(View.INVISIBLE);
            spawnedButtons.add(button);
        }
    }

    public List<Button> getSpawnedButtons() {
        return spawnedButtons;
    }

    public void setSpawnedButtons(List<Button> spawnedButtons) {
        this.spawnedButtons = spawnedButtons;
    }

    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }
}
