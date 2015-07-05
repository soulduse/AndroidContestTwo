package info.project.datapotal.viewpager.product.adapter;

import info.project.datapotal.R;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import database.Recall_SQLiteHandler;
import database.Sign_SQLiteHandler;

public class Last_RecallActivity extends Activity {

	DisplayImageOptions defaultOptions;
	Builder configBuilder;
	ImageLoaderConfiguration config;
	Cursor cursor;
	Recall_SQLiteHandler handler;

	String linkURL;
	String imgFilter[];
	String productName;
	String productCategory;
	String model;
	String makingNation;
	String nationFilter[];
	String sellTerm1;
	String sellTerm2;
	String recallNationType;
	String recallType;
	String harmCause;
	String harmContents;
	String recallAction;
	String actions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.last_recall_activity);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("리콜제품 상세");
		
		Intent intent = getIntent();
		Bundle bundle = new Bundle();
		bundle = intent.getExtras();
		HashMap<String, String> map = (HashMap<String, String>) bundle
				.getSerializable("map");

		if (handler == null)
			handler = Recall_SQLiteHandler.open(getApplicationContext());

		ImageView imgIcon = (ImageView) findViewById(R.id.last_imgIcon);
		TextView txtProduct = (TextView) findViewById(R.id.last_txtproduct);
		TextView txtProductCategory = (TextView) findViewById(R.id.last_txtProductCategory);
		TextView txtModel = (TextView) findViewById(R.id.last_txtmodel);
		TextView txtMakingNation = (TextView) findViewById(R.id.last_txtmakingNation);
		TextView txtSellTerm1 = (TextView) findViewById(R.id.last_sellTerm1);
		TextView txtSellTerm2 = (TextView) findViewById(R.id.last_sellTerm2);
		TextView txtRecallNatinoType = (TextView) findViewById(R.id.last_recallNationType);
		TextView txtRecallType = (TextView) findViewById(R.id.last_recallType);
		TextView txtHarmCause = (TextView) findViewById(R.id.last_harmCause);
		TextView txtHarmContent = (TextView) findViewById(R.id.last_harmContents);
		TextView txtRecallAction = (TextView) findViewById(R.id.last_recallAction);
		TextView txtAction = (TextView) findViewById(R.id.last_Action);

		linkURL = map.get("linkURL");
		imgFilter = linkURL.split(", ");
		productName = map.get("productName");
		productCategory = map.get("productCategory");
		model = map.get("model");
		makingNation = map.get("makingNation");
		nationFilter = makingNation.split(">");
		sellTerm1 = map.get("sellTerm1");
		sellTerm2 = map.get("sellTerm2");
		recallNationType = map.get("recallNationType");
		recallType = map.get("recallNationType");
		harmCause = map.get("harmCause");
		harmContents = map.get("harmContents");
		recallAction = map.get("recallAction");
		actions = map.get("actions");

		defaultOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.loading)
				.showImageForEmptyUri(R.drawable.error)
				.showImageOnFail(R.drawable.loading_fail).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		config = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024)
				// 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		ImageLoader.getInstance().init(config);

		ImageLoader.getInstance().displayImage(imgFilter[0], imgIcon,
				defaultOptions);
		if (imgFilter[0].equals(""))
			Log.i("MyTag", "imgFilter[0]:" + imgFilter[0]);
		txtProduct.setText(productName);
		txtProductCategory.setText(productCategory);
		txtModel.setText(model);
		txtMakingNation.setText(nationFilter[nationFilter.length - 1]);
		if (sellTerm1.equals("") == false) {
			txtSellTerm1.setText(sellTerm1 + " 부터");
		} else {
			txtSellTerm1.setText("판매시작 정보 없음");
		}
		if (sellTerm2.equals("") == false) {
			txtSellTerm2.setText(sellTerm2 + " 까지");
		} else {
			txtSellTerm2.setText("판매종료 정보 없음");
		}
		if (recallNationType.equals("1")) {
			txtRecallNatinoType.setText("국내에서");
		} else if (recallNationType.equals("2")) {
			txtRecallNatinoType.setText("국외에서");
		}
		if (recallType.equals("1")) {
			txtRecallType.setText("권고에 의해서 리콜");
		} else if (recallType.equals("2")) {
			txtRecallType.setText("명령에 의해서 리콜");
		} else if (recallType.equals("3")) {
			txtRecallType.setText("자발적인 의식에 인한 리콜");
		}
		txtHarmCause.setText(harmCause);
		txtHarmContent.setText(harmContents);
		txtRecallAction.setText(recallAction);
		txtAction.setText(actions);
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

			String tmp_model;
			String tmp_productName;
			String tmp_URL;

			if (model.trim().equals("")) {
				tmp_model = "모델정보 없음";
			} else {
				tmp_model = model;
			}

			if (productName.trim().equals("")) {
				tmp_productName = "null";
			} else {
				tmp_productName = productName;
			}

			if (imgFilter[0].trim().equals("")) {
				tmp_URL = "null";
			} else {
				tmp_URL = imgFilter[0];
			}

			cursor = handler.select();
			if (cursor.getCount() > 0) {
				while(cursor.moveToNext()){
					String compare_model = cursor.getString(cursor.getColumnIndex("model"));
					String compare_url = cursor.getString(cursor.getColumnIndex("linkurl"));
					String compare_product = cursor.getString(cursor.getColumnIndex("productname"));
					
					if(compare_model.equals(tmp_model) && compare_url.equals(tmp_URL)
							&& compare_product.equals(tmp_productName)){
						
						Toast.makeText(getApplicationContext(), "이미 등록된 항목입니다",
								Toast.LENGTH_SHORT).show();
						areThereExistSameValues = true;
						break;
					}
				}
			}else{
				
			}
			
			if (areThereExistSameValues == false) {
				Log.i("MyTag", "모델:" + tmp_model + "\nURL:" + tmp_URL + "\n제품명:"
						+ tmp_productName);
				handler.insert(tmp_model, tmp_URL, tmp_productName);
				Toast.makeText(getApplicationContext(), "즐겨찾기에 추가되었습니다",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}
}
