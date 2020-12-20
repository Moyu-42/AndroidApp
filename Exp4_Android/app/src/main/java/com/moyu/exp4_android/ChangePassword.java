package com.moyu.exp4_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;

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

public class ChangePassword extends Activity {
    String username, passwd, passwd_confirm;
    TextView username_tv;
    EditText passwd_et, passwd_confirm_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
        username_tv = (TextView)this.findViewById(R.id.username_change);
        passwd_et = (EditText)this.findViewById(R.id.password_change);
        passwd_confirm_et = (EditText)this.findViewById(R.id.password_confirm_change);
        username = getIntent().getExtras().getString("username");
        username_tv.setText("User: " + username);
    }

    public void submit_change(View view) {
        passwd = passwd_et.getText().toString();
        passwd_confirm = passwd_confirm_et.getText().toString();
        if (passwd.isEmpty()) {
            Toast.makeText(ChangePassword.this, "请输入密码", Toast.LENGTH_SHORT).show();
        }else if (!passwd.equals(passwd_confirm)) {
            Toast.makeText(ChangePassword.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
        }else {
            changeRequest(username, passwd);
        }
    }
    public void changeRequest(final String username, final String password) {
        String url = "http://49.234.84.130:8080/Exp4/changePasswdServlet";
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
                            Toast.makeText(ChangePassword.this, result, Toast.LENGTH_SHORT).show();
                            if (result.equals("修改成功!")) {
                                Intent intent = new Intent(ChangePassword.this, Loginfo.class);
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
                return params;
            }
        };
        request.setTag(tag);
        requestQueue.add(request);
    }
    public void cancel_change(View view) {
        Intent intent = new Intent(ChangePassword.this, Loginfo.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
