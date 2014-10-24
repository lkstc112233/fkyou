package com.galaxystudio.fkyou.ui;

import net.muststudio.util.guiitemlib.ui.BackgroundPainterBase;
import net.muststudio.util.guiitemlib.ui.BackgroundPainterPureColorRoundSquare;
import net.muststudio.util.guiitemlib.ui.GenericButton;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import android.graphics.Canvas;
import android.graphics.Color;

public class MyButton extends GenericButton {
	public MyButton(RelativePoint left_up, RelativePoint right_bottom) {
		this(left_up, right_bottom, "", new BackgroundPainterPureColorRoundSquare(left_up,
				right_bottom));
	}

	public MyButton(RelativePoint left_up, RelativePoint right_bottom, String str,
			BackgroundPainterBase background) {
		super(left_up, right_bottom, str, background);
	}

	@Override
	public void draw(Canvas canvas) {
		background.draw(canvas);
		textPainter.setTextSize(rect.height() * 2 / 3);
		if (textPainter.measureText(title.getString()) > guiItemSquareRectF.width())
			textPainter.setTextSize(rect.height() * 18 / 30
					/ textPainter.measureText(title.getString()) * guiItemSquareRectF.width());
		textPainter.setColor(Color.BLACK);
		canvas.drawText(title.getString(),
				rect.left + (rect.width() - textPainter.measureText(title.getString())) / 2,
				rect.bottom - rect.height() / 4, textPainter);
	}
}
