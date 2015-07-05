package info.project.datapotal.viewpager.sign.adapter;

import info.project.datapotal.R;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ExampleAdapter extends CursorAdapter {

	TextView textView;
	ArrayList<String> list;
	int position = 0;
	boolean mLock = false;
	
	ArrayList<String> compareList;
	
	boolean isMade;
	int count=0;

	public ExampleAdapter(Context ctx, Cursor cursor, ArrayList<String> list) {
		super(ctx, cursor, true);
		this.list = list;
		compareList = new ArrayList<String>();
		cursor.moveToFirst();
		isMade = false;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public void bindView(View arg0, Context arg1, Cursor cursor) {
		/*boolean flag = true;
		
		for (int i = 0; i < list.size(); i++) {
			Log.i("MyTag", "list.get(" + i + "):" + list.get(i));
		}*/
		/*for(int i=0; i<compareList.size(); i++){
			if(compareList.get(i).equals(list.get(cursor.getPosition()))){
				flag = false;
			}
		}
		if(flag==true){
			textView.setText(list.get(cursor.getPosition()));
			compareList.add(list.get(cursor.getPosition()));
			Log.i("MyTag","cursur post:"+cursor.getPosition());
		}*/
		textView.setText(list.get(cursor.getPosition()));
		
	}

	@Override
	public View newView(Context context, Cursor arg1, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.item,parent,false);

		textView = (TextView) view.findViewById(R.id.text);
		
		return view;
	}

}
