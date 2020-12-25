package com.moyu.exp4_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;

import android.util.Log;
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

public class Modify extends Activity {
    String username, name, age, teleno, origin_name;
    EditText name_et, age_et, teleno_et;
    TextView username_vt;
    boolean age_bool = false, teleno_bool = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
        username_vt = (TextView) this.findViewById(R.id.username_modify);
        name_et = (EditText)this.findViewById(R.id.name_modify);
        age_et = (EditText)this.findViewById(R.id.age_modify);
        teleno_et = (EditText)this.findViewById(R.id.teleno_modify);

        username = getIntent().getExtras().getString("username");
        origin_name = getIntent().getExtras().getString("origin_name");
        System.out.println(origin_name);

        username_vt.setText("Username: " + username);
    }

    public void submit_modify(View view) {
        name = name_et.getText().toString();
        age = age_et.getText().toString();
        if (age.isEmpty() && !age_bool) {
            age = "-1";
        }
        Integer age_;
        if (!age.isEmpty())
            age_ = Integer.parseInt(age);
        else age_ = null;
        teleno = teleno_et.getText().toString();
        if (teleno.isEmpty() && !teleno_bool) {
            teleno = "-1";
        }

        if (name.isEmpty() && age.isEmpty() && teleno.isEmpty()) {
            Toast.makeText(Modify.this, "至少填入一项信息!", Toast.LENGTH_SHORT).show();
        }
        else if (!name.isEmpty() && name.length() > 20) {
            Toast.makeText(Modify.this, "Name不能超过20", Toast.LENGTH_SHORT).show();
        }else if (!teleno.isEmpty() && teleno.length() != 11 && !teleno.equals("-1")) {
            Toast.makeText(Modify.this, "Teleno只能为11位", Toast.LENGTH_SHORT).show();
        }else if (!age.isEmpty() && (age_ < 0 || age_ > 800) && (age_ != -1)) {
            Toast.makeText(Modify.this, "Age只能在0~800之内", Toast.LENGTH_SHORT).show();
        }else {
            modifyRequest(username, name, origin_name, age, teleno);
        }
    }
    public void modifyRequest(final String username, final String name, final String origin_name, final String age, final String teleno) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Modify.this);
        builder.setTitle("确认要修改信息？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String url = "http://49.234.84.130:8080/Exp4/modifyInfoServlet";
                String tag = "Modify";
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
                                    Toast.makeText(Modify.this, result, Toast.LENGTH_SHORT).show();
                                    if (result.equals("修改信息成功!")) {
                                        Intent intent = new Intent(Modify.this, Loginfo.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("username", username);
                                        intent.putExtras(bundle);
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
                        params.put("name", name);
                        params.put("origin_name", origin_name);
                        params.put("age", age);
                        params.put("teleno", teleno);
                        return params;
                    }
                };
                request.setTag(tag);
                requestQueue.add(request);
            }
        });
        builder.show();
    }
    public void cancel_modify(View view) {
        Intent intent = new Intent(Modify.this, Loginfo.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    public void age_change(View view) {
        if (age_bool) {
            age_bool = false;
            Toast.makeText(Modify.this, "若age为空，则不会修改age", Toast.LENGTH_SHORT).show();
        }else {
            age_bool = true;
            Toast.makeText(Modify.this, "若age为空，则会修改age", Toast.LENGTH_SHORT).show();
        }
    }
    public void teleno_change(View view) {
        if (teleno_bool) {
            teleno_bool = false;
            Toast.makeText(Modify.this, "若teleno为空，则不会修改teleno", Toast.LENGTH_SHORT).show();
        }else {
            teleno_bool = true;
            Toast.makeText(Modify.this, "若teleno为空，则会修改teleno", Toast.LENGTH_SHORT).show();
        }
    }
}
