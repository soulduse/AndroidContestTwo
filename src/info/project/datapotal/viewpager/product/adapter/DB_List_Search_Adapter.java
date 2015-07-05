package info.project.datapotal.viewpager.product.adapter;

import info.project.datapotal.R;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class DB_List_Search_Adapter extends BaseAdapter {
	
	Context mContext;
	LayoutInflater inflater;
	ArrayList<HashMap<String,String>> data;
	HashMap<String, String> hresult = new HashMap<String,String>();
	
	public DB_List_Search_Adapter(Context c, ArrayList<HashMap<String, String>> arraylist) {
		mContext = c;
		data = arraylist;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView sb_makingCountry;
		TextView sb_makingCompany;
		TextView sp_model_str;
		TextView sc_confirmNum;
		TextView sc_confirmDay;
		
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
		View itemView = inflater.inflate(R.layout.db_all_list_item, parent, false);
		hresult = data.get(position);
		
		sb_makingCountry = (TextView) itemView.findViewById(R.id.sb_makingCountry);
		sb_makingCompany = (TextView) itemView.findViewById(R.id.sb_makingCompany);
		sp_model_str = (TextView) itemView.findViewById(R.id.sp_model_str);
		sc_confirmNum = (TextView) itemView.findViewById(R.id.sc_confirmNum);
		sc_confirmDay = (TextView) itemView.findViewById(R.id.sc_confirmDay);
		
		sb_makingCountry.setText(hresult.get(DB_List_Search_Main_Activity.COUNTRY));
		sb_makingCompany.setText(hresult.get(DB_List_Search_Main_Activity.COMPANY));
		sp_model_str.setText(hresult.get(DB_List_Search_Main_Activity.MODEL));
		sc_confirmNum.setText(hresult.get(DB_List_Search_Main_Activity.CONFIRMNUM));
		sc_confirmDay.setText(hresult.get(DB_List_Search_Main_Activity.CONFIRMDAY));

		itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hresult = data.get(position);
			
				Intent intent = new Intent(mContext, DB_List_Search_Final_Activity.class);
				intent.putExtra("sb_makingCompany", hresult.get(DB_List_Search_Main_Activity.COMPANY));
				intent.putExtra("sb_makingCountry", hresult.get(DB_List_Search_Main_Activity.COUNTRY));
				intent.putExtra("sp_goods", hresult.get(DB_List_Search_Main_Activity.GOODS));
				intent.putExtra("sp_model_str", hresult.get(DB_List_Search_Main_Activity.MODEL));
				intent.putExtra("totalcodeName", hresult.get(DB_List_Search_Main_Activity.CODENAME));
				intent.putExtra("sc_confirmNum", hresult.get(DB_List_Search_Main_Activity.CONFIRMNUM));
				intent.putExtra("sc_confirmDay", hresult.get(DB_List_Search_Main_Activity.CONFIRMDAY));
				intent.putExtra("sc_division", hresult.get(DB_List_Search_Main_Activity.DIVISION));
				intent.putExtra("sp_brand", hresult.get(DB_List_Search_Main_Activity.BRAND));
				
			    mContext.startActivity(intent);
			    ((Activity) mContext).overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
			    
			}
		});
		return itemView;
	}
	
}