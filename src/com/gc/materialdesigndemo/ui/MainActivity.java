package com.gc.materialdesigndemo.ui;

import br.com.informapa.DetailsActivity;
import com.gc.materialdesign.views.ButtonFloatSmall;
import com.gc.materialdesign.views.LayoutRipple;
import com.gc.materialdesign.widgets.ColorSelector;
import com.gc.materialdesign.widgets.ColorSelector.OnColorSelectedListener;
import br.com.informapa.R;
import com.nineoldandroids.view.ViewHelper;
import com.pushbots.push.Pushbots;
import com.td.rssreader.SplashActivity;
import com.td.rssreader.SplashActivityAnuncios;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnColorSelectedListener {
	Context mContext = MainActivity.this;
	SharedPreferences appPreferences;
	boolean isAppInstalled = false;

	int backgroundColor = Color.parseColor("#1E88E5");
	ButtonFloatSmall buttonSelectColor;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Pushbots.sharedInstance().init(this);

		buttonSelectColor = (ButtonFloatSmall) findViewById(R.id.buttonColorSelector);
		buttonSelectColor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ColorSelector colorSelector = new ColorSelector(
						MainActivity.this, backgroundColor, MainActivity.this);
				colorSelector.show();
			}
		});

		LayoutRipple layoutRipple = (LayoutRipple) findViewById(R.id.itemButtons);

		setOriginRiple(layoutRipple);

		layoutRipple.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						br.com.informapa.ListActivityCategoria.class);
				intent.putExtra("BACKGROUND", backgroundColor);
				startActivity(intent);
			}
		});
		layoutRipple = (LayoutRipple) findViewById(R.id.itemSwitches);

		setOriginRiple(layoutRipple);

		layoutRipple.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						SplashActivity.class);
				intent.putExtra("BACKGROUND", backgroundColor);
				startActivity(intent);
			}
		});
		layoutRipple = (LayoutRipple) findViewById(R.id.itemProgress);

		setOriginRiple(layoutRipple);

		layoutRipple.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						SplashActivityAnuncios.class);
				intent.putExtra("BACKGROUND", backgroundColor);
				startActivity(intent);
			}
		});
		layoutRipple = (LayoutRipple) findViewById(R.id.itemWidgets);

		setOriginRiple(layoutRipple);

		layoutRipple.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						DetailsActivity.class);
				intent.putExtra("BACKGROUND", backgroundColor);
				startActivity(intent);
			}
		});
		
		/**
         * check if application is running first time, only then create shorcut
         */
        appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isAppInstalled = appPreferences.getBoolean("isAppInstalled",false);
        if(isAppInstalled==false){
        /**
         * create short code
         */
        Intent shortcutIntent = new Intent(getApplicationContext(),MainActivity.class);
        shortcutIntent.setAction(Intent.ACTION_MAIN);
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "InforMapa");
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.ic_laucher_app));
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(intent);
        /**
         * Make preference true
         */
        SharedPreferences.Editor editor = appPreferences.edit();
        editor.putBoolean("isAppInstalled", true);
        editor.commit();
 }


	}

	private void setOriginRiple(final LayoutRipple layoutRipple) {

		layoutRipple.post(new Runnable() {

			@Override
			public void run() {
				View v = layoutRipple.getChildAt(0);
				layoutRipple.setxRippleOrigin(ViewHelper.getX(v) + v.getWidth()
						/ 2);
				layoutRipple.setyRippleOrigin(ViewHelper.getY(v)
						+ v.getHeight() / 2);

				layoutRipple.setRippleColor(Color.parseColor("#1E88E5"));

				layoutRipple.setRippleSpeed(30);
			}
		});

	}

	@Override
	public void onColorSelected(int color) {
		backgroundColor = color;
		buttonSelectColor.setBackgroundColor(color);
	}

}
