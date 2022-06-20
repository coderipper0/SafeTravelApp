package com.safetravel.safetravel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.safetravel.safetravel.adapters.CommentsAdapter;
import com.safetravel.safetravel.models.Comment;
import com.safetravel.safetravel.models.Forum;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForumFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FORUM_ID = "forumId";
    private static final String USER_ID = "userId";

    // TODO: Rename and change types of parameters
    private long forumId;
    private long userId;

    public ForumFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param forumId Parameter 1.
     * @param userId Parameter 2.
     * @return A new instance of fragment ForumFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForumFragment newInstance(long forumId, long userId) {
        ForumFragment fragment = new ForumFragment();
        Bundle args = new Bundle();
        args.putLong(FORUM_ID, forumId);
        args.putLong(USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            forumId = getArguments().getLong(FORUM_ID);
            userId = getArguments().getLong(USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forum, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialTextView commentsCount = view.findViewById(R.id.comments_count);
        RecyclerView commentsList = view.findViewById(R.id.comments_list);
        TextInputEditText inputComment = view.findViewById(R.id.input_comment);
        Button addComment = view.findViewById(R.id.add_comment);

        MainActivity mainActivity = (MainActivity) requireActivity();
        for (Forum forum : mainActivity.forums) {
            if(forum.getId() == forumId) {
                String count = "Comentarios: " + forum.getComments().size();
                commentsCount.setText(count);
                ArrayList<Comment> comments = new ArrayList<>(forum.getComments());
                CommentsAdapter adapter = new CommentsAdapter(comments);
                commentsList.setAdapter(adapter);
                commentsList.setHasFixedSize(true);
                commentsList.scrollToPosition(forum.getComments().size() - 1);

                addComment.setOnClickListener(v -> {
                    String commentText = Objects.requireNonNull(inputComment.getText()).toString().trim();
                    if (!commentText.isEmpty()) {
                        Comment comment = new Comment(commentText, userId, new Date());
                        comments.add(comment);
                        forum.addComment(comment);
                        adapter.notifyDataSetChanged();
                        inputComment.setText("");
                        String count1 = "Comentarios: " + forum.getComments().size();
                        commentsList.scrollToPosition(forum.getComments().size() - 1);
                        commentsCount.setText(count1);
                    }
                });
            }
        }
    }
}