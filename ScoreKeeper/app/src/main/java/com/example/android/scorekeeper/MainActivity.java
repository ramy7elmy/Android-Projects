package com.example.android.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Tracks score for team A
    int totalScoreTeamA = 0;
    // Tracks score for team B
    int totalScoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Add six score to Team A Score.
     */
    public void addSixForTeamA(View view) {
        totalScoreTeamA = totalScoreTeamA + 6;
        displayForTeamA();
    }

    /**
     * Add six score to Team B Score.
     */
    public void addSixForTeamB(View view) {
        totalScoreTeamB = totalScoreTeamB + 6;
        displayForTeamB();
    }

    /**
     * Add three score to Team A Score.
     */
    public void addThreeForTeamA(View view) {
        totalScoreTeamA = totalScoreTeamA + 3;
        displayForTeamA();
    }

    /**
     * Add three score to Team B Score.
     */
    public void addThreeForTeamB(View view) {
        totalScoreTeamB = totalScoreTeamB + 3;
        displayForTeamB();
    }

    /**
     * Add two score to Team A Score.
     */
    public void addTwoForTeamA(View view) {
        totalScoreTeamA = totalScoreTeamA + 2;
        displayForTeamA();
    }

    /**
     * Add two score to Team B Score.
     */
    public void addTwoForTeamB(View view) {
        totalScoreTeamB = totalScoreTeamB + 2;
        displayForTeamB();
    }

    /**
     * Add one score to Team A Score.
     */
    public void addOneForTeamA(View view) {
        totalScoreTeamA = totalScoreTeamA + 1;
        displayForTeamA();
    }

    /**
     * Add one score to Team B Score.
     */
    public void addOneForTeamB(View view) {
        totalScoreTeamB = totalScoreTeamB + 1;
        displayForTeamB();
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA() {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(totalScoreTeamA));
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamB() {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(totalScoreTeamB));
    }

    /**
     * Resets score for Team A and B.
     */
    public void resetScore(View view) {
        totalScoreTeamA = 0;
        totalScoreTeamB = 0;
        displayForTeamA();
        displayForTeamB();
    }
}