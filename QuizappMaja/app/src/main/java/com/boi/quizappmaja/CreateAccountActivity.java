package com.boi.quizappmaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Check ob lokaler Benutzer schon einen Account hat
        SharedPreferences userPref = getSharedPreferences("userAcc", Context.MODE_PRIVATE);
        String userAcc = userPref.getString("userAcc", "");
        if(userAcc.equals("true")){
            Intent i1 = new Intent(CreateAccountActivity.this, LoginActivity.class);
            startActivity(i1);
            return;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        EditText usernameInput = findViewById(R.id.creacc_input_username);
        EditText emailInput = findViewById(R.id.creacc_input_email);
        EditText passwordInput = findViewById(R.id.creacc_input_password);
        EditText reppasswordInput = findViewById(R.id.creacc_input_reppassword);
        Button createAccountBtn = findViewById(R.id.creacc_btn_confirm);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://130.162.39.221/QA/qa_php/index.php/accounts/";

                if(passwordInput.getText().toString().equals(reppasswordInput.getText().toString())){
                    //url += "post_username=" + usernameInput.getText().toString() + "&post_password=" + passwordInput.getText().toString() + "&post_email=" + emailInput.getText().toString();
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("post_username", usernameInput.getText().toString());
                            params.put("post_password", passwordInput.getText().toString());
                            params.put("post_email", emailInput.getText().toString());

                            return params;
                        }
                    };
                    requestQueue.add(postRequest);


                    //Speichern, dass lokaler Benutzer einen Useraccount hat
                    SharedPreferences userPref = getSharedPreferences("userAcc", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = userPref.edit();
                    edit.putString("userAcc", "true");
                    edit.apply();

                    Toast.makeText(getApplicationContext(), "Account Successfully Created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}