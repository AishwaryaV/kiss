package com.example.kiss;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DatabaseHelper db = new DatabaseHelper(this);
		db.resetDatabase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void jumpToInventory(View view){
		Intent intent = new Intent(this, InventoryActivity.class);
		startActivity(intent);
	}
	
	public void jumpToGrocery(View view){
		Intent intent = new Intent(this, GroceryActivity.class);
		startActivity(intent);
	}

}
