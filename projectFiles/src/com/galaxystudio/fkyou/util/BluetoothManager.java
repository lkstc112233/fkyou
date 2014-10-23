package com.galaxystudio.fkyou.util;

import android.bluetooth.BluetoothAdapter;

public final class BluetoothManager {
	private static BluetoothAdapter adapter;

	public static void init() {
		adapter = BluetoothAdapter.getDefaultAdapter();
	}

	public static void openBluetooth() {
		adapter.enable();
	}

	public static void closeBluetooth() {
		adapter.disable();
	}

	private BluetoothManager() {
	}
}
