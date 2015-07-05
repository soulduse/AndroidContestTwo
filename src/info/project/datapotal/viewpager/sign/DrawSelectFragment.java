package info.project.datapotal.viewpager.sign;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.twotoasters.jazzylistview.JazzyGridView;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.ServiceHandler;
import info.project.datapotal.viewpager.sign.adapter.SignOneAdapter;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

public class DrawSelectFragment extends Fragment {
	
	 private JazzyGridView mGrid;
	    
		private ProgressDialog pDialog;

			// JSON Node names
			public static String TAG_CONTENT = "content";
			public static String TAG_PICSEQNO = "picseqno";
			public static String TAG_DESCRIPTION = "description";
			public static String TAG_APPLYFIELD = "applyField";
		    public static String TAG_IMG = "fileName";
		    public static String TAG_KORNAME = "korName";
		    public static String TAG_ENGNAME = "engName";
		    public static String TAG_RELKSNAME = "relKsName";
		    public static String TAG_CODE = "code";
		    
		    private SignOneAdapter mAdapter;
		    
			// contacts JSONArray
			JSONArray contacts = null;

			// Hashmap for ListView
			ArrayList<HashMap<String, String>> contactList;
			
			// 생성자로 부터 각각의 URL 데이터를 넘겨받고 저장
			private String url;
			public DrawSelectFragment(String url)
			{
				this.url = url;
			}
			
			@Override
			public View onCreateView(LayoutInflater inflater,
					 ViewGroup container,  Bundle savedInstanceState) {
				
				contactList = new ArrayList<HashMap<String, String>>();
				
				View rootView = inflater.inflate(R.layout.fragment_danger_viewpager, container, false);
				
				mAdapter = new SignOneAdapter(getActivity(), contactList);
				mGrid = (JazzyGridView) rootView.findViewById(R.id.viewpager_grid);

				mGrid.setAdapter(mAdapter);
				
				
				// Gridview on item click listener
		        mGrid.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// getting values from selected ListItem

						Intent in = new Intent(getActivity(), SignIntentActivity.class);
						in.putExtra(TAG_DESCRIPTION, contactList.get(position).get(TAG_DESCRIPTION));
						in.putExtra(TAG_PICSEQNO, contactList.get(position).get(TAG_PICSEQNO));
						in.putExtra(TAG_APPLYFIELD, contactList.get(position).get(TAG_APPLYFIELD));
						in.putExtra(TAG_IMG, contactList.get(position).get(TAG_IMG));
						in.putExtra(TAG_KORNAME, contactList.get(position).get(TAG_KORNAME));
						in.putExtra(TAG_ENGNAME, contactList.get(position).get(TAG_ENGNAME));
						in.putExtra(TAG_RELKSNAME, contactList.get(position).get(TAG_RELKSNAME));
						in.putExtra(TAG_CODE, contactList.get(position).get(TAG_CODE));
						startActivity(in);
						
						// Fragment에서 overridePendingTransition을 사용하려면 getActivity를 붙여야한다.
						getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
					}
				});
		        
		        //String url = "http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query=%7B%22code%22:%7B%22$regex%22:%272.100%27%7D%7D";
		        getContacts(url);
				return rootView;
			}
			
			
			public void getContacts(String url)
			{
				Log.v("Android Spinner JSON Data Activity", url);
				
				new GetContacts().execute(url);
			}
			
			/**
			 * Async task class to get json by making HTTP call
			 * */
			private class GetContacts extends AsyncTask<String, Void, Void> {
				
				@Override
				protected void onPreExecute() {
					super.onPreExecute();

					// Showing progress dialog
					pDialog = new ProgressDialog(getActivity());
					pDialog.setMessage("Please wait...");
					pDialog.setCancelable(false);
					pDialog.show();
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

							// looping through All Contacts
							for (int i = 0; i < contacts.length(); i++) {
								JSONObject c = contacts.getJSONObject(i);
								
								String picseqno = c.getString(TAG_PICSEQNO);
								String description = c.getString(TAG_DESCRIPTION);
								String apply = c.getString(TAG_APPLYFIELD);
								String img = c.getString(TAG_IMG);
								String korName = c.getString(TAG_KORNAME);
								String engName = c.getString(TAG_ENGNAME);
								String code = c.getString(TAG_CODE);
								String relKsName = c.getString(TAG_RELKSNAME);
								
								// tmp hashmap for single contact
								HashMap<String, String> contact = new HashMap<String, String>();

								// adding each child node to HashMap key => value
								contact.put(TAG_DESCRIPTION, description);
								contact.put(TAG_PICSEQNO, picseqno);
								contact.put(TAG_APPLYFIELD, apply);
								contact.put(TAG_IMG, img);
								contact.put(TAG_KORNAME, korName);
								contact.put(TAG_ENGNAME, engName);
								contact.put(TAG_CODE, code);
								contact.put(TAG_RELKSNAME, relKsName);
								// adding contact to contact list
								contactList.add(contact);
							}
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
					// Dismiss the progress dialog
					if (pDialog.isShowing())
						pDialog.dismiss();
					mGrid.setAdapter(mAdapter);
				}
			}
		}


			//http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query={$and:[{%22code%22:{%22$regex%22:%271.1%27}}]}

			/**
			 *  { : %7B , } : %7D
			 *  [ : %5B , ] : %5D
			 *  "a" -> \"a\"
			 *  // "&model_query_pageable={%27pageNumber%27:%27"+pageNumber+"%27}" 페이지 넘버 처리
			 */
			
			// 검정  - 0~21번 페이지 까지 있음.
			// http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query=%7B$or:%5B%7B%22code%22:%7B%22$regex%22:%271.1%27%7D%7D,%7B%22code%22:%7B%22$regex%22:%271.2%27%7D%7D,%7B%22code%22:%7B%22$regex%22:%271.3%27%7D%7D,%7B%22code%22:%7B%22$regex%22:%271.4%27%7D%7D,%7B%22code%22:%7B%22$regex%22:%271.5%27%7D%7D%5D%7D
			// http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query={$or:[{%22code%22:{%22$regex%22:%271.1%27}},{%22code%22:{%22$regex%22:%271.2%27}},{%22code%22:{%22$regex%22:%271.3%27}},{%22code%22:{%22$regex%22:%271.4%27}},{%22code%22:{%22$regex%22:%271.5%27}}]}
			// "&model_query_pageable={%27pageNumber%27:%27"+pageNumber+"%27}" 페이지 넘버 처리
			
			
			//녹색 - 0 페이지 끝
			// http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query=%7B%22code%22:%7B%22$regex%22:%272.10%27%7D%7D
			// http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query={%22code%22:{%22$regex%22:%272.10%27}}
			
			// 빨강 - 0~4 페이지
			// http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query=%7B$or:%5B%7B%22code%22:%7B%22$regex%22:%272.200%27%7D%7D,%7B%22code%22:%7B%22$regex%22:%272.3%27%7D%7D%5D%7D
			// http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query={$or:[{%22code%22:{%22$regex%22:%272.200%27}},{%22code%22:{%22$regex%22:%272.3%27}}]}
			
			// 노랑 - 0~2 페이지
			// http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query=%7B%22code%22:%7B%22$regex%22:%272.4%27%7D%7D
			// http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query={%22code%22:{%22$regex%22:%272.4%27}}
			
			// 파랑 0~1 페이지
			// http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query=%7B%22code%22:%7B%22$regex%22:%272.5%27%7D%7D
			// http://www.ibtk.kr/publicAssistanceFigurecover_api/8f297146209d85a9502285f56f33c3f9?model_query={%22code%22:{%22$regex%22:%272.5%27}}