package org.lyd.layermaskview;

import org.lyd.layermaskview.view.FermodeLayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class MainActivity extends Activity {
	
	private FermodeLayout mLayout;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLayout = (FermodeLayout) findViewById(R.id.fermode);
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				mLayout.setRadius(100);
			}
		}, 100);
	}
	
	int mLastMotionY;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionY = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int delay = (int) (event.getY() - mLastMotionY);
			if(delay > 0){
				mLayout.setRadius(50 - delay);
			}else {
				mLayout.setRadius(50 - delay);
			}
			break;
		}
		return true;
	}
}
