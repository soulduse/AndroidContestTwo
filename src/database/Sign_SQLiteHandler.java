package database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Sign_SQLiteHandler {
	Sign_SQLiteOpenHandler helper;
	SQLiteDatabase db;
	
	//생성자
	public Sign_SQLiteHandler(Context ctx){
		helper = new Sign_SQLiteOpenHandler(ctx, "sign.sqlite", null, 1);
	}
	
	//데이터베이스 open
	public static Sign_SQLiteHandler open(Context ctx){
		return new Sign_SQLiteHandler(ctx);
	}
	
	//데이터베이스 close
	public void close(){
		helper.close();
	}
	
	public void insert(String korName, String picseqno, String fileName){
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("korname", korName);
		values.put("picseqno", picseqno);
		values.put("filename", fileName);
		
		db.insert("sign_bucket", null, values);
	}
	
	public void delete(String picseqno){
		db = helper.getWritableDatabase();
		db.delete("sign_bucket", "picseqno=?", new String[]{picseqno.toString()});
	}
	
	public void deleteAll(){
		db = helper.getWritableDatabase();
		db.delete("sign_bucket", null, null);
	}
	
	public Cursor select(){
		db = helper.getReadableDatabase();
		Cursor c = db.query("sign_bucket", null, null, null, null, null, "no ASC");
		
		return c;
	}
	
	public Cursor find(String picseqno){
		db = helper.getReadableDatabase();
		//Cursor c = db.query("sign_bucket", null, "picseqno=?", new String[]{picseqno}, null, null, null);
		String sql = "select * from sign_bucket where picseqno=?;";
		Cursor c = db.rawQuery(sql, new String[]{picseqno.toString()});
		return c;
	}
}
