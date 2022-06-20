package com.safetravel.safetravel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;
import com.safetravel.safetravel.models.Details;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SECTOR_NAME = "sectorName";
    private static final String DETAILS_ID = "detailsId";

    // TODO: Rename and change types of parameters
    private String sectorName;
    private long detailsId;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sectorName Parameter 1.
     * @param detailsId Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String sectorName, long detailsId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(SECTOR_NAME, sectorName);
        args.putLong(DETAILS_ID, detailsId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sectorName = getArguments().getString(SECTOR_NAME);
            detailsId = getArguments().getLong(DETAILS_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialTextView sectorTitle = view.findViewById(R.id.title_sector);
        MaterialTextView sectorDetails = view.findViewById(R.id.details_sector);

        MainActivity mainActivity = (MainActivity) requireActivity();

        for (Details detail : mainActivity.details) {
            if(detail.getId() == detailsId) {
                sectorDetails.setText(detail.getDetails());
            }
        }
        sectorTitle.setText(sectorName);
    }
}