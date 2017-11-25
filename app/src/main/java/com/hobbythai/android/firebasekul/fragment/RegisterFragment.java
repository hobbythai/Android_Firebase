package com.hobbythai.android.firebasekul.fragment;

import android.os.Bundle;
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

import com.hobbythai.android.firebasekul.MainActivity;
import com.hobbythai.android.firebasekul.R;

/**
 * Created by ks on 11/25/2017 AD.
 */

public class RegisterFragment extends Fragment {

    //explicit
    private String tag = "25NovV1";

    private String nameString, emailString, passwordString;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
        emailString=emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_save,menu);

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
