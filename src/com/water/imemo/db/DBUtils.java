package com.water.imemo.db;

import java.util.ArrayList;
import java.util.List;

import com.water.imemo.utils.Constants;
import com.water.imemo.utils.MemoApp;

import android.database.sqlite.SQLiteDatabase;

public class DBUtils {
	
	public static final String DB_NAME = "MyMemo";
	
	private SQLiteDatabase sqLiteDatabase;
	
	private static DBUtils instance;
	
	private List<DBTableInterface> tableList;
	
	public static DBUtils getInstance(){
		instance = new DBUtils();
		return instance;
	}
	
	private DBUtils(){
		DBHelper helper = new DBHelper(MemoApp.getInstance(), DB_NAME);
		sqLiteDatabase = helper.getWritableDatabase();
		tableList = new ArrayList<DBTableInterface>();
		tableList.add(new TableMyMemo());
	}
	
	public SQLiteDatabase getDataBase(){
		return sqLiteDatabase;
	}
	
	public void initDB(){
		
		for(DBTableInterface table:tableList){
			table.onInit(getDataBase());
		}
		
		int oldVersion = MemoApp.getInstance().getInt(Constants.DB_VERSION,1);
		if(DBHelper.VERSION != oldVersion){
			for(DBTableInterface table:tableList){
				table.onUpgrade(getDataBase(), DBHelper.VERSION, oldVersion);
			}
		}
	}
	
	public TableMyMemo getMyMemoDB(){
		for(DBTableInterface inter:tableList){
			if(inter instanceof TableMyMemo){
				return (TableMyMemo) inter;
			}
		}
		return new TableMyMemo();
	}
}
