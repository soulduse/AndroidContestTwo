package info.project.datapotal.viewpager.product.adapter;

import info.project.datapotal.R;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.Toast;


public class DB_List_Search_Main_Activity extends Activity implements OnScrollListener{
	
	JSONObject jobject;
	JSONArray jarray;
	DB_List_Search_Adapter adapter;
	
	ArrayList<HashMap<String,String>> arraylist;
    ProgressDialog dialog;
    
    static int size = 20;
    static boolean first = true;
    
	static String COMPANY = "sb_makingCompany";
	static String COUNTRY = "sb_makingCountry";
	static String GOODS = "sp_goods";
	static String MODEL = "sp_model_str";
	static String CODENAME = "totalcodeName";
	static String CONFIRMNUM = "sc_confirmNum";
	static String CONFIRMDAY = "sc_confirmDay";
	static String DIVISION = "sc_division";
	static String BRAND = "sp_brand";
	
	public static String url;
	
    ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_nation_list);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("인증제품 검색결과");
		new JsonDown().execute();
	}
	
private class JsonDown extends AsyncTask<Void, Void, Void> {
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(DB_List_Search_Main_Activity.this);
			dialog.setTitle("잠시만기다려주세요");
			dialog.setMessage("로딩중...");
			dialog.setIndeterminate(false);
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			arraylist = new ArrayList<HashMap<String, String>>();
			jobject = DB_Json.getJURL
					("http://www.ibtk.kr/api_confirm_detail/2b43e497a1327b04b376a603f249df9c?" +
							"model_query_pageable=%7B'pageSize':" +
							size +
							"%7D&"+ "model_query=%7B$and:%5B"
							+ url + "%5D%7D");
			try {
				jarray = jobject.getJSONArray("content");
				for(int i = 0; i < jarray.length(); i++) {
					HashMap<String, String> map = new HashMap<String,String>();
					jobject = jarray.getJSONObject(i);
					map.put("sb_makingCompany", jobject.getString("sb_makingCompany"));
					map.put("sb_makingCountry", jobject.getString("sb_makingCountry"));
					map.put("sp_goods", jobject.getString("sp_goods"));
					map.put("sp_model_str", jobject.getString("sp_model_str"));
					map.put("totalcodeName", jobject.getString("totalcodeName"));
					map.put("sc_confirmNum", jobject.getString("sc_confirmNum"));
					map.put("sc_confirmDay", jobject.getString("sc_confirmDay"));
					map.put("sc_division", jobject.getString("sc_division"));
					map.put("sp_brand", jobject.getString("sp_brand"));
					arraylist.add(map);
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if(arraylist.size() == 0){
					Toast.makeText(getApplication(), "검색 결과 없음", Toast.LENGTH_SHORT).show();
					dialog.dismiss();
			} else {
			listview = (ListView) findViewById(R.id.db_nation_listview);
			adapter = new DB_List_Search_Adapter(DB_List_Search_Main_Activity.this, arraylist);
			listview.setAdapter(adapter);
			dialog.dismiss();
			listview.setOnScrollListener(DB_List_Search_Main_Activity.this);
            if(first == false) {
            	listview.setSelection(listview.getCount() - 20);
            }
			}
		}
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if(view.isShown()) {
			 if(scrollState == SCROLL_STATE_IDLE) {
                // 리스트뷰의 0 번 인덱스 항목이 리스트뷰의 상단에 보이고 있는 경우
				 if(view.getLastVisiblePosition() == size - 1){
					 size += 20;
					 first = false;
		                new JsonDown().execute();
				 }
			 }
		}
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
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