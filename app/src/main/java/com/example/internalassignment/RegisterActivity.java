package com.example.internalassignment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Register");
        }

        TextView tvLoginLink = findViewById(R.id.tvLoginLink);
        tvLoginLink.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnRegister).setOnClickListener(view -> {
            String fullName = ((EditText) findViewById(R.id.etFullName)).getText().toString();
            String email = ((EditText) findViewById(R.id.etEmail)).getText().toString();
            String password = ((EditText) findViewById(R.id.etPassword)).getText().toString();
            register(fullName, email, password);
        });

        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(view -> showForgotPasswordDialog());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void register(String fullName, String email, String password) {

        if (fullName == null || fullName.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            String BASE_URL = "http://10.0.2.2/InternalAssignment/";

//            String endpoint = BASE_URL + "register_user.php?fullName=" + fullName + "&email=" + email + "&password=" + password;

            String endpoint = BASE_URL + "register_user.php?fullName=" + URLEncoder.encode(fullName, "UTF-8") +
                    "&email=" + URLEncoder.encode(email, "UTF-8") +
                    "&password=" + URLEncoder.encode(password, "UTF-8");

            Log.d("Register","Request URL" + endpoint);

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, endpoint,
                    response -> Toast.makeText(this, response, Toast.LENGTH_SHORT).show(),
                    error -> Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show());

            queue.add(stringRequest);

        }catch (UnsupportedEncodingException e) {
            Toast.makeText(this, "Error encoding URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showForgotPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forgot Password");

        final EditText input = new EditText(this);
        input.setHint("Enter your email");
        builder.setView(input);

        builder.setPositiveButton("Submit", (dialog, which) -> {
            String email = input.getText().toString();
            if (email.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
                return;
            }
            handleForgotPassword(email);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
    private void handleForgotPassword(String email) {
        String url = "http://10.0.2.2/InternalAssignment/forgot_password.php/forgot_password.php?email=" + email;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Toast.makeText(this, response, Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show());

        queue.add(stringRequest);
    }
}
