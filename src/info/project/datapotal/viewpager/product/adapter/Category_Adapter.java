package info.project.datapotal.viewpager.product.adapter;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.product.vo.NationsManager;
import info.project.datapotal.viewpager.product.vo.ProductManager;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Category_Adapter extends BaseAdapter {

	Context mContext;
	int layout;
	LayoutInflater mInflater;
	int currentBtn;
	ProductManager pManager;
	NationsManager nManager;

	public Category_Adapter(Context mContext, int layout, int currentBtn) {
		this.mContext = mContext;
		this.layout = layout;
		this.currentBtn = currentBtn;
		
		// 항목 관리자 클래스 설정
		pManager = new ProductManager();
		nManager = new NationsManager();
		
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if(currentBtn==0){
			return nManager.getProduct_Category().length;
		}else if(currentBtn==1){
			return pManager.getProduct_Category().length;
		}else if(currentBtn==2){
			return 0;
		}else{
			return 0;
		}
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
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(layout, null);
		}
		
		TextView txtTitle = (TextView) convertView.findViewById(R.id.category_txt);
		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.category_img);
		
		if(currentBtn==0){
			imgIcon.setImageResource(nManager.getImgIcon(position));
			txtTitle.setText(nManager.getProduct_Category(position));
			
		}else if(currentBtn==1){
			//imgIcon.setImageResource(mManager.getImgIcon(position));
			//txtTitle.setText(mManager.getProduct_Category(position));
			imgIcon.setImageResource(pManager.getImgIcon(position));
			txtTitle.setText(pManager.getProduct_Category(position));
			
		}else if(currentBtn==2){
			
			
		}

		return convertView;
	}

}
