package org.lyd.layermaskview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * �򵥵�����Ч��
 * @author lyd
 * @date 2015-1
 *
 */
public class FermodeLayout extends FrameLayout{
	
	private Bitmap mBitmap;
	private Paint mPaint;

	public FermodeLayout(Context context) {
		this(context,null);
	}

	public FermodeLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public FermodeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	@SuppressLint("NewApi")
	private void init(){
		if(Build.VERSION.SDK_INT < VERSION_CODES.HONEYCOMB){
			setDrawingCacheEnabled(true);
		}else {
			if(Build.VERSION.SDK_INT >= 11){
				setLayerType(LAYER_TYPE_SOFTWARE, null);
			}
		}
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if(mBitmap != null){
			mPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
			canvas.drawBitmap(mBitmap, 0, 0, mPaint);
			mPaint.setXfermode(null);
		}
	}
	
	public void setRadius(int radius){
		if(mBitmap != null && !mBitmap.isRecycled()){
			mBitmap.recycle();
		}
		mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(mBitmap);
		canvas.drawCircle(getWidth() / 2F, getHeight() / 2F, radius, mPaint);
		invalidate();
	}
}
