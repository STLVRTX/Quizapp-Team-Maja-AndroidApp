package com.boi.quizappmaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

public class StartRoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_round);

        Button startButton = findViewById(R.id.startround_start);
        Button backButton = findViewById(R.id.startround_back);
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        startButton.setOnClickListener(view -> {
            String url = "http://130.162.39.221/QA/qa_php/index.php/accounts/" + MainMenuActivity.user + "/ingame=true";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    response -> {
                        Intent i3 = new Intent(StartRoundActivity.this, ChooseCategoryActivity.class);
                        startActivity(i3); },
                    error ->
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show());
            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonArrayRequest);
        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(StartRoundActivity.this, ChooseModeActivity.class);
            startActivity(intent);
        });
    }
}