package com.example.caushie.flashcard;

import android.animation.Animator;
import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private void startTimer() {
        countDownTimer.cancel();
        countDownTimer.start();
    }

    boolean isShowingAnswers = true;
    // 1.1  We create an instance of our database so we can read/write it.
    // It should be outside of OnCreate  to allow us to acces this variable in all methods of MainActivity  ***
    FlashcardDatabase flashcardDatabase;


    //2. we need to update our list of flashcards so that when one is added we can access it
    // this variable holds our flashcard objects (list of flashcards)
    List<Flashcard> allFlashcards;

    int currentCardDisplayIndex = 0;

    // Creating a Countdown Timer

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 1.2 Initializing flashcard database
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());


        setContentView(R.layout.activity_main);
        // 3. We can acces the cards after initialized.
        allFlashcards = flashcardDatabase.getAllCards();


        countDownTimer = new CountDownTimer(16000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.timer)).setText(""+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

            }
        };
        startTimer();


        //Gets the flashcards in the database at position 0. Should always be in Oncreate because
        // it'll first check the database to see if there's any saved flashcards before
        // displaying the default one that was populated from main_activity.xml.
        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.answer1)).setText(allFlashcards.get(0).getWrongAnswer1());
            ((TextView) findViewById(R.id.answer2)).setText(allFlashcards.get(0).getWrongAnswer2());
        }
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
                    isShowingAnswers = false;
                } else {
                    findViewById(R.id.answer1).setVisibility(View.VISIBLE);
                    findViewById(R.id.answer2).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.eye_on);
                    isShowingAnswers = true;
                }
            }
        });

        findViewById(R.id.plus_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        });
        // Sets new card answer to visible
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View answerSideView1 = findViewById(R.id.flashcard_answer);
                View answerSideView2 = findViewById(R.id.answer1);
                View answerSideView3 = findViewById(R.id.answer2);
                // Getting the center for  the clipping circle for each answer.

                int cx = answerSideView1.getWidth() / 2;
                int cy = answerSideView1.getHeight() / 2;

                // get the final radius for the clipping circle


                float finalRadius = (float) Math.hypot(cx, cy);
                // create the animator for this view (the start radius is zero)
                Animator anim1 = ViewAnimationUtils.createCircularReveal(answerSideView1, cx, cy, 0f, finalRadius);
                Animator anim2 = ViewAnimationUtils.createCircularReveal(answerSideView2, cx, cy, 0f, finalRadius);
                Animator anim3 = ViewAnimationUtils.createCircularReveal(answerSideView3, cx, cy, 0f, finalRadius);


                // Show questions and  prepare for playing the animation!
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                findViewById(R.id.answer1).setVisibility(View.VISIBLE);
                findViewById(R.id.answer2).setVisibility(View.VISIBLE);

                anim1.setDuration(1500);
                anim2.setDuration(1500);
                anim3.setDuration(1500);

                anim1.start();
                anim2.start();
                anim3.start();
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
                intent1.putExtra("pass_question1", pass_q1);
                intent1.putExtra("pass_answer1", pass_a1);
                intent1.putExtra("pass_answer2", pass_a2);
                intent1.putExtra("pass_answer3", pass_a3);

                // this is the prompt for the next activity
                MainActivity.this.startActivityForResult(intent1, 111);


            }
        });

        findViewById(R.id.next_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                     startTimer();

                if (allFlashcards.size() == 1 || allFlashcards.size() == 0) {
                    Toast.makeText(getApplicationContext(), "No saved flashcards", Toast.LENGTH_SHORT).show();
                }
                // advance our pointer index so we can show the next card
                currentCardDisplayIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we
                // are viewing the last indexed card in our list

                if (currentCardDisplayIndex > allFlashcards.size() - 1) {

                    currentCardDisplayIndex = 0;

                }

                // set the question and answer TextViews with data from the database
                if (allFlashcards.size() > 0) {

                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayIndex).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayIndex).getAnswer());
                    ((TextView) findViewById(R.id.answer1)).setText(allFlashcards.get(currentCardDisplayIndex).getWrongAnswer1());
                    ((TextView) findViewById(R.id.answer2)).setText(allFlashcards.get(currentCardDisplayIndex).getWrongAnswer2());
                }
                findViewById(R.id.answer1).setVisibility(View.INVISIBLE);
                findViewById(R.id.answer2).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                //  Toast.makeText(getApplicationContext(), "size is: " + allFlashcards.size() + " " + currentCardDisplayIndex  , Toast.LENGTH_SHORT).show();


                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts


                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }


                });
                // Animation should start after onClick
                findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                findViewById(R.id.flashcard_question).startAnimation(rightInAnim);

            }
        });

        // Deletes current flashcard and displays the previous one.
        findViewById(R.id.trashCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());


                allFlashcards = flashcardDatabase.getAllCards();


                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayIndex).getAnswer());
                ((TextView) findViewById(R.id.answer1)).setText(allFlashcards.get(currentCardDisplayIndex).getWrongAnswer1());
                ((TextView) findViewById(R.id.answer2)).setText(allFlashcards.get(currentCardDisplayIndex).getWrongAnswer2());


                if (currentCardDisplayIndex > allFlashcards.size() - 1) {

                    // currentCardDisplayIndex = 0;
                    Toast.makeText(getApplicationContext(), "No flashcards left", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == 100 || requestCode == 111) && (data != null)) { // this 100 needs to match the 100  or 111we used when we called startActivityForResult!

            String string1 = data.getExtras().getString("string 1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("string 2");
            String string3 = data.getExtras().getString("string 3");// 2 new multiple choice answers
            String string4 = data.getExtras().getString("string 4");


            //Here we are puting the strings we got from the other intent into this activity using textview and set text
            ((TextView) findViewById(R.id.flashcard_question)).setText(string1);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(string2);
            ((TextView) findViewById(R.id.answer2)).setText(string3);
            ((TextView) findViewById(R.id.answer1)).setText(string4);
            // Setting the answers invisible so the user can try to answer the question himself.
            findViewById(R.id.answer1).setVisibility(View.INVISIBLE);
            findViewById(R.id.answer2).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);

            // 2. Once we have our database instance***, in onActivityResult() we can add this line to save a flashcard:
            // string1 ,string2, string3, string4 are data we extracted by data.getExtras
            // there is a template in Flascard.java that assigns these strings to a name we can "ngjisim" to the database when we call it
            flashcardDatabase.insertCard(new Flashcard(string1, string2, string3, string4));


            // 3. We can acces the cards after initialized.
            allFlashcards = flashcardDatabase.getAllCards();


            Snackbar.make(findViewById(R.id.card2_answer),
                    "Card was created!",
                    Snackbar.LENGTH_SHORT)
                    .show();

        }


    }
}