package com.boi.quizappmaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button playButton = findViewById(R.id.menu_btn_play);
        Button highscoresButton = findViewById(R.id.menu_btn_highscores);
        Button quitButton = findViewById(R.id.menu_btn_quit);
        Button logoutButton = findViewById(R.id.menu_btn_logout);
        TextView statusText = findViewById(R.id.menu_text_statuslogin);
        Intent intent = getIntent();

        String user = intent.getStringExtra(LoginActivity.userExtra);
        String logUser = "Logged in as: " + user;
        statusText.setText(logUser);

        playButton.setOnClickListener(view -> {
            Intent i1 = new Intent(this, ChooseModeActivity.class);
            intent.putExtra(LoginActivity.userExtra, user);
            startActivity(i1);
        });

        highscoresButton.setOnClickListener(view -> {
            Intent i2 = new Intent(this, HighscoresActivity.class);
            intent.putExtra(LoginActivity.userExtra, user);
            startActivity(i2);
        });

        quitButton.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });

        logoutButton.setOnClickListener(view -> {
            Intent i3 = new Intent(this, LoginActivity.class);
            String url = "http://130.162.39.221/QA/qa_php/index.php/accounts/" + user + "/login=true";
            Toast.makeText(getApplicationContext(), "Logout successful!", Toast.LENGTH_SHORT).show();
            startActivity(i3);
        });
    }

    @Override
    public void onBackPressed() { }
}