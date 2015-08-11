package br.com.informapa.categoria;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import br.com.informapa.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaBar extends FragmentActivity {
	
	@SuppressWarnings("unused")
	private static final int GPS_ERRORDIALOG_REQUEST = 9001;
	GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_categoria);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setUpMapIfNeeded();
	}

/*	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}
*/
	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				new MarkerTask().execute();
			}

		}
		mMap.setMyLocationEnabled(true);
		if (!isNetworkAvailable()) { // loading offline
			Context context = getApplicationContext();
			CharSequence text = "Sem Conexão! Impossível carregar os pontos";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}

	private boolean isNetworkAvailable() {
		Context context = getApplicationContext();
		CharSequence text = "Pesquisando pontos, essa ação dependerá de sua conexão";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

		Context context2 = getApplicationContext();
		CharSequence text2 = "Aguarde! Carregando pontos...";
		int duration2 = Toast.LENGTH_LONG;

		Toast toast2 = Toast.makeText(context2, text2, duration2);
		toast2.show();

		ConnectivityManager connectivityManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	private class MarkerTask extends AsyncTask<Void, Void, String> {

		private static final String LOG_TAG = "ExampleApp";
		private static final String SERVICE_URL = "http://pedreiras.esy.es/categorias/4.php";

		// private static final String SERVICE_URL =
		// "https://api.myjson.com/bins/4gi2x";

		// Invoked by execute() method of this object
		@Override
		protected String doInBackground(Void... args) {

			HttpURLConnection conn = null;
			final StringBuilder json = new StringBuilder();
			try {
				// Connect to the web service
				URL url = new URL(SERVICE_URL);
				conn = (HttpURLConnection) url.openConnection();
				InputStreamReader in = new InputStreamReader(
						conn.getInputStream());

				// Read the JSON data into the StringBuilder
				int read;
				char[] buff = new char[1024];
				while ((read = in.read(buff)) != -1) {
					json.append(buff, 0, read);
				}
			} catch (IOException e) {
				Log.e(LOG_TAG, "Error connecting to service", e);
				// throw new IOException("Error connecting to service", e);
				// //uncaught
			} finally {
				if (conn != null) {
					conn.disconnect();
				}
			}

			return json.toString();
		}

		// Executed after the complete execution of doInBackground() method
		@Override
		protected void onPostExecute(String json) {

			try {
				// De-serialize the JSON string into an array of city objects
				JSONArray jsonArray = new JSONArray(json);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);

					LatLng latLng = new LatLng(jsonObj.getJSONArray("latlng")
							.getDouble(0), jsonObj.getJSONArray("latlng")
							.getDouble(1));
					LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
					Criteria criteria = new Criteria();

					Location location = locationManager
							.getLastKnownLocation(locationManager
									.getBestProvider(criteria, false));
					if (location != null) {
						mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
								new LatLng(location.getLatitude(), location
										.getLongitude()), 13));

						CameraPosition cameraPosition = new CameraPosition.Builder()
								.target(new LatLng(location.getLatitude(),
										location.getLongitude())) // Sets the
																	// center of
																	// the map
																	// to
																	// location
																	// user
								.zoom(15) // Sets the zoom
								.bearing(90) // Sets the orientation of the
												// camera to east
								.tilt(40) // Sets the tilt of the camera to 30
											// degrees
								.build(); // Creates a CameraPosition from the
											// builder
						mMap.animateCamera(CameraUpdateFactory
								.newCameraPosition(cameraPosition));

					}

					/*
					 * // move CameraPosition on first result if (i == 0) {
					 * CameraPosition cameraPosition = new
					 * CameraPosition.Builder()
					 * .target(latLng).zoom(13).build();
					 * 
					 * mMap.animateCamera(CameraUpdateFactory
					 * .newCameraPosition(cameraPosition)); }
					 */

					// Create a marker for each city in the JSON data.
					mMap.addMarker(new MarkerOptions()
							.icon(BitmapDescriptorFactory
									.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
							.title(jsonObj.getString("name"))
							// .snippet(Integer.toString(jsonObj.getInt("population")))
							.snippet(jsonObj.getString("population"))
							.position(latLng));
				}
			} catch (JSONException e) {
				Log.e(LOG_TAG, "Error processing JSON", e);
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_categoria, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.mapTypeNone:
			mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
			break;
		case R.id.mapTypeNormal:
			mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.mapTypeSatellite:
			mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

			break;
		case R.id.mapTypeTerrain:
			mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			break;
		case R.id.mapTypeHybrid:
			mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;
		case android.R.id.home:
			// app icon in action bar clicked; finish activity to go home
			finish();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
