package com.example.vetpet;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailET, passET;
    private ProgressBar progressBar;

    private TextView forgotPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        emailET = findViewById(R.id.emailEditText);
        passET  = findViewById(R.id.passwordEditText);
        Button signBtn = findViewById(R.id.signInButton);
        progressBar = findViewById(R.id.progressBar);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);

        mAuth = FirebaseAuth.getInstance();

        forgotPasswordText.setOnClickListener(v -> showResetPasswordDialog());

        signBtn.setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        String email = emailET.getText().toString().trim();
        String pass  = passET.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailET.setError("Email can not be empty");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            passET.setError("Password can not be empty");
            return;
        }
        if (pass.length() < 6) {
            passET.setError("Password needs to be at least 6 characters long");
            return;
        }

        progressBar.setVisibility(android.view.View.VISIBLE);

        // Firebase Auth
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, task -> {
                    progressBar.setVisibility(android.view.View.GONE);

                    if (task.isSuccessful()) {
                        Intent intent = new Intent(SignInActivity.this, Homepage.class);
                        startActivity(intent);
                        finish();
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(this,
                                "Welcome: " + user.getEmail(),
                                Toast.LENGTH_SHORT).show();

                        // startActivity(new Intent(this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this,
                                "Sign in failed: " +
                                        task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void showResetPasswordDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reset Password");

        final EditText input = new EditText(this);
        input.setHint("Enter your email");
        input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        builder.setView(input);

        builder.setPositiveButton("Send", (dialog, which) -> {
            String email = input.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Email can not be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this,
                                    "Email has been sent to you",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast
                                    .makeText(this,
                                            "Email could not be sent: " + task.getException().getMessage(),
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

}
