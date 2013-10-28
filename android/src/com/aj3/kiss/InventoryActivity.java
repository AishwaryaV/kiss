package com.aj3.kiss;

import android.os.Bundle;
import android.view.Menu;
import com.aj3.kiss.R;
import java.util.List;

public class InventoryActivity extends ItemListActivity {
	public static final String NAME = "inventory";

//	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);

		DatabaseHelper db = new DatabaseHelper(this);
		List<ListItem> listItems = db.getInventory();
		db.close();
		
		this.displayList(listItems);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inventory, menu);
		return true;
	}
}
