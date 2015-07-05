package info.project.datapotal.viewpager.product.adapter;

import info.project.datapotal.R;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("Instantiatable") 
public class DB_List_Products_Adapter extends BaseAdapter {
	Context mContext;
	LayoutInflater inflater;
	ArrayList<HashMap<String,String>> p_data;
	HashMap<String, String> p_hash = new HashMap<String,String>();
	
	@SuppressLint("Instantiatable") 
	public DB_List_Products_Adapter(Context c, ArrayList<HashMap<String, String>> arraylist) {
		mContext = c;
		p_data = arraylist;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return p_data.size();
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
		TextView code3Name;
		TextView sb_makingCompany;
		TextView sp_model_str;
		TextView sc_confirmNum;
		
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View itemView = inflater.inflate(R.layout.db_products_list_item, parent, false);
		p_hash = p_data.get(position);
		
		code3Name = (TextView) itemView.findViewById(R.id.code3Name);
		sb_makingCompany = (TextView) itemView.findViewById(R.id.sb_makingCompany);
		sp_model_str = (TextView) itemView.findViewById(R.id.sp_model_str);
		sc_confirmNum = (TextView) itemView.findViewById(R.id.sc_confirmNum);
		
		code3Name.setText(p_hash.get(DB_List_Products_Main_Activity.CODE3NAME));
		sb_makingCompany.setText(p_hash.get(DB_List_Products_Main_Activity.COMPANY));
		sp_model_str.setText(p_hash.get(DB_List_Products_Main_Activity.MODEL));
		sc_confirmNum.setText(p_hash.get(DB_List_Products_Main_Activity.CONFIRMNUM));
		
		itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				p_hash = p_data.get(position);
				
				Intent intent = new Intent(mContext, DB_List_Products_Final_Activity.class);
				intent.putExtra("sb_makingCompany", p_hash.get(DB_List_Products_Main_Activity.COMPANY));
				intent.putExtra("sb_makingCountry", p_hash.get(DB_List_Products_Main_Activity.COUNTRY));
				intent.putExtra("sp_goods", p_hash.get(DB_List_Products_Main_Activity.GOODS));
				intent.putExtra("sp_model_str", p_hash.get(DB_List_Products_Main_Activity.MODEL));
				intent.putExtra("totalcodeName", p_hash.get(DB_List_Products_Main_Activity.CODENAME));
				intent.putExtra("sc_confirmNum", p_hash.get(DB_List_Products_Main_Activity.CONFIRMNUM));
				intent.putExtra("sc_confirmDay", p_hash.get(DB_List_Products_Main_Activity.CONFIRMDAY));
				intent.putExtra("sc_division", p_hash.get(DB_List_Products_Main_Activity.DIVISION));
				intent.putExtra("code3Name", p_hash.get(DB_List_Products_Main_Activity.CODE3NAME));
				intent.putExtra("sp_brand", p_hash.get(DB_List_Products_Main_Activity.BRAND));
				
			    mContext.startActivity(intent);
			    ((Activity) mContext).overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
			}
		});
		return itemView;
	}
	
}