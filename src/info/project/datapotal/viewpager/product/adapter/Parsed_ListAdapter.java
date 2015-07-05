package info.project.datapotal.viewpager.product.adapter;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.ServiceHandler;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.twotoasters.jazzylistview.JazzyGridView;
import com.twotoasters.jazzylistview.JazzyListView;

public class Parsed_ListAdapter extends Activity implements OnScrollListener, OnItemClickListener{

	Intent intent, newIntent;
	RecallAdapter recallAdapter;
	ArrayList<HashMap<String, String>> list;
	int currentBtn;
	ProgressBar progress;
	JazzyGridView jazzyGrid;

	static int pageNumber;
	static String urls;
	boolean mLockListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parsed_listadapter);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("리콜제품 상세");

		pageNumber = 0;
		mLockListView = true;

		list = new ArrayList<HashMap<String, String>>();
		intent = getIntent();
		Bundle bundle = new Bundle();
		bundle = intent.getExtras();
		urls = bundle.getString("urls");
		currentBtn = bundle.getInt("currentBtn");
		
		jazzyGrid = (JazzyGridView) findViewById(R.id.jazziGrid);

		progress = (ProgressBar) findViewById(R.id.progressBar1);
		recallAdapter = new RecallAdapter(getApplicationContext(),
				R.layout.recalladapter, list, currentBtn);
		new GetParsedData().execute(urls);
		jazzyGrid.setAdapter(recallAdapter);
		jazzyGrid.setOnItemClickListener(this);
		jazzyGrid.setOnScrollListener(this);

	}

	private class GetParsedData extends AsyncTask<String, Void, Void> {

		/* 통신 변수 설정 */

		private String url;
		private String target;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			mLockListView = true;

			new DefaultHttpClient();
			url = "http://www.ibtk.kr/recallDetail_api/fe3a671e51cbb7de10a5fbc27b127119?";

		}

		@Override
		protected Void doInBackground(String... params) {

			target = params[0];
			/**
			 * 가져와야 할 내용
			 * 
			 * model - 모델 actions - 조치사항 recallNationType - 리콜형태 linkURL - 이미지
			 * URL productCategory - 상품분류 harmCause - 위해원인 makingNation - 제조국
			 * harmContents - 위해내용 companyName - 제보사업자명 saleCompany - 판매업체
			 * recallAction - 리콜조치원인 sellTerm1,sellTerm2 - 판매기간 시작,종료
			 * productContents - 제품명
			 * 
			 * 화면에 공통적으로 나와야 할 내용 ------------------ 제조국(makingNation) - 오름차순으로
			 * 정렬 모델(model) - 오름차순으로 정렬 제품명(productContents) - 오름차순으로 정렬
			 * ------------------
			 */

			/**
			 * (example) URL(나라별) - "" => [알 수 없음] 으로 따로 처리해 분류하도록 한다.
			 * 
			 * http://api.ibtk.kr/recallDetail_api/
			 * fe3a671e51cbb7de10a5fbc27b127119?
			 * model_query=%7B%22makingNation%22:&7B%22$ne%22:%22%22%7D%7D&
			 * model_query_pageable=%7B'pageNumber':원하는 페이지
			 * 번호,'pageSize':50,%27sortOrders%27:
			 * [%7B%27property%27:%22makingNation%22,%27direction%27:1%7D]%7D
			 */

			// 작업 시작 //
			/**
			 * { : %7B , } : %7D [ : %5B , ] : %5D "a" -> \"a\"
			 */
			url += target;
			url += "&model_query_pageable=%7B'pageNumber':" + pageNumber
					+ ",'pageSize':20%7D";
			pageNumber++;
			ServiceHandler sh = new ServiceHandler();
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

			if (jsonStr != null) {
				try {
					JSONObject obj = new JSONObject(jsonStr);
					JSONArray jArray = obj.getJSONArray("content");

					for (int i = 0; i < jArray.length(); i++) {
						JSONObject jOject = jArray.getJSONObject(i);
						HashMap<String, String> map = new HashMap<String, String>();

						// 파싱된 데이터 집어넣기
						map.put("model", jOject.getString("model"));
						map.put("actions", jOject.getString("actions"));
						map.put("productName", jOject.getString("productName"));
						map.put("recallNationType",
								jOject.getString("recallNationType"));
						map.put("recallType",
								jOject.getString("recallType"));
						map.put("linkURL", jOject.getString("linkURL"));
						map.put("productCategory",
								jOject.getString("productCategory"));
						map.put("harmCause", jOject.getString("harmCause"));
						map.put("makingNation",
								jOject.getString("makingNation"));
						map.put("harmContents",
								jOject.getString("harmContents"));
						map.put("companyName", jOject.getString("companyName"));
						map.put("saleCompany", jOject.getString("saleCompany"));
						map.put("recallAction",
								jOject.getString("recallAction"));
						map.put("sellTerm1", jOject.getString("sellTerm1"));
						map.put("sellTerm2", jOject.getString("sellTerm2"));
						map.put("productContents",
								jOject.getString("productContents"));
						list.add(map);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}// end doInBackground

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			recallAdapter.notifyDataSetChanged();
			mLockListView = false;
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
		if ((firstVisibleItem + visibleItemCount) == totalItemCount
				&& totalItemCount != 0 && mLockListView == false) {
			new GetParsedData().execute(urls);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		newIntent = new Intent(getApplicationContext(), Last_RecallActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("map", list.get(position));
		newIntent.putExtras(bundle);
		startActivity(newIntent);
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			onBackPressed();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
