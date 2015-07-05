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



public class DB_Grid_Products_Adapter extends BaseAdapter {

	Context mContext;
	LayoutInflater inflater;
	int layout;
	Intent intent;
	
	public DB_Grid_Products_Adapter(Context c, int layout) {
		mContext = c;
		this.layout = layout;

		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return productsImage.length;
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
		
		imageView.setImageResource(productsImage[position]);
		textView.setText(productsText[position]);
		
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(position) {
				case 0:
					DB_List_Products_Main_Activity.url =
					"model_query=%7B$or:%5B%7B'code3Name':%7B'$regex':'등산'%7D%7D," +
					"%7B'code3Name':%7B'$regex':'스포츠'%7D%7D," +
					"%7B'code3Name':%7B'$regex':'헬스'%7D%7D";
					DB_List_Products_Main_Activity.size = 20;
					break;
				case 1:
					DB_List_Products_Main_Activity.url =
					"model_query=%7B$or:%5B%7B'code3Name':%7B'$regex':'유아용침대'%7D%7D," +
					"%7B'code3Name':%7B'$regex':'소파'%7D%7D," +
					"%7B'code3Name':%7B'$regex':'가구'%7D%7D";
					DB_List_Products_Main_Activity.size = 20;
					break;
				case 2:
					DB_List_Products_Main_Activity.url =
					"model_query=%7B%22code3Name%22:%7B%22$regex%22:%22조명%22%7D%7D";
					DB_List_Products_Main_Activity.size = 20;
					break;
				case 3:
					DB_List_Products_Main_Activity.url =
				"model_query=%7B$or:%5B%7B'code3Name':%7B'$regex':'섬유'%7D%7D," +
				"%7B'code3Name':%7B'$regex':'기저귀'%7D%7D," +
				"%7B'code3Name':%7B'$regex':'수유'%7D%7D";
					DB_List_Products_Main_Activity.size = 20;
					break;
				case 4:
		DB_List_Products_Main_Activity.url =
		"model_query=%7B$or:%5B%7B'code3Name':%7B'$regex':'전기'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'정보'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'전동'%7D%7D";
		DB_List_Products_Main_Activity.size = 20;			
		break;
				case 5:
					DB_List_Products_Main_Activity.url =
			"model_query=%7B%22code3Name%22:%7B%22$regex%22:%22완구%22%7D%7D";
					DB_List_Products_Main_Activity.size = 20;
					break;
				case 6:
					DB_List_Products_Main_Activity.url =
		"model_query=%7B$or:%5B%7B'code3Name':%7B'$regex':'화학'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'절연'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'유모차'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'승강기'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'실내용'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'벽지'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'온열팩'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'물휴지'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'합성'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'자동차'%7D%7D," +
		"%7B'code3Name':%7B'$regex':'AD'%7D%7D%5D%7D";
					DB_List_Products_Main_Activity.size = 20;
					break;
				}
				intent = new Intent(mContext,
	        			DB_List_Products_Main_Activity.class);
				mContext.startActivity(intent);
			}
			
		});
		
		return convertView;
	}
	
	private Integer[] productsImage = {
		R.drawable.mage_bicycle, R.drawable.mage_bed, R.drawable.mage_bulb,
		R.drawable.mage_dress, R.drawable.mage_electric,
		R.drawable.mage_toy, R.drawable.mage_etc
	};	
	
	private String[] productsText = {"운동기구&관련제품", "침구류", "전구류",
			"의류", "전기제품류", "장남감류", "그외제품"};
	
	
}