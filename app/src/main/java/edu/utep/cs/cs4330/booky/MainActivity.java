package edu.utep.cs.cs4330.booky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private Button registerButton;

    private EditText emailEditText;
    private EditText passwordEditText;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    Log.d("TAG", "User is logged in: "+user.getDisplayName());
                } else{
                    Log.d("TAG", "User is NOT logged in: ");
                }
            }
        };

        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextPassword);

        loginButton = findViewById(R.id.buttonLogin);
        registerButton = findViewById(R.id.buttonRegister);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        updateLoginState();
    }

    private void updateLoginState(){
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!=null){
            Log.d("TAG", "User is logged in: "+user.getEmail());
        } else{
            Log.d("TAG", "User is NOT logged in: ");
        }
    }

    private void createUser(){
        String emailCreated,passwordCreated;
        if(!checkFields()){
            Toast.makeText(getApplicationContext(), "Incorrect information", Toast.LENGTH_SHORT).show();
        } else {
            emailCreated = emailEditText.getText().toString();
            passwordCreated = passwordEditText.getText().toString();

            mAuth.createUserWithEmailAndPassword(emailCreated, passwordCreated).addOnCompleteListener(this,
            new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Account has been created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Error creating account", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void signIn(){
        String email,password;
        if(!checkFields()){
            Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
        } else {
            email = emailEditText.getText().toString();
            password = passwordEditText.getText().toString();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                    updateLoginState();
                }
            });
        }
    }

    private boolean checkFields(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(email.isEmpty()){
            emailEditText.setText("Enter valid email");
            return false;
        }
        if(password.isEmpty()){
            passwordEditText.setText("Enter valid password");
            return false;
        }
        return true;
    }

}