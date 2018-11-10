package com.example.caushie.flashcard;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
        // Sets new card answer to visible
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                findViewById(R.id.answer1).setVisibility(View.VISIBLE);
                findViewById(R.id.answer2).setVisibility(View.VISIBLE);
            }
        });

        //Passing data to AdddCardActivity
        findViewById(R.id.pencil_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intent makes possible passing data to new activity when we want to edit the one we already have.
                Intent intent1 = new Intent(MainActivity.this, AddCardActivity.class);
// the order of the operations is really important. you are creating an intent then  passing the textview into strings

                String pass_q1 = ((TextView) findViewById(R.id.flashcard_question)).getText().toString();  //Convert to string
                String pass_a1 = ((TextView) findViewById(R.id.answer1)).getText().toString();
                String pass_a2 = ((TextView) findViewById(R.id.answer2)).getText().toString();
                String pass_a3 = ((TextView) findViewById(R.id.flashcard_answer)).getText().toString();



                // Then you pass the stirings into the intent with a key to retrieve in the other activity.
                intent1.putExtra("pass_question1",pass_q1);
                intent1.putExtra("pass_answer1" ,pass_a1);
                intent1.putExtra("pass_answer2", pass_a2);
                intent1.putExtra("pass_answer3", pass_a3);

                // this is the prompt for the next activity
                MainActivity.this.startActivityForResult(intent1, 111);





            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 || requestCode == 111) { // this 100 needs to match the 100  or 111we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("string 1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("string 2");
            String string3 = data.getExtras().getString("string 3");// 2 new multiple choice answers
            String string4 = data.getExtras().getString("string 4");


            //Here we are puting the strings we got from the other intent into this activity using textview and set text
            ((TextView)findViewById(R.id.flashcard_question)).setText(string1);
            ((TextView)findViewById(R.id.flashcard_answer)).setText(string2);
            ((TextView) findViewById(R.id.answer2)).setText(string3);
            ((TextView) findViewById(R.id.answer1)).setText(string4);
            // Setting the answers invisible so the user can try to answer the question himself.
            findViewById(R.id.answer1).setVisibility(View.INVISIBLE);
            findViewById(R.id.answer2).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);



            Snackbar.make(findViewById(R.id.card2_answer),
            "Card was created!",
            Snackbar.LENGTH_SHORT)
            .show();

        }


    }
}