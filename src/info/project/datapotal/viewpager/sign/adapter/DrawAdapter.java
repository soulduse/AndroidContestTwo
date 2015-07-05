package info.project.datapotal.viewpager.sign.adapter;

import info.project.datapotal.R;
import info.project.datapotal.viewpager.sign.vo.DrawMenu;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawAdapter extends BaseAdapter{
	
	LayoutInflater inf;
	Context con;
	ArrayList<DrawMenu> drawItem;
	
	public DrawAdapter(Context con, ArrayList<DrawMenu> drawItem)
	{
		this.con =con;
		this.drawItem = drawItem;
		inf = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return drawItem.size();
	}

	@Override
	public Object getItem(int position) {
		return drawItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null)
		{
			convertView = inf.inflate(R.layout.drawer_list_item, null);
		}
		ImageView imgView = (ImageView)convertView.findViewById(R.id.icon);
		TextView txtView = (TextView)convertView.findViewById(R.id.title);
		DrawMenu dm = drawItem.get(position);
		
		imgView.setImageResource(dm.getImage());
		txtView.setText(dm.getMenu());
		
		return convertView;
	}

}
