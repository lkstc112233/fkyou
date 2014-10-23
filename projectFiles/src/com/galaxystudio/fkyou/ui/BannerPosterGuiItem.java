package com.galaxystudio.fkyou.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import net.muststudio.util.guiitemlib.ui.SquareGuiItem;

public class BannerPosterGuiItem extends SquareGuiItem {
	private Paint backgroundPaint;
	private Bitmap logo;
	private Matrix bitmapMatrix;

	public BannerPosterGuiItem(Bitmap logo, int color, RelativePoint left_up,
			RelativePoint right_bottom) {
		super(left_up, right_bottom);
		backgroundPaint = new Paint();
		backgroundPaint.setColor(color);
		this.logo = logo;
		bitmapMatrix = new Matrix();
		float scale = (float) (guiItemSquareRect.height() * 0.8 / logo.getHeight());
		bitmapMatrix.setScale(scale, scale);
		bitmapMatrix.postTranslate(
				(float) (guiItemSquareRect.left + guiItemSquareRect.height() * 0.1),
				(float) (guiItemSquareRect.top + guiItemSquareRect.height() * 0.1));
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(guiItemSquareRect, backgroundPaint);
		canvas.drawBitmap(logo, bitmapMatrix, backgroundPaint);
	}

}
