package com.example.dog.fragmentbreeds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dog.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BreedsAdapter extends RecyclerView.Adapter<BreedsAdapter.DogItemViewHolder> {

    private List<String> mApiDogsRepositories;

    public void setApiDogsRepositories(String apiDogsRepositories) {
        this.mApiDogsRepositories.add(apiDogsRepositories);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DogItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_breeds, parent, false);
        return new DogItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogItemViewHolder holder, int position) {
        holder.bind(mApiDogsRepositories.get(position));
    }

    @Override
    public int getItemCount() {
        return (mApiDogsRepositories != null) ? mApiDogsRepositories.size() : 0;
    }

    public class DogItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textBreed;

        public DogItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textBreed = itemView.findViewById(R.id.textBreed);
        }
        public void bind(String apiDogsRepository) {
            textBreed.setText(apiDogsRepository);
        }
    }
}
