package info.project.datapotal.viewpager.sign;

import info.project.datapotal.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import database.Sign_SQLiteHandler;

public class SignIntentActivity extends Activity {

	// JSON node keys
	public static String TAG_DESCRIPTION = "description";
	public static String TAG_APPLYFIELD = "applyField";
	public static String TAG_IMG = "fileName";
	public static String TAG_KORNAME = "korName";
	public static String TAG_ENGNAME = "engName";
	public static String TAG_RELKSNAME = "relKsName";
	public static String TAG_PICSEQNO = "picseqno";

	DisplayImageOptions options;
	ImageLoaderConfiguration config;
	Sign_SQLiteHandler handler;
	Cursor cursor;

	String title;
	String description;
	String applyField;
	String fileName;
	String korName;
	String engName;
	String relKsName;
	String picseqno;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_child);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("�����ȳ� �׸�ǥ�� ��");
		// �׼ǹٿ��� �ڷΰ��� ��ư�� �����ϱ� ���� ����.
		
		if(handler==null)
			handler = Sign_SQLiteHandler.open(getApplicationContext());

		// �ʱ⼳��
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.loading)
				.showImageForEmptyUri(R.drawable.error)
				.showImageOnFail(R.drawable.loading_fail).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		config = new ImageLoaderConfiguration.Builder(this)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024)
				// 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);

		// getting intent data
		Intent in = getIntent();

		// Get JSON values from previous intent
		title = in.getStringExtra(TAG_KORNAME) + " ��";
		description = in.getStringExtra(TAG_DESCRIPTION);
		applyField = in.getStringExtra(TAG_APPLYFIELD);
		fileName = in.getStringExtra(TAG_IMG);
		korName = in.getStringExtra(TAG_KORNAME);
		engName = in.getStringExtra(TAG_ENGNAME);
		relKsName = in.getStringExtra(TAG_RELKSNAME);
		picseqno = in.getStringExtra(TAG_PICSEQNO);
		Log.i("MyTag","SigIntent�ȿ����� picseqno:"+picseqno);

		// Displaying all values on the screen
		ImageView signView = (ImageView) findViewById(R.id.sign_child_icon);
		TextView signTitle = (TextView) findViewById(R.id.sign_title_child);
		TextView signDescription = (TextView) findViewById(R.id.sign_description_child);
		TextView signapplyField = (TextView) findViewById(R.id.sign_applyField_child);
		TextView signKorName = (TextView) findViewById(R.id.sign_korName_child);
		TextView signEngName = (TextView) findViewById(R.id.sign_engName_child);
		TextView signRelKsName = (TextView) findViewById(R.id.sign_relKsName_child);

		signTitle.setText(title);
		signDescription.setText(description);
		signapplyField.setText(applyField);
		ImageLoader.getInstance().displayImage(fileName.toString(), signView,
				options);
		signKorName.setText(korName);
		signEngName.setText(engName);
		signRelKsName.setText(relKsName);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_into_bucket, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		case R.id.addItem:
			boolean areThereExistSameValues = false;
			cursor = handler.find(picseqno);
			if (cursor.getCount()>0) {
				Toast.makeText(getApplicationContext(), "�̹� ��ϵ� �׸��Դϴ�",
						Toast.LENGTH_SHORT).show();
				areThereExistSameValues = true;
				
			}
			if (areThereExistSameValues == false) {
				handler.insert(korName, picseqno, fileName);
				Toast.makeText(getApplicationContext(), "���ã�⿡ �߰��Ǿ����ϴ�",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
