package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ubicacion_texto extends AppCompatActivity implements OnMapReadyCallback {

    private TextView input_address;

    private GoogleMap mMap;
    private MapView map;
    private Button boton1, boton2;
    private ImageView boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_texto);

        getAddress();
        map = (MapView) findViewById(R.id.mapa);
        map.onCreate(savedInstanceState);
        map.getMapAsync(this);

        navegation();
        cart();
        profile();

    }

    private void getAddress () {

        input_address = (TextView) findViewById(R.id.textView45);
        LocationManager location = (LocationManager) ubicacion_texto.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Geocoder geolocalizador = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> direccion = geolocalizador.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    input_address.setText(direccion.get(0).getAddressLine(0));
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

        int permissionCheck = ContextCompat.checkSelfPermission(ubicacion_texto.this, Manifest.permission.ACCESS_FINE_LOCATION);
        location.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(4.5731911, -74.1506723);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Johan's V. Store"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
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

    public void navegation () {
        boton1 = (Button) findViewById(R.id.button5);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ubicacion_texto.this, navegacion.class));
            }
        });
    }

    public void cart () {
        boton2 = (Button) findViewById(R.id.carrito);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ubicacion_texto.this, CarroDeCompras.class));
            }
        });
    }

    public void profile () {
        boton3 = (ImageView) findViewById(R.id.imageView5);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ubicacion_texto.this, perfil.class));
            }
        });
    }

}
