package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Sign_SQLiteOpenHandler extends SQLiteOpenHelper{
	
	public Sign_SQLiteOpenHandler(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	//테이블생성
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table sign_bucket (_id integer, no integer primary key autoincrement, " +
				"korname text, picseqno text, filename text)";
		db.execSQL(sql);
	}

	//테이블삭제
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "drop table if exists student";
		db.execSQL(sql);
		
		onCreate(db);
	}
}
