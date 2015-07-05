package database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Recall_SQLiteHandler {
	Recall_SQLiteOpenHandler helper;
	SQLiteDatabase db;
	
	//생성자
	public Recall_SQLiteHandler(Context ctx){
		helper = new Recall_SQLiteOpenHandler(ctx, "recall.sqlite", null, 1);
	}
	
	//데이터베이스 open
	public static Recall_SQLiteHandler open(Context ctx){
		return new Recall_SQLiteHandler(ctx);
	}
	
	//데이터베이스 close
	public void close(){
		helper.close();
	}
	
	public void insert(String model, String linkurl, String productname){
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("model", model);
		values.put("linkurl", linkurl);
		values.put("productname", productname);
		
		db.insert("recall_bucket", null, values);
	}
	
	public void delete(String model, String linkurl, String productname){
		db = helper.getWritableDatabase();
		db.delete("recall_bucket", "model=? and linkurl=? and productname=?",
				new String[]{model.toString(), linkurl.toString(), productname.toString()});
	}
	
	public void deleteAll(){
		db = helper.getWritableDatabase();
		db.delete("recall_bucket", null, null);
	}
	
	public Cursor select(){
		db = helper.getReadableDatabase();
		Cursor c = db.query("recall_bucket", null, null, null, null, null, "_id ASC");
		
		return c;
	}
	
	public Cursor find(String picseqno){
		db = helper.getReadableDatabase();
		//Cursor c = db.query("sign_bucket", null, "picseqno=?", new String[]{picseqno}, null, null, null);
		String sql = "select * from recall_bucket where picseqno=?;";
		Cursor c = db.rawQuery(sql, new String[]{picseqno.toString()});
		return c;
	}
}
