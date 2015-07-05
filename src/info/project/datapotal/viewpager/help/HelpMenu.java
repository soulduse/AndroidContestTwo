package info.project.datapotal.viewpager.help;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.ViewPagerActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelpMenu extends Activity implements OnClickListener {

	Button helpProduct;
	Button helpRecall;
	Button helpPicture;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_helpmenu);
		setTitle("도움말");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// 액션바에서 뒤로가기 버튼을 생성하기 위해 선언.

		helpProduct = (Button) findViewById(R.id.helpBtn1);
		helpRecall = (Button) findViewById(R.id.helpBtn2);
		helpPicture = (Button) findViewById(R.id.helpBtn3);

		helpProduct.setOnClickListener(this);
		helpRecall.setOnClickListener(this);
		helpPicture.setOnClickListener(this);
		
	}
	
	/*
	 * Android Activity transition slide in/out animation back 버튼눌렀을시 인텐트 애니메이션
	 * 효과 주는 부분
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		case R.id.action_search:
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.helpBtn1:
			intent = new Intent(getApplicationContext(), CircleViewFlowExample.class);
			intent.putExtra("help1", 1);
			startActivity(intent);
			overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
			break;
			
		case R.id.helpBtn2:
			intent = new Intent(getApplicationContext(), CircleViewFlowExample.class);
			intent.putExtra("help2", 2);
			startActivity(intent);
			overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
			break;
			
		case R.id.helpBtn3:
			intent = new Intent(getApplicationContext(), CircleViewFlowExample.class);
			intent.putExtra("help3", 3);
			startActivity(intent);
			overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
			break;
		}

	}

}
