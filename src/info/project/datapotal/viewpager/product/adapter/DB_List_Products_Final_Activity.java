package info.project.datapotal.viewpager.product.adapter;

import database.Safe_SQLiteHandler;
import info.project.datapotal.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class DB_List_Products_Final_Activity extends Activity {
	String sb_makingCompany;
	String sb_makingCountry;
	String sp_goods;
	String sp_model_str;
    String totalcodeName;
	String sc_confirmNum;
	String sc_confirmDay;
	String sc_division;
	String code3Name;
	String sp_brand;
	String sc_pharosUID;
	
	Cursor cursor;
	Safe_SQLiteHandler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_products_list_activity);
		
		if (handler == null)
			handler = Safe_SQLiteHandler.open(getApplicationContext());
		
		Intent intent = getIntent();
		sb_makingCompany = intent.getStringExtra("sb_makingCompany");
		sb_makingCountry = intent.getStringExtra("sb_makingCountry");
		sp_goods = intent.getStringExtra("sp_goods");
		sp_model_str = intent.getStringExtra("sp_model_str");
		totalcodeName = intent.getStringExtra("totalcodeName");
		sc_confirmNum = intent.getStringExtra("sc_confirmNum");
		sc_confirmDay = intent.getStringExtra("sc_confirmDay");
		sc_division = intent.getStringExtra("sc_division");
		code3Name = intent.getStringExtra("code3Name");
		sp_brand = intent.getStringExtra("sp_brand");
		
		String code3Name_Filter[] = code3Name.split(">");
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(code3Name_Filter[code3Name_Filter.length-1]);
		
		TextView tsb_makingCompany = (TextView) findViewById(R.id.sb_makingCompany);
		TextView tsb_makingCountry = (TextView) findViewById(R.id.sb_makingCountry);
		TextView tsp_goods = (TextView) findViewById(R.id.sp_goods);
		TextView tsp_model_str = (TextView) findViewById(R.id.sp_model_str);
		TextView ttotalcodeName = (TextView) findViewById(R.id.totalcodeName);
		TextView tsc_confirmNum = (TextView) findViewById(R.id.sc_confirmNum);
		TextView tsc_confirmDay = (TextView) findViewById(R.id.sc_confirmDay);
		TextView tsc_division = (TextView) findViewById(R.id.sc_division);
		TextView tcode3Name = (TextView) findViewById(R.id.code3Name);
		TextView tsp_brand = (TextView) findViewById(R.id.sp_brand);
		
		tsb_makingCompany.setText(sb_makingCompany);
		tsb_makingCountry.setText(sb_makingCountry);
		tsp_goods.setText(sp_goods);
		tsp_model_str.setText(sp_model_str);
		ttotalcodeName.setText(totalcodeName);
		tsc_confirmNum.setText(sc_confirmNum);
		tsc_confirmDay.setText(sc_confirmDay);
		tsc_division.setText(sc_division);		
		tcode3Name.setText(code3Name);
		tsp_brand.setText(sp_brand);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_into_bucket, menu);

		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		case R.id.addItem:
			boolean areThereExistSameValues = false;

			String tmp_sc_confirmNum;
			String tmp_sp_model_str;
			String tmp_sc_pharosUID;
			String tmp_sb_makingCountry;

			if(sc_confirmNum.trim().equals("")){
				tmp_sc_confirmNum = "인증번호 없음";
			}else{
				tmp_sc_confirmNum = sc_confirmNum;
			}
			
			if(sp_model_str.trim().equals("")){
				tmp_sp_model_str = "모델정보 없음";
			}else{
				tmp_sp_model_str = sp_model_str;
			}
			
			if(sb_makingCountry.trim().equals("")){
				tmp_sb_makingCountry = "나라정보 없음";
			}else{
				tmp_sb_makingCountry = sb_makingCountry;
			}

			cursor = handler.select();
			if (cursor.getCount() > 0) {
				while(cursor.moveToNext()){
					String compare_sc_confirmNum = cursor.getString(cursor.getColumnIndex("sc_confirmNum"));
					String compare_sp_model_str = cursor.getString(cursor.getColumnIndex("sp_model_str"));
					String compare_sb_makingCountry = cursor.getString(cursor.getColumnIndex("sb_makingCountry"));
					
					if(compare_sc_confirmNum.equals(tmp_sc_confirmNum) && compare_sp_model_str.equals(tmp_sp_model_str)
							&& compare_sb_makingCountry.equals(tmp_sb_makingCountry)){
						
						Toast.makeText(getApplicationContext(), "이미 등록된 항목입니다",
								Toast.LENGTH_SHORT).show();
						areThereExistSameValues = true;
						break;
					}
				}
			}else{
				
			}
			
			if (areThereExistSameValues == false) {
				handler.insert(tmp_sc_confirmNum, tmp_sp_model_str,tmp_sb_makingCountry,2);
				Toast.makeText(getApplicationContext(), "즐겨찾기에 추가되었습니다",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}
}