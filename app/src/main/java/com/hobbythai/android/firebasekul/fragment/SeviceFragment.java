package com.hobbythai.android.firebasekul.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hobbythai.android.firebasekul.R;

/**
 * Created by ks on 11/26/2017 AD.
 */

public class SeviceFragment extends Fragment {

    //explicit
    private String tag = "26NovV3";
    private String uidString;
    private DatabaseReference databaseReference;
    private String nameDisplayString;

    public static SeviceFragment seviceInstance(String strID) {

        SeviceFragment seviceFragment = new SeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("UID", strID);
        seviceFragment.setArguments(bundle);

        return seviceFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //get value from argument
        uidString = getArguments().getString("UID");
        Log.d(tag, "Recieve UID = " + uidString);

        //find name display
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        return view;
    }
}
