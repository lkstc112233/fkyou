package com.galaxystudio.fkyou.ui;

import java.util.Calendar;

import android.graphics.Color;
import android.graphics.Rect;

import com.galaxystudio.fkyou.R;
import com.galaxystudio.fkyou.database.DatabaseHelper;

import net.muststudio.util.guiitemlib.ui.BlockedBackToRemoveGuiItemContainer;
import net.muststudio.util.guiitemlib.ui.GenericButton.Task;
import net.muststudio.util.guiitemlib.ui.GuiItemContainer;
import net.muststudio.util.guiitemlib.ui.PressableButton;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import net.muststudio.util.guiitemlib.util.BitmapHolder;

public class HistoryGuiItem extends BlockedBackToRemoveGuiItemContainer {
	private class DateSelectGuiItem extends GuiItemContainer {
		public DateSelectGuiItem(RelativePoint left_up, RelativePoint right_bottom) {
			RelativePoint mainPosition;
			RelativePoint subPosition;
			if (left_up.getRelativeX() > right_bottom.getRelativeX()) {
				if (left_up.getRelativeY() > right_bottom.getRelativeY()) {
					mainPosition = right_bottom;
					subPosition = left_up;
				} else {
					mainPosition = new RelativePoint(right_bottom.getRelativeX(),
							left_up.getRelativeY());
					subPosition = new RelativePoint(left_up.getRelativeX(),
							right_bottom.getRelativeY());
				}

			} else if (left_up.getRelativeY() < right_bottom.getRelativeY()) {
				mainPosition = left_up;
				subPosition = right_bottom;
			} else {
				mainPosition = new RelativePoint(left_up.getRelativeX(),
						right_bottom.getRelativeY());
				subPosition = new RelativePoint(right_bottom.getRelativeX(), left_up.getRelativeY());
			}
			Rect guiItemSquareRect = new Rect(mainPosition.getScreenX(), mainPosition.getScreenY(),
					subPosition.getScreenX(), subPosition.getScreenY());
			for (int i = 0; i < 3; ++i)
				for (int j = 0; j < 2; ++j)
					addToList(new PressableButton(RelativePoint.getRelativePoint(
							guiItemSquareRect.left + i * guiItemSquareRect.width() / 3,
							guiItemSquareRect.top + j * guiItemSquareRect.height() / 2),
							RelativePoint.getRelativePoint(guiItemSquareRect.left + (i + 1)
									* guiItemSquareRect.width() / 3, guiItemSquareRect.top
									+ (j + 1) * guiItemSquareRect.height() / 2), getString(i, j),
							Color.rgb(0xdc, 0xea, 0xf1)).setTask(getTask(i, j)));
		}

		private String getString(int i, int j) {
			switch (i * 2 + j) {
			case 0:
				return "全部数据";
			case 1:
				return "当年数据";
			case 2:
				return "当月数据";
			case 3:
				return "上月数据";
			case 4:
				return "本日数据";
			case 5:
				return "昨日数据";
			}
			return "";
		}

		private Task getTask(int i, int j) {
			switch (i * 2 + j) {
			case 0:
				return new Task() {
					@Override
					public void task() {
						chart.setTempertureData(DatabaseHelper.getTempertureAll());
					}
				};
			case 1:
				return new Task() {
					@Override
					public void task() {
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(System.currentTimeMillis());
						chart.setTempertureData(DatabaseHelper.getTempertureYear(
								cal.get(Calendar.YEAR), cal.get(Calendar.YEAR)));
					}
				};
			case 2:
				return new Task() {
					@Override
					public void task() {
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(System.currentTimeMillis());
						chart.setTempertureData(DatabaseHelper.getTempertureTime(
								cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1,
								cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
								cal.getActualMaximum(Calendar.DATE)));
					}
				};
			case 3:
				return new Task() {
					@Override
					public void task() {
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(System.currentTimeMillis());
						cal.roll(Calendar.MONTH, -1);
						chart.setTempertureData(DatabaseHelper.getTempertureTime(
								cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1,
								cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
								cal.getActualMaximum(Calendar.DATE)));
					}
				};
			case 4:
				return new Task() {
					@Override
					public void task() {
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(System.currentTimeMillis());
						chart.setTempertureData(DatabaseHelper.getTempertureTime(
								cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
								cal.get(Calendar.DATE), cal.get(Calendar.YEAR),
								cal.get(Calendar.MONTH), cal.get(Calendar.DATE)));
					}
				};
			case 5:
				return new Task() {
					@Override
					public void task() {
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(System.currentTimeMillis());
						cal.roll(Calendar.DATE, -1);
						chart.setTempertureData(DatabaseHelper.getTempertureTime(
								cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
								cal.get(Calendar.DATE), cal.get(Calendar.YEAR),
								cal.get(Calendar.MONTH), cal.get(Calendar.DATE)));
					}
				};
			}
			return null;
		}
	}

	private HistoryChartGuiItem chart;

	public HistoryGuiItem() {
		setBackgroundColor(Color.rgb(0xdc, 0xea, 0xf1));
		addToList(new BannerPosterGuiItem(BitmapHolder.getInstance().getBitmap(R.drawable.history),
				Color.rgb(0x40, 0xb7, 0xb0), new RelativePoint(0, 0), new RelativePoint(1, 0.2)));
		addToList(new BannerPosterGuiItem(BitmapHolder.getInstance().getBitmap(R.drawable.chart),
				Color.rgb(0xa8, 0xd5, 0x9d), new RelativePoint(0, 0.2), new RelativePoint(1, 0.27)));
		addToList(chart = new HistoryChartGuiItem(DatabaseHelper.getTempertureAll(),
				new RelativePoint(0, 0.27), new RelativePoint(1, 1.13)));
		addToList(new BannerPosterGuiItem(BitmapHolder.getInstance().getBitmap(R.drawable.timeset),
				Color.rgb(0xa8, 0xd5, 0x9d), new RelativePoint(0, 1.13), new RelativePoint(1, 1.2)));
		addToList(new DateSelectGuiItem(new RelativePoint(0.03, 1.2), new RelativePoint(0.97, 1.42)));
	}
}
