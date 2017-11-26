package com.hobbythai.android.firebasekul.utility;

/**
 * Created by ks on 11/26/2017 AD.
 */

public class UserModel {

    private String uidString, nameDisplayString;

    //getter
    public UserModel() {
    }

    //setter
    public UserModel(String uidString, String nameDisplayString) {
        this.uidString = uidString;
        this.nameDisplayString = nameDisplayString;
    }

    public String getUidString() {
        return uidString;
    }

    public void setUidString(String uidString) {
        this.uidString = uidString;
    }

    public String getNameDisplayString() {
        return nameDisplayString;
    }

    public void setNameDisplayString(String nameDisplayString) {
        this.nameDisplayString = nameDisplayString;
    }
}
