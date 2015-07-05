package info.project.datapotal.viewpager.sign.adapter;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.sign.FragmentSignOne;
import info.project.datapotal.viewpager.sign.holder.SignOneViewHolder;

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

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class SignOneAdapter extends BaseAdapter{
	
	Context con;
	ArrayList<HashMap<String, String>> contactList;
	LayoutInflater inf;
	
	DisplayImageOptions options;
	ImageLoaderConfiguration config;
	int count;
    
	public SignOneAdapter(Context con, ArrayList<HashMap<String, String>> contactList){
		this.con = con;
		this.contactList = contactList;
		
		inf = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		// √ ±‚º≥¡§
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.loading)
		.showImageForEmptyUri(R.drawable.error)
		.showImageOnFail(R.drawable.loading_fail)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
		
		config = new ImageLoaderConfiguration.Builder(con)
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.denyCacheImageMultipleSizesInMemory()
		.diskCacheFileNameGenerator(new Md5FileNameGenerator())
		.diskCacheSize(50 * 1024 * 1024) // 50 Mb
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		.writeDebugLogs() // Remove for release app
		.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
	

	@Override
	public int getCount() {
		if(contactList.size()<10)
		{
			count = contactList.size();
		}else{
			count = contactList.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ∫‰»¶¥ı ∞¥√º∫Øºˆ º±æ
		final SignOneViewHolder viewHolder;
		
		if(convertView==null){
			convertView = inf.inflate(R.layout.adpater_sign_one, null);
			viewHolder = new SignOneViewHolder();
			viewHolder.icon = (ImageView)convertView.findViewById(R.id.sign_icon);
			viewHolder.description = (TextView)convertView.findViewById(R.id.sign_description);
			viewHolder.korName = (TextView)convertView.findViewById(R.id.sign_korName);
			
			convertView.setTag(viewHolder);
		}
		
		// ƒ≥Ω√µ» ∫‰∞° ¿÷¿ª ∞ÊøÏ ¿˙¿Âµ» ∫‰»¶¥ı ªÁøÎ
		else
		{
			viewHolder = (SignOneViewHolder)convertView.getTag();
		}
		viewHolder.description.setText(contactList.get(position).get(FragmentSignOne.TAG_DESCRIPTION));
		viewHolder.korName.setText(contactList.get(position).get(FragmentSignOne.TAG_KORNAME));
		
		ImageLoader.getInstance().displayImage(
				contactList.get(position).get(FragmentSignOne.TAG_IMG), viewHolder.icon, options);

		
		return convertView;
	}
}
