package com.safetravel.safetravel;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.safetravel.safetravel.models.Details;
import com.safetravel.safetravel.models.Sector;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {
    MapView mapView;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));
        GeoPoint gdl = new GeoPoint(20.66682, -103.39182);

        mapView = view.findViewById(R.id.map_view);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);

        IMapController mapController = mapView.getController();
        mapController.setZoom(14.0);
        mapController.setCenter(gdl);
        mapView.setMultiTouchControls(true);

        ArrayList<OverlayItem> marks = new ArrayList<>();
        MainActivity mainActivity = (MainActivity) requireActivity();
        for (Sector sector : mainActivity.sectors) {
            String extra = "";
            for (Details detail : mainActivity.details) {
                if(detail.getId() == sector.getDetailsId())
                    extra = detail.getDetails();
            }
            if (extra.length() > 20) {
                extra = extra.substring(0, 19);
                extra += "...";
            }
            OverlayItem mark = new OverlayItem(sector.getName(), extra, new GeoPoint(sector.getLatitude(), sector.getLongitude()));
            //Drawable icon = mark.getMarker(0);
            marks.add(mark);
        }

        ItemizedOverlayWithFocus<OverlayItem> overlay = new ItemizedOverlayWithFocus<>(requireContext(), marks,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(int index, OverlayItem item) {
                        MainActivity mainActivity = (MainActivity) requireActivity();
                        long id = mainActivity.sectors.get(index).getId();
                        SectorFragment sectorFragment = SectorFragment.newInstance(id);
                        getParentFragmentManager().beginTransaction().add(R.id.sector_container, sectorFragment).commit();
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(int index, OverlayItem item) {
                        return false;
                    }
                }
        );

        overlay.setFocusItemsOnTap(true);
        mapView.getOverlays().add(overlay);
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
}