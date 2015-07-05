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

		setTitle("���İ����� ���ǥ�ؿ�");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// �׼ǹٿ��� �ڷΰ��� ��ư�� �����ϱ� ���� ����.

		// webview.setWebViewClient(new WebClient()); // �������α׷����� ���� url ó��

		wv = (WebView) findViewById(R.id.webview);
		
		// �̷��� �ؾ� Web�� App �ٱ��������� ������ �ʴ´�.
		wv.setWebViewClient(new MyWebClient());
		
		// WebSetting �ɼ�
		WebSettings set = wv.getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);	// ���н� �� ȭ�� Ȯ��
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadUrl("http://www.kats.go.kr/mobile/main.do");
	}

	/*
	 * Android Activity transition slide in/out animation back ��ư�������� ����Ʈ �ִϸ��̼�
	 * ȿ�� �ִ� �κ�
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
