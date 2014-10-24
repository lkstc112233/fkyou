package com.galaxystudio.fkyou;

import com.galaxystudio.fkyou.R;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity implements SensorEventListener {
	public Bitmap photo;
	static public MainActivity act;
	public QuestionsXmlPhraser phraser;
	private SensorManager mSensorManager = null;
	private SoundPool soundPool;
	int sound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		sound = soundPool.load(this, R.raw.ha, 1);

		act = this;

		Acknowager.init(this);
		GravitySensorManager.init(this);
		TempertureSettings.getSettings(this);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		phraser = new QuestionsXmlPhraser(this);

		setContentView(new MainView(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		photo = (Bitmap) data.getExtras().get("data");
	}

	public void startCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onResume() {
		super.onResume();

		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		mSensorManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		int sensorType = arg0.sensor.getType();
		float[] values = arg0.values;
		if (sensorType == Sensor.TYPE_ACCELEROMETER)
			if (Math.abs(values[0] * values[0] + values[1] * values[1] + values[2] * values[2]
					- 100) > 500)
				soundPool.play(sound, 1, 1, 1, 1, 1);
	}
}
