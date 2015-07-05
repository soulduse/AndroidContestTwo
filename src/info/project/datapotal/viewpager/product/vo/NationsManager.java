package info.project.datapotal.viewpager.product.vo;

import info.project.datapotal.R;

public class NationsManager {
	private String product_Category[] = 
		{	
			"���׸���", "�׸���", "�����", "��������ī", "�״�����", "����", "�븸", "���ѹα�", "����ũ", "����", "��Ʈ���", "���þ�",
			"�縶�Ͼ�", "�����ƴϾ�", "���ٰ���ī��", "�����̽þ�", "�߽���", "�����", "�̱�", "�̾Ḷ", "��۶󵥽�", "��Ʈ��", "���⿡", 
			"�����Ͼ�", "����", "�Ұ�����", "�����", "�������", "������ī", "������", "������", "������", "���ι�Ű��", "���κ��Ͼ�", 
			"�ø���", "�Ƹ���Ƽ��", "���Ϸ���", "�˹ٴϾ�", "������Ͼ�", "����", "����Ʈ���ϸ���", "����Ʈ����", "��ũ���̳�", "�̽���",
			"����Ʈ", "��Ż����", "�ε�", "�ε��׽þ�", "�Ϻ�", "�߱�", "ü��", "į�����", "ĳ����", "ũ�ξ�Ƽ��", 
			"Ű���ν�", "�±�", "��Ű", "Ƣ����", "��Ű��ź", "���", "��������", "������", "������", "�ɶ���", "�ʸ���", "�밡��", "ȫ��", "��Ÿ"
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
			"���׸���", "�׸���", "�����", "��������ī", "�״�����", "����", "�븸", "���ѹα�", "����ũ", "����", "��Ʈ���", "���þ�",
			"�縶�Ͼ�", "�����ƴϾ�", "���ٰ���ī��", "�����̽þ�", "�߽���", "�����", "�̱�", "�̾Ḷ", "��۶󵥽�", "��Ʈ��", "���⿡", 
			"�����Ͼ�", "����", "�Ұ�����", "�����", "�������", "������ī", "������", "������", "������", "���ι�Ű��", "���κ��Ͼ�", 
			"�ø���", "�Ƹ���Ƽ��", "���Ϸ���", "�˹ٴϾ�", "������Ͼ�", "����", "����Ʈ���ϸ���", "����Ʈ����", "��ũ���̳�", "�̽���",
			"����Ʈ", "��Ż����", "�ε�", "�ε��׽þ�", "�Ϻ�", "�߱�", "ü��", "į�����", "ĳ����","ũ�ξ�Ƽ��", 
			"Ű���ν�", "�±�", "��Ű", "Ƣ����", "��Ű��ź", "���", "��������", "������", "������", "�ɶ���", "�ʸ���", "�밡��", "ȫ��", "��Ÿ"
		};
	/**
	 * ���� sample
	 * model_query={$or:[{"productContents":{"$regex":"�ϱ�"}},{"productContents":{"$regex":"������"}}]}
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
		if(contents.equals("��Ÿ")){
			url += model_query_comma + model_query_context_start + "%22" + field + "%22" + model_query_context_body
					+ "%22���ڽ����Ϸ���%22" + model_query_context_end;
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
