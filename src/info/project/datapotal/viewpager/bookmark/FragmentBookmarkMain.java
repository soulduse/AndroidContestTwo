package info.project.datapotal.viewpager.bookmark;

import info.project.datapotal.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class FragmentBookmarkMain extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.bookmark_main, container,
				false);
		try {
			ImageView img1 = (ImageView) rootView.findViewById(R.id.bookmark_main_img1);
			img1.setImageBitmap(loadBackgroundsBitmap(getResources(), R.drawable.bookmark_main1, getActivity()));

			ImageView img2 = (ImageView) rootView.findViewById(R.id.bookmark_main_img2);
			img2.setImageBitmap(loadBackgroundsBitmap(getResources(), R.drawable.bookmark_main2, getActivity()));

			ImageView img3 = (ImageView) rootView.findViewById(R.id.bookmark_main_img3);
			img3.setImageBitmap(loadBackgroundsBitmap(getResources(), R.drawable.bookmark_main3, getActivity()));

			ImageView img4 = (ImageView) rootView.findViewById(R.id.bookmark_main_img4);
			img4.setImageBitmap(loadBackgroundsBitmap(getResources(), R.drawable.bookmark_main4, getActivity()));

			ImageView img5 = (ImageView) rootView.findViewById(R.id.bookmark_main_img5);
			img5.setImageBitmap(loadBackgroundsBitmap(getResources(), R.drawable.bookmark_main5, getActivity()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rootView;
	}

	public static Bitmap loadBackgroundsBitmap(Resources res, int id, Context context
			) throws Exception, OutOfMemoryError {

		// 폰의 화면 사이즈를 구한다.
		Point p = new Point();
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		display.getSize(p);
		int displayWidth = p.x;
		int displayHeight = p.y;

		// 읽어들일 이미지의 사이즈를 구한다.
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Config.RGB_565;
		options.inJustDecodeBounds = true;
		//BitmapFactory.decodeFile(imgFilePath, options);
		BitmapFactory.decodeResource(res, id, options);

		// 화면 사이즈에 가장 근접하는 이미지의 리스케일 사이즈를 구한다.
		// 리스케일의 사이즈는 짝수로 지정한다. (이미지 손실을 최소화하기 위함.)
		float widthScale = options.outWidth / displayWidth;
		float heightScale = options.outHeight / displayHeight;
		float scale = widthScale > heightScale ? widthScale : heightScale;

		if (scale >= 8) {
			options.inSampleSize = 8;
		} else if (scale >= 6) {
			options.inSampleSize = 6;
		} else if (scale >= 4) {
			options.inSampleSize = 4;
		} else if (scale >= 2) {
			options.inSampleSize = 2;
		} else {
			options.inSampleSize = 1;
		}

		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeResource(res, id, options);
	}
}

