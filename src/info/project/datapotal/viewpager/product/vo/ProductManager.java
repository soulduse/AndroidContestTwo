package info.project.datapotal.viewpager.product.vo;

import info.project.datapotal.R;

public class ProductManager {
	private String product_Category[] = 
		{	
			"침구류","자전거","전등","의류","전기제품","기타","공구","장난감"
		};
	private int imgIcon[] = 
		{
			R.drawable.bed, R.drawable.bicycle, R.drawable.bulb, R.drawable.dress,
			R.drawable.electric, R.drawable.etc, R.drawable.ladder, R.drawable.toy
		};
	private String URL[][] = 
		{
			{"침대"},{"자전거"},{"램프","전구"},{"수영복","모자","마스크","의류","신발","장갑"},{"장난감","인형","놀이울"},
			{"어댑터","연장선","모니터","전기","컴퓨터"},{"사다리"},
			{"완구","건조기","양초","캠핑세트","욕실","보행기","조명체인","착즙기","팔찌","스파","퍼즐","플라스틱공"}
		};
	/**
	 * 쿼리 sample
	 * model_query={$or:[{"productContents":{"$regex":"완구"}},{"productContents":{"$regex":"건조기"}}]}
	 * 
	 * model_query
	 * head + context_(start+field+contents+end) + tail
	 */
	private String model_query_head = "model_query_distinct=model&model_query=%7B$or:%5B";
	private String model_query_context_start = "%7B";
	private String model_query_context_field = "";
	private String model_query_context_body = ":%7B%22$regex%22:";
	private String model_query_context_contents = "";
	private String model_query_context_end = "%7D%7D";
	private String model_query_comma = ",";
	private String model_query_tail = "%5D%7D";
	
	public String getQuery_One_Section(String field, String contents){
		String url = 
				model_query_context_start + "%22"
				+ field + "%22" + model_query_context_body + "%22" + contents + "%22" + model_query_context_end;
		return url;
	}
	
	public ProductManager() {
		super();
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

	public String[][] getURL() {
		return URL;
	}
	
	public String[] getURL(int position){
		return URL[position];
	}

	public void setURL(String[][] uRL) {
		URL = uRL;
	}
}
