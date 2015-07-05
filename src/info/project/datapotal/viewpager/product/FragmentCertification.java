package info.project.datapotal.viewpager.product;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.product.adapter.DB_Grid_Nation_Adapter;
import info.project.datapotal.viewpager.product.adapter.DB_Grid_Products_Adapter;
import info.project.datapotal.viewpager.product.adapter.DB_List_Search_Main_Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class FragmentCertification extends Fragment {

	String CWhite = "#FFFFFF";
	String CGray = "#CCCCCC";
	String CBlack = "#000000";
	Intent intent = null;
	boolean check = false;
	static AutoCompleteTextView textView;
	String url;

	static String[] COUNTRY = new String[] { "유럽연합", "알바니아", "아르메니아", "오스트리아",
			"벨기에", "불가리아", "체코", "크로아티아", "키프로스", "덴마크", "에스토니아", "프랑스", "독일",
			"그리스", "헝가리", "아일랜드", "이탈리아", "라트비아", "리투아니아", "룩셈부르크", "말타",
			"네델란드", "노르웨이", "포르투갈", "루마니아", "러시아", "스웨덴", "세르비아", "슬로베니아",
			"스위스", "우크라이나", "영국", "폴란드", "몰다비아", "방글라데시", "캄보디아", "중국", "홍콩",
			"인도", "인도네시아", "이스라엘", "일본", "요르단", "한국", "라오스", "마카오", "말레이시아",
			"미얀마", "네팔", "북한", "파키스탄", "필리핀", "스리랑카", "대만", "투르크메니스탄", "베트남",
			"아르헨티나", "볼리비아", "브라질", "캐나다", "칠레", "콜롬비아", "과테말라", "온두라스", "멕시코",
			"파라과이", "페루", "미국", "이집트", "케냐", "레소토", "마다가스카르", "모리셔스", "모로코",
			"남아프리카", "튀니지", "호주", "나우루", "뉴질랜드" };

	@Override
	public void onResume() {
		super.onResume();
		   url = "";
		   check = false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		RelativeLayout layout = (RelativeLayout) inflater.inflate(
				R.layout.db_nation_main_activity, container, false);
		final Button db0btn = (Button) layout.findViewById(R.id.db0btn);
		final Button db1btn = (Button) layout.findViewById(R.id.db1btn);
		final Button db2btn = (Button) layout.findViewById(R.id.db2btn);
		final GridView gridview = (GridView) layout.findViewById(R.id.db_grid);
		final Button searchbtn = (Button) layout.findViewById(R.id.searchbtn);
		final LinearLayout searchlinear = (LinearLayout) layout
				.findViewById(R.id.searchlinear);
		final EditText numedit = (EditText) layout.findViewById(R.id.numedit);
		final EditText modeledit = (EditText) layout
				.findViewById(R.id.modeledit);
		final EditText companyedit = (EditText) layout
				.findViewById(R.id.companyedit);
		final EditText countryedit = (EditText) layout
				.findViewById(R.id.countryedit);
		final EditText dateedit = (EditText) layout.findViewById(R.id.dateedit);
		final EditText monthedit = (EditText) layout
				.findViewById(R.id.monthedit);
		final EditText yearedit = (EditText) layout.findViewById(R.id.yearedit);

		db0btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db0btn.setTextColor(Color.parseColor(CWhite));
				db0btn.setBackgroundColor(Color.parseColor(CGray));
				db1btn.setTextColor(Color.parseColor(CBlack));
				db1btn.setBackgroundColor(Color.parseColor(CWhite));
				db2btn.setTextColor(Color.parseColor(CBlack));
				db2btn.setBackgroundColor(Color.parseColor(CWhite));
				gridview.setVisibility(View.GONE);
				searchlinear.setVisibility(View.VISIBLE);
			}

		});
		db1btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db1btn.setTextColor(Color.parseColor(CWhite));
				db1btn.setBackgroundColor(Color.parseColor(CGray));
				db0btn.setTextColor(Color.parseColor(CBlack));
				db0btn.setBackgroundColor(Color.parseColor(CWhite));
				db2btn.setTextColor(Color.parseColor(CBlack));
				db2btn.setBackgroundColor(Color.parseColor(CWhite));
				gridview.setAdapter(new DB_Grid_Nation_Adapter(getActivity(),
						R.layout.db_nation_grid));
				gridview.setVisibility(View.VISIBLE);
				searchlinear.setVisibility(View.GONE);
			}
		});
		db2btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db2btn.setTextColor(Color.parseColor(CWhite));
				db2btn.setBackgroundColor(Color.parseColor(CGray));
				db1btn.setTextColor(Color.parseColor(CBlack));
				db1btn.setBackgroundColor(Color.parseColor(CWhite));
				db0btn.setTextColor(Color.parseColor(CBlack));
				db0btn.setBackgroundColor(Color.parseColor(CWhite));
				gridview.setAdapter(new DB_Grid_Products_Adapter(getActivity(),
						R.layout.db_nation_grid));
				gridview.setVisibility(View.VISIBLE);
				searchlinear.setVisibility(View.GONE);
			}

		});

		searchbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (numedit.getText().toString().trim().equals("") == false) {
					ForSearch("sc_confirmNum", numedit.getText().toString());
				}
				if (modeledit.getText().toString().trim().equals("") == false) {
					ForSearch("sp_model_str", modeledit.getText().toString());
				}
				if (companyedit.getText().toString().trim().equals("") == false) {
					ForSearch("sb_makingCompany", companyedit.getText()
							.toString());
				}
				if (textView.getText().toString().trim().equals("") == false) {
					ForSearch("sb_makingCountry", countryedit.getText()
							.toString());
				}
				if (dateedit.getText().toString().trim().equals("") == false
						&& monthedit.getText().toString().trim().equals("") == false
						&& yearedit.getText().toString().trim().equals("") == false) {
					ForSearch("sc_confirmDay", yearedit.getText().toString(),
							monthedit.getText().toString(), dateedit.getText()
									.toString());
				}
				if (check == true) {
					DB_List_Search_Main_Activity.url = url;
					SearchStart();
				} else {
					Toast.makeText(getActivity(), "아무것도 입력하지 않으셨습니다.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		db1btn.setTextColor(Color.parseColor("#FFFFFF"));
		gridview.setAdapter(new DB_Grid_Nation_Adapter(getActivity(),
				R.layout.db_nation_grid));

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_dropdown_item_1line, COUNTRY);
		textView = (AutoCompleteTextView) layout.findViewById(R.id.countryedit);
		textView.setAdapter(adapter);

		return layout;
	}// end onCreateView

	public void ForSearch(String field, String value) {
		if (check == true) {
			url += ",";
			url += "%7B'" + field + "':%7B'$regex':'" + value + "'%7D%7D";
		} else {
			url = "%7B'" + field + "':%7B'$regex':'" + value + "'%7D%7D";
		}
		check = true;
	}

	public void ForSearch(String field, String year, String month, String date) {
		if (check == true) {
			url += ",";
			url += "%7B'" + field + "':%7B'$regex':'" + year + "-" + month
					+ "-" + date + "'%7D%7D";
		} else {
			url = "%7B'" + field + "':%7B'$regex':'" + year + "-" + month + "-"
					+ date + "'%7D%7D";
		}
		check = true;
	}

	public void SearchStart() {
		Intent intent = new Intent(getActivity(),
				DB_List_Search_Main_Activity.class);
		getActivity().startActivity(intent);
	}

}
/*
 * public class FragmentCertification extends Fragment{
 * 
 * String CRed = "#FF033E"; String CBlack = "#A1A194"; Intent intent = null; int
 * checkcount = 0;
 * 
 * @Override public void onCreate(Bundle savedInstanceState) { // TODO
 * Auto-generated method stub super.onCreate(savedInstanceState); }
 * 
 * @Override public View onCreateView(LayoutInflater inflater,
 * 
 * @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // TODO
 * Auto-generated method stub RelativeLayout layout = (RelativeLayout)
 * inflater.inflate(R.layout.db_nation_main_activity, container, false); final
 * Button db1btn = (Button) layout.findViewById(R.id.db1btn); final Button
 * db2btn = (Button) layout.findViewById(R.id.db2btn); final Button db0btn =
 * (Button) layout.findViewById(R.id.db0btn); final GridView gridview =
 * (GridView) layout.findViewById(R.id.db_grid); final Button searchbtn =
 * (Button) layout.findViewById(R.id.searchbtn); final LinearLayout searchlinear
 * = (LinearLayout) layout.findViewById(R.id.searchlinear); final EditText
 * numedit = (EditText) layout.findViewById(R.id.numedit); final EditText
 * modeledit = (EditText) layout.findViewById(R.id.modeledit); final EditText
 * companyedit = (EditText) layout.findViewById(R.id.companyedit); final
 * EditText countryedit = (EditText) layout.findViewById(R.id.countryedit);
 * final EditText dateedit = (EditText) layout.findViewById(R.id.dateedit);
 * 
 * searchbtn.setOnClickListener(new OnClickListener() {
 * 
 * @Override public void onClick(View v) { // TODO Auto-generated method stub
 * 
 * } });
 * 
 * db0btn.setOnClickListener(new OnClickListener() {
 * 
 * @Override public void onClick(View v) { // TODO Auto-generated method stub
 * db0btn.setTextColor(Color.parseColor(CRed));
 * db1btn.setTextColor(Color.parseColor(CBlack));
 * db2btn.setTextColor(Color.parseColor(CBlack));
 * gridview.setVisibility(View.GONE); searchlinear.setVisibility(View.VISIBLE);
 * }
 * 
 * }); db1btn.setOnClickListener(new OnClickListener() {
 * 
 * @Override public void onClick(View v) { // TODO Auto-generated method stub
 * db1btn.setTextColor(Color.parseColor(CRed));
 * db0btn.setTextColor(Color.parseColor(CBlack));
 * db2btn.setTextColor(Color.parseColor(CBlack)); gridview.setAdapter(new
 * DB_Grid_Nation_Adapter(getActivity(),R.layout.db_nation_grid));
 * gridview.setVisibility(View.VISIBLE); searchlinear.setVisibility(View.GONE);
 * } }); db2btn.setOnClickListener(new OnClickListener() {
 * 
 * @Override public void onClick(View v) { // TODO Auto-generated method stub
 * db2btn.setTextColor(Color.parseColor(CRed));
 * db1btn.setTextColor(Color.parseColor(CBlack));
 * db0btn.setTextColor(Color.parseColor(CBlack)); gridview.setAdapter(new
 * DB_Grid_Products_Adapter(getActivity(),R.layout.db_nation_grid));
 * gridview.setVisibility(View.VISIBLE); searchlinear.setVisibility(View.GONE);
 * }
 * 
 * }); db1btn.setTextColor(Color.parseColor("#FF033E"));
 * 
 * return layout; } }
 */

