package com.example.dog.fragmentbreeds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dog.R;

import java.util.ArrayList;
import java.util.List;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BreedsAdapter extends RecyclerView.Adapter<BreedsAdapter.DogItemViewHolder> {

    interface BreedsItemListener{
        void onItemCLick(String nameBreeds);
    }

    private List<String> mApiDogsRepositories = new ArrayList<>();
    private BreedsItemListener breedsItemListener;

    public void setBreedsItemListener(BreedsItemListener breedsItemListener) {
        this.breedsItemListener = breedsItemListener;
    }

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

        public DogItemViewHolder(@NonNull View itemView ) {
            super(itemView);
            textBreed = itemView.findViewById(R.id.textBreed);
        }

        public void bind(final String apiDogsRepository) {
            textBreed.setText(apiDogsRepository);
            itemView.setOnClickListener(view ->
                    breedsItemListener.onItemCLick(textBreed.getText().toString()));
        }
    }
}
