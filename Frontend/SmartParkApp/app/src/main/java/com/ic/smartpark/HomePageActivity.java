package com.ic.smartpark;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="MyPreferences";
    private static final String TOKEN="TOKEN";

    Button codeButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        codeButton=findViewById(R.id.getCode);
        codeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue= Volley.newRequestQueue(HomePageActivity.this);
                String url="http://192.168.1.228:8080/getAvailableParkingSpot";
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.isEmpty()) {
                            //Toast.makeText(HomePageActivity.this, response, Toast.LENGTH_LONG).show();
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i=new Intent(HomePageActivity.this,BarCodeActivity.class);
                                    i.putExtra(BarCodeActivity.BARCODE,response);
                                    startActivity(i);
                                    finish();
                                }
                            },500);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {}
                });
                queue.add(stringRequest);
            }
        });
        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
     public void logout(View view){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(TOKEN);
        editor.apply();
         Intent i=new Intent(HomePageActivity.this,MainActivity.class);
         startActivity(i);
         finish();
     }


}