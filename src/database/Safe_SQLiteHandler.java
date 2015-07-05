package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Safe_SQLiteHandler {
	Safe_SQLiteOpenHandler helper;
	SQLiteDatabase db;

	// 생성자
	public Safe_SQLiteHandler(Context ctx) {
		helper = new Safe_SQLiteOpenHandler(ctx, "safe.sqlite", null, 1);
	}

	// 데이터베이스 open
	public static Safe_SQLiteHandler open(Context ctx) {
		return new Safe_SQLiteHandler(ctx);
	}

	// 데이터베이스 close
	public void close() {
		helper.close();
	}
	
	public void insert(String sc_confirmNum, String sp_model_str,  String sb_makingCountry, int type){
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("sc_confirmNum", sc_confirmNum);
		values.put("sp_model_str", sp_model_str);
		values.put("sb_makingCountry", sb_makingCountry);
		values.put("type", type);
		
		db.insert("safe_bucket", null, values);
	}
	
	public void deleteAll(){
		db = helper.getWritableDatabase();
		db.delete("safe_bucket", null, null);
	}
	
	public Cursor select(){
		db = helper.getReadableDatabase();
		Cursor c = db.query("safe_bucket", null, null, null, null, null, "no ASC");
		
		return c;
	}
	
	public void delete(String sc_confirmNum, String sp_model_str, String sb_makingCountry){
		db = helper.getWritableDatabase();
		db.delete("safe_bucket", "sc_confirmNum=? and sp_model_str=? and sb_makingCountry=?", 
				new String[]{sc_confirmNum.toString(), sp_model_str.toString(), sb_makingCountry.toString()});
	}
}
