package com.galaxystudio.fkyou.ui;

import com.galaxystudio.fkyou.R;

import net.muststudio.util.guiitemlib.ui.BitmapPainter;
import net.muststudio.util.guiitemlib.ui.BlockedBackToRemoveGuiItemContainer;
import net.muststudio.util.guiitemlib.ui.GenericButton.Task;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import net.muststudio.util.guiitemlib.util.BitmapHolder;

public class GalleryGuiItem extends BlockedBackToRemoveGuiItemContainer {
	public GalleryGuiItem() {
		addToList(new BitmapButton(new RelativePoint(0, 0, true, true), new RelativePoint(0.33,
				0.33, true, true), BitmapHolder.getInstance().getBitmap(R.drawable.pic1))
				.setTask(new Task() {
					@Override
					public void task() {
						BlockedBackToRemoveGuiItemContainer c = new BlockedBackToRemoveGuiItemContainer();
						c.addToList(new BitmapPainter(new RelativePoint(0, 0), new RelativePoint(1,
								0, false)).setBitmap(BitmapHolder.getInstance().getBitmap(
								R.drawable.pic1)));
						addTo(c);
					}
				}));
		addToList(new BitmapButton(new RelativePoint(0.33, 0, true, true), new RelativePoint(
				0.67, 0.33, true, true), BitmapHolder.getInstance().getBitmap(R.drawable.pic2))
				.setTask(new Task() {

					@Override
					public void task() {
						BlockedBackToRemoveGuiItemContainer c = new BlockedBackToRemoveGuiItemContainer();
						c.addToList(new BitmapPainter(new RelativePoint(0, 0), new RelativePoint(1,
								0, false)).setBitmap(BitmapHolder.getInstance().getBitmap(
								R.drawable.pic2)));
						addTo(c);
					}
				}));
		addToList(new BitmapButton(new RelativePoint(0.67, 0, true, true), new RelativePoint(1,
				0.33, true, true), BitmapHolder.getInstance().getBitmap(R.drawable.pic3))
				.setTask(new Task() {

					@Override
					public void task() {
						BlockedBackToRemoveGuiItemContainer c = new BlockedBackToRemoveGuiItemContainer();
						c.addToList(new BitmapPainter(new RelativePoint(0, 0), new RelativePoint(1,
								0, false)).setBitmap(BitmapHolder.getInstance().getBitmap(
								R.drawable.pic3)));
						addTo(c);
					}
				}));
		addToList(new BitmapButton(new RelativePoint(0, 0.33, true, true), new RelativePoint(
				0.33, 0.67, true, true), BitmapHolder.getInstance().getBitmap(R.drawable.pic4))
				.setTask(new Task() {

					@Override
					public void task() {
						BlockedBackToRemoveGuiItemContainer c = new BlockedBackToRemoveGuiItemContainer();
						c.addToList(new BitmapPainter(new RelativePoint(0, 0), new RelativePoint(1,
								0, false)).setBitmap(BitmapHolder.getInstance().getBitmap(
								R.drawable.pic4)));
						addTo(c);
					}
				}));
		addToList(new BitmapButton(new RelativePoint(0.33, 0.33, true, true), new RelativePoint(
				0.67, 0.67, true, true), BitmapHolder.getInstance().getBitmap(R.drawable.pic5))
				.setTask(new Task() {

					@Override
					public void task() {
						BlockedBackToRemoveGuiItemContainer c = new BlockedBackToRemoveGuiItemContainer();
						c.addToList(new BitmapPainter(new RelativePoint(0, 0), new RelativePoint(1,
								0, false)).setBitmap(BitmapHolder.getInstance().getBitmap(
								R.drawable.pic5)));
						addTo(c);
					}
				}));
		addToList(new BitmapButton(new RelativePoint(0.67, 0.33, true, true), new RelativePoint(
				1, 0.67, true, true), BitmapHolder.getInstance().getBitmap(R.drawable.pic6))
				.setTask(new Task() {

					@Override
					public void task() {
						BlockedBackToRemoveGuiItemContainer c = new BlockedBackToRemoveGuiItemContainer();
						c.addToList(new BitmapPainter(new RelativePoint(0, 0), new RelativePoint(1,
								0, false)).setBitmap(BitmapHolder.getInstance().getBitmap(
								R.drawable.pic6)));
						addTo(c);
					}
				}));
		addToList(new BitmapButton(new RelativePoint(0, 0.67, true, true), new RelativePoint(
				0.33, 1, true, true), BitmapHolder.getInstance().getBitmap(R.drawable.pic7))
				.setTask(new Task() {

					@Override
					public void task() {
						BlockedBackToRemoveGuiItemContainer c = new BlockedBackToRemoveGuiItemContainer();
						c.addToList(new BitmapPainter(new RelativePoint(0, 0), new RelativePoint(1,
								0, false)).setBitmap(BitmapHolder.getInstance().getBitmap(
								R.drawable.pic7)));
						addTo(c);
					}
				}));
		addToList(new BitmapButton(new RelativePoint(0.33, 0.67, true, true), new RelativePoint(
				0.67, 1, true, true), BitmapHolder.getInstance().getBitmap(R.drawable.pic8))
				.setTask(new Task() {

					@Override
					public void task() {
						BlockedBackToRemoveGuiItemContainer c = new BlockedBackToRemoveGuiItemContainer();
						c.addToList(new BitmapPainter(new RelativePoint(0, 0), new RelativePoint(1,
								0, false)).setBitmap(BitmapHolder.getInstance().getBitmap(
								R.drawable.pic8)));
						addTo(c);
					}
				}));
		addToList(new BitmapButton(new RelativePoint(0.67, 0.67, true, true), new RelativePoint(
				1, 1, true, true), BitmapHolder.getInstance().getBitmap(R.drawable.pic9))
				.setTask(new Task() {

					@Override
					public void task() {
						BlockedBackToRemoveGuiItemContainer c = new BlockedBackToRemoveGuiItemContainer();
						c.addToList(new BitmapPainter(new RelativePoint(0, 0), new RelativePoint(1,
								0, false)).setBitmap(BitmapHolder.getInstance().getBitmap(
								R.drawable.pic9)));
						addTo(c);
					}
				}));
	}
}
