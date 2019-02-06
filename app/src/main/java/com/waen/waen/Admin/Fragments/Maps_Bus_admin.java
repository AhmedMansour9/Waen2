package com.waen.waen.Admin.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.waen.waen.Admin.Adapter.Buses_Adapter_admin;
import com.waen.waen.Admin.Model.BusDetail;
import com.waen.waen.Admin.Model.CustomWinfoViewAdmin;
import com.waen.waen.Admin.Model.GetBusesInfo;
import com.waen.waen.Admin.Presenter.GetBuses_Presenter;
import com.waen.waen.Admin.View.Details_Bus;
import com.waen.waen.Admin.View.GetBusesMap_View;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Adapter.CustomWinfoView;
import com.waen.waen.SuperVisor.Model.InfoWindowData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Maps_Bus_admin extends Fragment implements Details_Bus,OnMapReadyCallback, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{


    public Maps_Bus_admin() {
        // Required empty public constructor
    }
    private GoogleMap googleMap;
    GoogleApiClient mGoogleApiClient;
    LocationRequest locationReques;
    final int REQUEST_LOCATION_CODE = 99;
    View view;
    RecyclerView recyclerBus;
    Buses_Adapter_admin buses_adapter;

    List<Marker> lismarket;
    HashMap<String, Marker> markerlist = new HashMap<>();
    ArrayList<String> listid;
    Marker m;

    String User_token;
    BusDetail busDetail;
    List<BusDetail> busDetailss=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_maps__bus_admin, container, false);

        User_token = SharedPrefManager.getInstance(getContext()).getUserToken();
        listid = new ArrayList<>();
        lismarket = new ArrayList<Marker>();
        checkLocationPermission();

//        getBuses_presenter.GetBuses(User_token);
        initMap();
        Recyclview();


        return  view;
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);



            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);
            }
            return false;
        } else {
            return true;
        }
    }
    public void GetBusesFirebase(final Firebasecallback firebasecallback) {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(User_token);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                busDetailss.clear();
                buses_adapter.notifyDataSetChanged();
           for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                busDetail=dataSnapshot1.getValue(BusDetail.class);

               firebasecallback.Callback(busDetail);
               busDetailss.add(busDetail);
           }
                buses_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void show(String lat, String lon) {
        LatLng a = new LatLng(Double.parseDouble(lat),Double.parseDouble( lon));
        CameraPosition currentPlace = new CameraPosition.Builder()
                .target(a)
                .bearing(240).tilt(30).zoom(16f).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                currentPlace));

    }

    private interface Firebasecallback {
        void Callback(BusDetail e);
    }

    private void Recyclview() {
        recyclerBus=view.findViewById(R.id.recycler_Buses);
        recyclerBus.setHasFixedSize(true);
        buses_adapter = new Buses_Adapter_admin(busDetailss, getContext());
        buses_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerBus.setLayoutManager(linearLayoutManager);
        recyclerBus.setItemAnimator(new DefaultItemAnimator());
        recyclerBus.setAdapter(buses_adapter);

    }

    public void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MAP);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationReques = new LocationRequest();
        locationReques.setSmallestDisplacement(10);
        locationReques.setFastestInterval(10000);
        locationReques.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationReques, this);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationReques);
        SettingsClient client = LocationServices.getSettingsClient(getActivity());
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
        task.addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    try {
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(getActivity(),
                                REQUEST_LOCATION_CODE);
                    } catch (IntentSender.SendIntentException sendEx) {
                    }
                }
            }
        });

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMa) {
        googleMap = googleMa;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        buildGoogleapiclint();
        GetBusesFirebase(new Firebasecallback() {
            @Override
            public void Callback(BusDetail e) {
                if(e.getAction().equals("Start")) {
//                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    listid.add(e.getBusName());
                    markerlist.get(e.getBusName());//get marker from list

                    if (markerlist.containsKey(e.getBusName())) {
                        Marker marker = markerlist.get(e.getBusName());
                        marker.remove();
                    }

                    LatLng l = new LatLng(Double.parseDouble(e.getLat()), Double.parseDouble(e.getLng()));

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(l)
                            .title(e.getBusName())
                            .snippet(e.getBusNumber())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconbus));
//
//
                    BusDetail info = new BusDetail();

                    info.setBusName(e.getBusName());


                    info.setBusNumber(e.getBusNumber());
                    info.setSpeed(e.getSpeed());
                    info.setSupervisorName(e.getSupervisorName());
                    CustomWinfoViewAdmin customInfoWindow = new CustomWinfoViewAdmin(getActivity());
                    googleMap.setInfoWindowAdapter(customInfoWindow);

                    m = googleMap.addMarker(markerOptions);
                    markerlist.put(e.getBusName(), m);//add marker to list
                    m.setTag(info);
                    m.showInfoWindow();
//                builder.include(m.getPosition());
//                try {
//
//                        final LatLngBounds bounds = builder.build();
//                        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 18));
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                }
            }else {
                    listid.add(e.getBusName());
                    markerlist.get(e.getBusName());//get marker from list

                    if (markerlist.containsKey(e.getBusName())) {
                        Marker marker = markerlist.get(e.getBusName());
                        marker.remove();
                    }                }
                }
        });

    }
    private synchronized void buildGoogleapiclint() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        buildGoogleapiclint();

                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                    default:
                        break;
                }
                break;
        }

    }



}
