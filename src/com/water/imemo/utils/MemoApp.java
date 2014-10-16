package com.water.imemo.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MemoApp extends Application {
	
	private SharedPreferences preference;
	
	private static MemoApp instance;
	
	public static MemoApp getInstance(){
		return instance;
	}
	
	static final String NAME = "Memo";
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		preference = getSharedPreferences(NAME, Context.MODE_PRIVATE);
	}

	public String getString(String key,String def){
		return preference.getString(key, def);
	}
	
	public void setString(String key,String value){
		preference.edit().putString(key, value).commit();
	}
	
	public Long getLong(String key){
		return preference.getLong(key, 0);
	}

	public void setLong(String key, long value) {
		preference.edit().putLong(key, value).commit();
	}
	
}
