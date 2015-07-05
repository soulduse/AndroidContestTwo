package info.project.datapotal.viewpager.bookmark;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.ServiceHandler;
import info.project.datapotal.viewpager.product.adapter.DB_List_Products_Final_Activity;
import info.project.datapotal.viewpager.product.adapter.DB_List_Search_Final_Activity;
import info.project.datapotal.viewpager.product.adapter.DB_List_Nation_Final_Activity;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import database.Safe_CursorAdapter;
import database.Safe_SQLiteHandler;

public class FragmentBookmarkTwo extends Fragment implements OnItemClickListener,OnItemLongClickListener {

	Safe_SQLiteHandler handler;
	boolean mLockListView;
	Cursor cursor;
	JSONArray contacts = null;
	HashMap<String, String> contact;
	Safe_CursorAdapter adapter;
	ListView listView;
	static int type;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 데이터베이스 open
		handler = Safe_SQLiteHandler.open(getActivity());
		cursor = handler.select();
		mLockListView = true;
		type=0;
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.bookmark_two, container,
				false);
		contact = new HashMap<String, String>();
		listView = (ListView) rootView.findViewById(R.id.bookmark_two_listview);

		// 데이터베이스 조회
		adapter = new Safe_CursorAdapter(getActivity(), cursor,
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
		
		String sc_confirmNum = tmp_cursor.getString(tmp_cursor
				.getColumnIndex("sc_confirmNum"));
		String sp_model_str = tmp_cursor.getString(tmp_cursor.getColumnIndex("sp_model_str"));
		String sb_makingCountry = tmp_cursor.getString(tmp_cursor
				.getColumnIndex("sb_makingCountry"));
		type = tmp_cursor.getInt(tmp_cursor.getColumnIndex("type"));
		
		String url = "";
		if (sc_confirmNum.equals("인증번호 없음")) {
			sc_confirmNum = "";
		}else{
			sc_confirmNum = sc_confirmNum.replaceAll("\\p{Space}", "%20")
					.replaceAll("&", "%26")
					.replaceAll("-", "%2D")
					.replaceAll("<", "%3C")
					.replaceAll(">", "%3E")
					.replaceAll("[?]", "")
					.replaceAll("#", "%23");
		}
		if (sp_model_str.equals("모델정보 없음")) {
			sp_model_str = "";
		}else{
			sp_model_str = sp_model_str.replaceAll("\\p{Space}", "%20")
					.replaceAll("&", "%26")
					.replaceAll("-", "%2D")
					.replaceAll("<", "%3C")
					.replaceAll(">", "%3E")
					.replaceAll("[?]", "")
					.replaceAll("#", "%23");
		}
		if (sb_makingCountry.equals("나라정보 없음")) {
			sb_makingCountry = "";
		}else{
			sb_makingCountry = sb_makingCountry.replaceAll("\\p{Space}", "%20")
					.replaceAll("&", "%26")
					.replaceAll("-", "%2D")
					.replaceAll("<", "%3C")
					.replaceAll(">", "%3E")
					.replaceAll("[?]", "")
					.replaceAll("#", "%23");
		}
		
		
		url += "http://www.ibtk.kr/api_confirm_detail/05dd06fd20f1737ef9a9b685802c6261?model_query=" +
				"%7B$and:%5B%7B%22sc_confirmNum%22:%7B%22$regex%22:%22" + sc_confirmNum + "%22%7D%7D,%7B%22" +
						"sp_model_str%22:%7B%22$regex%22:%22" + sp_model_str + "%22%7D%7D," +
				"%7B%22sb_makingCountry%22:%7B%22$regex%22:%22" + sb_makingCountry + "%22%7D%7D%5D%7D";
		new GetContacts().execute(url);
	}
	
	private class GetContacts extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mLockListView = true;
		}

		@Override
		protected Void doInBackground(String... urls) {
			String URL = null;
			URL = urls[0];
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(URL, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					contacts = jsonObj.getJSONArray("content");

					JSONObject c = contacts.getJSONObject(0);

					// tmp hashmap for single contact
					contact = new HashMap<String, String>();

					// adding each child node to HashMap key => value
					contact.put("sb_makingCompany", c.getString("sb_makingCompany"));
					contact.put("sb_makingCountry", c.getString("sb_makingCountry"));
					contact.put("sp_goods", c.getString("sp_goods"));
					contact.put("sp_model_str", c.getString("sp_model_str"));
					contact.put("totalcodeName", c.getString("totalcodeName"));
					contact.put("sc_confirmNum", c.getString("sc_confirmNum"));
					contact.put("sc_confirmDay", c.getString("sc_confirmDay"));
					contact.put("sc_division", c.getString("sc_division"));
					contact.put("code3Name", c.getString("code3Name"));
					contact.put("sp_brand", c.getString("sp_brand"));

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			mLockListView = false;
			Intent intent;
			
			if(type==1){
				//Nation_Final
				intent = new Intent(getActivity(),DB_List_Nation_Final_Activity.class);
			}else if(type==2){
				//Products_Final
				intent = new Intent(getActivity(),DB_List_Products_Final_Activity.class);
			}else{
				//type==3
				//Search_Final
				intent = new Intent(getActivity(),DB_List_Search_Final_Activity.class);
			}
			
			intent.putExtra("sb_makingCompany", contact.get("sb_makingCompany"));
			intent.putExtra("sb_makingCountry", contact.get("sb_makingCountry"));
			intent.putExtra("sp_goods", contact.get("sp_goods"));
			intent.putExtra("sp_model_str", contact.get("sp_model_str"));
			intent.putExtra("totalcodeName", contact.get("totalcodeName"));
			intent.putExtra("sc_confirmNum", contact.get("sc_confirmNum"));
			intent.putExtra("sc_confirmDay", contact.get("sc_confirmDay"));
			intent.putExtra("sc_division", contact.get("sc_division"));
			intent.putExtra("code3Name", contact.get("code3Name"));
			intent.putExtra("sp_brand", contact.get("sp_brand"));
			
			startActivity(intent);

			getActivity().overridePendingTransition(R.anim.pull_in_right,
					R.anim.push_out_left);

		}
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
				String sc_confirmNum = tmp_cursor.getString(tmp_cursor.getColumnIndex("sc_confirmNum"));
				String sp_model_str = tmp_cursor.getString(tmp_cursor.getColumnIndex("sp_model_str"));
				String sb_makingCountry = tmp_cursor.getString(tmp_cursor.getColumnIndex("sb_makingCountry"));
				
				handler.delete(sc_confirmNum, sp_model_str, sb_makingCountry);
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
	@Override
	public void onResume() {
		super.onResume();
		cursor = handler.select();
		adapter.changeCursor(cursor);
		adapter.notifyDataSetChanged();
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
			Toast.makeText(getActivity(), "리스트를 모두 비웠습니다", Toast.LENGTH_SHORT).show();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
}
