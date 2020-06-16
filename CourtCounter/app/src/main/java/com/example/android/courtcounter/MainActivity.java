package com.example.android.courtcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Tracks score for team A
    int totalScoreA = 0;
    // Tracks score for team B
    int totalScoreB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Add three score to Team A Score.
     */
    public void addThreeForA(View view) {
        totalScoreA = totalScoreA + 3;
        displayForTeamA();
    }

    /**
     * Add three score to Team B Score.
     */
    public void addThreeForB(View view) {
        totalScoreB = totalScoreB + 3;
        displayForTeamB();
    }

    /**
     * Add two score to Team A Score.
     */
    public void addTwoForA(View view) {
        totalScoreA = totalScoreA + 2;
        displayForTeamA();
    }

    /**
     * Add two score to Team B Score.
     */
    public void addTwoForB(View view) {
        totalScoreB = totalScoreB + 2;
        displayForTeamB();
    }

    /**
     * Add one score to Team A Score.
     */
    public void addOneForA(View view) {
        totalScoreA = totalScoreA + 1;
        displayForTeamA();
    }

    /**
     * Add one score to Team B Score.
     */
    public void addOneForB(View view) {
        totalScoreB = totalScoreB + 1;
        displayForTeamB();
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA() {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(totalScoreA));
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamB() {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(totalScoreB));
    }

    /**
     * Resets score for Team A and B.
     */
    public void reset(View view) {
        totalScoreA = 0;
        totalScoreB = 0;
        displayForTeamA();
        displayForTeamB();
    }
}
