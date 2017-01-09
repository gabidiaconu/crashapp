package com.example.user.seproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    EditText licenceNumber;
    EditText retypePassWordEditText;
    EditText passwordEditText;
    EditText emailEditText;
    EditText fullNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        licenceNumber = (EditText) findViewById(R.id.licence_nr_id);
        retypePassWordEditText =  (EditText) findViewById(R.id.repassword_edittext);
        passwordEditText =  (EditText) findViewById(R.id.password_edittext);
        emailEditText =  (EditText) findViewById(R.id.email_edittext);
        fullNameEditText =  (EditText) findViewById(R.id.name_edittext);;

        mAuth = FirebaseAuth.getInstance();

    }

    public void registerNewUser2(View view) {
        final Firebase firebase = new Firebase("https://crashapp-e58a9.firebaseio.com" + "/Users/");

        Log.e("Check","" + ""+ passwordEditText.getText().toString());
        if (passwordEditText.getText().toString().equals(retypePassWordEditText.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(RegisterActivity.this, MainMenuActivity.class);
                                Log.d("LogCheck","User loged in!");
                                mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(),passwordEditText.getText().toString());

                                SaveSharedPreferences.setInsuranceNumber(getApplicationContext(),licenceNumber.getText().toString());
                                Firebase newUserFirebase = firebase.child(mAuth.getCurrentUser().getUid());
                                Map<String, Object> characteristics = new HashMap<>();
                                characteristics.put("Name", fullNameEditText.getText().toString());
                                characteristics.put("LicenceNR", licenceNumber.getText().toString());
                                characteristics.put("Picture", "nopicture");
                                newUserFirebase.updateChildren(characteristics);
                                startActivity(intent);
                            } else
                                Toast.makeText(RegisterActivity.this, "Register error! ", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

}
