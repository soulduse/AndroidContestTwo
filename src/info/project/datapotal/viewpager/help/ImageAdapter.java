/*
 * Copyright (C) 2011 Patrik Åkerfeldt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.project.datapotal.viewpager.help;

import info.project.datapotal.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private int pageChange;
	private int pageCount;
	
	private LayoutInflater mInflater;
	private static final int[] product = { R.drawable.db_a,
			R.drawable.db_b, R.drawable.db_c,
			R.drawable.db_d, R.drawable.db_e,
			R.drawable.db_f, R.drawable.db_g, R.drawable.db_h};

	private static final int[] recall = { R.drawable.recall_help1,
			R.drawable.recall_help2, R.drawable.recall_help3,
			R.drawable.recall_help4, R.drawable.recall_help5,
			R.drawable.recall_help6, R.drawable.recall_help7,
			R.drawable.recall_help8 };

	private static final int[] picture = { R.drawable.help_picture1,
			R.drawable.help_picture2, R.drawable.help_picture3,
			R.drawable.help_picture4, R.drawable.help_picture5,
			R.drawable.help_picture6, R.drawable.help_picture7 };

	public ImageAdapter(Context context, int pageChange) {
		this.pageChange = pageChange;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if(pageChange==1)
		{
			pageCount = product.length;
		}
		if(pageChange==2)
		{
			pageCount = recall.length;
		}
		if(pageChange==3)
		{
			pageCount = picture.length;
		}
		return pageCount;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.image_item, null);
		}
		if(pageChange==1)
		{
			((ImageView) convertView.findViewById(R.id.imgView))
			.setImageResource(product[position]);
		}
		if(pageChange==2)
		{
			((ImageView) convertView.findViewById(R.id.imgView))
			.setImageResource(recall[position]);
		}
		if(pageChange==3)
		{
			((ImageView) convertView.findViewById(R.id.imgView))
			.setImageResource(picture[position]);
		}
		
		return convertView;
	}
	

}
