package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ubicacion_1 extends AppCompatActivity implements OnMapReadyCallback {

    private Button regret;
    private TextView addr;
    private GoogleMap mMap;
    private MapView map;
    public double longitud, latitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_1);

        map = (MapView) findViewById(R.id.my_map);
        map.onCreate(savedInstanceState);
        map.getMapAsync(this);


        carga_direccion();

    }

    private void carga_direccion () {

        addr = (TextView) findViewById(R.id.textView20);
        regret = (Button) findViewById(R.id.button10);

        LocationManager location = (LocationManager) ubicacion_1.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationlistener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Geocoder geoloalizador = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> direccion = geoloalizador.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    addr.setText(direccion.get(0).getAddressLine(0));
                    longitud = location.getLongitude();
                    latitud = location.getLatitude();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        int permissionCheck = ContextCompat.checkSelfPermission(ubicacion_1.this, Manifest.permission.ACCESS_FINE_LOCATION);
        location.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationlistener);
        permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            }
            else {
                ActivityCompat.requestPermissions(this,
                        new String[] {
                                Manifest.permission.ACCESS_FINE_LOCATION
                        },1);
            }
        }
    }



    private void atras () {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(4.5731911, -74.1506723);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Johan's V. Store"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        map.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        map.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);
    }
}
