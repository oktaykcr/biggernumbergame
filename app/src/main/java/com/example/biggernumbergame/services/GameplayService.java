package com.example.biggernumbergame.services;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.biggernumbergame.activities.GameoverActivity;
import com.example.biggernumbergame.helpers.ActivityHelper;
import com.example.biggernumbergame.helpers.MathHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameplayService {

    private List<Button> spawnedButtons = new ArrayList<>();
    private CountDownTimer countDownTimer;
    private boolean gameover = false;
    private int level = 1;
    private int correctCount = 0;
    private List<Integer> currentNumbersInGameLayout = new ArrayList<>();


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
        // shuffle button index to visible random button on the gameplay layout
        List<Integer> buttonRandomIndex = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
        Collections.shuffle(buttonRandomIndex);
        switch (level) {
            case 1:
                for(int i = 0 ; i < 2 ; i++) {
                    spawnButton(buttonRandomIndex.get(i),
                            10, 0);
                }
                break;
            case 2:
                for(int i = 0 ; i < 3 ; i++) {
                    spawnButton(buttonRandomIndex.get(i),
                            20, 0);
                }
                break;
            case 3:
                for(int i = 0 ; i < 3 ; i++) {
                    spawnButton(buttonRandomIndex.get(i),
                            30, 0);
                }
                break;
            case 4:
                for(int i = 0 ; i < 4 ; i++) {
                    spawnButton(buttonRandomIndex.get(i),
                            40, 0);
                }
                break;
            case 5:
                for(int i = 0 ; i < 4 ; i++) {
                    spawnButton(buttonRandomIndex.get(i),
                            40, -40);
                }
                break;
            case 6:
                for(int i = 0 ; i < 4 ; i++) {
                    spawnButton(buttonRandomIndex.get(i),
                            60, -60);
                }
                break;
            case 7:
                for(int i = 0 ; i < 4 ; i++) {
                    spawnButton(buttonRandomIndex.get(i),
                            100, -100);
                }
                break;
            case 8:
                for(int i = 0 ; i < 5 ; i++) {
                    spawnButton(buttonRandomIndex.get(i) ,
                            100, -100);
                }
                break;
            case 9:
                for(int i = 0 ; i < 6 ; i++) {
                    spawnButton(buttonRandomIndex.get(i),
                            100, -100);
                }
                break;
            case 10:
                for(int i = 0 ; i < 8 ; i++) {
                    spawnButton(buttonRandomIndex.get(i),
                            100, -100);
                }
                break;
        }
        Collections.sort(currentNumbersInGameLayout); // sort the number after create level for checking process
    }

    public void increaseLevel() {
        currentNumbersInGameLayout.clear(); // clear old numbers
        correctCount = 0;
        if(level < 10) {
            this.level += 1;
            prepareLevel(this.level);
        }
    }

    public void continueLevel() {
        currentNumbersInGameLayout.clear(); // clear old numbers
        prepareLevel(this.level);
        correctCount += 1;
    }

    /**
     * if the button is invisible so it can be spawned on the layout
     * @param index index of button which already created on the gameplay layout
     * @param upper generated number
     * @param lower generated number
     */
    private void spawnButton(int index, int upper, int lower) {
        spawnedButtons.get(index).setVisibility(View.VISIBLE);
        int randomInt = MathHelper.generateRandomNumber(upper, lower);
        currentNumbersInGameLayout.add(randomInt); // save all created button numbers to check
        spawnedButtons.get(index).setText(String.valueOf(randomInt));
    }

    public void getButtonsFromGameplayLayout(GridLayout gameplay_layout) {

        for(int i = 0 ; i < gameplay_layout.getChildCount() ; i++) {
            Button button = (Button) gameplay_layout.getChildAt(i);
            button.setVisibility(View.INVISIBLE);
            spawnedButtons.add(button);
        }
    }

    public void checkButtonNumber(Context context, Integer number) {
        System.out.println(currentNumbersInGameLayout.toString());
        if(currentNumbersInGameLayout.get(0).equals(number)) {
            currentNumbersInGameLayout.remove(0);
            if(currentNumbersInGameLayout.isEmpty()) {
                if(correctCount == 4) increaseLevel();
                else continueLevel();
            }
        } else {
            gameOver(context);
            System.out.println("GAME OVER : " + gameover);
        }
    }

    private void gameOver(Context context) {
        gameover = true;
        Map<String, Integer> data = new HashMap<>();
        data.put("level", this.level);
        data.put("correct", this.correctCount);
        ActivityHelper.changeActivityWithIntegerData(context, GameoverActivity.class, data);
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
