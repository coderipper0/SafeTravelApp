package com.safetravel.safetravel.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.safetravel.safetravel.DetailsFragment;
import com.safetravel.safetravel.ForumFragment;

public class SectionsAdapter extends FragmentStateAdapter {
    public SectionsAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position == 0) {
            fragment = new DetailsFragment();
        } else if(position == 1) {
            fragment = new ForumFragment();
        } else {
            fragment = new DetailsFragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}