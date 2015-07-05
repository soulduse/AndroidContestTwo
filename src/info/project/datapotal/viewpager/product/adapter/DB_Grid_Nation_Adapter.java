package info.project.datapotal.viewpager.product.adapter;

import info.project.datapotal.R;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class DB_Grid_Nation_Adapter extends BaseAdapter {

	Context mContext;
	LayoutInflater inflater;
	int layout;
	Intent intent;
	
	public DB_Grid_Nation_Adapter(Context c, int layout) {
		mContext = c;
		this.layout = layout;

		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return euImage.length;
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
		if(convertView == null) {
			convertView = inflater.inflate(layout, null);
		} 
		ImageView imageView = (ImageView) convertView.findViewById(R.id.gridimageview);
		TextView textView = (TextView) convertView.findViewById(R.id.gridtextview);
		
		imageView.setImageResource(euImage[position]);
		textView.setText(euText[position]);
		
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(position) {
				case 0:
					DB_List_Nation_Main_Activity.nation = "몽골";
					DB_List_Nation_Main_Activity.size = 20;break;
				case 1:
					DB_List_Nation_Main_Activity.nation = "알바니아";
					DB_List_Nation_Main_Activity.size = 20;break;
				case 2:
					DB_List_Nation_Main_Activity.nation = "아르메니아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 3:
					DB_List_Nation_Main_Activity.nation = "오스트리아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 4:
					DB_List_Nation_Main_Activity.nation = "벨기에";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 5:
					DB_List_Nation_Main_Activity.nation = "불가리아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 6:
					DB_List_Nation_Main_Activity.nation = "체코";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 7:
					DB_List_Nation_Main_Activity.nation = "크로아티아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 8:
					DB_List_Nation_Main_Activity.nation = "키프로스";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 9:
					DB_List_Nation_Main_Activity.nation = "덴마크";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 10:
					DB_List_Nation_Main_Activity.nation = "에스토니아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 11:
					DB_List_Nation_Main_Activity.nation = "프랑스";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 12:
					DB_List_Nation_Main_Activity.nation = "독일";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 13:
					DB_List_Nation_Main_Activity.nation = "그리스";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 14:
					DB_List_Nation_Main_Activity.nation = "헝가리";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 15:
					DB_List_Nation_Main_Activity.nation = "아일랜드";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 16:
					DB_List_Nation_Main_Activity.nation = "이탈리아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 17:
					DB_List_Nation_Main_Activity.nation = "라트비아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 18:
					DB_List_Nation_Main_Activity.nation = "리투아니아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 19:
					DB_List_Nation_Main_Activity.nation = "룩셈부르크";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 20:
					DB_List_Nation_Main_Activity.nation = "말타";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 21:
					DB_List_Nation_Main_Activity.nation = "네델란드";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 22:
					DB_List_Nation_Main_Activity.nation = "노르웨이";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 23:
					DB_List_Nation_Main_Activity.nation = "포르투갈";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 24:
					DB_List_Nation_Main_Activity.nation = "루마니아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 25:
					DB_List_Nation_Main_Activity.nation = "러시아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 26:
					DB_List_Nation_Main_Activity.nation = "스웨덴";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 27:
					DB_List_Nation_Main_Activity.nation = "세르비아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 28:
					DB_List_Nation_Main_Activity.nation = "슬로베니아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 29:
					DB_List_Nation_Main_Activity.nation = "스위스";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 30:
					DB_List_Nation_Main_Activity.nation = "우크라이나";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 31:
					DB_List_Nation_Main_Activity.nation = "영국";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 32:
					DB_List_Nation_Main_Activity.nation = "폴란드";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 33:
					DB_List_Nation_Main_Activity.nation = "몰다비아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 34:
					DB_List_Nation_Main_Activity.nation = "방글라데시";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 35:
					DB_List_Nation_Main_Activity.nation = "캄보디아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 36:
					DB_List_Nation_Main_Activity.nation = "중국";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 37:
					DB_List_Nation_Main_Activity.nation = "홍콩";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 38:
					DB_List_Nation_Main_Activity.nation = "인도";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 39:
					DB_List_Nation_Main_Activity.nation = "인도네시아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 40:
					DB_List_Nation_Main_Activity.nation = "스페인";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 41:
					DB_List_Nation_Main_Activity.nation = "이스라엘";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 42:
					DB_List_Nation_Main_Activity.nation = "일본";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 43:
					DB_List_Nation_Main_Activity.nation = "요르단";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 44:
					DB_List_Nation_Main_Activity.nation = "한국";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 45:
					DB_List_Nation_Main_Activity.nation = "라오스";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 46:
					DB_List_Nation_Main_Activity.nation = "마카오";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 47:
					DB_List_Nation_Main_Activity.nation = "말레이시아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 48:
					DB_List_Nation_Main_Activity.nation = "미얀마";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 49:
					DB_List_Nation_Main_Activity.nation = "네팔";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 50:
					DB_List_Nation_Main_Activity.nation = "북한";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 51:
					DB_List_Nation_Main_Activity.nation = "파키스탄";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 52:
					DB_List_Nation_Main_Activity.nation = "필리핀";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 53:
					DB_List_Nation_Main_Activity.nation = "태국";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 54:
					DB_List_Nation_Main_Activity.nation = "스리랑카";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 55:
					DB_List_Nation_Main_Activity.nation = "대만";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 56:
					DB_List_Nation_Main_Activity.nation = "투르크메니스탄";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 57:
					DB_List_Nation_Main_Activity.nation = "베트남";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 58:
					DB_List_Nation_Main_Activity.nation = "아르헨티나";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 59:
					DB_List_Nation_Main_Activity.nation = "볼리비아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 60:
					DB_List_Nation_Main_Activity.nation = "브라질";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 61:
					DB_List_Nation_Main_Activity.nation = "캐나다";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 62:
					DB_List_Nation_Main_Activity.nation = "칠레";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 63:
					DB_List_Nation_Main_Activity.nation = "콜롬비아";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 64:
					DB_List_Nation_Main_Activity.nation = "터키";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 65:
					DB_List_Nation_Main_Activity.nation = "과테말라";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 66:
					DB_List_Nation_Main_Activity.nation = "온두라스";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 67:
					DB_List_Nation_Main_Activity.nation = "멕시코";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 68:
					DB_List_Nation_Main_Activity.nation = "파라과이";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 69:
					DB_List_Nation_Main_Activity.nation = "페루";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 70:
					DB_List_Nation_Main_Activity.nation = "미국";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 71:
					DB_List_Nation_Main_Activity.nation = "뉴질랜드";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 72:
					DB_List_Nation_Main_Activity.nation = "이집트";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 73:
					DB_List_Nation_Main_Activity.nation = "케냐";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 74:
					DB_List_Nation_Main_Activity.nation = "레소토";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 75:
					DB_List_Nation_Main_Activity.nation = "마다가스카르";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 76:
					DB_List_Nation_Main_Activity.nation = "모리셔스";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 77:
					DB_List_Nation_Main_Activity.nation = "모로코";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 78:
					DB_List_Nation_Main_Activity.nation = "남아프리카";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 79:
					DB_List_Nation_Main_Activity.nation = "튀니지";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 80:
					DB_List_Nation_Main_Activity.nation = "호주";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 81:
					DB_List_Nation_Main_Activity.nation = "나우루";
					 DB_List_Nation_Main_Activity.size = 20;break;
				
				}
				intent = new Intent(mContext,
	        			DB_List_Nation_Main_Activity.class);
				mContext.startActivity(intent);
			}
			
		});
		
		return convertView;
	}
	
	private Integer[] euImage = {R.drawable.mongolia,
		R.drawable.eu_albania, R.drawable.eu_armenia,
		R.drawable.eu_austria, R.drawable.eu_belguie, R.drawable.eu_bulgaria,
		R.drawable.eu_chzechpublic, R.drawable.eu_croatia, R.drawable.eu_cyprus,
		R.drawable.eu_denmark, R.drawable.eu_estonia, R.drawable.eu_fancer,
		R.drawable.eu_germany, R.drawable.eu_greece, R.drawable.eu_hungary,
		R.drawable.eu_ireland, R.drawable.eu_italy, R.drawable.eu_latvia,
		R.drawable.eu_lithuania, R.drawable.eu_luxembourg, R.drawable.eu_malta,
		R.drawable.eu_netherlands, R.drawable.eu_norway,
		R.drawable.eu_portugal, R.drawable.eu_romania,
		R.drawable.eu_russia, R.drawable.eu_seden, R.drawable.eu_serbia,
		R.drawable.eu_slovenia, R.drawable.eu_switzerland, R.drawable.eu_ukraine,
		R.drawable.eu_unitedkingdom, R.drawable.eu_poland, R.drawable.eu_moldova,
		R.drawable.asia_bangladesh, R.drawable.asia_cambodia, R.drawable.asia_china,
		R.drawable.asia_hong_kong, R.drawable.asia_india, R.drawable.asia_indonesia,
		R.drawable.eu_spain, R.drawable.asia_israel,
		R.drawable.asia_japan, R.drawable.asia_jordan,
		R.drawable.asia_korea, R.drawable.asia_laos,
		R.drawable.asia_macau, R.drawable.asia_malaysia,
		R.drawable.asia_myanmar, R.drawable.asia_nepal, R.drawable.asia_northkorea,
		R.drawable.asia_pakistan, R.drawable.asia_philippines, 
		R.drawable.asia_thailand ,R.drawable.asia_srilanka,
		R.drawable.asia_taiwan, R.drawable.asia_turkmenistan, R.drawable.asia_vietnam,
		R.drawable.ameria_argentina, R.drawable.ameria_bolivia, R.drawable.ameria_brazil,
		R.drawable.ameria_canada, R.drawable.ameria_chile,
		R.drawable.ameria_colombia, R.drawable.africa_turkey, 
		R.drawable.ameria_guatemala, R.drawable.ameria_honduras,
		R.drawable.ameria_mexico, R.drawable.ameria_paraguay,
		R.drawable.ameria_peru, R.drawable.ameria_usa, R.drawable.oceania_newzealand,
		R.drawable.afria_epypt, R.drawable.africa_kenya, R.drawable.africa_lesotho,
		R.drawable.africa_madagascar, R.drawable.africa_mauritius,
		R.drawable.africa_morocco, R.drawable.africa_southafrica,
		R.drawable.africa_tunisia,R.drawable.oceania_australia,
		R.drawable.oceania_nauru
	};	
	
	private String[] euText = {"몽골","알바니아", "아르메니아", "오스트리아",
			"벨기에", "불가리아", "체코", "크로아티아", "키프로스", "덴마크", "에스토니아",
			"프랑스", "독일", "그리스", "헝가리", "아일랜드", "이탈리아", "라트비아",
			"리투아니아", "룩셈부르크", "말타", "네델란드", "노르웨이",
			"포르투갈", "루마니아", "러시아", "스웨덴", "세르비아", "슬로베니아", "스위스",
			"우크라이나", "영국", "폴란드", "몰다비아",
			"방글라데시", "캄보디아", "중국", "홍콩", "인도", "인도네시아",
			"스페인","이스라엘","일본", "요르단","한국", "라오스", "마카오", 
			"말레이시아", "미얀마","네팔", "북한", "파키스탄", "필리핀",
			"태국", "스리랑카", "대만",
			"투르크메니스탄", "베트남",
			"아르헨티나", "볼리비아", "브라질", "캐나다",
			"칠레", "콜롬비아", "터키", "과테말라", "온두라스", "멕시코",
			"파라과이", "페루", "미국", "뉴질랜드",
			"이집트", "케냐", "레소토", "마다가스카르",
			"모리셔스", "모로코", "남아프리카", "튀니지",
			"호주", "나우루"
			};

	
}