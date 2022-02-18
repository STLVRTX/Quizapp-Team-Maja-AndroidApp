package com.boi.quizappmaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        TextView statusText = findViewById(R.id.menu_text_statuslogin);
        Intent intent = getIntent();
        String user = intent.getStringExtra(LoginActivity.userExtra);

        String logUser = "Logged in as: " + user;
        statusText.setText(logUser);
    }

    @Override
    public void onBackPressed() { }
}