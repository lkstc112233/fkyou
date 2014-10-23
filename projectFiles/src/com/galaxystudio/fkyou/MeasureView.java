package com.galaxystudio.fkyou;

import com.galaxystudio.fkyou.R;
import com.galaxystudio.fkyou.database.DatabaseHelper;
import com.galaxystudio.fkyou.ui.BannerPosterGuiItem;
import com.galaxystudio.fkyou.ui.BitmapButton;
import com.galaxystudio.fkyou.ui.BluetoothGuiItem;
import com.galaxystudio.fkyou.ui.HistoryGuiItem;
import com.galaxystudio.fkyou.util.BluetoothManager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import net.muststudio.util.guiitemlib.ui.BitmapPainter;
import net.muststudio.util.guiitemlib.ui.BlockedBackToRemoveGuiItemContainer;
import net.muststudio.util.guiitemlib.ui.DialogGuiItem;
import net.muststudio.util.guiitemlib.ui.GenericButton.Task;
import net.muststudio.util.guiitemlib.ui.GuiItemContainer;
import net.muststudio.util.guiitemlib.ui.PressableButton;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import net.muststudio.util.guiitemlib.ui.TextEditGuiItem;
import net.muststudio.util.guiitemlib.ui.TextPainter;
import net.muststudio.util.guiitemlib.util.BitmapHolder;
import net.muststudio.util.guiitemlib.view.SurfaceViewFrame;

public class MeasureView extends SurfaceViewFrame {
	private class MainMenuGuiItems extends GuiItemContainer {
		private class SettingsGuiItems extends BlockedBackToRemoveGuiItemContainer {
			private TextEditGuiItem ageEdit;

			public SettingsGuiItems() {
				setBackgroundColor(Color.rgb(0xdc, 0xea, 0xf1));
				addToList(new BannerPosterGuiItem(BitmapHolder.getInstance().getBitmap(
						R.drawable.settings), Color.rgb(0x40, 0xb7, 0xb0), new RelativePoint(0, 0),
						new RelativePoint(1, 0.2)));
				addToList(new TextPainter(new RelativePoint(0.05, 0.25),
						new RelativePoint(0.3, 0.3)).setHorizontalCenter(true).setTextCount(8)
						.setString("婴儿年龄（月）："));
				addToList(ageEdit = new TextEditGuiItem(new RelativePoint(0.4, 0.25),
						new RelativePoint(0.95, 0.3), Integer.toString(TempertureSettings
								.getSettings().getAge())));
				addToList(new PressableButton(new RelativePoint(0.05, 0.3), new RelativePoint(0.5,
						0.4), "确定", Color.rgb(0xdc, 0xea, 0xf1)).setTask(new Task() {
					@Override
					public void task() {
						try {
							TempertureSettings.getSettings()
									.setAge(Integer.valueOf(ageEdit.getText())).write();
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
						removeThis();
					}
				}));
				addToList(new PressableButton(new RelativePoint(0.5, 0.3), new RelativePoint(0.95,
						0.4), "取消", Color.rgb(0xdc, 0xea, 0xf1)).setTask(new Task() {
					@Override
					public void task() {
						removeThis();
					}
				}));
				addToList(new PressableButton(new RelativePoint(0, 0, false), new RelativePoint(1,
						0.05, false), "清空记录", Color.rgb(0xdc, 0xea, 0xf1)).setTask(new Task() {
					@Override
					public void task() {
						addTo(new DialogGuiItem("此操作不可撤销。您确定吗？", "我很确定", new Task() {
							@Override
							public void task() {
								addTo(new DialogGuiItem("此操作真的不可撤销。您真的确定吗？", "果然还是算了吧", new Task() {
									@Override
									public void task() {
									}
								}, "我真的很确定", new Task() {
									@Override
									public void task() {
										DatabaseHelper.eraseDatabase();
									}
								}));
							}
						}, "算了吧"));
					}
				}));

				addToList(new BitmapPainter(new RelativePoint(0.8, 0.06, false), new RelativePoint(
						0.95, 0.17, false)).setBitmap(BitmapHolder.getInstance().getBitmap(
						R.drawable.paw)));
			}
		}

		public MainMenuGuiItems() {
			addToList(new BannerPosterGuiItem(
					BitmapHolder.getInstance().getBitmap(R.drawable.main), Color.rgb(0x40, 0xb7,
							0xb0), new RelativePoint(0, 0), new RelativePoint(1, 0.2)));
			addToList(new BitmapButton(new RelativePoint(1. / 20, 11. / 60, true, true),
					new RelativePoint(9. / 20, 19. / 60, true, true), BitmapHolder.getInstance()
							.getBitmap(R.drawable.bluetooth_button)).setTask(new Task() {
				@Override
				public void task() {
					BluetoothManager.openBluetooth();
					addTo(new BluetoothGuiItem());
				}
			}));
			addToList(new BitmapButton(new RelativePoint(1. / 20, 21. / 60, true, true),
					new RelativePoint(9. / 20, 29. / 60, true, true), BitmapHolder.getInstance()
							.getBitmap(R.drawable.settings_button)).setTask(new Task() {
				@Override
				public void task() {
					addTo(new SettingsGuiItems());
				}
			}));
			addToList(new BitmapButton(new RelativePoint(1. / 2, 11. / 60, true, true),
					new RelativePoint(19. / 20, 29. / 60, true, true), BitmapHolder.getInstance()
							.getBitmap(R.drawable.history_button)).setTask(new Task() {
				@Override
				public void task() {
					addTo(new HistoryGuiItem());
				}
			}));
			addToList(new BitmapPainter(new RelativePoint(0.65, 0.12, false), new RelativePoint(
					0.95, 0.42, false)).setBitmap(BitmapHolder.getInstance().getBitmap(
					R.drawable.angle)));
			addToList(new BannerPosterGuiItem(BitmapHolder.getInstance().getBitmap(
					R.drawable.dark_paw), Color.rgb(0x44, 0xb4, 0xb0), new RelativePoint(0, 0,
					false), new RelativePoint(1, 0.1, false)));
		}

		@Override
		public void draw(Canvas canvas) {
			canvas.drawRGB(0xdc, 0xea, 0xf1);
			super.draw(canvas);
		}
	}

	public MeasureView(Context context) {
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
