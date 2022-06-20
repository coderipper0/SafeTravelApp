package com.safetravel.safetravel.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.safetravel.safetravel.R;
import com.safetravel.safetravel.models.Comment;

import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private ArrayList<Comment> comments;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialTextView commentMessage;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            commentMessage = view.findViewById(R.id.comments_text);
        }

        public TextView getTextView() {
            return commentMessage;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param comments String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CommentsAdapter(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.comment_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(comments.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
