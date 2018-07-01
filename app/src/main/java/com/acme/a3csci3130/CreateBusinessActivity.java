package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class CreateBusinessActivity extends Activity {

    private Button submitButton;
    private EditText nameField, businessTypeField,addressField,provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_activity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        businessTypeField = (EditText) findViewById(R.id.businessType);
        addressField=(EditText) findViewById(R.id.address);
        provinceField=(EditText) findViewById(R.id.province);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        appState.firebaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Gets the amount of businesses already registered
                int id=(int) dataSnapshot.getChildrenCount();
                id++;
                String number = formatNumber(id);
                String name = nameField.getText().toString();
                String businessType = businessTypeField.getText().toString();
                String address=addressField.getText().toString();
                String province=provinceField.getText().toString();
                Business person = new Business(number, name, businessType,address,province);

                appState.firebaseReference.child(number).setValue(person);
                finish();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
                });





    }
    //Method that formats the ID given into a 9 digit int
    public String formatNumber(int id){
        String number="";
        int length=String.valueOf(id).length();
        for (int i=length; i<9;i++){
            number+="0";
        }
        number+=Integer.toString(id);
        return number;
    }
}
