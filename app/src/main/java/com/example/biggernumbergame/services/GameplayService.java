package com.example.biggernumbergame.services;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.biggernumbergame.R;
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
    private int countDown = 10;
    private boolean gameover = false;
    private int level = 1;
    private int correctCount = 0;
    private List<Integer> currentNumbersInGameLayout = new ArrayList<>();


    public void startGame(final TextView text_time) {
        prepareLevel(level);
        resetTimer(text_time);
    }

    public void prepareLevel(int level) {
        // shuffle button index to visible random button on the gameplay layout
        List<Integer> buttonRandomIndex = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
        Collections.shuffle(buttonRandomIndex);
        // evert correct count reset countDown according to level
        System.out.println("LEVEL : " + level);
        this.countDown = 10 - level + 2;
        System.out.println(countDown);
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
    }

    public void increaseLevel() {
        resetButtonsAndValues();
        correctCount = 0;
        if(level < 10) {
            this.level += 1;
            prepareLevel(this.level);
        }
    }

    public void continueLevel() {
        resetButtonsAndValues();
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

        // Set random background to button
        TypedArray drawable_buttons = spawnedButtons.get(index).getContext().getResources().obtainTypedArray(R.array.drawable_buttons);
        int choice = (int) (Math.random() * drawable_buttons.length());
        spawnedButtons.get(index).setBackgroundResource(drawable_buttons.getResourceId(choice, R.drawable.btn_number));
        drawable_buttons.recycle();

        // if the button is spawned left run left to right anim, otherwise right to left
        Animation slide;
        if(index % 2 == 0) {
            slide = AnimationUtils.loadAnimation(spawnedButtons.get(index).getContext(), R.anim.anim_slide_lefttoright);
        } else {
            slide = AnimationUtils.loadAnimation(spawnedButtons.get(index).getContext(), R.anim.anim_slide_righttoleft);
        }
        spawnedButtons.get(index).setAnimation(slide);
    }

    public void getButtonsFromGameplayLayout(GridLayout gameplay_layout) {

        for(int i = 0 ; i < gameplay_layout.getChildCount() ; i++) {
            Button button = (Button) gameplay_layout.getChildAt(i);
            button.setVisibility(View.INVISIBLE);
            spawnedButtons.add(button);
        }
    }

    public void checkButtonNumber(Context context, TextView text_time, Integer number) {
        if(Collections.max(currentNumbersInGameLayout).equals(number)) {
            if(correctCount == 4) increaseLevel();
            else continueLevel();
            resetTimer(text_time);
        } else {
            gameOver(context);
        }
    }

    /**
     * make buttons visibility to INVISIBLE in continue level or increase level
     * clear current numbers array in the layout to pass next level or continue
     */
    private void resetButtonsAndValues() {
        currentNumbersInGameLayout.clear(); // clear old numbers
        for ( Button button : spawnedButtons) {
            if(button.getVisibility() == View.VISIBLE) {
                button.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void gameOver(Context context) {
        gameover = true;
        countDownTimer.cancel();
        Map<String, Integer> data = new HashMap<>();
        data.put("level", this.level);
        data.put("correct", this.correctCount);
        ActivityHelper.changeActivityWithIntegerData(context, GameoverActivity.class, data);
    }

    private void resetTimer(final TextView text_time) {
        if(countDownTimer != null)
            countDownTimer.cancel();

        countDownTimer = new CountDownTimer(countDown * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                countDown =  (int) millisUntilFinished / 1000;
                System.out.println("COUNTDOWN : " + countDown);
                text_time.setText("" + countDown);
            }

            @Override
            public void onFinish() {
                gameOver(text_time.getContext());
            }
        }.start();

    }

    public int getLevel() {
        return level;
    }

    public int getCorrectCount() {
        return correctCount;
    }

}
