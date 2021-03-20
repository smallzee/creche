package com.app.fpestaffschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    public AppCompatButton developed_by;
    public TextInputEditText parent_id,password;
    public ProgressDialog progressDialog;
    public MaterialRippleLayout login;

    public Func func;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setTitle("Parent Account Login");

        func = new Func(Login.this);

        login = findViewById(R.id.login);
        parent_id = findViewById(R.id.parent_id);
        password = findViewById(R.id.password);
        developed_by = findViewById(R.id.developed_by);

        developed_by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Developer.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User_login(parent_id.getText().toString(), password.getText().toString());
            }
        });

    }


    public void User_login(String parent_id, String password){

        if (parent_id.isEmpty() || password.isEmpty()){
            func.vibrate();
            func.error_toast("Parent id and password are required");
            return;
        }

        if (parent_id.isEmpty()){
            func.vibrate();
            func.error_toast("Parent id is required");
            return;
        }

        if (password.isEmpty()){
            func.vibrate();
            func.error_toast("Password is required");
            return;
        }

        func.startDialog();

        StringRequest request = new StringRequest(Request.Method.POST, Core.SITE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                func.dismissDialog();

                try {

                    JSONObject object = new JSONObject(response);
                    JSONObject data;

                    data = object.getJSONObject("status");

                    if (data.getString("error").equals("0")){
                        func.vibrate();
                        func.error_toast(data.getString("msg"));
                        return;
                    }

                    func.vibrate();
                    SharedPreferences.Editor user_info = getSharedPreferences("ALL_USER_INFO", MODE_PRIVATE).edit();
                    user_info.putString("all_user_info", object.toString());
                    user_info.apply();
                    startActivity(new Intent(Login.this, Main.class));

                }catch (JSONException e){
                    e.printStackTrace();
                }

                
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                func.vibrate();
                func.error_toast("No internet connection, try again");
                func.dismissDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("login", "");
                param.put("parent_id", parent_id);
                param.put("password", password);
                return  param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}