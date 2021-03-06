package com.aj3.kiss.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.aj3.kiss.R;
import com.aj3.kiss.R.id;
import com.aj3.kiss.models.ListItem;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
		
		Collections.sort(items, new Comparator<ListItem>() {
			public int compare(ListItem left, ListItem right)  {
				return left.getItem().getName().compareTo(right.getItem().getName()); // The order depends on the direction of sorting.
			}
		});
		
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

			/**
			 * On long press give the option to delete the item
			 */
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				showDeleteDialog(listItems.get(arg2));
				return false;
			}
		 }); 
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			/**
			 * On short press show more information about the item
			 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				showInfoDialog(listItems.get(arg2));
			}
		 }); 
		
	}

	/**
	 * Show and Android dialog with more information about the selected item
	 * @param listItem
	 */
	protected void showInfoDialog(final ListItem listItem) {
		// Create an instance of the dialog fragment and show it
		new AlertDialog.Builder(this)
		.setTitle(listItem.getItem().getName())
		.setMessage("You have " + listItem.getQuantity() + " " + listItem.getItem().getUnit().getName())
		.setPositiveButton("Move", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				showMoveDialog(listItem);
			}
		})
		.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				// do nothing
			}
		})
		.show();
		
	}
	/**
	 * Show the dialog to confirm that the item is to be moved to the other list
	 * @param listItem
	 */
	protected abstract void showMoveDialog(ListItem listItem) ;

	/**
	 * Show a dialog confirming that the user wants to delete this ListItem
	 * @param li
	 */
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

	/**
	 * Remove the item from the list
	 * @param li
	 */
	protected abstract void deleteItem(ListItem li);
}
