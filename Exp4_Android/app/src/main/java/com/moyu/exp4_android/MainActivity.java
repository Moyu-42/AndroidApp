package com.moyu.exp4_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    String username, passwd;
    EditText username_et, passwd_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username_et = (EditText)this.findViewById(R.id.username);
        passwd_et = (EditText)this.findViewById(R.id.password);
        passwd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public void login(View view) {
        username = username_et.getText().toString();
        passwd = passwd_et.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
        }else if (passwd.isEmpty()) {
            Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
        }else {
            loginRequest(username, passwd);
        }
    }
    public void loginRequest(final String username, final String password) {
        String url = "http://49.234.84.130:8080/Exp4/loginServlet";
        String tag = "Login";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.cancelAll(tag);
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response);
                            String result = jsonObject.getString("Result");
                            System.out.println(result);
                            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                            if (result.equals("登录成功!")) {
                                Intent intent = new Intent(MainActivity.this, Loginfo.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("username", username);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                System.out.println(username);
                System.out.println(password);
                return params;
            }
        };
        request.setTag(tag);
        requestQueue.add(request);
    }
    public void register(View view) {
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }
    public void forget_password(View view) {
        Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
        startActivity(intent);
    }
}
