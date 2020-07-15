package com.example.dog.fragmentbreeds;

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
import com.example.dog.repo.Dogs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;

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

public class FragmentBreeds extends Fragment {

    public static final String FRAGMENTBREEDS = "FRAGMENTBREEDS";
    private RecyclerView recyclerView;
    private BreedsAdapter breedsAdapter;
    private static final String URL_DOGS_ALL = "http://dog.ceo/api/breeds/list";
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private Activity mActivity;
    private OnNameBreedsDogAction onNameBreedsDogAction;
    private SharedPreferences mSharedPreferences;

    public interface OnNameBreedsDogAction{
        void onNameBreedsClick();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_breeds_dogs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
        if (mActivity != null) {
            initRecycler(view);
            setDogs();
            mSharedPreferences = mActivity.getSharedPreferences(MainActivity.MAINACTIVItY , Context.MODE_PRIVATE);
            clickBreeds();
        }
    }

    private void clickBreeds() {
        if(breedsAdapter != null) {
            breedsAdapter.setBreedsItemListener(nameBreeds -> {
                onNameBreedsDogAction.onNameBreedsClick();
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString(FragmentBreeds.FRAGMENTBREEDS, nameBreeds);
                editor.apply();
            });
        }
    }

    private void setDogs() {
        Request request = new Request.Builder()
                .url(URL_DOGS_ALL)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    Type type = new TypeToken<Dogs>(){}.getType();
                    final Dogs dogs = new Gson().fromJson(json , type);
                    new Handler(mActivity.getMainLooper()).post(() -> {
                        for (String breeds: dogs.getMessage()) {
                            breedsAdapter.setApiDogsRepositories(breeds);
                        }
                    });
                }
            }
        });
    }

    private void initRecycler(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_breeds);
        recyclerView.setAdapter(new BreedsAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        breedsAdapter = (BreedsAdapter) recyclerView.getAdapter();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnNameBreedsDogAction){
            onNameBreedsDogAction = (OnNameBreedsDogAction) context;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onNameBreedsDogAction = null;
    }
}
