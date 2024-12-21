package com.example.internalassignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findViewById(R.id.btnLogin).setOnClickListener(view -> {
            startActivity(new Intent(SignInActivity.this, LoginActivity.class));
        });
        findViewById(R.id.btnRegister).setOnClickListener(view -> {
            startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
        });
    }
}
