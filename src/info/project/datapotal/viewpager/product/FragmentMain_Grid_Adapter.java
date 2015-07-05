package info.project.datapotal.viewpager.product;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.sign.FragmentSignOne;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentMain_Grid_Adapter extends BaseAdapter {

	Context mContext;
	LayoutInflater inflater;
	int layout;
	DisplayImageOptions options;
	ImageLoaderConfiguration config;

	public FragmentMain_Grid_Adapter(Context c, int layout) {
		mContext = c;
		this.layout = layout;

		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		// 초기설정
		options = new DisplayImageOptions.Builder()
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
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Image.length;
	}

	@Override
	public Object getItem(int arg0) {
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
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = inflater.inflate(layout, null);
		}
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.frag_grid_image);
		TextView textView = (TextView) convertView
				.findViewById(R.id.frag_grid_text);

		ImageLoader.getInstance().displayImage(
				Image[position], imageView, options);
		textView.setText(Text[position]);

		return convertView;
	}

	private String[] Image = { "drawable://" + R.drawable.a,
			"drawable://" + R.drawable.b, "drawable://" + R.drawable.c };

	private String[] Text = { "인증제품 검색 주의사항 입니다.", "리콜제품 검색 방법 입니다.",
			"리콜제품 검색 주의사항 입니다." };
}