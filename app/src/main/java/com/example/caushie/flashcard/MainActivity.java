package com.example.caushie.flashcard;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean isShowingAnswers = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // If user guesses the Answer correctly its filled green.
        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findViewById(R.id.flashcard_answer).setBackgroundResource(R.drawable.drawable_correct);
            }
        });
        // If user guesses the Answer incorrectly the correct answer is filled green and incorrect answer is filled red.
        findViewById(R.id.answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer1).setBackgroundResource(R.drawable.drawable_incorect);
                findViewById(R.id.flashcard_answer).setBackgroundResource(R.drawable.drawable_correct);
            }
        });
        // If user guesses the Answer incorrectly the correct answer is filled green and incorrect answer is filled red.
        findViewById(R.id.answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer2).setBackgroundResource(R.drawable.drawable_incorect);
                findViewById(R.id.flashcard_answer).setBackgroundResource(R.drawable.drawable_correct);
            }
        });
        // Reset the colors.
        findViewById(R.id.toggle_choices_visibility);
        findViewById(R.id.rootview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer2).setBackgroundResource(R.drawable.card_answer);
                findViewById(R.id.answer1).setBackgroundResource(R.drawable.card_answer);
                findViewById(R.id.flashcard_answer).setBackgroundResource(R.drawable.card_answer);


            }
        });

        // Answers Hide and Eye icon Changes
        // boolean is used to know when to show and hide answers.
        // false and true values are passed between the if and else statement to achieve a " loop" effect
        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                if (isShowingAnswers) {
                    findViewById(R.id.answer1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.answer2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.eye_off);
                    isShowingAnswers=false;
            }
            else {
                    findViewById(R.id.answer1).setVisibility(View.VISIBLE);
                    findViewById(R.id.answer2).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.eye_on);
                    isShowingAnswers=true;
                }
            }
        });






    }


}