package com.galaxystudio.fkyou;

import net.muststudio.util.guiitemlib.ui.BitmapPainter;
import net.muststudio.util.guiitemlib.ui.BlockedBackToRemoveGuiItemContainer;
import net.muststudio.util.guiitemlib.ui.RelativePoint;

public class EditGuiItem extends BlockedBackToRemoveGuiItemContainer {
	public EditGuiItem() {
		addToList(new BitmapPainter(new RelativePoint(0, 0), new RelativePoint(1, 0, false))
				.setBitmap(MainActivity.act.photo));
		addToList(new DrawGuiItem());
	}
}
