package com.galaxystudio.fkyou;

import com.galaxystudio.fkyou.R;
import com.galaxystudio.fkyou.database.DatabaseHelper;
import com.galaxystudio.fkyou.util.BluetoothManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Acknowager.init(this);
		GravitySensorManager.init(this);
		BluetoothManager.init();
		TempertureSettings.getSettings(this);
		DatabaseHelper.connectToDatabase(this, "Records");

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		setContentView(new MeasureView(this));
		// setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
