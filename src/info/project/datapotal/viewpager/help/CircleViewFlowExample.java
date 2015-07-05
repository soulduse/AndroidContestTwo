/*
 * Copyright (C) 2011 Patrik 걃erfeldt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.project.datapotal.viewpager.help;

import info.project.datapotal.R;

import net.simonvt.menudrawer.MenuDrawer;

import org.taptwo.android.widget.CircleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class CircleViewFlowExample extends Activity {

	private ViewFlow viewFlow;
	public int pageChange;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("도움말");
		setContentView(R.layout.activity_indicator);

		viewFlow = (ViewFlow) findViewById(R.id.viewflow);

		Intent intent = getIntent();
		if (intent.getExtras().getInt("help1") == 1) {
			viewFlow.setAdapter(new ImageAdapter(this, 1), 0);
		}
		if (intent.getExtras().getInt("help2") == 2) {
			viewFlow.setAdapter(new ImageAdapter(this, 2), 0);
		}
		if (intent.getExtras().getInt("help3") == 3) {
			viewFlow.setAdapter(new ImageAdapter(this, 3), 0);
		}
		
		CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
		viewFlow.setFlowIndicator(indic);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		// 액션바에서 뒤로가기 버튼을 생성하기 위해 선언.
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

	/*
	 * If your min SDK version is < 8 you need to trigger the
	 * onConfigurationChanged in ViewFlow manually, like this
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		viewFlow.onConfigurationChanged(newConfig);
	}
}