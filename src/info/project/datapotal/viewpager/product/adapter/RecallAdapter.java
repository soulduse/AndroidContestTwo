package info.project.datapotal.viewpager.product.adapter;

import info.project.datapotal.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class RecallAdapter extends BaseAdapter implements Serializable{

	Context mContext;
	int layout;
	ArrayList<HashMap<String,String>> list;
	LayoutInflater mInflater;
	DisplayImageOptions defaultOptions;
	Builder configBuilder;
	ImageLoaderConfiguration config;
	int currentBtn;
	
	public RecallAdapter(Context mContext, int layout, ArrayList<HashMap<String,String>> list, int currentBtn){
		this.mContext = mContext;
		this.layout = layout;
		this.list = list;
		this.currentBtn = currentBtn;
		
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
		// 이미지 입히기 - AUIL
		defaultOptions = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.loading)
			.showImageForEmptyUri(R.drawable.error)
			.showImageOnFail(R.drawable.loading_fail)
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.considerExifParams(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();
				
		config = new ImageLoaderConfiguration.Builder(mContext)
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.denyCacheImageMultipleSizesInMemory()
		.diskCacheFileNameGenerator(new Md5FileNameGenerator())
		.diskCacheSize(50 * 1024 * 1024) // 50 Mb
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		.writeDebugLogs() // Remove for release app
		.build();
		ImageLoader.getInstance().init(config);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
		if(convertView==null){
			convertView = mInflater.inflate(layout, null);
		}
		
		TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
		TextView txtMid = (TextView) convertView.findViewById(R.id.txtMid);
		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
		
		String title="", middle="";
		String imgURL="";
		
		// 각 버튼에 따라 리스트뷰에 출력될 텍스트의 순서를 달리한다.
		if(currentBtn==0){
			title = list.get(position).get("makingNation");
			middle = "모델명  " + list.get(position).get("model");
			imgURL = list.get(position).get("linkURL");
			
			/*
			 * filter
			 * [0] : 국내/외 , [1] : 지역, [2] : 나라
			 */
			String[] filter = title.split(">");
			int length = filter.length;
			title = filter[length-1];
		}else if(currentBtn==1){
			title = list.get(position).get("productName");
			middle = "모델명  " + list.get(position).get("model");
			imgURL = list.get(position).get("linkURL");	
		}
			
		
		String[] imgFilter = imgURL.split(", ");
		
		ImageLoader.getInstance().displayImage(imgFilter[0], imgIcon, defaultOptions);
		
		txtTitle.setText(title);
		txtMid.setText(middle);
		
		return convertView;
	}

}
