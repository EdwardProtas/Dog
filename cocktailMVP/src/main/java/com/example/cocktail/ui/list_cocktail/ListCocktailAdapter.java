package com.example.cocktail.ui.list_cocktail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cocktail.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListCocktailAdapter extends RecyclerView.Adapter<ListCocktailAdapter.CocktailItemViewHolder> {

    interface CocktailItemListener{
        void onItemCLick(String nameBreeds);
    }

    private List<String> mApiCocktailRepositories = new ArrayList<>();
    private List<String> mApiCocktailURL = new ArrayList<>();
    private CocktailItemListener cocktailItemListener;


    public void setApiCocktailURL(String apiCocktailURL) {
        this.mApiCocktailURL.add(apiCocktailURL);
        notifyDataSetChanged();
    }

    public void setCocktailItemListener(CocktailItemListener cocktailItemListener) {
        this.cocktailItemListener = cocktailItemListener;
    }

    public void setApiCocktailRepositories(String mApiCocktailRepositories) {
        this.mApiCocktailRepositories.add(mApiCocktailRepositories);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CocktailItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cocktail_list, parent, false);
        return new CocktailItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailItemViewHolder holder, int position) {
        holder.bind(mApiCocktailRepositories.get(position) , mApiCocktailURL.get(position));

    }

    @Override
    public int getItemCount() {
        return (mApiCocktailRepositories != null) ? mApiCocktailRepositories.size() : 0;
    }

    public class CocktailItemViewHolder extends RecyclerView.ViewHolder {

        private TextView nameCocktail;
        private ImageView imageListCocktail;

        public CocktailItemViewHolder(@NonNull View itemView ) {
            super(itemView);
            nameCocktail = itemView.findViewById(R.id.nameCocktail);
            imageListCocktail = itemView.findViewById(R.id.imageListCocktail);
        }

        public void bind(final String apiDogsRepository, final String url) {
            Glide.with(itemView.getContext()).load(url).into(imageListCocktail);
            nameCocktail.setText(apiDogsRepository);
            itemView.setOnClickListener(view ->
                    cocktailItemListener.onItemCLick(nameCocktail.getText().toString()));
        }
    }
}
