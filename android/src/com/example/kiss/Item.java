package com.example.kiss;

public class Item {
	public static final int NO_ID = -1;
	private int id;
	private String name;
	private Category category;
	private String unit;
	
	public Item(int id, String name, Category category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}
	
	public Item(String name, Category category) {
		this.id = NO_ID;
		this.name = name;
		this.category = category;
	}
	
	public Item() {
		this.id = NO_ID;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}	
}
