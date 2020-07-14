package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dog.fragmentbreeds.FragmentBreeds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer , new FragmentBreeds(), "FragmentBreeds" )
                .commit();
    }
}
