package com.example.geo.studtrans.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;

import com.example.geo.studtrans.R;
import com.example.geo.studtrans.entity.Statie;
import com.example.geo.studtrans.user.Trasee;
import com.example.geo.studtrans.entity.Traseu;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by Geo on 1/11/2015.
 */
public class HartaTrasee extends FragmentActivity {


    private GoogleMap map;
    private List<LatLng> points;

    {
        points=new ArrayList<LatLng>();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        Traseu traseu= Trasee.trasee.get(Trasee.selected);
       final Map<Statie,Integer> statii=traseu.getStatii();
        List<Statie> statiiList=new ArrayList<Statie>();
        for(Statie statie:statii.keySet()){
            statiiList.add(statie);
        }
        Collections.sort(statiiList, new Comparator<Statie>() {
            @Override
            public int compare(Statie lhs, Statie rhs) {
                Integer index1=statii.get(lhs);
                Integer index2=statii.get(rhs);
                return index1.compareTo(index2);
            }
        });
       points.clear();
        for(Statie statie:statiiList){
            LatLng point=new LatLng(statie.getCx(),statie.getCy());
            map.addMarker(new MarkerOptions().position(point).title(statie.getName()));
            points.add(point);
        }
        addLines(points);


        // createMapView();
        // addMarker();
    }

    private void addLines(List<LatLng> points) {

        map
                .addPolyline((new PolylineOptions())
                        .addAll(points).width(5).color(Color.BLUE)
                        .geodesic(true));
        // move camera to zoom on map
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(points.get(0),
                13));
    }

    private void setUpMapIfNeeded() {
        // check if we have got the googleMap already
        if (map == null) {
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            if (map != null) {
                addLines(points);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    public void onClick_City(View v)
    {
        //CameraUpdate update= CameraUpdateFactory.newLatLng(LOCATION_BURNABY);
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(points.get(0), 16);
        map.animateCamera(update);
    }

    public void onClick_Burnaby(View v)
    {
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(points.get(0),14);
        map.animateCamera(update);
    }

    public void onClick_Surrey(View v)
    {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(points.get(0),16);
        map.animateCamera(update);
    }




}
