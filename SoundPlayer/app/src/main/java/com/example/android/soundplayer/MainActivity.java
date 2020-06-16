package com.example.android.soundplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton;
    private Button pauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sway_michael_buble);
        playButton = findViewById(R.id.play);
        pauseButton = findViewById(R.id.pause);
        Button resetButton = findViewById(R.id.reset);

        pauseButton.setEnabled(false);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                mediaPlayer.start();
                pauseButton.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Play", Toast.LENGTH_SHORT).show();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                playButton.setEnabled(true);
                mediaPlayer.pause();
                Toast.makeText(getApplicationContext(), "Pause", Toast.LENGTH_SHORT).show();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
                mediaPlayer.pause();
                playButton.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Reset", Toast.LENGTH_SHORT).show();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
            }
        });
    }
}