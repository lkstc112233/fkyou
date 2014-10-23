package com.galaxystudio.fkyou.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import net.muststudio.util.guiitemlib.ui.GenericButton;
import net.muststudio.util.guiitemlib.ui.RelativePoint;

public class BitmapButton extends GenericButton {
	protected Bitmap bitmap;

	public BitmapButton(RelativePoint left_up, RelativePoint right_bottom, Bitmap bitmap) {
		super(left_up, right_bottom);
		this.bitmap = bitmap;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, null, guiItemSquareRect, null);
	}

}
