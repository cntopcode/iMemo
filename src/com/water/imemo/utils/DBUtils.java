package com.water.imemo.utils;

import android.database.sqlite.SQLiteDatabase;

public class DBUtils {
	
	public static final String DB_NAME = "MyMemo";
	
	private SQLiteDatabase sqLiteDatabase;
	
	private static DBUtils instance;
	
	public static DBUtils getInstance(){
		instance = new DBUtils();
		return instance;
	}
	
	private DBUtils(){
		DBHelper helper = new DBHelper(MemoApp.getInstance(), DB_NAME);
		sqLiteDatabase = helper.getWritableDatabase();
	}
	
	public SQLiteDatabase getDataBase(){
		return sqLiteDatabase;
	}
	
}
