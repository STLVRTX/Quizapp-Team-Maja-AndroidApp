package com.boi.quizappmaja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameInput = findViewById(R.id.login_input_username);
        EditText passwordInput = findViewById(R.id.login_input_password);
        Button loginButton = findViewById(R.id.login_btn_confirm);
        TextView test = findViewById(R.id.testText);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://130.162.39.221/QA/qa_php/index.php/accounts/" + usernameInput.getText().toString();

                JsonArrayRequest
                        jsonArrayRequest
                        = new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                test.setText(response.toString());
                                try {
                                    JSONObject user = response.getJSONObject(0);

                                    String username = user.getString("USERNAME");
                                    String password = user.getString("PASSWORD");

                                    if(passwordInput.getText().toString() == password) {
                                        //success
                                    }
                                }
                                catch(JSONException e){
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                            }
                        });
                jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                        10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(jsonArrayRequest);
            }
        });
    }
}