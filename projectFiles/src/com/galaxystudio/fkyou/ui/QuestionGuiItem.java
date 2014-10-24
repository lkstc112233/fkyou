package com.galaxystudio.fkyou.ui;

import java.util.ArrayList;
import java.util.Random;

import net.muststudio.util.guiitemlib.ui.BlockedBackToRemoveGuiItemContainer;
import net.muststudio.util.guiitemlib.ui.GenericButton;
import net.muststudio.util.guiitemlib.ui.GenericButton.Task;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import net.muststudio.util.guiitemlib.ui.StringHolder;
import net.muststudio.util.guiitemlib.ui.TextPainter;

import com.galaxystudio.fkyou.QuestionsXmlPhraser;
import com.galaxystudio.fkyou.QuestionsXmlPhraser.Question;

public class QuestionGuiItem extends BlockedBackToRemoveGuiItemContainer {
	ArrayList<Question> questions;
	Random rand = new Random();
	TextPainter text;
	StringHolder button1Text;
	StringHolder button2Text;

	public QuestionGuiItem(QuestionsXmlPhraser p) {
		questions = p.questions;
		text = new TextPainter(new RelativePoint(0, 0), new RelativePoint(1, 0.2, false));
		text.setHorizontalCenter(true);
		text.setVerticalCenter(true);
		text.setTextCount(15);
		addToList(text);
		GenericButton button = new MyButton(new RelativePoint(0, 0, false), new RelativePoint(0.5,
				0.2, false));
		button1Text = button.getStringHolder();
		button.setTask(new Task() {
			@Override
			public void task() {
				loadNextQuestion();
			}
		});
		addToList(button);
		button = new MyButton(new RelativePoint(1, 0, false), new RelativePoint(0.5, 0.2, false));
		button2Text = button.getStringHolder();
		button.setTask(new Task() {
			@Override
			public void task() {
				loadNextQuestion();
			}
		});
		addToList(button);
		loadNextQuestion();
	}

	public void loadNextQuestion() {
		Question q = questions.get(rand.nextInt(questions.size()));
		text.setString(q.title);
		button1Text.setString(q.A);
		button2Text.setString(q.B);
	}
}
