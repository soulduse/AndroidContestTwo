package info.project.datapotal.viewpager.bookmark;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.ServiceHandler;
import info.project.datapotal.viewpager.product.adapter.Last_RecallActivity;

import java.util.HashMap;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;
import database.Recall_CursorAdapter;
import database.Recall_SQLiteHandler;

public class FragmentBookmarkThree extends Fragment implements
		OnItemClickListener, OnItemLongClickListener {

	Recall_SQLiteHandler handler;
	boolean mLockListView;
	Cursor cursor;
	JSONArray contacts = null;
	HashMap<String, String> contact;
	Recall_CursorAdapter adapter;
	ListView listView;
	HashMap<String, String> map;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 데이터베이스 open
		handler = Recall_SQLiteHandler.open(getActivity());
		cursor = handler.select();
		mLockListView = true;
		map = new HashMap<String, String>();
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.bookmark_three, container,
				false);
		contact = new HashMap<String, String>();
		listView = (ListView) rootView
				.findViewById(R.id.bookmark_three_listview);

		// 데이터베이스 조회
		adapter = new Recall_CursorAdapter(getActivity(), cursor,
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);

		return rootView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Cursor tmp_cursor = handler.select();

		tmp_cursor.moveToPosition(position);

		String linkURL = tmp_cursor.getString(tmp_cursor
				.getColumnIndex("linkurl"));
		String model = tmp_cursor.getString(tmp_cursor.getColumnIndex("model"));
		String productName = tmp_cursor.getString(tmp_cursor
				.getColumnIndex("productname"));

		String url = "";
		if (model.equals("모델정보 없음")) {
			model = "";
		}else{
			model = model.replaceAll("\\p{Space}", "%20")
					.replaceAll("&", "%26")
					.replaceAll("-", "%2D")
					.replaceAll("<", "%3C")
					.replaceAll(">", "%3E")
					.replaceAll("[?]", "")
					.replaceAll("#", "%23");
		}
		if (productName.equals("null")) {
			productName = "";
		}else{
			productName = productName.replaceAll("\\p{Space}", "%20")
					.replaceAll("&", "%26")
					.replaceAll("-", "%2D")
					.replaceAll("<", "%3C")
					.replaceAll(">", "%3E")
					.replaceAll("[?]", "")
					.replaceAll("#", "%23");
		}

		if (linkURL.equals("null")) {
			// 이미지가 없는 경우
			url += "http://www.ibtk.kr/recallDetail_api/fe3a671e51cbb7de10a5fbc27b127119?model_query="
					+ "%7B$and:%5B%7B%22linkURL%22:%22%22%7D,%7B%22model%22:%7B%22$regex%22:%22"
					+ model
					+ "%22%7D%7D"
					+ ",%7B%22productName%22:%22"
					+ productName + "%22%7D%5D%7D&model_query_distinct=model";
		} else {
			// 이미지가 있는 경우
			url += "http://www.ibtk.kr/recallDetail_api/fe3a671e51cbb7de10a5fbc27b127119?model_query="
					+ "%7B%22linkURL%22:%7B%22$regex%22:%22"
					+ linkURL
					+ "%22%7D%7D&model_query_distinct=model";
		}
		new GetParsedData().execute(url);
	}

	private class GetParsedData extends AsyncTask<String, Void, Void> {

		/* 통신 변수 설정 */

		private String target;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			mLockListView = true;

			new DefaultHttpClient();

		}

		@Override
		protected Void doInBackground(String... params) {

			target = params[0];

			ServiceHandler sh = new ServiceHandler();
			String jsonStr = sh.makeServiceCall(target, ServiceHandler.GET);

			if (jsonStr != null) {
				try {
					JSONObject obj = new JSONObject(jsonStr);
					JSONArray jArray = obj.getJSONArray("content");

					JSONObject jOject = jArray.getJSONObject(0);

					// 파싱된 데이터 집어넣기
					map.put("model", jOject.getString("model"));
					map.put("actions", jOject.getString("actions"));
					map.put("productName", jOject.getString("productName"));
					map.put("recallNationType",
							jOject.getString("recallNationType"));
					map.put("recallType", jOject.getString("recallType"));
					map.put("linkURL", jOject.getString("linkURL"));
					map.put("productCategory",
							jOject.getString("productCategory"));
					map.put("harmCause", jOject.getString("harmCause"));
					map.put("makingNation", jOject.getString("makingNation"));
					map.put("harmContents", jOject.getString("harmContents"));
					map.put("companyName", jOject.getString("companyName"));
					map.put("saleCompany", jOject.getString("saleCompany"));
					map.put("recallAction", jOject.getString("recallAction"));
					map.put("sellTerm1", jOject.getString("sellTerm1"));
					map.put("sellTerm2", jOject.getString("sellTerm2"));
					map.put("productContents",
							jOject.getString("productContents"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}// end doInBackground

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			mLockListView = false;
			
			Intent intent = new Intent(getActivity(),Last_RecallActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("map", map);
			intent.putExtras(bundle);
			startActivity(intent);
			
			getActivity().overridePendingTransition(R.anim.pull_in_right,
					R.anim.push_out_left);
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.bucket_menu, menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.sign_deleteall:
			handler.deleteAll();
			cursor = handler.select();
			adapter.changeCursor(cursor);
			Toast.makeText(getActivity(), "리스트를 모두 비웠습니다", Toast.LENGTH_SHORT)
					.show();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		final int location = position;
		AlertDialog.Builder alertDig = new AlertDialog.Builder(view.getContext());
		alertDig.setTitle("알림창");
		
		alertDig.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Cursor tmp_cursor = handler.select();
				tmp_cursor.moveToPosition(location);
				String model = tmp_cursor.getString(tmp_cursor.getColumnIndex("model"));
				String linkurl = tmp_cursor.getString(tmp_cursor.getColumnIndex("linkurl"));
				String productname = tmp_cursor.getString(tmp_cursor.getColumnIndex("productname"));
				handler.delete(model,linkurl,productname);
				Toast.makeText(getActivity(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
				tmp_cursor = handler.select();
				adapter.changeCursor(tmp_cursor);
				dialog.dismiss();
			}
		});
		
		alertDig.setNegativeButton("취소", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		alertDig.setMessage("정말로 삭제하시겠습니까?");
		alertDig.show();
		
		return true;
	}
}
