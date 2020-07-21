package com.example.dog.fragmentImage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dog.R;
import com.example.dog.repo.UrlImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

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

public class FragmentImage extends Fragment {

    private static final String URL_IMAGE = "https://dog.ceo/api/breed/hound/images";
    private RecyclerView mRecyclerView;
    private ImageAdapter mImageAdapter;
    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_dogs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
        if (mActivity != null) {
            initRecycler(view);
            loadData();
        }
    }

    private void loadData() {
        Request request = new Request.Builder()
                .url(URL_IMAGE)
                .build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(mActivity, "onFailure", Toast.LENGTH_SHORT);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    Type type = new TypeToken<UrlImage>() {
                    }.getType();
                    UrlImage urlImage = new Gson().fromJson(json, type);
                    new Handler(mActivity.getMainLooper()).post(() ->
                            mImageAdapter.setUrlImage(urlImage.getMessage()));
                }
            }
        });
    }

    private void initRecycler(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView_image);
        mRecyclerView.setAdapter(new ImageAdapter());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false));
        mImageAdapter = (ImageAdapter) mRecyclerView.getAdapter();
    }
}
