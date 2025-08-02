package com.veames.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView editTextUsername;
    private TextView editTextPassword;
    private TextView textViewSignUp;
    private Button buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if (userName.isEmpty() || password.isEmpty()) {
                    Log.d("MainActivity", "Some of the fields are empty!");
                    Toast.makeText(MainActivity.this,
                            R.string.error_fields_empty,
                            Toast.LENGTH_SHORT).show();
                } else {
                    launchNextScreen(userName);
                }
            }
        });
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }


    private void launchNextScreen(String userName) {
        Intent intent = MakeOrderActivity.newIntent(this, userName);
        startActivity(intent);
    }

    private void initViews() {
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextUsername = findViewById(R.id.editTextUsername);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        textViewSignUp = findViewById(R.id.textViewSignUp);
    }

}