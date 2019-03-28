package com.Troldmandgo.wizardgo.GameScreen;

import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.Troldmandgo.wizardgo.Client.ConsoleLogger;
import com.Troldmandgo.wizardgo.Client.MockClient;
import com.Troldmandgo.wizardgo.Helpers.LocationDataSet;
import com.Troldmandgo.wizardgo.Login.Login_Activity;
import com.Troldmandgo.wizardgo.R;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;
import java.util.List;

public class Game_Activity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener, Game_Presenter.View {

    private final String ACCESS_KEY = "pk.eyJ1IjoiaW5zYW5pdG9yIiwiYSI6ImNqdGZxNXdtMTBjb3o0YW1oaXh2bXJnbmwifQ.AXEmfNbBF89KZZ1kcW0Dxw";
    private MapView mapView;
    private MapboxMap map;
    private PermissionsManager permissionsManager;
    private Game_Presenter mPresenter;
    MockClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new Game_Presenter(this);

        // Mapbox Access token
        Mapbox.getInstance(getApplicationContext(), ACCESS_KEY);

        setContentView(R.layout.activity_game);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(11.78, 55.444901))
                .zoom(10)
                .build();

        float[] iAmAFloatArray = new float[] {5,10,7,12};
        client = new MockClient(mPresenter,new ConsoleLogger(),2,iAmAFloatArray);



    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        Game_Activity.this.map = mapboxMap;
        map.setStyle(Style.LIGHT, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                enableLocationComponent(style);
                client.start();
//                //Adds a marker with no functionality
//                Feature myMarker;
//                myMarker = Feature.fromGeometry(Point.fromLngLat(11.78, 55.444901));
//                style.addSource(new GeoJsonSource("LayerSource", FeatureCollection.fromFeature(myMarker)));
//                style.addImage("MyImage", BitmapFactory.decodeResource(Game_Activity.this.getResources(), R.drawable.mapbox_marker_icon_default));
//                style.addLayer(new SymbolLayer("MarkerLayer", "LayerSource")
//                        .withProperties(PropertyFactory.iconImage("MyImage")));
//
//
////                Adds a marker with an onclick-event and a popup message
//                map.addMarker(new MarkerOptions()
//                        .position(new LatLng(11.78, 55.444901))
//                        .title("Eiffel Tower"));


            }
        });
    }

    @SuppressWarnings({"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        //Check if permissions are granted
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            //Get an instance of the component
            LocationComponent locationComponent = map.getLocationComponent();
            LocationComponentOptions options = LocationComponentOptions.builder(this)

                    .foregroundDrawable(R.drawable.ic_wizard)
                    .backgroundDrawable(R.drawable.ic_wizard)
                    .foregroundDrawableStale(R.drawable.ic_wizard)
                    .backgroundDrawableStale(R.drawable.ic_wizard)
                    .accuracyAlpha(0)
                    .compassAnimationEnabled(true)
                    .accuracyAnimationEnabled(true)
                    .compassAnimationEnabled(true)
                    .minZoomIconScale(1f)
                    .maxZoomIconScale(1.5f)
                    .build();

            //Activate with options
            locationComponent.activateLocationComponent(this, loadedMapStyle, options);

            //Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            //Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING_GPS_NORTH);

            //Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_message, Toast.LENGTH_SHORT);
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            map.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onOwnCharacterChanged() {

    }

    @Override
    public void onCharacterChanged(long enjoyerId, long characterId) {

    }

    @Override
    public void onVisibilityChanged() {

    }

    @Override
    public void onSignin() {

    }

    @Override
    public void onLocationsReceived(final ArrayList<LocationDataSet> locationData) {
        Log.e("TAG", "Message!!!!");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < locationData.size(); i++) {
                    map.addMarker(new MarkerOptions()
                            .position(new LatLng(locationData.get(i).getLatitude(), locationData.get(i).getLongitude()))
                            .title(String.valueOf(locationData.get(i).getEnjoyerId())));

                }
            }
        });
    }

    @Override
    public void onPlayerJoin(long enjoyerId) {

    }

    @Override
    public void onPlayerLeave(long enjoyerId) {

    }
}

