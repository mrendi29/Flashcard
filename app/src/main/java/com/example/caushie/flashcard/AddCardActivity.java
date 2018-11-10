package com.example.caushie.flashcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        // Process 1 
        // Again order is important. you are getting the information from the other activity before launching it.

//We modify the edittext by finding its id and we set its content to be equal to the string we got from the other activity
        EditText questionField = (EditText) findViewById(R.id.editTextField);
        questionField.setText(getIntent().getStringExtra("pass_question1"));

        EditText answerField = findViewById(R.id.editTextAnswer);
        answerField.setText(getIntent().getStringExtra("pass_answer1"));
        ((EditText) findViewById(R.id.editTextAnsw2)).setText(getIntent().getStringExtra("pass_answer2"));
        ((EditText) findViewById(R.id.editTextAnsw3)).setText(getIntent().getStringExtra("pass_answer3"));

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
                // All of these actions are happening in the "body of save sign" this is a 3 step proces.
                // *Process 2*
                // Get the updated values the user added to the question and answer text fields
                String question = ((EditText) findViewById(R.id.editTextField)).getText().toString();
                String answ = ((EditText) findViewById(R.id.editTextAnswer)).getText().toString();
                String answ2 = ((EditText) findViewById(R.id.editTextAnsw2)).getText().toString();
                String answ3 = ((EditText) findViewById(R.id.editTextAnsw3)).getText().toString();

                // Insert these updated question and answer values into an intent to send back as a result

// Close the activity and send the result back to onActivityResult in MainActivity

                if (question.length() == 0 || answ.length() == 0 || answ2.length() == 0 || answ3.length() == 0) {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    // Process 3 passing the data we retrieved to the other activity.
                    Intent data = new Intent();
                    data.putExtra("string 1", question);
                    data.putExtra("string 2", answ);
                    data.putExtra("string 3", answ2);
                    data.putExtra("string 4", answ3);


                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });

    }


}
