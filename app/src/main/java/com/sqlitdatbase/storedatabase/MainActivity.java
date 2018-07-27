package com.sqlitdatbase.storedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
        static ProductDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new ProductDatabase(this);
        if(findViewById(R.id.fragment_frame)!=null){
            if(savedInstanceState!=null)
                return;

            MainPageFragment mpf=new MainPageFragment();
            mpf.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_frame,mpf).commit();
        }
    }
}
