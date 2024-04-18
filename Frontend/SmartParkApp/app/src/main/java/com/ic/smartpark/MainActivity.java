package com.ic.smartpark;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="MyPreferences";
    private static final String TOKEN="TOKEN";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        if(sharedPreferences.getString(TOKEN,null)!=null){
            Intent intent=new Intent(MainActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void goToSignIn(View view){
        Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    public void goToLogIn(View view){
        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
