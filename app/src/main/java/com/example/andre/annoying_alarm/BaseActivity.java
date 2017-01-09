package com.example.andre.annoying_alarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.TextView;

import com.example.andre.annoying_alarm.preferences.AlarmPreferencesActivity;
import com.example.andre.annoying_alarm.service.AlarmServiceBroadcastReciever;

import java.lang.reflect.Field;

public abstract class BaseActivity  extends ActionBarActivity implements android.view.View.OnClickListener {

	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception ex) {
			// Ignore
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String url = null;
		Intent intent = null;
		switch (item.getItemId()) {
			case R.id.menu_item_new:
				Intent newMath = new Intent(this, AlarmPreferencesActivity.class);
				startActivity(newMath);
				break;

			//case R.id.menu_item_new_math:
			//	Intent newAlarmIntentMath = new Intent(this, AlarmPreferencesActivity.class);
			//	startActivity(newAlarmIntentMath);
			//	;
			// case R.id.menu_item_new_puzzle:
			//	Intent newAlarmIntentPuzzle = new Intent(this, AlarmPreferencesActivityPuzzle.class);
			//		startActivity(newAlarmIntentPuzzle);
			//	break;


		}
		return super.onOptionsItemSelected(item);
	}

	protected void callMathAlarmScheduleService() {
		Intent mathAlarmServiceIntent = new Intent(this, AlarmServiceBroadcastReciever.class);
		sendBroadcast(mathAlarmServiceIntent, null);
	}
}
