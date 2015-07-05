package info.project.datapotal.viewpager.product.vo;

import info.project.datapotal.R;

public class ProductManager {
	private String product_Category[] = 
		{	
			"ħ����","������","����","�Ƿ�","������ǰ","��Ÿ","����","�峭��"
		};
	private int imgIcon[] = 
		{
			R.drawable.bed, R.drawable.bicycle, R.drawable.bulb, R.drawable.dress,
			R.drawable.electric, R.drawable.etc, R.drawable.ladder, R.drawable.toy
		};
	private String URL[][] = 
		{
			{"ħ��"},{"������"},{"����","����"},{"������","����","����ũ","�Ƿ�","�Ź�","�尩"},{"�峭��","����","���̿�"},
			{"�����","���弱","�����","����","��ǻ��"},{"��ٸ�"},
			{"�ϱ�","������","����","ķ�μ�Ʈ","���","�����","����ü��","�����","����","����","����","�ö�ƽ��"}
		};
	/**
	 * ���� sample
	 * model_query={$or:[{"productContents":{"$regex":"�ϱ�"}},{"productContents":{"$regex":"������"}}]}
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
