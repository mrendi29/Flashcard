package com.example.caushie.flashcard;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

        findViewById(R.id.plus_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                        MainActivity.this.startActivityForResult(intent , 100);
            }
        });

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.card2_answer).setVisibility(View.VISIBLE);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("string 1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("string 2");
            ((TextView)findViewById(R.id.flashcard_question)).setText(string1);
            ((TextView)findViewById(R.id.card2_answer)).setText(string2);
            findViewById(R.id.answer1).setVisibility(View.INVISIBLE);
            findViewById(R.id.answer2).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);


        }


    }
}