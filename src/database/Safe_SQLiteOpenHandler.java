package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class Safe_SQLiteOpenHandler extends SQLiteOpenHelper {
	public Safe_SQLiteOpenHandler(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	// 테이블생성
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table safe_bucket (_id integer, no integer primary key autoincrement, "
				+ "sc_confirmNum text, sp_model_str text, sb_makingCountry text, type integer)";
		db.execSQL(sql);
	}

	// 테이블삭제
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "drop table if exists student";
		db.execSQL(sql);

		onCreate(db);
	}
}
