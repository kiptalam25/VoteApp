package com.sqlite.orderapp.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.sqlite.orderapp.MainActivity;
import com.sqlite.orderapp.R;
import com.sqlite.orderapp.vewmodel.LogInViewModel;
import com.sqlite.orderapp.vewmodel.SignInViewModel;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
SignInViewModel signInViewModel;
EditText login_txt_email,login_txt_password;
Button btn_login;
TextView not_having_an_account;
FirebaseUser currentUser;
LogInViewModel logInViewModel;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        getUserSession();
        //if(auth.getCurrentUser().getUid()!="")
        login_txt_email=findViewById(R.id.login_txt_email);
        login_txt_password=findViewById(R.id.login_txt_password);
        btn_login=findViewById(R.id.btn_login);
        not_having_an_account=findViewById(R.id.not_having_an_account);

//        signInViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
//                .getInstance(getApplication()))
//                .get(SignInViewModel.class);
        logInViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()))
                .get(LogInViewModel.class);

        btn_login.setOnClickListener(v->{
           logInUser();
        });

        not_having_an_account.setOnClickListener(v->{
            Intent intent = new Intent(this,CreateAccountActivity.class);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            startActivity(intent);
//            finish();
        });
            }

            public  void init(){
                logInViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()))
                        .get(LogInViewModel.class);
            }
    private void getUserSession() {
        logInViewModel.getFirebaseUserLogInStatus();
        logInViewModel.firebaseUserLoginStatus.observe(this, firebaseUser -> {
            currentUser = firebaseUser;
            if (currentUser != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
//                finish();
            }
        });


    }

    public void logInUser() {
        logInViewModel.userLogIn(login_txt_email.getText().toString(), login_txt_password.getText().toString());
        logInViewModel.logInUser.observe(this, task -> {
            if (!task.isSuccessful()) {
                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                frameLayoutLogin.setVisibility(View.GONE);
//                scrollViewLogin.setClickable(true);
//                et_emailIdLogIn.setClickable(true);
//                et_pwdLogIn.setClickable(true);
//                et_emailIdLogIn.setClickable(true);
//                textToSignUp.setClickable(true);
//
//                et_emailIdLogIn.setText("");
//                et_pwdLogIn.setText("");
//                et_emailIdLogIn.requestFocus();
                try {
                    throw Objects.requireNonNull(task.getException());
                } catch (FirebaseAuthInvalidUserException invalidEmail) {
                    Toast.makeText(LoginActivity.this, "Invalid credentials, please try again.", Toast.LENGTH_SHORT).show();
                } catch (FirebaseAuthInvalidCredentialsException wrongPassword) {
                    Toast.makeText(LoginActivity.this, "Wrong password or username , please try again.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "Check Internet Connection.", Toast.LENGTH_SHORT).show();
                }

            } else {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
            }

        });
    }
}