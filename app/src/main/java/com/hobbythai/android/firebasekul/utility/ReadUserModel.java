package com.hobbythai.android.firebasekul.utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ks on 11/26/2017 AD.
 */

public class ReadUserModel {

    public String nameDisplayString, uidString;
    public Map<String, Boolean> stars = new HashMap<>();

    public ReadUserModel() {
    }

    public ReadUserModel(String nameDisplayString, String uidString) {
        this.nameDisplayString = nameDisplayString;
        this.uidString = uidString;
    }


    public String getNameDisplayString() {
        return nameDisplayString;
    }

    public void setNameDisplayString(String nameDisplayString) {
        this.nameDisplayString = nameDisplayString;
    }

    public String getUidString() {
        return uidString;
    }

    public void setUidString(String uidString) {
        this.uidString = uidString;
    }
}
