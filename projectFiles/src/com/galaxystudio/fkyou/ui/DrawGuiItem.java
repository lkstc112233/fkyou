package com.galaxystudio.fkyou.ui;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import net.muststudio.util.guiitemlib.ui.GuiItem;

public class DrawGuiItem extends GuiItem {
	private static class Line {
		public ArrayList<PointF> points = new ArrayList<PointF>();

		public void drawLine(Canvas canvas, Paint paint) {
			PointF pl = null;
			for (PointF p : points) {
				if (pl != null)
					canvas.drawLine(pl.x, pl.y, p.x, p.y, paint);
				canvas.drawCircle(p.x, p.y, 3, paint);
				pl = p;
			}
		}
	}

	private Line l = null;
	private ArrayList<Line> lines = new ArrayList<Line>();
	private Paint paint;

	public DrawGuiItem() {
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(6);
	}

	@Override
	public void draw(Canvas canvas) {
		synchronized (lines) {
			for (Line ln : lines)
				ln.drawLine(canvas, paint);
		}
		if (l != null)
			synchronized (l) {
				l.drawLine(canvas, paint);
			}
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			l = new Line();
			synchronized (l) {
				l.points.add(new PointF(e.getX(), e.getY()));
			}
			return true;
		case MotionEvent.ACTION_MOVE:
			synchronized (l) {
				l.points.add(new PointF(e.getX(), e.getY()));
			}
			return true;
		case MotionEvent.ACTION_UP:
			synchronized (l) {
				l.points.add(new PointF(e.getX(), e.getY()));
			}
			synchronized (lines) {
				lines.add(l);
			}
			l = null;
			return true;
		default:
			return false;
		}
	}

}
