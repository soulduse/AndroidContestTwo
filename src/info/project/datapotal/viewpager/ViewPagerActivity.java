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

	// DrawMenu ��������� ViewPager�� ��ȣ Visibleó���� ���� Lock����
	static boolean visibleLock;
	static boolean actionBarLock;

	int idxNum;

	// Tab titles
	private String[] tabs1 = { "�˾Ƶα�", "����\n��ǰ", "����\n��ǰ" };
	private String[] tabs2 = { "�ϻ��Ȱ", "�������", "��������", "���ǰ���", "��Ȱ����" };
	private String[] tabs3 = { "�˾Ƶα�","�ȳ��׸�", "������ǰ", "������ǰ" };

	// DrawMenu items
	private int[] image = { R.drawable.tab_list, R.drawable.sisul_a,
			R.drawable.sisul_b, R.drawable.sisul_c, R.drawable.sisul_d,
			R.drawable.sisul_e, R.drawable.sisul_f, R.drawable.sisul_g,
			R.drawable.sisul_h, R.drawable.sisul_i, R.drawable.sisul_j };

	private String[] menu = { "�� ���� ����", "�����ü�", "����ü�", "����ü�", "��ȭ/����",
			"ü���ü�", "��������", "ȭ��/���", "����", "���/����", "����" };
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

	// URL ���� class ��ü �ҷ���
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
		setTitle("�����ȳ� �׸�ǥ��");
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
			setTitle("��ǰ����");

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
						 * �����̵� �޴����� Ư�� �������� Ŭ���Ǿ����� ���������� ������ �ش�Ǵ� �����͸�
						 * FrameLayout���� ������
						 */
						if (visibleLock == false) {
							viewPager.setVisibility(View.INVISIBLE);
							frame.setVisibility(View.VISIBLE);
							visibleLock = true;
						}
						/*
						 * Ÿ��Ʋ�� ������ ������ �ٲ��ְ�, �����ܵ� �����Ѵ�. �׸��� �����̵� �޴��� �ݴ´�.
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

		// ���ã�� ��
		if (intent.getExtras().getInt("tab3") == 3) {
			actionBarLock = false;
			viewPager.setOffscreenPageLimit(4);
			mDrawer.setTouchMode(MenuDrawer.GONE);
			btn1.setVisibility(View.INVISIBLE);
			setTitle("���ã��");

			// Adding Tabs
			setTabText(tabs3, 3);
		}

		actionBar.setDisplayHomeAsUpEnabled(true);
		// �׼ǹٿ��� �ڷΰ��� ��ư�� �����ϱ� ���� ����.

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			/**
			 * �������� �ٲ������(��ũ�ѷ� �Ѱ����� Ȥ�� ù Fragment�� ��������) �߻��ϴ� �޼ҵ��Դϴ�. Fragment��
			 * ���������� �������°��� FragmentPagerAdapter�� �ϰ�, ���⼭�� ��ũ�ѷ� Fragment�� �ѱ涧,
			 * ActionBar���� Ȱ��ȭǥ�� �κе� ���� �Ű� ������ �ϱ� ���ؼ� �����մϴ�.
			 * 
			 */
			@Override
			public void onPageSelected(int position) {
				// �巡�׷� �������� �ѱ�� �ش�Ǵ� �׼ǹ� ���� ���õǴ� ���
				actionBar.setSelectedNavigationItem(position);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// �������� ��ũ�� �Ǿ�������
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// ����� ������ ��ġ�� callback ����.
				// �̺κ��� �� ó���ϸ� ����ڰ� ���� ȭ�� ���� �ε��ϰ� ��.
			}
		});
	}

	// �׼ǹ� �޴� ���� �κ�
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (actionBarLock == true) {
			MenuInflater minflater = getMenuInflater();
			minflater.inflate(R.menu.activity_main_actions, menu);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				searchItem = menu.findItem(R.id.action_search);
				searchView = (SearchView) searchItem.getActionView();
				
				// �˻� Text �Ͼ������ �����ϱ����� ����
				int id = searchView
						.getContext()
						.getResources()
						.getIdentifier("android:id/search_src_text", null, null);
				EditText editText = (EditText) searchView.findViewById(id);
				editText.setTextColor(Color.WHITE);
				editText.setHintTextColor(Color.WHITE);
				
				// �׼ǹ� �˻� ��� ��ư X ������ֱ�
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
				searchView.setQueryHint("ǥ���Ǹ����� �˻�");
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
	 * �׼ǹ� �˻� �޼ҵ�
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
		
		if(newText.trim().equals("") || newText.trim().equals("��") ||
				newText.trim().equals("�ÿ�") || newText.trim().equals("����")){
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

	// TabMenu�� �ٲ��ֱ� ���� �޼ҵ�
	public void setTabText(String[] item, int index) {
		for (String tab_name : item) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
			mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), index);
			viewPager.setAdapter(mAdapter);
		}
	}

	/*
	 * Android Activity transition slide in/out animation back ��ư�������� ����Ʈ �ִϸ��̼�
	 * ȿ�� �ִ� �κ�
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
		 * �����̵� �޴��� ���������� �����ӷ��̾ƿ��� ������ ������ �������� ������ ������
		 */
		if (visibleLock == true) {
			viewPager.setVisibility(View.VISIBLE);
			frame.setVisibility(View.INVISIBLE);
			getActionBar().setIcon(R.drawable.logo_main);
			setTitle("�����ȳ� �׸�ǥ��");
			visibleLock = false;
		}
		// �׼ǹ� ���� �������� �ڵ����� ȭ���� ������� �ʴ´�.
		// ViewPager�� �� ���� �̺�Ʈ�� ���� �˰����� �ʱ� ������ �������� �Ǻ���
		// �����ʸ� ����ؼ� �並 �����ؾߵȴ�. �̶� ���
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
