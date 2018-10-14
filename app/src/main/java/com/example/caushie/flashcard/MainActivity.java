package com.example.caushie.flashcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // If user guesses the Answer correctly its filled green.
        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setBackgroundColor(getResources().getColor(R.color.green));
            }
        });
        // If user guesses the Answer incorrectly the correct answer is filled green and incorrect answer is filled red.
        findViewById(R.id.answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer1).setBackgroundColor(getResources().getColor(R.color.red));
                findViewById(R.id.flashcard_answer).setBackgroundColor(getResources().getColor(R.color.green));
            }
        });
        // If user guesses the Answer incorrectly the correct answer is filled green and incorrect answer is filled red.
        findViewById(R.id.answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.red));
                findViewById(R.id.flashcard_answer).setBackgroundColor(getResources().getColor(R.color.green));
            }
        });
        // Reset the colors.
        findViewById(R.id.toggle_choices_visibility);
        findViewById(R.id.rootview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.yellow));
                findViewById(R.id.answer1).setBackgroundColor(getResources().getColor(R.color.yellow));
                findViewById(R.id.flashcard_answer).setBackgroundColor(getResources().getColor(R.color.yellow));


            }
        });
        // Answers Hide and Eye icon Changes
        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                findViewById(R.id.answer1).setVisibility(View.INVISIBLE);
                findViewById(R.id.answer2).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.toggle_choices_invisibility).setVisibility(View.VISIBLE);
            }
        });
        
        // Answers reappear and icon turns default
        findViewById(R.id.toggle_choices_invisibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer1).setVisibility(View.VISIBLE);
                findViewById(R.id.answer2).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                findViewById(R.id.toggle_choices_invisibility).setVisibility(View.INVISIBLE);

                }
        });




    }


}