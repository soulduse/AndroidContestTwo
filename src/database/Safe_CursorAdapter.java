package database;

import info.project.datapotal.R;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class Safe_CursorAdapter extends CursorAdapter {
	
	LayoutInflater inflater;
	TextView txt_sb_makingCountry,txt_sp_model_str,txt_sc_confirmNum;
	
	public Safe_CursorAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {

		return inflater.inflate(R.layout.bookmark_two_adapter, parent, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		txt_sb_makingCountry = (TextView) view.findViewById(R.id.sb_bookmark_makingCountry);
		txt_sp_model_str = (TextView) view.findViewById(R.id.sp_bookmark_model_str);
		txt_sc_confirmNum = (TextView) view.findViewById(R.id.sc_bookmark_confirmNum);

		String sb_makingCountry = cursor.getString(cursor.getColumnIndex("sb_makingCountry"));
		String sp_model_str = cursor.getString(cursor.getColumnIndex("sp_model_str"));
		String sc_confirmNum = cursor.getString(cursor.getColumnIndex("sc_confirmNum"));

		txt_sb_makingCountry.setText(sb_makingCountry);
		txt_sp_model_str.setText(sp_model_str);
		txt_sc_confirmNum.setText(sc_confirmNum);

	}
}
