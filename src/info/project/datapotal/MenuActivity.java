package info.project.datapotal;

import info.project.datapotal.viewpager.ViewPagerActivity;
import info.project.datapotal.viewpager.help.HelpMenu;
import info.project.datapotal.viewpager.sign.vo.Words_SQLiteHandler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MenuActivity extends Activity implements OnClickListener {

	ImageButton btnProduct, btnImg, btnFavorite, btnHelp, btnSite;
	Intent intent;
	Words_SQLiteHandler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		if(handler==null){
			handler = Words_SQLiteHandler.open(getApplicationContext());
		}
		Cursor c = handler.selectAll();
		Log.i("MyTag","c.getcount() : " + c.getCount());
		
		btnProduct = (ImageButton)findViewById(R.id.imageMenu1);
		btnImg = (ImageButton)findViewById(R.id.imageMenu2);
		btnFavorite = (ImageButton)findViewById(R.id.imageMenu3);
		btnHelp = (ImageButton)findViewById(R.id.imageMenu4);
		btnSite = (ImageButton)findViewById(R.id.img_site);
		
		btnProduct.setBackgroundColor(Color.parseColor("#F0F0F0"));
		btnImg.setBackgroundColor(Color.parseColor("#F0F0F0"));
		btnFavorite.setBackgroundColor(Color.parseColor("#F5F5F5"));
		btnHelp.setBackgroundColor(Color.parseColor("#F5F5F5"));
		
		btnProduct.setOnClickListener(this);
		btnImg.setOnClickListener(this);
		btnFavorite.setOnClickListener(this);
		btnHelp.setOnClickListener(this);
		btnSite.setOnClickListener(this);

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch (item.getItemId()) {
	      case android.R.id.home:
	         NavUtils.navigateUpTo(this,
	               new Intent(this, MenuActivity.class));
	         return true;
	   }
	   return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imageMenu1:
			intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
			intent.putExtra("tab1", 1);
			startActivity(intent);
			overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
			break;
		
		case R.id.imageMenu2:
			intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
			intent.putExtra("tab2", 2);
			startActivity(intent);
			overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
			break;
		
		case R.id.imageMenu3:
			intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
			intent.putExtra("tab3", 3);
			startActivity(intent);
			overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
			break;
		
		case R.id.imageMenu4:
			Intent intent = new Intent(getApplicationContext(), HelpMenu.class);
			startActivity(intent);
			overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
			break;
			
		case R.id.img_site:
			Intent intent_webView = new Intent(getApplicationContext(), WebViewActivity.class);
			startActivity(intent_webView);
			overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("확인 대화 상자")
		.setMessage("종료하시겠습니까?")
		.setCancelable(false)
		.setNegativeButton("YES", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				MenuActivity.this.finish();
			}
		})
		.setPositiveButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		}).setOnKeyListener(new DialogInterface.OnKeyListener() {
			
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				dialog.cancel();
				return false;
			}
		})
		.show();
		AlertDialog alert = builder.create();
	}	
}
