package com.moyu.exp4_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class Loginfo extends Activity {
    String username, age, teleno, name;
    TextView username_tv, name_tv, age_tv, teleno_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginfo);
        username_tv = (TextView)this.findViewById(R.id.username_info);
        name_tv = (TextView)this.findViewById(R.id.name_info);
        age_tv = (TextView)this.findViewById(R.id.age_info);
        teleno_tv = (TextView)this.findViewById(R.id.teleno_info);

        username = getIntent().getExtras().getString("username");
        String url = "http://49.234.84.130:8080/Exp4/getLogInfoServlet";
        String tag = "Loginfo";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.cancelAll(tag);
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response);
                            name = jsonObject.getString("name");
                            try {
                                age = jsonObject.getString("age");
                            }catch (JSONException e) {
                                age = "";
                            }
                            teleno = jsonObject.getString("teleno");

                            username_tv.setText("Login User: " + username);
                            name_tv.setText("Hello " + name + "!");
                            age_tv.setText("Age: " + age);
                            teleno_tv.setText("Telephone Number: " + teleno);
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
                return params;
            }
        };
        request.setTag(tag);
        requestQueue.add(request);
    }
    public void delete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Loginfo.this);
        builder.setTitle("确认要删除？该操作不可逆");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String url = "http://49.234.84.130:8080/Exp4/deleteServlet";
                String tag = "Delete";
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
                                    Toast.makeText(Loginfo.this, result, Toast.LENGTH_SHORT).show();
                                    if (result.equals("注销成功!")) {
                                        Intent intent = new Intent(Loginfo.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
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
                        return params;
                    }
                };
                request.setTag(tag);
                requestQueue.add(request);
            }
        });
        builder.show();
    }
    public void logout(View view) {
        Intent intent = new Intent(Loginfo.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void changepassword(View view) {
        Intent intent = new Intent(Loginfo.this, ChangePassword.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    public void modify(View view) {
        Intent intent = new Intent(Loginfo.this, Modify.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("origin_name", name);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
