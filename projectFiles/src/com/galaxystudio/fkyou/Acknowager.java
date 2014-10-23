package com.galaxystudio.fkyou;

import android.content.Context;
import android.os.Vibrator;

public final class Acknowager {
	private static Vibrator vibrator;
	public static void init(Context context) {
		vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	public static void playAcknowage(){
		vibrator.vibrate(5000); 
	}

	private Acknowager() {
	}
}
