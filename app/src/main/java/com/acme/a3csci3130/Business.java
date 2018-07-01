package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase database. This is converted to a JSON format
 * @author Luis Cordero -- Based on Contact.java by jmfranz
 *
 */

public class Business implements Serializable {

    public  String name;
    public  String number;
    public String businessType;
    public String address;
    public String province;

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Business(String number, String name, String businessType, String address, String province){
        this.name = name;
        this.number=number;
        this.businessType = businessType;
        this.address=address;
        this.province=province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("number", number);
        result.put("businessType", businessType);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
