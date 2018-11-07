package com.example.caushie.flashcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        // Again order is important. you are getting the information from the other activity before launching it.

//We modify the edittext by finding its id and we set its content to be equal to the string we got from the other activity
        EditText questionField = (EditText) findViewById(R.id.editTextField);
        questionField.setText(getIntent().getStringExtra("pass_question1"));

        EditText answerField  = (EditText) findViewById(R.id.editTextAnswer);
        answerField.setText(getIntent().getStringExtra("pass_answer1"));

       // String question = getIntent().getStringExtra("pass_question1");
       // String answer = getIntent().getStringExtra("pass_answer1");

        findViewById(R.id.cancel_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        findViewById(R.id.save_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated values the user added to the question and answer text fields
                String question = ((EditText) findViewById(R.id.editTextField)).getText().toString();
                String answ = ((EditText) findViewById(R.id.editTextAnswer)).getText().toString();
                // Insert these updated question and answer values into an intent to send back as a result
                Intent data = new Intent();
                data.putExtra("string 1", question);
                data.putExtra("string 2", answ);
                setResult(RESULT_OK, data);
// Close the activity and send the result back to onActivityResult in MainActivity

                finish();
            }



        });


    }


}
