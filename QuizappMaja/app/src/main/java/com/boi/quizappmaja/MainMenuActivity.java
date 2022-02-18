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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainMenuActivity extends AppCompatActivity {

    public static String user;

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
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String user = intent.getStringExtra(LoginActivity.userExtra);
        String logUser = "Logged in as: " + user;
        statusText.setText(logUser);

        playButton.setOnClickListener(view -> {
            Intent i1 = new Intent(this, ChooseModeActivity.class);
            i1.putExtra(this.user, user);
            startActivity(i1);
        });

        highscoresButton.setOnClickListener(view -> {
            Intent i2 = new Intent(this, HighscoresActivity.class);
            i2.putExtra(this.user, user);
            startActivity(i2);
        });

        quitButton.setOnClickListener(view -> {
            System.exit(0);
        });

        logoutButton.setOnClickListener(view -> {
            //Request an den Server den Player abzumelden
            String url = "http://130.162.39.221/QA/qa_php/index.php/accounts/" + user + "/login=false";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    response -> {
                        Intent i3 = new Intent(MainMenuActivity.this, LoginActivity.class);
                        Toast.makeText(getApplicationContext(), "Logout successful!", Toast.LENGTH_SHORT).show();
                        startActivity(i3); },
                    error ->
                        Toast.makeText(getApplicationContext(), "Logout unsuccessful!", Toast.LENGTH_SHORT).show());
            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonArrayRequest);
        });
    }

    @Override
    public void onBackPressed() { }
}