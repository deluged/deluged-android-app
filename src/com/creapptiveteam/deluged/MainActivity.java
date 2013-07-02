package com.creapptiveteam.deluged;


import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ViewFlipper;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlayOptions;

public class MainActivity extends FragmentActivity {
	private static final LatLng WATER_STREET = new LatLng(40.70251,-74.00931);
	private static final LatLng MOUNTAIN_VIEW = new LatLng(37.4, -122.1);
GoogleMap googleMap;
ViewFlipper screen;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    screen=(ViewFlipper)findViewById(R.id.screen);
    Handler mHandler=new Handler();

    mHandler.postDelayed(new Runnable(){

		@Override
		public void run() {
		screen.setDisplayedChild(1);
			
		}
    	
    },3000);
    // Getting status
    int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

    // Showing status
    if(status==ConnectionResult.SUCCESS)
    {
        SupportMapFragment supportMapFragment = (SupportMapFragment) 
                getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting a reference to the map
        googleMap = supportMapFragment.getMap();
        
        
     // Move the camera instantly to Sydney with a zoom of 15.
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 15));
        
        
       // googleMap.animateCamera(CameraUpdateFactory.zoomIn());

     // Zoom out to zoom level 10, animating with a duration of 2 seconds.
       // googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

     // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
     CameraPosition cameraPosition = new CameraPosition.Builder()
         .target(WATER_STREET)      // Sets the center of the map to Mountain View
         .zoom(23)                   // Sets the zoom
         .bearing(90)                // Sets the orientation of the camera to east
         .tilt(83.25232535853311f)                // Sets the tilt of the camera to 30 degrees
         .build();                   // Creates a CameraPosition from the builder
     
 
     
     
//  // Instantiates a new Polyline object and adds points to define a rectangle
//     PolygonOptions rectOptions = new PolygonOptions()
//             .add(new LatLng(40.70251,-74.00931))
//             .add(new LatLng(40.80251,-74.20931))  // North of the previous point, but at the same longitude
//             .add(new LatLng(40.90251,-74.40931))  // Same latitude, and 30km to the west
//             .add(new LatLng(40.90251,-74.20931))  // Same longitude, and 16km to the south
//             .add(new LatLng(40.70251,-74.00931)); // Closes the polyline.
//
//     new Color();
//	// Set the rectangle's color to red
//     rectOptions.fillColor(Color.alpha(150));
//     
//     rectOptions.
//
//     // Get back the mutable Polyline
//     googleMap.addPolygon(rectOptions);
//     
     
//     Uri uri1 = Uri.parse("geo:0,0?q=https://dl.dropboxusercontent.com/u/2122636/footprints2m.kml");
//     Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri1);
//     mapIntent.setData(uri1);
//     startActivity(Intent.createChooser(mapIntent, "Sample Map ")); 
     googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(new CustomMapTileProvider(getResources().getAssets())));

     googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
     //CameraUpdate upd = CameraUpdateFactory.newLatLngZoom(new LatLng(40.702842, -74.00935200000001), 23);
    // googleMap.moveCamera(upd);
     
    }
    else{

        int requestCode = 10;
        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
        dialog.show();
    }
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
}

}
