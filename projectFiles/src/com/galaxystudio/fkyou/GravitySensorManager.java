package com.galaxystudio.fkyou;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public final class GravitySensorManager {
	private static SensorManager sm;
	private static Sensor sensor;

	public static void init(Context context) {
		sm = (SensorManager) context.getSystemService(Service.SENSOR_SERVICE);
		sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	public static void registerSensor(SensorEventListener listener) {
		sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	private GravitySensorManager() {
	}
}
