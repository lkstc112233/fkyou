package com.galaxystudio.fkyou.ui;

import android.graphics.Canvas;
import android.graphics.Color;

import com.galaxystudio.fkyou.GravitySensorManager;
import com.galaxystudio.fkyou.R;
import com.galaxystudio.fkyou.temperture.GravityWiggler;
import com.galaxystudio.fkyou.temperture.TempertureReceiver;
import com.galaxystudio.fkyou.util.BluetoothManager;

import net.muststudio.util.guiitemlib.ui.BitmapPainter;
import net.muststudio.util.guiitemlib.ui.BlockedBackToRemoveGuiItemContainer;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import net.muststudio.util.guiitemlib.util.BitmapHolder;

public class BluetoothGuiItem extends BlockedBackToRemoveGuiItemContainer {
	private GravityWiggler wiggler;

	public BluetoothGuiItem() {
		setBackgroundColor(Color.rgb(0xdc, 0xea, 0xf1));
		TempertureReceiver rcv = new TempertureReceiver();
		wiggler = new GravityWiggler(rcv);
		GravitySensorManager.registerSensor(wiggler);
		addToList(new BannerPosterGuiItem(BitmapHolder.getInstance()
				.getBitmap(R.drawable.bluetooth), Color.rgb(0x40, 0xb7, 0xb0), new RelativePoint(0,
				0), new RelativePoint(1, 0.2)));
		addToList(new BannerPosterGuiItem(BitmapHolder.getInstance().getBitmap(
				net.muststudio.util.guiitemlib.R.drawable.invisible), Color.rgb(0x40, 0xb7, 0xb0),
				new RelativePoint(0, 0.2), new RelativePoint(1, 0.27)));
		addToList(new BannerPosterGuiItem(BitmapHolder.getInstance().getBitmap(
				R.drawable.realtimedata), Color.rgb(0xd6, 0xe7, 0xef), new RelativePoint(0, 0.2),
				new RelativePoint(1, 0.25)));
		addToList(new ChartGuiItem(rcv, new RelativePoint(0, 0.27), new RelativePoint(1, 1.17)));
		addToList(new BitmapPainter(new RelativePoint(0.8, 0.05, false), new RelativePoint(0.95,
				0.17, false)).setBitmap(BitmapHolder.getInstance().getBitmap(R.drawable.paw)));
	}

	@Override
	public boolean checkState() {
		wiggler.postTemperture();
		return true;
	}

	@Override
	public boolean onBackKey() {
		if (super.onBackKey()) {
			BluetoothManager.closeBluetooth();
			return true;
		}
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}
}
