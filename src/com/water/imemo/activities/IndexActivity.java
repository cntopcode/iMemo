package com.water.imemo.activities;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.water.imemo.R;
import com.water.imemo.bean.MyMemo;
import com.water.imemo.db.DBUtils;
import com.water.imemo.db.TableMyMemo;
import com.water.imemo.utils.Constants;
import com.water.imemo.utils.MemoApp;

public class IndexActivity extends Activity {
	
	private MemoApp app = MemoApp.getInstance();
	private TimerTask timerTask;
	private final Timer timer = new Timer();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_index);
		
		/*
		Message mesg = new Message();
		mesg.what = 1;
		handler.sendMessage(mesg);
		*/
		DBUtils.getInstance().initDB();
		
		initUI();
		
		if(app.getLong(Constants.APP_FIRST_TIME_INSTALL) == 0){
			app.setLong(Constants.APP_FIRST_TIME_INSTALL,System.currentTimeMillis());
		}
	}

	private void initUI() {
		TableMyMemo table = DBUtils.getInstance().getMyMemoDB();
		List<MyMemo> list = table.getMemo(DBUtils.getInstance().getDataBase(), 0, 5);
		if(list.size() == 0){
			Intent intent = new Intent(this,EditorActivity.class);
			intent.putExtra("openType", Constants.NO_MEMO_START_EDITOR);
			startActivity(intent);
		}else{
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.index, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};
	
}
