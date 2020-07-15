package com.example.dog.fragmentsubspecies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dog.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubspeciesAdapter extends RecyclerView.Adapter<SubspeciesAdapter.SubspeciesItemViewHolder> {

    private List<String> subspeciesBreeds = new ArrayList<>();


    public void setSubspeciesBreeds(String subspeciesBreeds) {
        this.subspeciesBreeds.add(subspeciesBreeds);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubspeciesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_breeds, parent, false);
        return new SubspeciesItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubspeciesItemViewHolder holder, int position) {
        holder.bind(subspeciesBreeds.get(position));
    }

    @Override
    public int getItemCount() {
        return (subspeciesBreeds != null) ? subspeciesBreeds.size() : 0;
    }

    public class SubspeciesItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textBreed;

        public SubspeciesItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textBreed = itemView.findViewById(R.id.textBreed);
        }

        public void bind(String apiDogsRepository) {
            textBreed.setText(apiDogsRepository);

        }
    }
}
