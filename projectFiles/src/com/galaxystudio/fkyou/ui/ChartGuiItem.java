package com.galaxystudio.fkyou.ui;

import java.util.ArrayDeque;

import com.galaxystudio.fkyou.temperture.TempertureReceiver;
import com.galaxystudio.fkyou.temperture.TempertureReceiver.TimeTemperturePair;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import net.muststudio.util.guiitemlib.ui.SquareGuiItem;

public final class ChartGuiItem extends SquareGuiItem {
	private Paint backgroundPaint;
	private Paint edgePaint;
	private Paint tagPaint;
	private Paint linePainter;
	private TempertureReceiver receiver;

	public ChartGuiItem(TempertureReceiver receiver, RelativePoint left_up,
			RelativePoint right_bottom) {
		super(left_up, right_bottom);
		this.receiver = receiver;
		backgroundPaint = new Paint();
		backgroundPaint.setColor(Color.rgb(0xdc, 0xea, 0xf1));
		edgePaint = new Paint();
		edgePaint.setStyle(Style.STROKE);
		edgePaint.setColor(Color.WHITE);
		tagPaint = new Paint();
		tagPaint.setColor(Color.WHITE);
		tagPaint.setTextSize(guiItemSquareRect.height() / 50);
		tagPaint.setStrokeWidth(2);
		linePainter = new Paint();
		linePainter.setColor(Color.BLUE);
		linePainter.setStrokeWidth(3);
		linePainter.setAntiAlias(true);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(guiItemSquareRect, backgroundPaint);
		canvas.drawRect(guiItemSquareRect, edgePaint);
		canvas.drawLine(guiItemSquareRect.left, guiItemSquareRect.top + guiItemSquareRect.height()
				/ 3, guiItemSquareRect.right, guiItemSquareRect.top + guiItemSquareRect.height()
				/ 3, tagPaint);
		canvas.drawLine(guiItemSquareRect.left, guiItemSquareRect.top + guiItemSquareRect.height()
				* 2 / 3, guiItemSquareRect.right,
				guiItemSquareRect.top + guiItemSquareRect.height() * 2 / 3, tagPaint);

		ArrayDeque<TimeTemperturePair> data = receiver.getTempertures();

		long div;
		long min;
		if (data.size() < 2)
			div = 1;
		else
			div = data.getLast().time - data.getFirst().time;
		if (data.size() < 1)
			min = 1;
		else
			min = data.getFirst().time;
		ArrayDeque<Point> points = new ArrayDeque<Point>();
		for (TimeTemperturePair t : data) {
			double dt = t.temperture - 36;
			dt /= 3;
			dt *= guiItemSquareRect.height();
			int y = (int) (guiItemSquareRect.bottom - dt);
			double dx = t.time - min;
			dx /= div;
			dx *= guiItemSquareRect.width() * ((double) data.size() / 100);
			dx += guiItemSquareRect.left + guiItemSquareRect.width()
					* (1 - (double) data.size() / 100);
			int x = (int) dx;
			points.add(new Point(x, y));
		}
		Point lst = null;
		for (Point p : points) {
			canvas.drawCircle(p.x, p.y, 3, linePainter);
			if (lst != null)
				canvas.drawLine(lst.x, lst.y, p.x, p.y, linePainter);
			lst = p;
		}
	}

	@Override
	public boolean checkState() {
		return true;

	}

}
