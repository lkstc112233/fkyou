package com.galaxystudio.fkyou;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public final class TempertureSettings {
	// [folding] ����ģʽ
	private static TempertureSettings obj;

	public static TempertureSettings getSettings(Context context) {
		if (null == obj)
			obj = new TempertureSettings(context);
		return obj;
	}

	public static TempertureSettings getSettings() {
		return obj;
	}

	// [end]
	private SharedPreferences storage;

	private TempertureSettings(Context context) {
		storage = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
		int version = storage.getInt("Version", 0x01);
		if (version > 0x01) {
			Toast.makeText(context, "�汾�����벻Ҫ���ǰ�װ�Ͱ汾�����򼴽��˳���", Toast.LENGTH_LONG).show();
			System.exit(0);
		}
		read();
	}

	// [folding] �������
	private int age;
	private long setDate;

	public int getAge() {
		return age;
	}

	public TempertureSettings setAge(int how) {
		age = how;
		setDate = System.currentTimeMillis();
		return this;
	}

	// [end]
	public void read() {
		age = storage.getInt("age", 0);
		setDate = storage.getLong("setDate", 0);
		if (setDate != 0) {
			long cur = System.currentTimeMillis();
			age += (cur - setDate) / 1000 / 60 / 60 / 24 / 30;
		}
	}

	public double getAlertHighThreshold() {
		switch (age) {
		default:
			return 37.5;
		}
	}

	public double getAlertLowThreshold() {
		switch (age) {
		default:
			return 36.8;
		}
	}

	public void write() {
		SharedPreferences.Editor edt = storage.edit();
		edt.putInt("Version", 0x01);
		edt.putInt("age", age).commit();
		edt.putLong("setDate", setDate);
	}
}
