package com.aj3.kiss.activities;

import java.util.ArrayList;
import java.util.List;

import com.aj3.kiss.R;
import com.aj3.kiss.R.id;
import com.aj3.kiss.models.ListItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

public abstract class ItemListActivity extends Activity {
	public static final String NAME = "itemList";

	protected List<ListItem> listItems;
	protected ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listItems = new ArrayList<ListItem>();
	}
	
	/** Function adds a list of ListItems on the page layout
	 * in a listView named 'list'
	 * @param items
	 */
	protected void displayList(List<ListItem> items) {
		this.listItems = items;
		// Get ListView object from xml
		listView = (ListView) findViewById(R.id.list);

		List<String> values = new ArrayList<String>();
		for (ListItem li : listItems) {
			values.add(li.toString());
		}
		
		
		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		  android.R.layout.simple_list_item_1, android.R.id.text1, values);
	   

		// Assign adapter to ListView
		listView.setAdapter(adapter); 
		
		//ListView Item Click Listener;
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				showDeleteDialog(listItems.get(arg2));
				return false;
			}
		 }); 
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				showInfoDialog(listItems.get(arg2));
			}
		 }); 
		
	}

	protected void showInfoDialog(final ListItem listItem) {
		// Create an instance of the dialog fragment and show it
		new AlertDialog.Builder(this)
		.setTitle(listItem.getItem().getName())
		.setMessage("You have " + listItem.getQuantity() + " " + listItem.getItem().getUnit().getName())
		.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				showEditDialog(listItem);
			}
		})
		.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				// do nothing
			}
		})
		.show();
		
	}
	
	protected void showEditDialog(final ListItem li) {
		final Dialog d = new Dialog(this);
		d.setTitle("NumberPicker");
		d.setContentView(R.layout.dialog_edit_quantity);
		Button b1 = (Button) d.findViewById(R.id.button1);
		Button b2 = (Button) d.findViewById(R.id.button2);
		final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
		np.setMaxValue(100);
		np.setMinValue(0);
		np.setValue((int)li.getQuantity());
		np.setWrapSelectorWheel(false);
//		np.setOnValueChangedListener(this);
		b1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				li.setQuantity(np.getValue());
				d.dismiss();
			}
		});
		b2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				d.dismiss();
			}
		});
		
		d.show();
	}
	
    protected abstract void showMoveDialog(ListItem listItem) ;

	public void showDeleteDialog(final ListItem li) {
        // Create an instance of the dialog fragment and show it
    	new AlertDialog.Builder(this)
        .setTitle("Delete entry")
        .setMessage("Are you sure you want to delete this entry?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                deleteItem(li);
            }
         })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                // do nothing
            }
         })
         .show();
    }

	protected abstract void deleteItem(ListItem li);
}
