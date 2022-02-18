package com.boi.quizappmaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class ChooseModeActivity extends AppCompatActivity {

    public static String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);

        Button singleplayerButton = findViewById(R.id.chomode_singleplayer);
        Button multiplayerButton = findViewById(R.id.chomode_multiplayer);
        Button backButton = findViewById(R.id.chomode_back);
        String user = getIntent().getStringExtra(MainMenuActivity.user);

        Toast.makeText(getApplicationContext(), user, Toast.LENGTH_SHORT).show();

        singleplayerButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChooseModeActivity.this, StartRoundActivity.class);
            intent.putExtra(this.user, user);
            startActivity(intent);
        });

        multiplayerButton.setOnClickListener(view -> {
            Intent intent = new Intent(ChooseModeActivity.this, StartRoundActivity.class);
            intent.putExtra(this.user, user);
            startActivity(intent);
        });

        backButton.setOnClickListener(view -> {
            finish();
        });
    }
}