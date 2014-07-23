package com.spconger.Lists;

import java.util.ArrayList;

/*
 * This class keeps an array list of
 * Items. It contains methods for adding
 * an item to the list, returning a single
 * item based on a name and returning the whole
 * list of items
 */

public class ItemList {
	ArrayList<Item> items;
	
	public ItemList()
	{
		//initialize the arraylist in the constructor
		items = new ArrayList<Item>();
	}
	
	//add an item to the arrayList
	public void addItem(Item i){
		items.add(i);
	}
	
	//get the list of items
	public ArrayList<Item> getItems(){
		return items;
	}
	
	//get a single item
	public Item getItem(String name){
		Item myItem=null;
		for(Item i: items){
			if(i.getName().equals(name)){
				myItem=i;
			}
		
		}
		return myItem;
	}
}
