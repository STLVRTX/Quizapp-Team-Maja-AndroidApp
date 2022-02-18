package com.boi.quizappmaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ChooseModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);

        Button singleplayerButton = findViewById(R.id.chomode_singleplayer);
        Button multiplayerButton = findViewById(R.id.chomode_multiplayer);
        Button backButton = findViewById(R.id.chomode_back);

        singleplayerButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChooseModeActivity.this, StartRoundActivity.class);
            startActivity(intent);
        });

        multiplayerButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChooseModeActivity.this, StartRoundActivity.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChooseModeActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }
}