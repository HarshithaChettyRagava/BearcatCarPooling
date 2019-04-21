package com.example.AndroidProject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.LocaleDisplayNames;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.icu.text.UnicodeSet.CASE;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private boolean mLocationPermissionGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    public double latitude;
    public double longitude;
    public LocationManager locationManager;

    private LocationRequest mLocationRequest;

    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    //widgets
    private EditText mSearchText;
    private ImageView mGps;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //mSearchText = findViewById(R.id.input_search);
        //mGps = findViewById(R.id.ic_gps);
        mFusedLocationProviderClient = getFusedLocationProviderClient(this);
        getLocationPermission();
//        startLocationUpdates();

        Log.d("Map", "Inside onCreate");
        //voice2text = intentThatCalled.getStringExtra("v2txt");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
    }

    private void init(){
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || event.getAction() == KeyEvent.ACTION_DOWN
                            || event.getAction() == KeyEvent.KEYCODE_ENTER){
                    //execute our method for searching
                    geoLocate();
                }
                return false;
            }
        });
//        mGps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("Map", "onClick: clicked gps icon");
//                if(mLocationPermissionGranted) {
//                    getDeviceLocation();
//                }
//            }
//        });
    }

    private void geoLocate(){
        Log.d("Map","geoLocate: called");
        String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder(this);

        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString,1);
        }catch (IOException ex){
            Log.e("Map","geoLocate: IOException: "+ex.getMessage());
        }

        if(list.size()>0){
            Address address = list.get(0);
            Log.d("Map","Geolocate: Found a location: "+address.toString());
            Toast.makeText(this,address.toString(),Toast.LENGTH_SHORT).show();
            //moveCamera(new LatLng(address.getLatitude(),address.getLongitude()),15f, address.getAddressLine(0));
        }
    }

    private void initMap() {
        Log.d("Map", "Initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getLocationPermission() {
        Log.d("Map", "getLocationPermission: called.");
        String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        if (ContextCompat.checkSelfPermission(getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("Map", "onRequestPermissionResult: called.");
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            Log.d("Map", "onRequestPermissionResult: Permission denied.");
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    Log.d("Map", "onRequestPermissionResult: Permission granted.");
                    //initialize the map
                    initMap();
                }
            }
        }
    }

    private void getDeviceLocation() {
        Log.d("Map", "getDeviceLocation: getting the current device location");
        mFusedLocationProviderClient = getFusedLocationProviderClient(this);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        try {
            if (mLocationPermissionGranted) {
                final Task location = mFusedLocationProviderClient.getLastLocation();
//                                        .addOnSuccessListener(new OnSuccessListener<Location>() {
//                                            @Override
//                                            public void onSuccess(Location location) {
//                                                // GPS location can be null if GPS is switched off
//                                                if (location != null) {
//                                                    onLocationChanged(location);
//                                                }
//                                            }
//                                        })
//                                        .addOnFailureListener(new OnFailureListener() {
//                                            @Override
//                                            public void onFailure(@NonNull Exception e) {
//                                                Log.d("Map", "Error trying to get last gps location");
//                                                e.printStackTrace();
//                                            }
//                                        });
                Log.d("Map", "getDeviceLocation: location is " + location);
                Log.d("Map", "getDeviceLocation: Task is " + location);
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Log.d("Map", "onComplete: found location!");
                            if (location != null) {
                                Log.d("Map", "getDeviceLocation: location inside is " + location);
                                Location currentLocation = (Location) task.getResult();
                                latitude = currentLocation.getLatitude();
                                longitude = currentLocation.getLongitude();
                                LatLng latlong = new LatLng(latitude,longitude);
                                moveCamera(new LatLng(latitude, longitude), 15f, "My location");
                            } else {
                                Toast.makeText(getApplicationContext(), "Location is null", Toast.LENGTH_SHORT).show();
                                Log.d("Map", "getDeviceLocation: Location is null");
                            }

                        } else {
                            Log.d("Map", "onComplete: found location is null!");
                            //Toast.makeText(getApplicationContext(), "unable to get current location", Toast.LENGTH_SHORT).show();
                            // Add a marker in Sydney and move the camera
//                            LatLng missouri = new LatLng(40.365095, -94.907837);
//                            LatLng nwmsu = new LatLng(40.351680, -94.880465);
//                            mMap.addMarker(new MarkerOptions().position(nwmsu).title("Marker in nwmsu"));
                            moveCamera(new LatLng(40.351680, -94.880465),15f,"nwmsu");
//                            mMap.moveCamera(CameraUpdateFactory.newLatLng(nwmsu));

                            //locationManager.requestLocationUpdates(bestProvider,1000,0,this);
                        }
                    }
                });
            }
            } catch(SecurityException e){
                Log.d("Map", "getDeviceLocation: Security Exception: " + e.getMessage());
            }
    }

    public void onLocationChanged(Location location) {
        // New location has now been determined
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        // You can now create a LatLng Object for use with maps
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
    }


    private void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d("Map", "moveCamera: moving to camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        //if(title.equals("My location")) {
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
//        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d("Map Launch", "OnMapReady: map is ready");

        if (mLocationPermissionGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);
            //mMap.getUiSettings().setMyLocationButtonEnabled(false);
//            init();
        }


//        // Add a marker in Sydney and move the camera
//        LatLng missouri = new LatLng(40.365095, -94.907837);
//        mMap.addMarker(new MarkerOptions().position(missouri).title("Marker in Missouri"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(missouri));
    }



//    @Override
//    public void onLocationChanged(Location location) {
//        //Hey, a non null location! Sweet!
//
//        //remove location callback:
//        locationManager.removeUpdates((android.location.LocationListener) this);
//
//        //open the map:
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//        Toast.makeText(this, "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
//        //searchNearestPlace(voice2text);
//    }

//    public void searchNearestPlace(String v2txt) {
//        //.....
//
//    }
}