package com.nol888.hwat;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HwatActivity extends Activity {

	private static final String[] texts = {
			"such hwæt",
			"so kotori",
			"many hwæt",
			"very love live",
			"wow",
			"such aidorus"
	};

	private Typeface comicSans;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		comicSans = Typeface.createFromAsset(getAssets(), "comic.ttf");

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_hwat);

		((ImageView) findViewById(R.id.imgHwat)).setOnClickListener(new HwatClickListener());

	}

	private class HwatClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			MediaPlayer mp = MediaPlayer.create(HwatActivity.this, R.raw.hwat);
			final TextView textView = new TextView(HwatActivity.this);
			final RelativeLayout layout = (RelativeLayout) findViewById(R.id.RelativeLayout1);
			Point pos = getRandomPosition();

			textView.setTypeface(comicSans);
			textView.setText(getText());
			textView.setTextSize(40);
			textView.setTextColor(getRandomColor());

			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			params.leftMargin = pos.x;
			params.topMargin = pos.y;
			layout.addView(textView, params);

			mp.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
					layout.removeView(textView);
				}
			});

			mp.start();
		}

		private Point getRandomPosition() {
			int width = getWindow().getDecorView().getWidth(), height = getWindow().getDecorView().getHeight();
			Random random = new Random();

			width /= 3;
			height /= 2;

			width = random.nextInt(width);
			height = random.nextInt(height);

			return new Point(width, height);
		}

		private int getRandomColor() {
			Random random = new Random();
			return Color.HSVToColor(new float[] { random.nextInt(360), (float) (random.nextFloat() * .4 + .6), 1f });
		}

		private String getText() {
			Random random = new Random();
			return texts[random.nextInt(texts.length)];
		}
	}
}
