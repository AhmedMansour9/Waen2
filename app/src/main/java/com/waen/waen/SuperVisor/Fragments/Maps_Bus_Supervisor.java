package com.waen.waen.SuperVisor.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.waen.waen.Admin.Adapter.Buses_Adapter_admin;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Adapter.CustomWinfoView;
import com.waen.waen.SuperVisor.Model.InFo;
import com.waen.waen.SuperVisor.Model.InfoWindowData;
import com.waen.waen.SuperVisor.Model.Routes_Details;
import com.waen.waen.SuperVisor.Model.StudentsInfo;
import com.waen.waen.SuperVisor.Presenter.GetStudentRouteInFo_Presenter;
import com.waen.waen.SuperVisor.Presenter.StartTrip_Presnter;
import com.waen.waen.SuperVisor.View.RouteInFo_View;
import com.waen.waen.SuperVisor.View.TripKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import retrofit2.http.QueryMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Maps_Bus_Supervisor extends Fragment implements RoutingListener,TripKey,RouteInFo_View, OnMapReadyCallback, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    public Maps_Bus_Supervisor() {
        // Required empty public constructor
    }

    private static final int[] COLORS = new int[]{R.color.colorPrimaryDark, R.color.colorPrimary, R.color.cardview_light_background, R.color.colorAccent, R.color.primary_dark_material_light};
    private GoogleMap googleMap;
    List<Polyline> polylines;
    GoogleApiClient mGoogleApiClient;
    LocationRequest locationReques;
    final int REQUEST_LOCATION_CODE = 99;
    View view;
    GetStudentRouteInFo_Presenter getStudentRouteInFo_presenter;
    String User_Token, Statues;
    double latitude,longitude;
    LatLng latyy;
    public List<StudentsInfo> StudentRouteInFo = new ArrayList<>();
    public List<StudentsInfo> LastStudentRouteInFo = new ArrayList<>();
    public String StartLat,StartLon,EndLat,EndLon;
    Button start,end;
    StartTrip_Presnter startTrip_presnter;
    String key;
    ProgressDialog progressdialog;
    Marker m;
    Boolean movie=false;
    String UserTokenAdmin;
    DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_maps__bus_supervisor, container, false);
        initMap();
        checkLocationPermission();
        progressdialog = new ProgressDialog(getActivity());
        CheckStartOrNot();
        getStudentRouteInFo_presenter = new GetStudentRouteInFo_Presenter(getActivity(), this);
        getStudentRouteInFo_presenter.GetRoutes(User_Token, Statues);
        startTrip_presnter=new StartTrip_Presnter(getActivity(),this);

       StartTrip();
       EndTrip();


        return view;
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
    public void CheckStartOrNot(){
       String  statue=SharedPrefManager.getInstance(getActivity()).getStartTrip();
       if(statue!=null){
           start.setVisibility(View.GONE);
           end.setVisibility(View.VISIBLE);
       }else {
           start.setVisibility(View.VISIBLE);
           end.setVisibility(View.GONE);

       }
    }

     public void StartTrip(){
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(latitude!=0) {
                    progressdialog.setMessage("Please Wait Starting Trip....");
                    progressdialog.show();
                    startTrip_presnter.StartTrip(User_Token, UserTokenAdmin, "Start", String.valueOf(latitude)
                            , String.valueOf(longitude), Statues);
                }else {
                    checkLocationPermission();
                    buildGoogleapiclint();
                }
            }
        });
     }
     public void EndTrip(){

         end.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(latitude!=0) {
                     progressdialog.setMessage("Please Wait Ending Trip....");
                     progressdialog.show();
                     String busid=SharedPrefManager.getInstance(getActivity()).getBusNumber();
                     startTrip_presnter.EndTrip(User_Token, UserTokenAdmin, "End", String.valueOf(latitude)
                             , String.valueOf(longitude), Statues);
                 }else {
                     checkLocationPermission();
                     buildGoogleapiclint();
                 }
             }
         });
     }

    public void initMap() {
        User_Token = SharedPrefManager.getInstance(getContext()).getUserToken();
        String backup = SharedPrefManager.getInstance(getContext()).getbackup();
        String Return = SharedPrefManager.getInstance(getContext()).getReturn();
        UserTokenAdmin=SharedPrefManager.getInstance(getContext()).getUserTokenAdmin();
         reference = FirebaseDatabase.getInstance().getReference(UserTokenAdmin).child(User_Token);

        start=view.findViewById(R.id.start);
        end=view.findViewById(R.id.end);
        polylines = new ArrayList<>();
        if (backup != null) {
            Statues = backup;
        }
        if (Return != null) {
            Statues = Return;
        }

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
//        locationReques.setSmallestDisplacement(10);
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
        try {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

          } catch (Exception e) {
            e.printStackTrace();
        }
        latyy = new LatLng(location.getLatitude(), location.getLongitude());

        latitude = location.getLatitude();
        longitude = location.getLongitude();
          if(movie) {
              CameraPosition currentPlace = new CameraPosition.Builder()
                      .target(new LatLng(location.getLatitude(), location.getLongitude()))
                      .bearing(240).tilt(30).zoom(18f).build();
              googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(currentPlace));
          }

        key=SharedPrefManager.getInstance(getContext()).getKey();
        if(key!=null){
            int kms = (int) location.getSpeed();
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("lat",String.valueOf(location.getLatitude()));
            hashMap.put("lng",String.valueOf(location.getLongitude()));
            hashMap.put("speed",kms);
            reference.updateChildren(hashMap);


        }
    }

    @Override
    public void onMapReady(GoogleMap googleMa) {
        googleMap = googleMa;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        buildGoogleapiclint();



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
    public void getlist(Routes_Details routes_details) {

     if(Statues.equals("backup")) {
         StartLat = routes_details.getRoutesLatStartPint();
         StartLon = routes_details.getRoutesLngStartPint();
         EndLat = routes_details.getRoutesLatEndPint();
         EndLon = routes_details.getRoutesLngEndPint();
     }else if(Statues.equals("return")){
         StartLat =routes_details.getRoutesLatEndPint();
         StartLon =routes_details.getRoutesLngEndPint();
         EndLat =routes_details.getRoutesLatStartPint();
         EndLon = routes_details.getRoutesLngStartPint();
     }


    }

    @Override
    public void getlistInFo(List<InFo> list) {
        for(int i=0;i<list.size();i++){
            Location selected_location=new Location("locationA");
            selected_location.setLatitude(Double.parseDouble(StartLat));
            selected_location.setLongitude(Double.parseDouble(StartLon));
            Location near_locations=new Location("locationB");
            near_locations.setLatitude(Double.parseDouble(list.get(i).getStudentLat()));
            near_locations.setLongitude(Double.parseDouble(list.get(i).getStudentLng()));
            double distance=selected_location.distanceTo(near_locations);

            StudentsInfo studentsInfo=new StudentsInfo();
            studentsInfo.setParentAddress(list.get(i).getParentAddress());
            studentsInfo.setParentPhone(list.get(i).getParentPhone());
            studentsInfo.setStudentAddress(list.get(i).getStudentAddress());
            studentsInfo.setStudentName(list.get(i).getStudentName());
            studentsInfo.setStudentLat(String.valueOf(list.get(i).getStudentLat()));
            studentsInfo.setStudentLng(list.get(i).getStudentLng());
            studentsInfo.setRoutesLngStartPint(StartLon);
            studentsInfo.setRoutesLatStartPint(StartLat);
            studentsInfo.setRoutesLngEndPint(EndLon);
            studentsInfo.setRoutesLatEndPint(EndLat);
            studentsInfo.setDistance(distance);
            StudentRouteInFo.add(studentsInfo);
        }
        Collections.sort(StudentRouteInFo, new Comparator<StudentsInfo>() {
            @Override
            public int compare(StudentsInfo o1, StudentsInfo o2) {
                return Double.compare(o1.getDistance(), o2.getDistance());
            }
        });

        AddFirstPiont();
        AddLastPoint();

        drawMarkers(googleMap);
        drawPath(googleMap);
    }

    public void AddFirstPiont(){
        StudentsInfo studentsInfo=new StudentsInfo();
        studentsInfo.setStudentLat(StartLat);
        studentsInfo.setStudentLng(StartLon);
        studentsInfo.setStudentName("Start Point");
        StudentRouteInFo.add(0,studentsInfo);
    }
    public void AddLastPoint(){
        StudentsInfo studentsIn=new StudentsInfo();
        studentsIn.setStudentLat(EndLat);
        studentsIn.setStudentLng(EndLon);
        studentsIn.setStudentName("End Point");
        StudentRouteInFo.add(studentsIn);
    }
    @Override
    public void success(String keys) {
        SharedPrefManager.getInstance(getContext()).saveKey(keys);
        movie=true;
        start.setVisibility(View.GONE);
        end.setVisibility(View.VISIBLE);
        SharedPrefManager.getInstance(getActivity()).saveStartTrip("true");
        progressdialog.dismiss();
    }

    @Override
    public void EndTrip(String key) {
        start.setVisibility(View.VISIBLE);
        end.setVisibility(View.GONE);
        movie=false;
        SharedPrefManager.getInstance(getActivity()).saveStartTrip(null);
        SharedPrefManager.getInstance(getActivity()).saveKey(null);
        progressdialog.dismiss();

    }

    @Override
    public void Error() {
        progressdialog.dismiss();
    }
    public void drawMarkers(GoogleMap map) {
        if (map != null) {
            this.googleMap = map;
        }
        if (this.googleMap != null) {
            List list;
            this.googleMap.clear();

            if (StudentRouteInFo != null) {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (int i = 0; i < this.StudentRouteInFo.size(); i++) {
                   int poistion=StudentRouteInFo.size()-1;
//
                    if (i == 0) {
//                        Marker marker = this.googleMap.addMarker(new MarkerOptions()
//                                .position(new LatLng(Double.parseDouble(StudentRouteInFo.get(i).getStudentLat()),
//                                        Double.parseDouble(StudentRouteInFo.get(i).getStudentLng())))
//                                .title("Start Point")
//                                .snippet("")
//                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconstart)));
//                        marker.setTag(String.valueOf(i));
                    }else if(i==poistion){
//                        Marker marker = this.googleMap.addMarker(new MarkerOptions()
//                                .position(new LatLng(Double.parseDouble(StudentRouteInFo.get(i).getStudentLat()),
//                                        Double.parseDouble(StudentRouteInFo.get(i).getStudentLng())))
//                                .title("End Point")
//                                .snippet("")
//                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconstart)));

//                        marker.setTag(String.valueOf(i));

                    }
                    else {
                        try {
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(new LatLng(Double.parseDouble(StudentRouteInFo.get(i).getStudentLat()),
                                            Double.parseDouble(StudentRouteInFo.get(i).getStudentLng())))
                                    .title(StudentRouteInFo.get(i).getStudentName())
                                    .snippet(StudentRouteInFo.get(i).getStudentAddress())
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));

                            InfoWindowData info = new InfoWindowData();
                            info.setStudentName(StudentRouteInFo.get(i).getStudentName());
                            info.setStudentAddress(StudentRouteInFo.get(i).getStudentAddress());
                            info.setParentPhone(StudentRouteInFo.get(i).getParentPhone());
                            info.setParentAddress(StudentRouteInFo.get(i).getParentAddress());
                            CustomWinfoView customInfoWindow = new CustomWinfoView(getActivity());
                            googleMap.setInfoWindowAdapter(customInfoWindow);

                            m = googleMap.addMarker(markerOptions);
                            m.setTag(info);
                            m.showInfoWindow();

//                            marker.setTag(Integer.valueOf(i));
                            builder.include(m.getPosition());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {

                    final LatLngBounds bounds = builder.build();
                    this.googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 18));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
    public void drawPath(GoogleMap map) {
        try {
            List<LatLng> list = new ArrayList();
            for (int i=0;i<StudentRouteInFo.size();i++) {
                list.add(new LatLng(Double.parseDouble(StudentRouteInFo.get(i).getStudentLat())
                        , Double.parseDouble(StudentRouteInFo.get(i).getStudentLng())));
            }
//            map.addPolyline(new PolylineOptions().addAll(list)
//                    .width(12.0f).color(ContextCompat.getColor(getActivity(),
//                    R.color.colorAccent)).geodesic(true));

            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    .withListener(this)
                    .key("AIzaSyBhazsvmuzBN4hwZ6n3P5gzNHb6cHnWD0c")
                    .waypoints(list)
                    .alternativeRoutes(false)
                    .build();
            routing.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseApp.initializeApp(getActivity());
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> arrayList, int y) {
        if (polylines.size() > 0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }
        polylines = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            int colorIndex = i % COLORS.length;
            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(Color.GRAY);
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(arrayList.get(i).getPoints());
            Polyline polyline = googleMap.addPolyline(polyOptions);
            polylines.add(polyline);
        }
    }

    @Override
    public void onRoutingCancelled() {
        Toast.makeText(getContext(), "cancel", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        buildGoogleapiclint();
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity()
                                , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        break;
                    case Activity.RESULT_CANCELED:

                        break;
                    default:
                        break;
                }
                break;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 99: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // permission was granted, yay! Do the
                        // location-related task you need to do
                        // .
                        if (ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.ACCESS_FINE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {
                            buildGoogleapiclint();
                        }
                    } else {
                }
                return;
            }
        }
    }

    }
