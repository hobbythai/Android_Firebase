package com.hobbythai.android.firebasekul.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hobbythai.android.firebasekul.MainActivity;
import com.hobbythai.android.firebasekul.R;
import com.hobbythai.android.firebasekul.utility.MyAlertDialog;

/**
 * Created by ks on 11/25/2017 AD.
 */

public class RegisterFragment extends Fragment {

    //explicit
    private String tag = "25NovV1";
    private String nameString, emailString, passwordString;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private FirebaseUser firebaseUser;
    //test
    //private DatabaseReference databaseReference;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //setup Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //create toolbar
        createToolbar();

        //create menu
        setHasOptionsMenu(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemSave) {
            checkSpace();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkSpace() {

        Log.d(tag, "Check Space Work");

        //init view
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText emailEditText = getView().findViewById(R.id.edtEmail);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

        //get value from editText
        nameString = nameEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //check space
        if (nameString.isEmpty() || emailString.isEmpty() || passwordString.isEmpty()) {
            //have space
            MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
            myAlertDialog.myNormalDialog("Have Space",
                    getString(R.string.sub_register));
        } else {
            //no space
            updateFirebase();
        }

    }

    private void updateFirebase() {

        //setup progress dialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //stop dialog
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            //success

                            saveNameDisplayToFirebase();

                            Toast.makeText(getActivity(),"Update Firebase Success",
                                    Toast.LENGTH_SHORT).show();
                            //go back
                            getActivity().getSupportFragmentManager().popBackStack();
                        } else {
                            //not success
                            MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                            myAlertDialog.myNormalDialog("Can't Update Firebase",
                                    task.getException().getMessage());
                        }

                    }
                });

    }

    //after success save email and pass to authen then save name to database with uid as key
    private void saveNameDisplayToFirebase() {

        //get uid
        firebaseUser = firebaseAuth.getCurrentUser();
        showLog();

        String strUserUID = firebaseUser.getUid();

        //create field on database
//        databaseReference = FirebaseDatabase.getInstance()
//                .getReference().child("Users");
//
//        UserModel userModel = new UserModel(strUserUID, nameString); //getter setter
//        databaseReference.child(strUserUID).setValue(userModel);

        //test
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(strUserUID);
        reference.setValue(nameString);
    }

    private void showLog() {
        String tag = "26NovV1";
        Log.d(tag, "UID = " + firebaseUser.getUid());
        Log.d(tag, "Email = " + firebaseUser.getEmail());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_save, menu);

        super.onCreateOptionsMenu(menu, inflater);

    }


    private void createToolbar() {

        //config toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        //setup title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.register);
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.sub_register));

        //back icon
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set back button toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .popBackStack();
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        return view;
    }
}
