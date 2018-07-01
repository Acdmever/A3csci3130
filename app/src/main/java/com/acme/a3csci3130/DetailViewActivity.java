package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText nameField, businessTypeField,addressField,provinceField;
    private MyApplicationData appState;
    Business receivedPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Business) getIntent().getSerializableExtra("Business");
        appState = ((MyApplicationData) getApplicationContext());
        nameField = (EditText) findViewById(R.id.name);
        businessTypeField = (EditText) findViewById(R.id.businessType);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            businessTypeField.setText(receivedPersonInfo.businessType);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);

        }
    }
    //Overrides the contact with the new information given
    public void updateContact(View v){
        String number=receivedPersonInfo.number;
        String name = nameField.getText().toString();
        String businessType = businessTypeField.getText().toString();
        String address=addressField.getText().toString();
        String province=provinceField.getText().toString();
        Business person = new Business(number, name, businessType,address,province);
        appState.firebaseReference.child(number).setValue(person);
        finish();
    }
    //Makes all the values null, making the object be erased by firebase
    public void eraseContact(View v)
    {
        String number=receivedPersonInfo.number;
        appState.firebaseReference.child(number).setValue(null);
        finish();
    }
}
