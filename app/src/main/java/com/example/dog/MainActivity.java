package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dog.fragmentImage.FragmentImage;
import com.example.dog.fragmentbreeds.FragmentBreeds;
import com.example.dog.fragmentsubspecies.FragmentSubspecies;

public class MainActivity extends AppCompatActivity implements FragmentBreeds.OnNameBreedsDogAction , FragmentSubspecies.OnSubspeciesImage {

    public static final String MAINACTIVItY = "MAINACTIVItY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer , new FragmentBreeds(), "FragmentBreeds" )
                .commit();
    }

    @Override
    public void onNameBreedsClick() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer , new FragmentSubspecies(), "FragmentSubspecies")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void OnClickSubspecies() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer , new FragmentImage(), "FragmentImage")
                .addToBackStack(null)
                .commit();
    }
}
