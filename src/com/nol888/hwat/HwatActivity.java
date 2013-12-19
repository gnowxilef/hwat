package com.nol888.hwat;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class HwatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_hwat);
		
		((ImageView) findViewById(R.id.imgHwat)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            	MediaPlayer mp = MediaPlayer.create(HwatActivity.this, R.raw.hwat);
                mp.setOnCompletionListener(new OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        findViewById(R.id.textView1).setVisibility(View.INVISIBLE);
                    }
                });
                
                findViewById(R.id.textView1).setVisibility(View.VISIBLE);
                mp.start();
            }
        });

	}
}
