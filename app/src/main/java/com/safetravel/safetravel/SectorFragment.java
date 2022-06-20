package com.safetravel.safetravel;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.safetravel.safetravel.adapters.ImagesAdapter;
import com.safetravel.safetravel.adapters.SectionsAdapter;
import com.safetravel.safetravel.models.Details;
import com.safetravel.safetravel.models.Sector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectorFragment extends BottomSheetDialogFragment {
    private static final String SECTOR_ID = "sectorId";

    private long sectorId;
    BottomSheetBehavior<MaterialCardView> sheetBehavior;

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
    public static SectorFragment newInstance(long sectorId) {
        SectorFragment fragment = new SectorFragment();
        Bundle args = new Bundle();
        args.putLong(SECTOR_ID, sectorId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sectorId = getArguments().getLong(SECTOR_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sector, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialCardView sectorCard = view.findViewById(R.id.sector_card);
        MaterialButton actionButton = view.findViewById(R.id.action_button);

        sheetBehavior = BottomSheetBehavior.from(sectorCard);
        sheetBehavior.addBottomSheetCallback(new SectorSheetCallback());
        sheetBehavior.setState(STATE_COLLAPSED);

        actionButton.setOnClickListener(v -> {
            String text;
            if(sheetBehavior.getState() == STATE_COLLAPSED) {
                sheetBehavior.setState(STATE_HALF_EXPANDED);
                text = "Mostrar todo";
            } else if(sheetBehavior.getState() == STATE_HALF_EXPANDED) {
                sheetBehavior.setState(STATE_EXPANDED);
                text = "Cerrar";
            } else {
                sheetBehavior.setState(STATE_HIDDEN);
                text = "Expandir";
            }
            actionButton.setText(text);
        });

        MainActivity mainActivity = (MainActivity) requireActivity();
        for (Sector sector : mainActivity.sectors) {
            if(sector.getId() == sectorId) {
                for (Details detail : mainActivity.details) {
                    if(detail.getId() == sector.getDetailsId()) {
                        ImagesAdapter imagesAdapter = new ImagesAdapter(detail.getImageIds());
                        MaterialCardView imagesCard = view.findViewById(R.id.images_card);
                        ViewPager2 imagesPager = view.findViewById(R.id.images_slider);
                        imagesPager.setAdapter(imagesAdapter);

                        SectionsAdapter adapter = new SectionsAdapter(this,
                                new Fragment[]{
                                        DetailsFragment.newInstance(sector.getName(), sector.getDetailsId()),
                                        ForumFragment.newInstance(sector.getForumId(), detail.getUserId()),
                                        GraphicsFragment.newInstance("", "")
                                });
                        ViewPager2 viewPager = view.findViewById(R.id.sections_slider);
                        viewPager.setAdapter(adapter);

                        String[] tabs = { "Detalles", "Foro", "Graficas" };

                        TabLayout tabLayout = view.findViewById(R.id.sections_tab);
                        new TabLayoutMediator(tabLayout, viewPager,
                                (tab, position) -> tab.setText(tabs[position])
                        ).attach();

                        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                TransitionManager.beginDelayedTransition(imagesCard, new AutoTransition());
                                if(tab.getPosition() == 1)
                                    imagesPager.setVisibility(View.GONE);
                                else
                                    imagesPager.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            }
                        });
                    }
                }
            }
        }
    }

    static class SectorSheetCallback extends BottomSheetCallback {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    }
}