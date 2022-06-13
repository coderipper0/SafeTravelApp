package com.safetravel.safetravel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.safetravel.safetravel.adapters.SectionsAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectorFragment extends BottomSheetDialogFragment {
    private static final String SECTOR_ID = "sectorId";

    private int sectorId;

    public SectorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sectorId Sector ID.
     * @return A new instance of fragment SectorFragment.
     */
    public static SectorFragment newInstance(int sectorId) {
        SectorFragment fragment = new SectorFragment();
        Bundle args = new Bundle();
        args.putInt(SECTOR_ID, sectorId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sectorId = getArguments().getInt(SECTOR_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sector, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialCardView sectorCard = view.findViewById(R.id.sector_card);
        BottomSheetBehavior<MaterialCardView> sheetBehavior = BottomSheetBehavior.from(sectorCard);
        sheetBehavior.addBottomSheetCallback(new SectorSheetCallback());

        SectionsAdapter adapter = new SectionsAdapter(this);
        ViewPager2 viewPager = view.findViewById(R.id.sections_slider);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.sections_tab);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();
    }

    class SectorSheetCallback extends BottomSheetBehavior.BottomSheetCallback {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    }
}