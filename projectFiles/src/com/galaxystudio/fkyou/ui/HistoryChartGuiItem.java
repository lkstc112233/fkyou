package com.galaxystudio.fkyou.ui;

import java.util.ArrayDeque;

import com.galaxystudio.fkyou.temperture.TempertureReceiver.TimeTemperturePair;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import net.muststudio.util.guiitemlib.ui.SquareGuiItem;

public final class HistoryChartGuiItem extends SquareGuiItem {
	private Paint backgroundPaint;
	private Paint edgePaint;
	private Paint tagPaint;
	private Paint linePainter;
	private ArrayDeque<Point> points = new ArrayDeque<Point>();

	public HistoryChartGuiItem(ArrayDeque<TimeTemperturePair> data, RelativePoint left_up,
			RelativePoint right_bottom) {
		this(left_up, right_bottom);
		setTempertureData(data);
	}

	private HistoryChartGuiItem(RelativePoint left_up, RelativePoint right_bottom) {
		super(left_up, right_bottom);
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

		Point lst = null;
		ArrayDeque<Point> pointsTemp = points; // 防止多线程下的问题。比锁好用。
		for (Point p : pointsTemp) {
			canvas.drawCircle(p.x, p.y, 3, linePainter);
			if (lst != null)
				canvas.drawLine(lst.x, lst.y, p.x, p.y, linePainter);
			lst = p;
		}
	}

	public HistoryChartGuiItem setTempertureData(ArrayDeque<TimeTemperturePair> data) {
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
			dx *= guiItemSquareRect.width();
			dx += guiItemSquareRect.left;
			int x = (int) dx;
			points.add(new Point(x, y));
		}
		this.points = points;
		return this;
	}

	@Override
	public boolean checkState() {
		return true;

	}

}
