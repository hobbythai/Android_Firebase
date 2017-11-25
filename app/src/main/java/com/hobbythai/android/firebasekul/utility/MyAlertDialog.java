package com.hobbythai.android.firebasekul.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.hobbythai.android.firebasekul.R;

/**
 * Created by ks on 11/25/2017 AD.
 */

public class MyAlertDialog {

    private Context context;

    public MyAlertDialog(Context context) {
        this.context = context;
    }

    public void myNormalDialog(String strTitle, String strMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_alert);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

}
