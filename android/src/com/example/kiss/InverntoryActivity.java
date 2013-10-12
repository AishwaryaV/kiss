package com.example.kiss;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class InverntoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent = getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inverntory);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inverntory, menu);
		return true;
	}
	
	public void addItemToInventory(View view){
		EditText name = (EditText)findViewById(R.id.editText1);
		EditText qnt = (EditText)findViewById(R.id.editText2);
		AutoCompleteTextView catagory = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		
		Item item = new Item();
		item.setName(name.getText().toString());
		item.setQuantity(Double.valueOf(qnt.getText().toString()));
		item.setCatagory(catagory.getText().toString());
		
		Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_LONG).show();
	}

}
