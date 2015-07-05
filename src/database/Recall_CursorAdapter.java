package database;

import info.project.datapotal.R;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class Recall_CursorAdapter extends CursorAdapter {

	LayoutInflater inflater;
	TextView txtTitle;
	ImageView imgIcon;
	DisplayImageOptions options;
	ImageLoaderConfiguration config;

	public Recall_CursorAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// 초기설정
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.loading)
				.showImageForEmptyUri(R.drawable.error)
				.showImageOnFail(R.drawable.loading_fail).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024)
				// 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {

		return inflater.inflate(R.layout.bookmark_one_adapter, parent, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		txtTitle = (TextView) view.findViewById(R.id.bucket_txt);
		imgIcon = (ImageView) view.findViewById(R.id.bucket_img);
		
		
		String model = cursor.getString(cursor.getColumnIndex("model"));
		String linkURL = cursor.getString(cursor.getColumnIndex("linkurl"));
		
		if(linkURL.equals("null")){
			linkURL = "";
		}
		
		ImageLoader.getInstance().displayImage(linkURL.toString(), imgIcon, options);
		txtTitle.setText(model);

	}

}
