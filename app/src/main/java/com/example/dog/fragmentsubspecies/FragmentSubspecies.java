package com.example.dog.fragmentsubspecies;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dog.MainActivity;
import com.example.dog.R;
import com.example.dog.fragmentbreeds.FragmentBreeds;
import com.example.dog.repo.Breeds;
import com.example.dog.repo.Dog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FragmentSubspecies extends Fragment {

    private RecyclerView mRecyclerView;
    private SubspeciesAdapter subspeciesAdapter;
    private Activity mActivity;
    private OnSubspeciesImage onSubspeciesImage;
    private SharedPreferences sharedPreferences;
    private static final String URL = "https://dog.ceo/api/breeds/list/all";
    private FloatingActionButton buttonImage;

    public interface OnSubspeciesImage {
        void OnClickSubspecies();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnSubspeciesImage) {
            onSubspeciesImage = (OnSubspeciesImage) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_unbreeds, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
        if (mActivity != null) {
            sharedPreferences = mActivity.getSharedPreferences(MainActivity.MAINACTIVItY, Context.MODE_PRIVATE);
            initRecyclerView(view);
            setSubrpecies();
            buttonImage = view.findViewById(R.id.buttonAddCity);
            pressingButton();
        }
    }

    private void pressingButton() {
        buttonImage.setOnClickListener(view ->
                onSubspeciesImage.OnClickSubspecies());
    }

    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView_unbreeds);
        mRecyclerView.setAdapter(new SubspeciesAdapter());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false));
        subspeciesAdapter = (SubspeciesAdapter) mRecyclerView.getAdapter();
    }

    private void setSubrpecies() {
        String nameBreeds = sharedPreferences.getString(FragmentBreeds.FRAGMENTBREEDS, "");
        String name = null;
        if (!nameBreeds.equals("")) {
            name = nameBreeds;
        }
        Request request = new Request.Builder()
                .url(URL)
                .build();

        String finalName = name;
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    Type type = new TypeToken<Dog>() {
                    }.getType();
                    final Dog dog = new Gson().fromJson(json, type);
                    new Handler(mActivity.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            List<String> arrayList = new ArrayList<>();
                            FragmentSubspecies.this.searchIncidents(arrayList, finalName, dog);

                        }
                    });
                }
            }
        });

    }

    private void searchIncidents(List<String> arrayList, String name, Dog dog) {
        switch (name) {
            case "affenpinscher":
                arrayList = dog.getMessage().getAffenpinscher();
                break;
            case "african":
                arrayList = dog.getMessage().getAfrican();
                break;
            case "bullterrier":
                arrayList = dog.getMessage().getBullterrier();
                break;
            case "terrier":
                arrayList = dog.getMessage().getTerrier();
                break;
            default:
                Toast.makeText(mActivity, "Нет подпороды ", Toast.LENGTH_SHORT).show();
                break;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            subspeciesAdapter.setSubspeciesBreeds(arrayList.get(i));
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        onSubspeciesImage = null;
    }
}
