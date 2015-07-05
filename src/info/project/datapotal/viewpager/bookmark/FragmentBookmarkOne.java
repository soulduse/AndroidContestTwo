package info.project.datapotal.viewpager.bookmark;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.ServiceHandler;
import info.project.datapotal.viewpager.sign.SignIntentActivity;

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
import database.Sign_CursorAdapter;
import database.Sign_SQLiteHandler;

public class FragmentBookmarkOne extends Fragment implements
		OnItemClickListener, OnItemLongClickListener {

	ListView listView;
	Sign_SQLiteHandler handler;
	Sign_CursorAdapter adapter;
	Cursor cursor;
	boolean mLockListView;
	JSONArray contacts = null;
	HashMap<String, String> contact;

	// JSON Node names
	public static String TAG_CONTENT = "content";
	public static String TAG_DESCRIPTION = "description";
	public static String TAG_APPLYFIELD = "applyField";
	public static String TAG_IMG = "fileName";
	public static String TAG_KORNAME = "korName";
	public static String TAG_ENGNAME = "engName";
	public static String TAG_RELKSNAME = "relKsName";
	public static String TAG_CODE = "code";
	public static String TAG_PICSEQNO = "picseqno";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 데이터베이스 open
		handler = Sign_SQLiteHandler.open(getActivity());
		cursor = handler.select();
		mLockListView = true;
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.bookmark_one, container,
				false);
		contact = new HashMap<String, String>();
		listView = (ListView) rootView.findViewById(R.id.bookmark_one_listview);

		// 데이터베이스 조회
		adapter = new Sign_CursorAdapter(getActivity(), cursor,
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);

		return rootView;
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

	@Override
	public void onResume() {
		super.onResume();
		cursor = handler.select();
		adapter.changeCursor(cursor);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Cursor tmp_cursor = handler.select();
		
		tmp_cursor.moveToPosition(position);
		String picseqno = tmp_cursor.getString(tmp_cursor
				.getColumnIndex("picseqno"));
		String url = "http://www.ibtk.kr/publicAssistanceFigurecover_api/6b9e0022e1da16cee844f14e9ce4210f"
				+ "?model_query=%7B%22picseqno%22:%22" + picseqno + "%22%7D";
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
					contacts = jsonObj.getJSONArray(TAG_CONTENT);

					JSONObject c = contacts.getJSONObject(0);

					String description = c.getString(TAG_DESCRIPTION);
					String apply = c.getString(TAG_APPLYFIELD);
					String img = c.getString(TAG_IMG);
					String korName = c.getString(TAG_KORNAME);
					String engName = c.getString(TAG_ENGNAME);
					String code = c.getString(TAG_CODE);
					String relKsName = c.getString(TAG_RELKSNAME);
					String picseqno = c.getString(TAG_PICSEQNO);
					Log.i("MyTag", "AsyncTask안에서의 picseqno : " + picseqno);

					// tmp hashmap for single contact
					contact = new HashMap<String, String>();

					// adding each child node to HashMap key => value
					contact.put(TAG_DESCRIPTION, description);
					contact.put(TAG_APPLYFIELD, apply);
					contact.put(TAG_IMG, img);
					contact.put(TAG_KORNAME, korName);
					contact.put(TAG_ENGNAME, engName);
					contact.put(TAG_CODE, code);
					contact.put(TAG_RELKSNAME, relKsName);
					contact.put(TAG_PICSEQNO, picseqno);
					Log.i("MyTag", "AsyncTask안에서의 contact.picseqno : "
							+ contact.get("picseqno"));

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
			Intent intent = new Intent(getActivity(), SignIntentActivity.class);
			intent.putExtra(TAG_DESCRIPTION, contact.get(TAG_DESCRIPTION));
			intent.putExtra(TAG_APPLYFIELD, contact.get(TAG_APPLYFIELD));
			intent.putExtra(TAG_IMG, contact.get(TAG_IMG));
			intent.putExtra(TAG_KORNAME, contact.get(TAG_KORNAME));
			intent.putExtra(TAG_ENGNAME, contact.get(TAG_ENGNAME));
			intent.putExtra(TAG_RELKSNAME, contact.get(TAG_RELKSNAME));
			intent.putExtra(TAG_CODE, contact.get(TAG_CODE));
			intent.putExtra(TAG_PICSEQNO, contact.get(TAG_PICSEQNO));
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
				String picseqno = tmp_cursor.getString(tmp_cursor.getColumnIndex("picseqno"));
				handler.delete(picseqno);
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
