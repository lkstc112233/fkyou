package com.galaxystudio.fkyou;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;

public class QuestionsXmlPhraser {
	public static class Question {
		public String title;
		public String A;
		public String B;
	}

	public ArrayList<Question> questions = new ArrayList<Question>();

	public QuestionsXmlPhraser(Context c) {
		XmlResourceParser xrp = c.getResources().getXml(R.xml.questions);
		try {
			Question q = null;
			String s = "";
			while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
				if (xrp.getEventType() == XmlResourceParser.START_TAG) {
					String name = xrp.getName();
					if (name.equals("question"))
						q = new Question();
				} else if (xrp.getEventType() == XmlPullParser.END_TAG) {
					String name = xrp.getName();
					if (name.equals("question"))
						questions.add(q);
					else if (name.equals("title"))
						q.title = s;
					else if (name.equals("A"))
						q.A = s;
					else if (name.equals("B"))
						q.B = s;
				} else if (xrp.getEventType() == XmlPullParser.TEXT) {
					s = xrp.getText();
				}
				// 下一个标签
				xrp.next();
			}
			// myTextView.setText(sb.toString());
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
