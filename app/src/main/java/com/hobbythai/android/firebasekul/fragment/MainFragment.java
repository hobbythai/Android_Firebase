package com.hobbythai.android.firebasekul.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hobbythai.android.firebasekul.R;
import com.hobbythai.android.firebasekul.utility.MyAlertDialog;

/**
 * Created by ks on 11/25/2017 AD.
 */

public class MainFragment extends Fragment {

    //explicit
    private String emailString, passwordString;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Register controller
        registerController();

        //login controller
        loginController();

    }

    private void loginController() {
        Button button = getActivity().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //initial view
                EditText emailEditText = getView().findViewById(R.id.edtEmail);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                //get value from edittext
                emailString = emailEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //check space
                if (emailString.isEmpty() || passwordString.isEmpty()) {
                    //empty
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.myNormalDialog("Have Space!",
                            getString(R.string.sub_register));
                } else {
                    //ok no space
                    checkEmailAnPass();
                }

            }
        });
    }

    private void checkEmailAnPass() {

        //setup progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            //success
                            Toast.makeText(getActivity(), "Welcome Login OK", Toast.LENGTH_SHORT).show();

                            processMoveToService();

                        } else {
                            //not success
                            MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                            myAlertDialog.myNormalDialog("Authen Error!",
                                    task.getException().getMessage());
                        }
                    }
                });
    }

    private void processMoveToService() {

        //find UID login
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String strUID = firebaseUser.getUid();
        Log.d("26NovV3", "strUID = " + strUID); //test get id

        //replace fragment and put vale
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentMainFragment,
                        SeviceFragment.seviceInstance(strUID)).commit();

    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null).commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }
}
