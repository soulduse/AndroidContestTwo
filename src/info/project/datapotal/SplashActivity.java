package info.project.datapotal;

import info.project.datapotal.viewpager.sign.WordsCompletion;
import info.project.datapotal.viewpager.sign.vo.Words_SQLiteHandler;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;
	Words_SQLiteHandler handler;
	WordsCompletion words;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		new Handler().postDelayed(new Runnable() {
			//
			// Showing splash screen with a timer. This will be useful when you
			// want to show case your app logo / company
			//
			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				words = new WordsCompletion();

				if (handler == null) {
					handler = Words_SQLiteHandler.open(getApplicationContext());
				}

				cursor = handler.selectAll();
				if (cursor.getCount() > 0) {

				} else {
					for (int i = 0; i < words.figure.length; i++) {
						handler.insert(words.figure[i]);
					}
				}
				
				Log.i("MyTag","getCount:"+cursor.getCount());

				Intent i = new Intent(SplashActivity.this, MenuActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}

}
