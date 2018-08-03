package com.iavariav.root.joon.Rule.Fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.Model.TpsModel;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;
import com.iavariav.root.joon.Service.GPSTracker;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TpsMapsFragment extends Fragment  implements
        OnMapReadyCallback, GoogleMap.OnPoiClickListener {

    private ArrayList<TpsModel> tpsModels;

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL = 1;
    private MapView mapV;
    GPSTracker gpsTracker;
    private double Lat, Long;
    private GoogleMap mMap;
    public TpsMapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tps_mapsk, container, false);
        tpsModels = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "Otw Permisi", Toast.LENGTH_SHORT).show();
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(getActivity(), "Permisi sukses", Toast.LENGTH_SHORT).show();
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        mapV = (MapView) view.findViewById(R.id.maps);
        mapV.onCreate(savedInstanceState);
        mapV.onResume();

        gpsTracker = new GPSTracker(getActivity());
        if (gpsTracker.canGetLocation()) {
            Lat = gpsTracker.getLatitude();
            Long = gpsTracker.getLongitude();


        } else {
            gpsTracker.showSettingsAlert();
        }

        mapV.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                MapsInitializer.initialize(getActivity());

                final LatLng user = new LatLng(Lat, Long);
                mMap = googleMap;

//                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.star);
//                BitmapDescriptor silver = BitmapDescriptorFactory.fromResource(R.drawable.silver);
//                BitmapDescriptor brows = BitmapDescriptorFactory.fromResource(R.drawable.brows);
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                ;
//                LatLng hermina = new LatLng(-7.0247246, 110.3820431);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(user));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(Config.ZOOM_TO_LEVEL));
                //                mMap.addCircle(new CircleOptions()
//                .center(user).radius(Config.RADIOUS_TO_LEVEL));
//                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); // klo di hilangin jadi biasa aja
                mMap.setMyLocationEnabled(true);

                final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Mencari Lokasi...", false, false);

                ApiServiceGas apiServiceServer  = ClientGas.getInstanceRetrofit();
                Call<ArrayList<TpsModel>> call = apiServiceServer.getDataMarkerTps();
                call.enqueue(new Callback<ArrayList<TpsModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<TpsModel>> call, Response<ArrayList<TpsModel>> response) {
                        tpsModels = response.body();
                        for (int i = 0; i < tpsModels.size(); i++) {
                            String namaTps = tpsModels.get(i).getNamaTps();
                            Float lat = Float.valueOf(tpsModels.get(i).getLat());
                            Float lng = Float.valueOf(tpsModels.get(i).getLng());

                            loading.dismiss();
                            LatLng posisi = new LatLng(lat, lng);

                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.marker);

                            googleMap.addMarker(new MarkerOptions()
                                    .position(posisi)
                                    .title(namaTps)
                                    .snippet("TPS")
                                    .icon(icon));
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<TpsModel>> call, Throwable t) {
                        Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                    }
                });

//                final LatLng user11 = new LatLng(Lat, Long);
//                mMap = googleMap;
//
////                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.star);
////                BitmapDescriptor silver = BitmapDescriptorFactory.fromResource(R.drawable.silver);
////                BitmapDescriptor brows = BitmapDescriptorFactory.fromResource(R.drawable.brows);
//                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    return;
//                }
//                ;
////                LatLng hermina = new LatLng(-7.0247246, 110.3820431);
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(user11));
//                mMap.animateCamera(CameraUpdateFactory.zoomTo(Config.ZOOM_TO_LEVEL));
//                //                mMap.addCircle(new CircleOptions()
////                .center(user).radius(Config.RADIOUS_TO_LEVEL));
////                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); // klo di hilangin jadi biasa aja
//                mMap.setMyLocationEnabled(true);

            }
        });

        return view;
    }

    @Override
    public void onPoiClick(PointOfInterest pointOfInterest) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng position = new LatLng(Lat, Long);
        mMap.addMarker(new MarkerOptions().position(position).title("Posisi" + Lat + Long));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
    }
}
