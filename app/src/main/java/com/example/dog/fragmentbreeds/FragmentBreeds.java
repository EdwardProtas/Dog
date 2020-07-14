package com.example.dog.fragmentbreeds;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dog.R;
import com.example.dog.repo.Dogs;
import com.example.dog.repo.DogsApi;
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
import retrofit2.Retrofit;

public class FragmentBreeds extends Fragment {

    private RecyclerView recyclerView;
    private BreedsAdapter breedsAdapter;
    private static final String URL_DOGS_ALL = "http://dog.ceo/api/breeds/list";
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private Activity mActivity;

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
        }
    }


    private void setDogs() {
        Request request = new Request.Builder().url(URL_DOGS_ALL).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(mActivity, "onFailure", Toast.LENGTH_SHORT).show();
        }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null){
                    Log.d("Tag" , "response " + response.toString());
                    String json = response.body().toString();
                    Log.d("Tag1" , "json " + json.toString());
                    Type type = new TypeToken<Dogs>(){}.getType();
                    Log.d("Tag2" , "type " + type.toString());
                    Dogs apiDogs = new Gson().fromJson(json ,type );
                    Log.d("Tag3" , "apiDogs " + apiDogs.toString());
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

}
