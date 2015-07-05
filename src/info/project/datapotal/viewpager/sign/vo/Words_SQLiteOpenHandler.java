package info.project.datapotal.viewpager.sign.vo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Words_SQLiteOpenHandler extends SQLiteOpenHelper{

	public Words_SQLiteOpenHandler(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table sign_words (_id integer, no integer primary key autoincrement, " +
				"korname text)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "drop table if exists student";
		db.execSQL(sql);
		
		onCreate(db);
	}

}
