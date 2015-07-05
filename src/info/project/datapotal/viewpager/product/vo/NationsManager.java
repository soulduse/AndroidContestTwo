package info.project.datapotal.viewpager.product.vo;

import info.project.datapotal.R;

public class NationsManager {
	private String product_Category[] = 
		{	
			"과테말라", "그리스", "나우루", "남아프리카", "네덜란드", "네팔", "대만", "대한민국", "덴마크", "독일", "라트비아", "러시아",
			"루마니아", "리투아니아", "마다가스카르", "말레이시아", "멕시코", "모로코", "미국", "미얀마", "방글라데시", "베트남", "벨기에", 
			"보스니아", "북한", "불가리아", "브라질", "세르비아", "스리랑카", "스웨덴", "스위스", "스페인", "슬로바키아", "슬로베니아", 
			"시리아", "아르헨티나", "아일랜드", "알바니아", "에스토니아", "영국", "오스트레일리아", "오스트리아", "우크라이나", "이스라엘",
			"이집트", "이탈리아", "인도", "인도네시아", "일본", "중국", "체코", "캄보디아", "캐나다", "크로아티아", 
			"키프로스", "태국", "터키", "튀니지", "파키스탄", "페루", "포르투갈", "폴란드", "프랑스", "핀란드", "필리핀", "헝가리", "홍콩", "기타"
		};
	private int imgIcon[] = 
		{
			R.drawable.ameria_guatemala, R.drawable.eu_greece, R.drawable.oceania_nauru, R.drawable.africa_southafrica,
			R.drawable.eu_netherlands, R.drawable.asia_nepal, R.drawable.asia_taiwan, R.drawable.asia_korea,
			R.drawable.eu_denmark, R.drawable.eu_germany, R.drawable.eu_latvia, R.drawable.eu_russia,
			R.drawable.eu_romania, R.drawable.eu_lithuania, R.drawable.africa_madagascar, R.drawable.asia_malaysia,
			R.drawable.ameria_mexico, R.drawable.africa_morocco, R.drawable.ameria_usa, R.drawable.asia_myanmar,
			R.drawable.asia_bangladesh, R.drawable.asia_vietnam, R.drawable.eu_belguie, R.drawable.eu_bosnia,
			R.drawable.asia_northkorea, R.drawable.eu_bulgaria, R.drawable.ameria_brazil, R.drawable.eu_serbia,
			R.drawable.asia_srilanka, R.drawable.eu_seden, R.drawable.eu_switzerland, R.drawable.eu_spain,
			R.drawable.eu_slovakia, R.drawable.eu_slovenia, R.drawable.africa_syria, R.drawable.ameria_argentina,
			R.drawable.eu_ireland, R.drawable.eu_albania, R.drawable.eu_estonia, R.drawable.eu_unitedkingdom,
			R.drawable.oceania_australia, R.drawable.eu_austria, R.drawable.eu_ukraine, R.drawable.asia_israel,
			R.drawable.afria_epypt, R.drawable.eu_italy, R.drawable.asia_india, R.drawable.asia_indonesia,
			R.drawable.asia_japan, R.drawable.asia_china, R.drawable.eu_chzechpublic, R.drawable.asia_cambodia,
			R.drawable.ameria_canada, R.drawable.eu_croatia, R.drawable.eu_cyprus, R.drawable.asia_thailand,
			R.drawable.africa_turkey, R.drawable.africa_tunisia, R.drawable.asia_pakistan, R.drawable.ameria_peru,
			R.drawable.eu_portugal, R.drawable.eu_poland, R.drawable.eu_fancer, R.drawable.eu_finland,
			R.drawable.asia_philippines, R.drawable.eu_hungary, R.drawable.asia_hong_kong, R.drawable.etc_flag
		};
	private String URL[] = 
		{
			"과테말라", "그리스", "나우루", "남아프리카", "네덜란드", "네팔", "대만", "대한민국", "덴마크", "독일", "라트비아", "러시아",
			"루마니아", "리투아니아", "마다가스카르", "말레이시아", "멕시코", "모로코", "미국", "미얀마", "방글라데시", "베트남", "벨기에", 
			"보스니아", "북한", "불가리아", "브라질", "세르비아", "스리랑카", "스웨덴", "스위스", "스페인", "슬로바키아", "슬로베니아", 
			"시리아", "아르헨티나", "아일랜드", "알바니아", "에스토니아", "영국", "오스트레일리아", "오스트리아", "우크라이나", "이스라엘",
			"이집트", "이탈리아", "인도", "인도네시아", "일본", "중국", "체코", "캄보디아", "캐나다","크로아티아", 
			"키프로스", "태국", "터키", "튀니지", "파키스탄", "페루", "포르투갈", "폴란드", "프랑스", "핀란드", "필리핀", "헝가리", "홍콩", "기타"
		};
	/**
	 * 쿼리 sample
	 * model_query={$or:[{"productContents":{"$regex":"완구"}},{"productContents":{"$regex":"건조기"}}]}
	 * 
	 * model_query
	 * head + context_(start+field+contents+end) + tail
	 */
	private String model_query_head = "model_query_distinct=model&model_query=%7B$or:%5B";
	private String model_query_head_and = "model_query_distinct=model&model_query=%7B$and:%5B";
	private String model_query_context_start = "%7B";
	private String model_query_context_field = "";
	private String model_query_context_body = ":%7B%22$regex%22:";
	private String model_query_context_contents = "";
	private String model_query_context_end = "%7D%7D";
	private String model_query_comma = ",";
	private String model_query_tail = "%5D%7D";
	
	public String getQuery_One_Section(String field, String contents){
		String url = "";
		
		url = model_query_context_start + "%22"
				+ field + "%22" + model_query_context_body + "%22" + contents + "%22" + model_query_context_end;
		if(contents.equals("기타")){
			url += model_query_comma + model_query_context_start + "%22" + field + "%22" + model_query_context_body
					+ "%22코코스아일랜드%22" + model_query_context_end;
		}
		
		return url;
	}
	
	public String getModel_query_head_and() {
		return model_query_head_and;
	}
	
	public String getModel_query_head() {
		return model_query_head;
	}

	public String getModel_query_tail() {
		return model_query_tail;
	}

	public String[] getProduct_Category() {
		return product_Category;
	}

	public String getProduct_Category(int position){
		return product_Category[position];
	}
	
	public void setProduct_Category(String[] product_Category) {
		this.product_Category = product_Category;
	}

	public int[] getImgIcon() {
		return imgIcon;
	}
	
	public int getImgIcon(int position){
		return imgIcon[position];
	}

	public void setImgIcon(int[] imgIcon) {
		this.imgIcon = imgIcon;
	}

	public String[] getURL() {
		return URL;
	}
	
	public String getURL(int position){
		return URL[position];
	}

	public void setURL(String[] uRL) {
		URL = uRL;
	}
}
