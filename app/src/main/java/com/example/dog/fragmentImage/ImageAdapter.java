package com.example.dog.fragmentImage;

import android.graphics.drawable.Drawable;
import android.opengl.EGLImage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.dog.R;
import com.example.dog.repo.UrlImage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageItemHolder> {

    private List<String> url = new ArrayList<>();

    public void setUrlImage(List<String> urlImage) {
        this.url = new ArrayList<>(urlImage);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ImageItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_breeds, parent, false);
        return new ImageItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageItemHolder holder, int position) {
        holder.bind(url.get(position));
    }

    @Override
    public int getItemCount() {
        return (url != null) ? url.size() : 0;
    }

    public class ImageItemHolder extends RecyclerView.ViewHolder {

        private ImageView imageBreed;

        public ImageItemHolder(@NonNull View itemView) {
            super(itemView);
            imageBreed = itemView.findViewById(R.id.imageBreed);
        }

        void bind(String urlImage){
            Glide.with(itemView.getContext()).load(urlImage).into(imageBreed);
        }
    }
}
