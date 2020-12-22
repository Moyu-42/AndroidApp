package com.moyu.exp4_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;

import android.view.KeyEvent;
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

public class Register extends Activity {
    String username, passwd, name, age, teleno, passwd_confirm;
    EditText username_et, passwd_et, name_et, age_et, teleno_et, passwd_confirm_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username_et = (EditText)this.findViewById(R.id.username_register);
        passwd_et = (EditText)this.findViewById(R.id.password_register);
        passwd_confirm_et = (EditText)this.findViewById(R.id.password_confirm_register);
        name_et = (EditText)this.findViewById(R.id.name_register);
        age_et = (EditText)this.findViewById(R.id.age_register);
        teleno_et = (EditText)this.findViewById(R.id.teleno_register);
        passwd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        passwd_confirm_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public void submit_register(View view) {
        username = username_et.getText().toString();
        passwd = passwd_et.getText().toString();
        name = name_et.getText().toString();
        age = age_et.getText().toString();
        Integer age_;
        if (!age.isEmpty())
            age_ = Integer.parseInt(age);
        else age_ = null;
        teleno = teleno_et.getText().toString();
        passwd_confirm = passwd_confirm_et.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(Register.this, "请输入用户名", Toast.LENGTH_SHORT).show();
        }else if (passwd.isEmpty()) {
            Toast.makeText(Register.this, "请输入密码", Toast.LENGTH_SHORT).show();
        }else if (name.isEmpty()) {
            Toast.makeText(Register.this, "请输入昵称", Toast.LENGTH_SHORT).show();
        }else if (!username.isEmpty() && username.length() > 10) {
            Toast.makeText(Register.this, "Username不能超过10", Toast.LENGTH_SHORT).show();
        }else if (!passwd.isEmpty() && passwd.length() > 8) {
            Toast.makeText(Register.this, "Password不能超过8", Toast.LENGTH_SHORT).show();
        }else if (!passwd.equals(passwd_confirm)) {
            Toast.makeText(Register.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
        }else if (!name.isEmpty() && name.length() > 20) {
            Toast.makeText(Register.this, "Name不能超过20", Toast.LENGTH_SHORT).show();
        }else if (!teleno.isEmpty() && teleno.length() != 11) {
            Toast.makeText(Register.this, "Teleno只能为11位", Toast.LENGTH_SHORT).show();
        }else if (!age.isEmpty() && (age_ < 0 || age_ > 800)) {
            Toast.makeText(Register.this, "Age只能在0~800之内", Toast.LENGTH_SHORT).show();
        }else {
            registerRequest(username, passwd, name, age, teleno);
        }
    }
    public void registerRequest(final String username, final String password, final String name, final String age, final String teleno) {
        String url = "http://49.234.84.130:8080/Exp4/registerServlet";
        String tag = "Register";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.cancelAll(tag);
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response);
                            String result = jsonObject.getString("Result");
//                            System.out.println(result);
                            Toast.makeText(Register.this, result, Toast.LENGTH_SHORT).show();
                            if (result.equals("注册成功!")) {
                                Intent intent = new Intent(Register.this, MainActivity.class);
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
                params.put("password", password);
                params.put("name", name);
                params.put("age", age);
                params.put("teleno", teleno);
                return params;
            }
        };
        request.setTag(tag);
        requestQueue.add(request);
    }
    public void cancel_register(View view) {
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
