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
					DB_List_Nation_Main_Activity.nation = "����";
					DB_List_Nation_Main_Activity.size = 20;break;
				case 1:
					DB_List_Nation_Main_Activity.nation = "�˹ٴϾ�";
					DB_List_Nation_Main_Activity.size = 20;break;
				case 2:
					DB_List_Nation_Main_Activity.nation = "�Ƹ��޴Ͼ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 3:
					DB_List_Nation_Main_Activity.nation = "����Ʈ����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 4:
					DB_List_Nation_Main_Activity.nation = "���⿡";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 5:
					DB_List_Nation_Main_Activity.nation = "�Ұ�����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 6:
					DB_List_Nation_Main_Activity.nation = "ü��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 7:
					DB_List_Nation_Main_Activity.nation = "ũ�ξ�Ƽ��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 8:
					DB_List_Nation_Main_Activity.nation = "Ű���ν�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 9:
					DB_List_Nation_Main_Activity.nation = "����ũ";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 10:
					DB_List_Nation_Main_Activity.nation = "������Ͼ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 11:
					DB_List_Nation_Main_Activity.nation = "������";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 12:
					DB_List_Nation_Main_Activity.nation = "����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 13:
					DB_List_Nation_Main_Activity.nation = "�׸���";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 14:
					DB_List_Nation_Main_Activity.nation = "�밡��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 15:
					DB_List_Nation_Main_Activity.nation = "���Ϸ���";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 16:
					DB_List_Nation_Main_Activity.nation = "��Ż����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 17:
					DB_List_Nation_Main_Activity.nation = "��Ʈ���";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 18:
					DB_List_Nation_Main_Activity.nation = "�����ƴϾ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 19:
					DB_List_Nation_Main_Activity.nation = "����θ�ũ";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 20:
					DB_List_Nation_Main_Activity.nation = "��Ÿ";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 21:
					DB_List_Nation_Main_Activity.nation = "�׵�����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 22:
					DB_List_Nation_Main_Activity.nation = "�븣����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 23:
					DB_List_Nation_Main_Activity.nation = "��������";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 24:
					DB_List_Nation_Main_Activity.nation = "�縶�Ͼ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 25:
					DB_List_Nation_Main_Activity.nation = "���þ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 26:
					DB_List_Nation_Main_Activity.nation = "������";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 27:
					DB_List_Nation_Main_Activity.nation = "�������";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 28:
					DB_List_Nation_Main_Activity.nation = "���κ��Ͼ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 29:
					DB_List_Nation_Main_Activity.nation = "������";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 30:
					DB_List_Nation_Main_Activity.nation = "��ũ���̳�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 31:
					DB_List_Nation_Main_Activity.nation = "����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 32:
					DB_List_Nation_Main_Activity.nation = "������";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 33:
					DB_List_Nation_Main_Activity.nation = "���ٺ��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 34:
					DB_List_Nation_Main_Activity.nation = "��۶󵥽�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 35:
					DB_List_Nation_Main_Activity.nation = "į�����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 36:
					DB_List_Nation_Main_Activity.nation = "�߱�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 37:
					DB_List_Nation_Main_Activity.nation = "ȫ��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 38:
					DB_List_Nation_Main_Activity.nation = "�ε�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 39:
					DB_List_Nation_Main_Activity.nation = "�ε��׽þ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 40:
					DB_List_Nation_Main_Activity.nation = "������";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 41:
					DB_List_Nation_Main_Activity.nation = "�̽���";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 42:
					DB_List_Nation_Main_Activity.nation = "�Ϻ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 43:
					DB_List_Nation_Main_Activity.nation = "�丣��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 44:
					DB_List_Nation_Main_Activity.nation = "�ѱ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 45:
					DB_List_Nation_Main_Activity.nation = "�����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 46:
					DB_List_Nation_Main_Activity.nation = "��ī��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 47:
					DB_List_Nation_Main_Activity.nation = "�����̽þ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 48:
					DB_List_Nation_Main_Activity.nation = "�̾Ḷ";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 49:
					DB_List_Nation_Main_Activity.nation = "����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 50:
					DB_List_Nation_Main_Activity.nation = "����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 51:
					DB_List_Nation_Main_Activity.nation = "��Ű��ź";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 52:
					DB_List_Nation_Main_Activity.nation = "�ʸ���";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 53:
					DB_List_Nation_Main_Activity.nation = "�±�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 54:
					DB_List_Nation_Main_Activity.nation = "������ī";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 55:
					DB_List_Nation_Main_Activity.nation = "�븸";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 56:
					DB_List_Nation_Main_Activity.nation = "����ũ�޴Ͻ�ź";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 57:
					DB_List_Nation_Main_Activity.nation = "��Ʈ��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 58:
					DB_List_Nation_Main_Activity.nation = "�Ƹ���Ƽ��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 59:
					DB_List_Nation_Main_Activity.nation = "�������";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 60:
					DB_List_Nation_Main_Activity.nation = "�����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 61:
					DB_List_Nation_Main_Activity.nation = "ĳ����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 62:
					DB_List_Nation_Main_Activity.nation = "ĥ��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 63:
					DB_List_Nation_Main_Activity.nation = "�ݷҺ��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 64:
					DB_List_Nation_Main_Activity.nation = "��Ű";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 65:
					DB_List_Nation_Main_Activity.nation = "���׸���";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 66:
					DB_List_Nation_Main_Activity.nation = "�µζ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 67:
					DB_List_Nation_Main_Activity.nation = "�߽���";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 68:
					DB_List_Nation_Main_Activity.nation = "�Ķ����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 69:
					DB_List_Nation_Main_Activity.nation = "���";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 70:
					DB_List_Nation_Main_Activity.nation = "�̱�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 71:
					DB_List_Nation_Main_Activity.nation = "��������";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 72:
					DB_List_Nation_Main_Activity.nation = "����Ʈ";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 73:
					DB_List_Nation_Main_Activity.nation = "�ɳ�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 74:
					DB_List_Nation_Main_Activity.nation = "������";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 75:
					DB_List_Nation_Main_Activity.nation = "���ٰ���ī��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 76:
					DB_List_Nation_Main_Activity.nation = "�𸮼Ž�";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 77:
					DB_List_Nation_Main_Activity.nation = "�����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 78:
					DB_List_Nation_Main_Activity.nation = "��������ī";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 79:
					DB_List_Nation_Main_Activity.nation = "Ƣ����";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 80:
					DB_List_Nation_Main_Activity.nation = "ȣ��";
					 DB_List_Nation_Main_Activity.size = 20;break;
				case 81:
					DB_List_Nation_Main_Activity.nation = "�����";
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
	
	private String[] euText = {"����","�˹ٴϾ�", "�Ƹ��޴Ͼ�", "����Ʈ����",
			"���⿡", "�Ұ�����", "ü��", "ũ�ξ�Ƽ��", "Ű���ν�", "����ũ", "������Ͼ�",
			"������", "����", "�׸���", "�밡��", "���Ϸ���", "��Ż����", "��Ʈ���",
			"�����ƴϾ�", "����θ�ũ", "��Ÿ", "�׵�����", "�븣����",
			"��������", "�縶�Ͼ�", "���þ�", "������", "�������", "���κ��Ͼ�", "������",
			"��ũ���̳�", "����", "������", "���ٺ��",
			"��۶󵥽�", "į�����", "�߱�", "ȫ��", "�ε�", "�ε��׽þ�",
			"������","�̽���","�Ϻ�", "�丣��","�ѱ�", "�����", "��ī��", 
			"�����̽þ�", "�̾Ḷ","����", "����", "��Ű��ź", "�ʸ���",
			"�±�", "������ī", "�븸",
			"����ũ�޴Ͻ�ź", "��Ʈ��",
			"�Ƹ���Ƽ��", "�������", "�����", "ĳ����",
			"ĥ��", "�ݷҺ��", "��Ű", "���׸���", "�µζ�", "�߽���",
			"�Ķ����", "���", "�̱�", "��������",
			"����Ʈ", "�ɳ�", "������", "���ٰ���ī��",
			"�𸮼Ž�", "�����", "��������ī", "Ƣ����",
			"ȣ��", "�����"
			};

	
}