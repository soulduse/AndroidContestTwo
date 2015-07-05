package info.project.datapotal.viewpager;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.sign.DrawSelectFragment;
import info.project.datapotal.viewpager.sign.UrlManager;
import info.project.datapotal.viewpager.sign.WordsCompletion;
import info.project.datapotal.viewpager.sign.adapter.DrawAdapter;
import info.project.datapotal.viewpager.sign.adapter.ExampleAdapter;
import info.project.datapotal.viewpager.sign.vo.DrawMenu;
import info.project.datapotal.viewpager.sign.vo.Words_SQLiteHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import org.json.JSONArray;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView.OnSuggestionListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ViewPagerActivity extends FragmentActivity implements
		ActionBar.TabListener, OnClickListener, OnQueryTextListener {

	
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	private DrawAdapter dAdapter;

	private MenuDrawer mDrawer;
	private FrameLayout frame;

	// DrawMenu 눌렀을경우 ViewPager와 상호 Visible처리를 위한 Lock변수
	static boolean visibleLock;
	static boolean actionBarLock;

	int idxNum;

	// Tab titles
	private String[] tabs1 = { "알아두기", "인증\n제품", "리콜\n제품" };
	private String[] tabs2 = { "일상생활", "생명관련", "금지관련", "주의관련", "생활안전" };
	private String[] tabs3 = { "알아두기","안내그림", "인증제품", "리콜제품" };

	// DrawMenu items
	private int[] image = { R.drawable.tab_list, R.drawable.sisul_a,
			R.drawable.sisul_b, R.drawable.sisul_c, R.drawable.sisul_d,
			R.drawable.sisul_e, R.drawable.sisul_f, R.drawable.sisul_g,
			R.drawable.sisul_h, R.drawable.sisul_i, R.drawable.sisul_j };

	private String[] menu = { "탭 별로 보기", "공공시설", "교통시설", "상업시설", "문화/관광",
			"체육시설", "안전유도", "화재/비상", "금지", "경고/주의", "지시" };
	ArrayList<DrawMenu> drawItem;
	SearchView searchView;
	MenuItem searchItem;
	LayoutInflater inflater;
	AutoCompleteTextView autoView1;
	ArrayAdapter<String> adapter;
	View v;
	
	SimpleCursorAdapter simpleAdapter;
	Words_SQLiteHandler handler;
	SearchManager manager;
	Cursor c;

	// URL 관리 class 객체 불러옴
	UrlManager url;
	JSONArray contacts = null;

	// JSON Node names
	public static String TAG_CONTENT = "content";
	public static String TAG_DESCRIPTION = "description";
	public static String TAG_APPLYFIELD = "applyField";
	public static String TAG_IMG = "fileName";
	public static String TAG_KORNAME = "korName";
	public static String TAG_ENGNAME = "engName";
	public static String TAG_RELKSNAME = "relKsName";
	public static String TAG_CODE = "code";
	ArrayList<HashMap<String, String>> contactList;
	ArrayAdapter<String> autoView_Adapter;
	WordsCompletion wordsCompletion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("공공안내 그림표지");
		contactList = new ArrayList<HashMap<String, String>>();
		wordsCompletion = new WordsCompletion();
		manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		
		if(handler==null){
			handler = Words_SQLiteHandler.open(getApplicationContext());
		}
		
		mDrawer = MenuDrawer.attach(this, Position.LEFT);
		mDrawer.setContentView(R.layout.activity_viewpager);
		mDrawer.setMenuView(R.layout.drawer_menu);

		ListView list = (ListView) mDrawer.findViewById(R.id.drawList);
		Button btn1 = (Button) findViewById(R.id.btn1);

		viewPager = (ViewPager) findViewById(R.id.pager);
		frame = (FrameLayout) findViewById(R.id.frame_container);

		actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		url = new UrlManager();

		Intent intent = getIntent();
		if (intent.getExtras().getInt("tab1") == 1) {
			actionBarLock = false;
			mDrawer.setTouchMode(MenuDrawer.GONE);
			viewPager.setOffscreenPageLimit(3);
			btn1.setVisibility(View.INVISIBLE);
			setTitle("제품안전");

			// Adding Tabs
			setTabText(tabs1, 1);
		}
		if (intent.getExtras().getInt("tab2") == 2) {
			actionBarLock = true;
			drawItem = new ArrayList<DrawMenu>();
			for (int i = 0; i < image.length; i++) {
				drawItem.add(new DrawMenu(image[i], menu[i]));
			}
			dAdapter = new DrawAdapter(getApplicationContext(), drawItem);
			list.setAdapter(dAdapter);

			viewPager.setOffscreenPageLimit(5);

			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Fragment fragment = null;
					switch (position) {
					case 0:
						actionBar
								.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
						break;
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
					case 7:
					case 8:
					case 9:
					case 10:
						fragment = new DrawSelectFragment(
								url.getURLList()[position - 1]);
						break;

					default:
						break;
					}

					if (fragment != null) {
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction()
								.replace(R.id.frame_container, fragment)
								.commit();

						/*
						 * 슬라이딩 메뉴에서 특정 아이템이 클릭되었을때 뷰페이저는 가리고 해당되는 데이터만
						 * FrameLayout에서 보여줌
						 */
						if (visibleLock == false) {
							viewPager.setVisibility(View.INVISIBLE);
							frame.setVisibility(View.VISIBLE);
							visibleLock = true;
						}
						/*
						 * 타이틀의 내용을 가져와 바꿔주고, 아이콘도 변경한다. 그리고 슬라이딩 메뉴를 닫는다.
						 */
						actionBar
								.setNavigationMode(ActionBar.DISPLAY_HOME_AS_UP);
						setTitle(menu[position]);
						getActionBar().setIcon(image[position]);
						mDrawer.closeMenu();
						/** Creating Android Tab */
					} else {
						mDrawer.closeMenu();
					}
				}
			});

			btn1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mDrawer.openMenu();
				}
			});

			// Adding Tabs
			setTabText(tabs2, 2);
		}

		// 즐겨찾기 탭
		if (intent.getExtras().getInt("tab3") == 3) {
			actionBarLock = false;
			viewPager.setOffscreenPageLimit(4);
			mDrawer.setTouchMode(MenuDrawer.GONE);
			btn1.setVisibility(View.INVISIBLE);
			setTitle("즐겨찾기");

			// Adding Tabs
			setTabText(tabs3, 3);
		}

		actionBar.setDisplayHomeAsUpEnabled(true);
		// 액션바에서 뒤로가기 버튼을 생성하기 위해 선언.

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			/**
			 * 페이지가 바뀌었을때(스크롤로 넘겼을때 혹은 첫 Fragment가 보여질때) 발생하는 메소드입니다. Fragment를
			 * 실질적으로 가져오는것은 FragmentPagerAdapter가 하고, 여기서는 스크롤로 Fragment를 넘길때,
			 * ActionBar탭의 활성화표시 부분도 같이 옮겨 가도록 하기 위해서 정의합니다.
			 * 
			 */
			@Override
			public void onPageSelected(int position) {
				// 드래그로 페이저를 넘기면 해당되는 액션바 탭이 선택되는 기능
				actionBar.setSelectedNavigationItem(position);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// 페이지가 스크롤 되어있을때
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// 변경된 페이지 위치를 callback 해줌.
				// 이부분을 잘 처리하면 사용자가 보는 화면 만을 로딩하게 됨.
			}
		});
	}

	// 액션바 메뉴 설정 부분
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (actionBarLock == true) {
			MenuInflater minflater = getMenuInflater();
			minflater.inflate(R.menu.activity_main_actions, menu);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				searchItem = menu.findItem(R.id.action_search);
				searchView = (SearchView) searchItem.getActionView();
				
				// 검색 Text 하얀색으로 설정하기위해 선언
				int id = searchView
						.getContext()
						.getResources()
						.getIdentifier("android:id/search_src_text", null, null);
				EditText editText = (EditText) searchView.findViewById(id);
				editText.setTextColor(Color.WHITE);
				editText.setHintTextColor(Color.WHITE);
				
				// 액션바 검색 취소 버튼 X 만들어주기
				Field searchField;
				try {
					searchField = SearchView.class.getDeclaredField("mCloseButton");
					searchField.setAccessible(true);
		            ImageView closeBtn = (ImageView) searchField.get(searchView);
		            closeBtn.setImageResource(R.drawable.ic_cancel);
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				searchView.setQueryHint("표지판명으로 검색");
				searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
				searchView.setOnQueryTextListener(this);
				searchView.setOnSuggestionListener(new OnSuggestionListener() {
					
					@Override
					public boolean onSuggestionSelect(int position) {
						// TODO Auto-generated method stub
						return false;
					}
					
					@Override
					public boolean onSuggestionClick(int position) {
						Cursor c = searchView.getSuggestionsAdapter().getCursor();
						c.moveToPosition(position);
						String target = c.getString(c.getColumnIndex("korname"));
						doSearch(target);
						return true;
					}
				});
				
			}
		}

		return super.onCreateOptionsMenu(menu);
	}

	/*******
	 * 액션바 검색 메소드
	 *******/
	@Override
	public boolean onQueryTextSubmit(String query) {
		searchItem.collapseActionView();
		Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT)
				.show();
		Fragment fragment = null;

		String target = query;
		doSearch(target);
		/*target = target.replaceAll("\\p{Space}", "%20").replaceAll("&", "%26")
				.replaceAll("-", "%2D").replaceAll("<", "%3C")
				.replaceAll(">", "%3E").replaceAll("[?]", "")
				.replaceAll("#", "%23");
		String msg = "http://www.ibtk.kr/publicAssistanceFigurecover_api/6b9e0022e1da16cee844f14e9ce4210f"
				+ "?model_query=%7B%22korName%22:%7B%22$regex%22:%22"
				+ target
				+ "%22%7D%7D";
		fragment = new DrawSelectFragment(msg);

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			if (visibleLock == false) {
				viewPager.setVisibility(View.INVISIBLE);
				frame.setVisibility(View.VISIBLE);
				visibleLock = true;
			}
			actionBar.setNavigationMode(ActionBar.DISPLAY_HOME_AS_UP);
			setTitle(target);
			getActionBar().setIcon(R.drawable.main_logo);
			mDrawer.closeMenu();
			//Creating Android Tab
		}*/
		return false;
	}
	
	public void doSearch(String target){
		searchItem.collapseActionView();
		Fragment fragment = null;
		target = target.replaceAll("\\p{Space}", "%20").replaceAll("&", "%26")
				.replaceAll("-", "%2D").replaceAll("<", "%3C")
				.replaceAll(">", "%3E").replaceAll("[?]", "")
				.replaceAll("#", "%23");
		String msg = "http://www.ibtk.kr/publicAssistanceFigurecover_api/6b9e0022e1da16cee844f14e9ce4210f"
				+ "?model_query=%7B%22korName%22:%7B%22$regex%22:%22"
				+ target
				+ "%22%7D%7D";
		fragment = new DrawSelectFragment(msg);

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			if (visibleLock == false) {
				viewPager.setVisibility(View.INVISIBLE);
				frame.setVisibility(View.VISIBLE);
				visibleLock = true;
			}
			actionBar.setNavigationMode(ActionBar.DISPLAY_HOME_AS_UP);
			setTitle(target);
			getActionBar().setIcon(R.drawable.main_logo);
			mDrawer.closeMenu();
			//Creating Android Tab
		}
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		
		if(newText.trim().equals("") || newText.trim().equals("싱") ||
				newText.trim().equals("시오") || newText.trim().equals("주의")){
			return false;
		}else{
			c = handler.select(newText);
		}
		if(c.getCount() != 0){
			Log.i("MyTag","c.getCount():"+c.getCount());
			if(c.getCount()<=7){
				ArrayList<String> list = new ArrayList<String>();
				while(c.moveToNext()){
					list.add(c.getString(c.getColumnIndex("korname")));
				}
				c.moveToPrevious();
				ExampleAdapter eAdapter = new ExampleAdapter(getApplicationContext(),c,list);
			
				searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
				searchView.setSuggestionsAdapter(eAdapter);
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}	

	// TabMenu를 바꿔주기 위한 메소드
	public void setTabText(String[] item, int index) {
		for (String tab_name : item) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
			mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), index);
			viewPager.setAdapter(mAdapter);
		}
	}

	/*
	 * Android Activity transition slide in/out animation back 버튼눌렀을시 인텐트 애니메이션
	 * 효과 주는 부분
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		case R.id.action_search:
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	final static String KEY_DISPLAY_OPT = "KEY_Display_Option";

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_DISPLAY_OPT, getActionBar().getDisplayOptions());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		int savedDisplayOpt = savedInstanceState.getInt(KEY_DISPLAY_OPT);
		if (savedDisplayOpt != 0) {
			getActionBar().setDisplayOptions(savedDisplayOpt);
		}
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());

		/*
		 * 슬라이딩 메뉴가 눌러졌을때 프레임레이아웃의 내용은 가리고 뷰페이져 내용을 보여줌
		 */
		if (visibleLock == true) {
			viewPager.setVisibility(View.VISIBLE);
			frame.setVisibility(View.INVISIBLE);
			getActionBar().setIcon(R.drawable.logo_main);
			setTitle("공공안내 그림표지");
			visibleLock = false;
		}
		// 액션바 탭을 눌렀을때 자동으로 화면이 변경되지 않는다.
		// ViewPager가 탭 변경 이벤트에 대해 알고있지 않기 때문에 수동으로 탭변경
		// 리스너를 사용해서 뷰를 변경해야된다. 이때 사용
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		}
	}

}
