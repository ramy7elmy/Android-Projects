package com.example.android.cookies;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the cookie should be eaten.
     */
    @SuppressLint("SetTextI18n")
    public void eatCookie(View view) {
        // Find a reference to the ImageView in the layout. Change the image.
        ImageView imageView = findViewById(R.id.android_cookie_image_view);
        imageView.setImageResource(R.drawable.after_cookie);
        // Find a reference to the TextView in the layout. Change the text.
        TextView textView = findViewById(R.id.status_text_view);
        textView.setText("I'm so full");
    }
}