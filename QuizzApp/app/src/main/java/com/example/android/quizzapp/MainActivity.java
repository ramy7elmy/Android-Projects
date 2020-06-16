package com.example.android.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Called when click on submit button.
    public void answersEvaluation(View view) {
        // Number of correct answers.
        int correctAnswers = 0;
        // Number of wrong answers.
        int wrongAnswers = 0;
        // Checking question #1 user answer.
        RadioGroup radioGroupQ1Answers = findViewById(R.id.Q1Answers);
        if (radioGroupQ1Answers.getCheckedRadioButtonId() == R.id.Q1Answer4)
            correctAnswers++;
        else wrongAnswers++;
        // Checking question #2 user answer.
        RadioGroup radioGroupQ2Answers = findViewById(R.id.Q2Answers);
        if (radioGroupQ2Answers.getCheckedRadioButtonId() == R.id.Q2Answer1)
            correctAnswers++;
        else wrongAnswers++;
        // Checking question #3 user answer.
        RadioGroup radioGroupQ3Answers = findViewById(R.id.Q3Answers);
        if (radioGroupQ3Answers.getCheckedRadioButtonId() == R.id.Q3Answer1)
            correctAnswers++;
        else wrongAnswers++;
        // Checking question #4 user answer.
        EditText editTextQ4Answer = findViewById(R.id.Q4Answer);
        if (editTextQ4Answer.getText().toString().equals("Country"))
            correctAnswers++;
        else wrongAnswers++;
        // Checking question #5 user answer.
        CheckBox checkBoxQ5Answer1 = findViewById(R.id.Q5Answer1);
        CheckBox checkBoxQ5Answer2 = findViewById(R.id.Q5Answer2);
        if (checkBoxQ5Answer1.isChecked() && checkBoxQ5Answer2.isChecked())
            correctAnswers++;
        else wrongAnswers++;
        // Displays result on screen
        Toast.makeText(this, getString(R.string.correctAnswers) + " " + correctAnswers +
                "\n" + getString(R.string.wrongAnswers) + " " + wrongAnswers, Toast.LENGTH_LONG).show();
    }
}
