package com.safetravel.safetravel.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.safetravel.safetravel.R;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private final ArrayList<Integer> imagesId;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatImageView commentMessage;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            commentMessage = view.findViewById(R.id.image_container);
        }

        public AppCompatImageView getImageView() {
            return commentMessage;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param imagesId String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public ImagesAdapter(ArrayList<Integer> imagesId) {
        this.imagesId = imagesId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.images_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getImageView().setImageResource(imagesId.get(position));
    }

    @Override
    public int getItemCount() {
        return imagesId.size();
    }
}
