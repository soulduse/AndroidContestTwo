package info.project.datapotal.viewpager.product;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.product.adapter.Category_Adapter;
import info.project.datapotal.viewpager.product.adapter.Parsed_ListAdapter;
import info.project.datapotal.viewpager.product.adapter.RecallAdapter;
import info.project.datapotal.viewpager.product.vo.NationsManager;
import info.project.datapotal.viewpager.product.vo.ProductManager;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class FragmentRecall extends Fragment
implements OnItemClickListener, OnClickListener, OnItemSelectedListener {

	Button mCountry, mFind, mProduct;
	RecallAdapter recallAdapter;
	GridView gridView1;
	boolean mLockListView=true;
	int currentBtn;
	Category_Adapter category_adapter;
	ScrollView scroll;

	/* Colors  */
	String colorRed = "#FF0000";
	String colorBlack = "#000000";
	
	/* MODEL_QUERY 작성을 위한 변수 */
	static int pageNumber;
	static String urls;
	
	/* 검색관련 변수 */
	Spinner spinner_nations, spinner_recallNationType, spinner_recallType;
	ArrayAdapter adapter_nations, adapter_Nationtypes, adapter_recallTypes;
	ArrayAdapter<String> strAdapter_model, strAdapter_product;
	AutoCompleteTextView  editModel, editProduct;
	int positionOfNations, positionOfRecallTypes, positionTypes;
	WordsForCompletion wordsBox;
	Button btnStart_Date,btnCancel,btnFind;
	static String startSignDate;
	static TextView txtSignDate;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		pageNumber=0;
		urls="";
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 변수 초기화 
		currentBtn=0;
		
		RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.activity_recall2, container, false);
		
		mCountry = (Button) layout.findViewById(R.id.countryBtn);
		mFind = (Button) layout.findViewById(R.id.findBtn);
		mProduct = (Button) layout.findViewById(R.id.productBtn);
		
		// 뷰 및 어댑터 설정
		gridView1 = (GridView) layout.findViewById(R.id.gridView1);
		category_adapter = new Category_Adapter(getActivity(),R.layout.category_adapter,currentBtn);
		gridView1.setAdapter(category_adapter);
		gridView1.setOnItemClickListener(this);
		mCountry.setTextColor(Color.parseColor("#FFFFFF"));
		mCountry.setBackgroundColor(Color.parseColor("#CCCCCC"));
		
		// 검색에 필요한 변수 초기화
		wordsBox = new WordsForCompletion();
		
		scroll = (ScrollView) layout.findViewById(R.id.scrollView1);
		scroll.setVisibility(View.INVISIBLE);
		positionOfNations = 0;
		positionOfRecallTypes = 0;
		positionTypes = 0;
		startSignDate = "";

		spinner_nations = (Spinner) layout.findViewById(R.id.spinner_nation);
		adapter_nations = ArrayAdapter.createFromResource(getActivity(),R.array.nation,android.R.layout.simple_spinner_item);
		adapter_nations.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_nations.setAdapter(adapter_nations);
		
		spinner_recallNationType = (Spinner) layout.findViewById(R.id.spinner_recallNationType);
		adapter_Nationtypes = ArrayAdapter.createFromResource(getActivity(),R.array.recall_nation_type,android.R.layout.simple_spinner_item);
		adapter_Nationtypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_recallNationType.setAdapter(adapter_Nationtypes);
		
		spinner_recallType = (Spinner) layout.findViewById(R.id.spinner_recallType);
		adapter_recallTypes = ArrayAdapter.createFromResource(getActivity(),R.array.recall_type,android.R.layout.simple_spinner_item);
		adapter_recallTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_recallType.setAdapter(adapter_recallTypes);
		
		editModel = (AutoCompleteTextView) layout.findViewById(R.id.editModel);
		strAdapter_model
		= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,wordsBox.model_keywords);
		editModel.setAdapter(strAdapter_model);
		
		editProduct = (AutoCompleteTextView) layout.findViewById(R.id.editProduct);
		strAdapter_product
		= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,wordsBox.product_keywords);
		editProduct.setAdapter(strAdapter_product);
		
		btnStart_Date = (Button) layout.findViewById(R.id.btnStart_Date);
		txtSignDate = (TextView) layout.findViewById(R.id.txtSignDate);
		btnCancel = (Button) layout.findViewById(R.id.btnCancel);
		
		btnFind = (Button) layout.findViewById(R.id.btnFind);
		
		// 이벤트 리스너 설정
		mCountry.setOnClickListener(this);
		mFind.setOnClickListener(this);
		mProduct.setOnClickListener(this);
		spinner_nations.setOnItemSelectedListener(this);
		spinner_recallNationType.setOnItemSelectedListener(this);
		spinner_recallType.setOnItemSelectedListener(this);
		btnStart_Date.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		btnFind.setOnClickListener(this);
		
		return layout;
	}
	
	/***********************************
	 * 버튼 및 아이템 클릭 이벤트 
	 ***********************************/
	@Override
	public void onClick(View v) {
		
		pageNumber=0;
		switch(v.getId()){
		case R.id.countryBtn:
			mCountry.setTextColor(Color.parseColor("#FFFFFF"));
			mCountry.setBackgroundColor(Color.parseColor("#CCCCCC"));
			mFind.setTextColor(Color.parseColor("#000000"));
			mFind.setBackgroundColor(Color.parseColor("#FFFFFF"));
			mProduct.setTextColor(Color.parseColor("#000000"));
			mProduct.setBackgroundColor(Color.parseColor("#FFFFFF"));
			
			currentBtn=0;
			category_adapter = new Category_Adapter(getActivity(),R.layout.category_adapter,currentBtn);
			gridView1.setAdapter(category_adapter);
			gridView1.setVisibility(View.VISIBLE);
			scroll.setVisibility(View.INVISIBLE);
			break;
		case R.id.productBtn:
			mCountry.setTextColor(Color.parseColor("#000000"));
			mCountry.setBackgroundColor(Color.parseColor("#FFFFFF"));
			mFind.setTextColor(Color.parseColor("#000000"));
			mFind.setBackgroundColor(Color.parseColor("#FFFFFF"));
			mProduct.setTextColor(Color.parseColor("#FFFFFF"));
			mProduct.setBackgroundColor(Color.parseColor("#CCCCCC"));
			
			currentBtn=1;
			category_adapter = new Category_Adapter(getActivity(),R.layout.category_adapter,currentBtn);
			gridView1.setAdapter(category_adapter);
			gridView1.setVisibility(View.VISIBLE);
			scroll.setVisibility(View.INVISIBLE);
			break;
		case R.id.findBtn:
			mCountry.setTextColor(Color.parseColor("#000000"));
			mCountry.setBackgroundColor(Color.parseColor("#FFFFFF"));
			mFind.setTextColor(Color.parseColor("#FFFFFF"));
			mFind.setBackgroundColor(Color.parseColor("#CCCCCC"));
			mProduct.setTextColor(Color.parseColor("#000000"));
			mProduct.setBackgroundColor(Color.parseColor("#FFFFFF"));
			
			cleanTheScreen();
			currentBtn=2;
			gridView1.setVisibility(View.INVISIBLE);
			scroll.setVisibility(View.VISIBLE);
			break;
		case R.id.btnStart_Date:
			DialogFragment newFragment_start = new DatePickerFragment_Start();
			newFragment_start.show(getActivity().getSupportFragmentManager(), "MyTag");
			startSignDate="";
			break;
		case R.id.btnCancel:
			txtSignDate.setText("Start Date");
			startSignDate="";
			break;
		case R.id.btnFind:
			urls = search();
			show_nextPage(0);
			break;
		}
	}

	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		switch(currentBtn){
		case 0:
			String model_URL = new NationsManager().getURL(position);
			urls = assemble_URL(model_URL);
			break;
		case 1:
			String product_URL[] = new ProductManager().getURL(position);
			urls = assemble_URL(product_URL);
			break;
		}
		if(currentBtn!=2){
			show_nextPage(currentBtn);
		}
	}
	
	// 발행일 시작날짜 DatePicker DialogFragment
	public static class DatePickerFragment_Start extends DialogFragment 
	implements DatePickerDialog.OnDateSetListener{

		boolean fired = false;
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar c = Calendar.getInstance();
	        int year = c.get(Calendar.YEAR);
	        int month = c.get(Calendar.MONTH);
	        int day = c.get(Calendar.DAY_OF_MONTH);
	        
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			if(fired==true) return;
			else{
				fired=true;
				startSignDate += year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
				txtSignDate.setText(startSignDate);
			}
		}
		
	}
	
	// 새로운 화면으로 넘어가 data를 listView에 보여주는 메소드
	public void show_nextPage(int btnNo){
		Intent intent = new Intent(getActivity(),Parsed_ListAdapter.class);
		Bundle bundle = new Bundle();
		bundle.putInt("currentBtn", btnNo);
		bundle.putString("urls", urls);
		intent.putExtras(bundle);
		startActivity(intent); 
		getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
	}
	
	// 검색화면으로 넘어올 때 변수들을 초기화 해주는 메소드
	public void cleanTheScreen(){
		positionOfNations = 0;
		positionOfRecallTypes = 0;
		positionTypes = 0;
		editModel.setText("");
		editProduct.setText("");
	}
	
	/**************************************************
	 * 요청 쿼리가 담겨있는 URL[]을 넘겨받아 최종 쿼리를 작성해주는 메소드
	 * @param params
	 * @return str
	 **************************************************/
	// 제품별
	public String assemble_URL(String[] params){
		String[] URL = params;
		
		String str = new ProductManager().getModel_query_head();
		String parameter = "";
		for(int i=0; i<URL.length; i++){
			if(i>0)
				parameter += ",";
			parameter = new ProductManager().getQuery_One_Section("productContents", URL[i]);
		}
		str += parameter;
		str += new ProductManager().getModel_query_tail();
		
		return str;
	}
	
	// 나라별
	public String assemble_URL(String param){
		String URL = param;
		
		String str = new NationsManager().getModel_query_head();
		String parameter = "";
		
		parameter = new NationsManager().getQuery_One_Section("makingNation", URL);
		
		str += parameter;
		str += new NationsManager().getModel_query_tail();
		
		return str;
	}
	
	//검색조건에 따른 URL 생성 메소드
	public String assemble_URL(String field, String contents){
		
		String parameter = "";
		if(field.equals("signDate")){
			parameter = "%7B%22signDate%22:%7B%22$gte%22:%22" + contents + "%22%7D%7D";
			return parameter;
		}
		parameter = new NationsManager().getQuery_One_Section(field, contents);
		return parameter;
	}

	// 스피너 클릭 이벤트 메소드
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		switch(parent.getId()){
		case R.id.spinner_nation:
			positionOfNations = position;
			Log.i("MyTag","positionOfNations : " + positionOfNations);
			break;
		case R.id.spinner_recallNationType:
			positionOfRecallTypes = position;
			Log.i("MyTag","positionOfRecallTypes : " + positionOfRecallTypes);
			break;
		case R.id.spinner_recallType:
			positionTypes = position;
			Log.i("MyTag","positionOfTypes : " + positionTypes);
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}
	
	/**
	 *  검색조건에 따른 검색 메소드
	 */
	public String search(){
		boolean isFirstPoint=true;
		String URL = "";
		NationsManager nManager = new NationsManager();
		
		URL += nManager.getModel_query_head_and();
		// 국가명
		if(positionOfNations!=0){
			String nation =  nManager.getProduct_Category(positionOfNations-1);
			URL += assemble_URL("makingNation", nation);
			isFirstPoint = false;
		}
		// 리콜형태
		if(positionOfRecallTypes!=0){
			/**
			 *  Type 1 : 국내, 2 : 국외
			 */
			if(isFirstPoint==false){
				URL += ",";
			}else{
				isFirstPoint = false;
			}
			String type = "" + positionOfRecallTypes;
			URL += assemble_URL("recallNationType", type);
		}
		// 리콜종류
		if(positionTypes!=0){
			if(isFirstPoint==false){
				URL += ",";
			}else{
				isFirstPoint = false;
			}
			String type="";
			
			if(positionTypes==1){
				type = "권고에";
			}else if(positionTypes==2){
				type = "명령에";
			}else if(positionTypes==3){
				type = "자발적";
			}
			URL += assemble_URL("recallType", type);
		}
		// 모델명
		String model = editModel.getText().toString();
		if(model.equals("")==false){
			if(isFirstPoint==false){
				URL += ",";
			}else{
				isFirstPoint = false;
			}
			model = model.replaceAll("\\p{Space}", "%20")
					.replaceAll("&", "%26")
					.replaceAll("-", "%2D")
					.replaceAll("<", "%3C")
					.replaceAll(">", "%3E")
					.replaceAll("[?]", "")
					.replaceAll("#", "%23");
			
			URL += assemble_URL("model",model);
		}
		// 제품명
		String product = editProduct.getText().toString();
		if(product.equals("")==false){
			if(isFirstPoint==false){
				URL += ",";
			}else{
				isFirstPoint = false;
			}
			product = product.replaceAll("\\p{Space}", "%20")
					.replaceAll("&", "%26")
					.replaceAll("-", "%2D")
					.replaceAll("<", "%3C")
					.replaceAll(">", "%3E")
					.replaceAll("[?]", "")
					.replaceAll("#", "%23");
			
			URL += assemble_URL("productName",product);
		}
		// 발행일자
		if(startSignDate.equals("")==false){
			if(isFirstPoint==false){
				URL += ",";
			}else{
				isFirstPoint = false;
			}
			URL += assemble_URL("signDate",startSignDate);
		}
		
		URL += nManager.getModel_query_tail();
		return URL;
	}//end search
	
}
