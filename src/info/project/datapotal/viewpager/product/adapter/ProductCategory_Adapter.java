package info.project.datapotal.viewpager.product.adapter;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.product.vo.ProductManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductCategory_Adapter extends BaseAdapter {

	Context mContext;
	int layout;
	LayoutInflater mInflater;
	ProductManager pManager;
	int count;

	public ProductCategory_Adapter(Context mContext, int layout) {
		this.mContext = mContext;
		this.layout = layout;
		pManager = new ProductManager();
		count = 0;

		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return pManager.getProduct_Category().length;
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
		TextView txtTitle = (TextView) convertView
				.findViewById(R.id.category_txt);
		ImageView imgIcon = (ImageView) convertView
				.findViewById(R.id.category_img);
		imgIcon.setImageResource(pManager.getImgIcon(position));
		txtTitle.setText(pManager.getProduct_Category(position));
		count++;

		return convertView;
	}

}
