package com.veames.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

    private TextView textViewSignIn;

    private EditText editTextSignUpUsername;
    private EditText editTextSignUpPassword;
    private EditText editTextSignUpEmail;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextSignUpUsername.getText().toString();
                String password = editTextSignUpPassword.getText().toString();
                String email = editTextSignUpEmail.getText().toString();
                if (userName.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    Log.d("SignUpActivity", "Some of the fields are empty!");
                    Toast.makeText(SignUpActivity.this,
                            R.string.error_fields_empty,
                            Toast.LENGTH_SHORT).show();
                } else {
                    // In the future, add logic for adding a user to the DB
                }
            }
        });
        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initViews() {
        textViewSignIn = findViewById(R.id.textViewSignIn);
        editTextSignUpUsername = findViewById(R.id.editTextSignUpUsername);
        editTextSignUpPassword = findViewById(R.id.editTextSignUpPassword);
        editTextSignUpEmail = findViewById(R.id.editTextSignUpEmail);
        buttonSignUp = findViewById(R.id.buttonSignUp);
    }
}