package info.project.datapotal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

	WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);

		setTitle("지식경제부 기술표준원");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// 액션바에서 뒤로가기 버튼을 생성하기 위해 선언.

		// webview.setWebViewClient(new WebClient()); // 응룡프로그램에서 직접 url 처리

		wv = (WebView) findViewById(R.id.webview);
		
		// 이렇게 해야 Web이 App 바깥영역으로 나가지 않는다.
		wv.setWebViewClient(new MyWebClient());
		
		// WebSetting 옵션
		WebSettings set = wv.getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);	// 감압식 폰 화면 확대
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadUrl("http://www.kats.go.kr/mobile/main.do");
	}

	/*
	 * Android Activity transition slide in/out animation back 버튼눌렀을시 인텐트 애니메이션
	 * 효과 주는 부분
	 */
	@Override
	public void onBackPressed() {
		if (wv.canGoBack()) {
			wv.goBack();
		} else {
			super.onBackPressed();
			overridePendingTransition(R.anim.pull_in_left,
					R.anim.push_out_right);
		}
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

	private class MyWebClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return super.shouldOverrideUrlLoading(view, url);
		}
	}
}
