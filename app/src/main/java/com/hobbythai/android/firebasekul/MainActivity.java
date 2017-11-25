package com.hobbythai.android.firebasekul;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hobbythai.android.firebasekul.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add Fragment
        if (savedInstanceState == null) {
            //add fragment to activity
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentMainFragment, new MainFragment()).commit();
        }



    }//on create

}//main class
