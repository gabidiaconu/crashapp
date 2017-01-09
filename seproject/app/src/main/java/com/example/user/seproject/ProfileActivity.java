package com.example.user.seproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ProfileActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText carModel;
    private EditText carRegistrationNumb;
    private EditText carColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firstName = (EditText) findViewById(R.id.first_name_id);
        lastName = (EditText) findViewById(R.id.last_name_id);
        phoneNumber = (EditText) findViewById(R.id.phone_id);
        carColor = (EditText) findViewById(R.id.car_color_id);
        carModel = (EditText) findViewById(R.id.car_model_id);
        carRegistrationNumb = (EditText) findViewById(R.id.car_number_id);

    }

    public void saveChanges(View view){
        if(firstName.getText().toString().isEmpty()){
            Toast.makeText(this,"First name field is empty, please complete it!",Toast.LENGTH_LONG).show();
        } else if(lastName.getText().toString().isEmpty()){
            Toast.makeText(this,"Last name field is empty, please complete it!",Toast.LENGTH_LONG).show();
        } else if(phoneNumber.getText().toString().isEmpty()){
            Toast.makeText(this,"Phone number field is empty, please complete it!",Toast.LENGTH_LONG).show();
        } else if(carModel.getText().toString().isEmpty()){
            Toast.makeText(this,"Car model field is empty, please complete it!",Toast.LENGTH_LONG).show();
        } else if(carColor.getText().toString().isEmpty()){
            Toast.makeText(this,"Car color field is empty, please complete it!",Toast.LENGTH_LONG).show();
        } else if(carRegistrationNumb.getText().toString().isEmpty()){
            Toast.makeText(this,"Car registration field is empty, please complete it!",Toast.LENGTH_LONG).show();
        } else {

            SaveSharedPreferences.setUserName(this,firstName.getText().toString() + " " + lastName.getText().toString());
            SaveSharedPreferences.setCarColor(this,carModel.getText().toString());
            SaveSharedPreferences.setUserPhoneNumber(this,phoneNumber.getText().toString());
            SaveSharedPreferences.setPrefCarModel(this,carModel.getText().toString());
            SaveSharedPreferences.setCarColor(this,carColor.getText().toString());
            SaveSharedPreferences.setRegistrationNumber(this,carRegistrationNumb.getText().toString());


        }
    }
}
