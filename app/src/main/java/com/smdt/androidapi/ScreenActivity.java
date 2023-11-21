package com.smdt.androidapi;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.smdt.SmdtManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class ScreenActivity extends Activity {

	private Button btn_screen;
	private VideoView videoView;
	private SmdtManager smdt;
	private String filePath = Environment.getExternalStoragePublicDirectory(
			Environment.DIRECTORY_PICTURES).getPath()
			+ File.separator;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.screen);

		btn_screen = (Button) findViewById(R.id.btn_screen);
		videoView = (VideoView) findViewById(R.id.videoView);
		final String sdPath = Environment.getExternalStorageDirectory()
				.getPath().toString()
				+ File.separator + "1.mp4";
		File file = new File(sdPath);
		Log.e("sdPath", "===file:" + file + ", sdPath: " + sdPath);
		if (file.exists()) {
			videoView.setMediaController(new MediaController(this));
			videoView.setVideoPath(sdPath);
			// videoView.setVideoURI(Uri.parse("http://mvvideo1.meitudata.com/572552eaf0d841441.mp4"));
			videoView.start();
			videoView.requestFocus();
			videoView
					.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							mp.start();
							mp.setLooping(true);
						}
					});
			videoView
					.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer arg0) {
							videoView.setVideoPath(sdPath);
							videoView.start();
						}

					});
		}

		btn_screen.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View arg0) {
				smdt = SmdtManager.create(getApplicationContext());
				SimpleDateFormat sdformats = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss-SSS");
				String fileNames = sdformats.format(new Date(System
						.currentTimeMillis())) + ".png";
				//zyh modify by 2018-6-7 A83T6.0
				//writeImageFile(screenShot(), fileNames,filePath);
				smdt.smdtTakeScreenshot(filePath, fileNames,getApplicationContext());
			}
		});
	}

	/*
	 * zyh add by 2018-6-7 A83T6.0
	 */
	public Bitmap screenShot() {
		Bitmap mScreenBitmap = null;
		WindowManager mWindowManager;
		DisplayMetrics mDisplayMetrics;
		Display mDisplay;
		mWindowManager = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		mDisplay = mWindowManager.getDefaultDisplay();
		mDisplayMetrics = new DisplayMetrics();
		mDisplay.getRealMetrics(mDisplayMetrics);

		float[] dims = { mDisplayMetrics.widthPixels,
				mDisplayMetrics.heightPixels };
		try {
			Class<?> testClass = Class.forName("android.view.SurfaceControl");
			Method[] methods = testClass.getMethods();
			for (int i = 0; i < methods.length; i++) {
				System.out.println("method -- >" + methods[i]);
			}
			Method saddMethod1 = testClass.getMethod("screenshot", new Class[] {
					int.class, int.class });
			mScreenBitmap = (Bitmap) saddMethod1.invoke(null, new Object[] {
					(int) dims[0], (int) dims[1] });
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (mScreenBitmap == null) {
			return null;
		}
		mScreenBitmap.setHasAlpha(false);
		mScreenBitmap.prepareToDraw();
		return mScreenBitmap;
	}

	/*
	 * zyh add by 2018-6-7 A83T6.0
	 */
	public void writeImageFile(Bitmap mScreenBitmap, String name, String path) {
		try {
			File file = new File(path + name);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out;
			out = new FileOutputStream(file);
			if (mScreenBitmap.compress(Bitmap.CompressFormat.PNG, 70, out)) {
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
