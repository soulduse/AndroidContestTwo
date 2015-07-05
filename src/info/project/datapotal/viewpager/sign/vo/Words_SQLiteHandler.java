package info.project.datapotal.viewpager.sign.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Words_SQLiteHandler {
	Words_SQLiteOpenHandler helper;
	SQLiteDatabase db;

	// 생성자
	public Words_SQLiteHandler(Context ctx) {
		helper = new Words_SQLiteOpenHandler(ctx, "words.sqlite", null, 1);
	}

	// 데이터베이스 open
	public static Words_SQLiteHandler open(Context ctx) {
		return new Words_SQLiteHandler(ctx);
	}

	// 데이터베이스 close
	public void close() {
		helper.close();
	}
	
	public void insert(String korname){
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("korname", korname);
		
		db.insert("sign_words", null, values);
	}
	
	public Cursor select(String key){
		db = helper.getReadableDatabase();
		if(key==null ||key.trim().equals("")){
			Cursor c = selectAll();
			return c;
		}
		Log.i("MyTag","key:"+key);
		//Cursor c = db.query("sign_words", null, "korname like '%?%'", new String[]{key.toString()}, null, null, null);
		String sql = "select * from sign_words where korname like '%?%';";
		sql = sql.replaceAll("[?]", key);
		Log.i("MyTag","sql:"+sql);
		Cursor c = db.rawQuery(sql, null);
		return c;
	}
	
	public Cursor selectAll(){
		db = helper.getReadableDatabase();
		Cursor c = db.query("sign_words", null, null, null, null, null, null, null);
		
		return c;
	}
	
	public boolean getCount(){
		db = helper.getReadableDatabase();
		
		Cursor c = db.query("sqlite_master", new String[]{"'korname'"},
				"name=?", new String[]{"sign_words"}, null, null, null);
		if(c.getCount()>0){
			return true;
		}else{
			return false;
		}
	}
}
