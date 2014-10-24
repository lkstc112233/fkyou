package com.galaxystudio.fkyou;

import com.galaxystudio.fkyou.R;
import com.galaxystudio.fkyou.ui.BitmapButton;
import com.galaxystudio.fkyou.ui.EditGuiItem;
import com.galaxystudio.fkyou.ui.GalleryGuiItem;
import com.galaxystudio.fkyou.ui.QuestionGuiItem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import net.muststudio.util.guiitemlib.ui.BlockedBackToRemoveGuiItemContainer;
import net.muststudio.util.guiitemlib.ui.GenericButton.Task;
import net.muststudio.util.guiitemlib.ui.BitmapPainter;
import net.muststudio.util.guiitemlib.ui.GuiItemContainer;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import net.muststudio.util.guiitemlib.util.BitmapHolder;
import net.muststudio.util.guiitemlib.view.SurfaceViewFrame;

public class MainView extends SurfaceViewFrame {
	private class MainMenuGuiItems extends GuiItemContainer {
		public MainMenuGuiItems() {
			Bitmap background = BitmapHolder.getInstance().getBitmap(R.drawable.sb);
			Bitmap button1 = BitmapHolder.getInstance().getBitmap(R.drawable.idiot);
			Bitmap button2 = BitmapHolder.getInstance().getBitmap(R.drawable.fool);
			Bitmap settingButton = BitmapHolder.getInstance().getBitmap(R.drawable.setting);

			addToList(new BitmapButton(new RelativePoint(0, 0, false), new RelativePoint(1, 1.0
					/ background.getWidth() * background.getHeight(), false), background));
			addToList(new BitmapButton(new RelativePoint(0.05, 0.5, false), new RelativePoint(0.5,
					0.5 + 0.45 / button1.getWidth() * button1.getHeight(), false), button1)
					.setTask(new Task() {
						@Override
						public void task() {
							MainActivity.act.startCamera();
						}
					}));
			addToList(new BitmapButton(new RelativePoint(1, 0.4, false), new RelativePoint(0.5, 0.4
					+ 0.5 / button2.getWidth() * button2.getHeight(), false), button2)
					.setTask(new Task() {
						@Override
						public void task() {
							addTo(new EditGuiItem());
						}
					}));
			addToList(new BitmapButton(new RelativePoint(0, 0), new RelativePoint(1, 0.2),
					BitmapHolder.getInstance().getBitmap(R.drawable.invisible)).setTask(new Task() {
				@Override
				public void task() {
					 addTo(new GalleryGuiItem());
				}
			}));
			addToList(new BitmapButton(new RelativePoint(0, 0.4, false), new RelativePoint(1, 0.3,
					false), BitmapHolder.getInstance().getBitmap(R.drawable.invisible))
					.setTask(new Task() {
						@Override
						public void task() {
							addTo(new QuestionGuiItem(MainActivity.act.phraser));
						}
					}));
			addToList(new BitmapButton(new RelativePoint(0.85, 0.25, false), new RelativePoint(0.6,
					0.25 + 0.25 / settingButton.getWidth() * settingButton.getHeight(), false),
					settingButton).setTask(new Task() {
				@Override
				public void task() {
					GuiItemContainer c = new BlockedBackToRemoveGuiItemContainer();
					c.addToList(new BitmapPainter(new RelativePoint(0, 0), new RelativePoint(1, 0,
							false)).setBitmap(BitmapHolder.getInstance()
							.getBitmap(R.drawable.about)));
					addTo(c);
				}
			}));
		}

		@Override
		public void draw(Canvas canvas) {
			canvas.drawRGB(0xdd, 0xdd, 0xdd);
			super.draw(canvas);
		}
	}

	public MainView(Context context) {
		super(context);
	}

	@Override
	protected void logic() {
	}

	@Override
	protected void createSurface() {
	}

	@Override
	protected void initilize() {
		GuiItemContainer ctner = new GuiItemContainer();

		ctner.addToList(new MainMenuGuiItems());

		guiItem = ctner;
	}

}
