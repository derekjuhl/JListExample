package com.spconger.Lists;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Mainform {

	/*
	 * This is the class that designs the form
	 * It consists of several nested panels.
	 * First there is a border panel.
	 * Inside the border panel there is a scrollPane
	 * that contains a List object to list items.
	 * The scroll pane is hooked to the WEST border.
	 * A grid panel with two labels, two text boxes
	 * and a button is hooked to the EAST.
	 * This panel allows a user to enter the items.
	 * After each item the text boxes clear for the 
	 * next entry. Along the bottom (SOUTH) is
	 * a button panel that uses flow layout.
	 * One button populates the list with items
	 * entered in the form above. The other
	 * Exits. The list is attached to a selection
	 * listener. When one of the items in the list
	 * is selected an option pane pops up
	 * with that items information.
	 * Steve Conger 7/22/2014
	 *
	 */
	
	//declare all the form objexts
	private JFrame frame;
	private JPanel panel;
	private JList<String> list;
	private JButton button;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JLabel label;
	private JButton btnList;
	private ItemList iList; //declare the ItemList class
	private JScrollPane scrollPane;
	private JPanel borderPanel;
	private JPanel outerGridPanel;
	private JPanel innerGrid;
	private JPanel buttonPanel;
	private JButton btnExit;
	
	public Mainform(){
		//Initialize the form and the ItemList 
		//in the constructor
		createFrame();
		iList = new ItemList();
	}
	
	//create the frame
	private void createFrame(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100,100,500,200);
	
		frame.setTitle("Item List");
		//load the boarder panel
		frame.add(createBorderPanel());
		frame.setVisible(true);
		
	}
	
	//create the border panel
	private JPanel createBorderPanel(){
		borderPanel = new JPanel();
		borderPanel.setLayout(new BorderLayout());
		
		//load the other panels and assign them their positions
		borderPanel.add(createScrollPane(), BorderLayout.WEST);
		borderPanel.add(createInnerGridPanel(),BorderLayout.CENTER);
		borderPanel.add(createButtonPanel(), BorderLayout.SOUTH);
		return borderPanel;
	}
	
	//create the scroll pane
	private JScrollPane createScrollPane(){
		list = new JList<String>();
		//add the selection listener to the list
		list.addListSelectionListener(new SelectionListener());
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(20, 20, 100, 200);
	
		
		return scrollPane;
	}
	
	
	
	//create the inner grid panel
	private JPanel createInnerGridPanel(){
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2,5,5));
		panel.setBounds(20, 20, 150, 200);
		
		lblName=new JLabel("Name");
		
		txtName=new JTextField();
		lblPrice = new JLabel("Price");
		
		txtPrice = new JTextField();
		button=new JButton("add");
		button.addActionListener(new ButtonListener());
		
		
		
		
	
		panel.add(lblName);
		panel.add(txtName);
		panel.add(lblPrice);
		panel.add(txtPrice);
		panel.add(button);
		
		
		return panel;
	}
	
	//create the button panel
	private JPanel createButtonPanel(){
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(Color.gray);
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ExitListener());
		
		btnList = new JButton("Fill list");
		btnList.addActionListener(new FillListListener());
		buttonPanel.add(btnList);
		buttonPanel.add(btnExit);
		return buttonPanel;
		
	}
	
	//This listener processes the add button
	//it takes the contents of the text fields
	//and assigns them to a new Item object
	//then passes that item to the ItemList
	//to be added to the array list
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//create a new Item
			Item i = new Item();
			//assign the values to the item
			i.setName(txtName.getText());
			i.setPrice(Double.parseDouble(txtPrice.getText()));
			//pass it to the ItemList to be added to the ArrayList
			iList.addItem(i);
			//clear the texts for the next entry
			txtName.setText("");
			txtPrice.setText("");
			
			
		}
		
	}
	//this class handles the fillList button
	// It calls the ItemList class method getItems
	//which returns the ArrayList with all the items
	//then it loops through them and writes the name
	//to the list object
	private class FillListListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//get the list of items
			ArrayList<Item>items=iList.getItems();
			//set the default model for the list object
			//our list will contain Strings
			DefaultListModel<String> model = new DefaultListModel<String>();
			//loop through the arrayList
			for(Item i : items){
				//add the item names to the list
				model.addElement(i.getName());
			}
			list.setModel(model);
		}
		
		
				
			}
	private class ExitListener implements ActionListener{
		//exit the program
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			
		}
		
	}
	//This method listens for changes in the selection in the List object
	//IT fires whenever you select an item in the list
	//and displays the item selected in a JOPtionPane
	private class SelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			 
					if (list.getSelectedIndex() != -1) {
				        //No selection, disable fire button.
				           String name=list.getSelectedValue();
				        
				          
				           Item i = iList.getItem(name);
				          JOptionPane.showMessageDialog(null,  i.getName() + " " + Double.toString(i.getPrice()),"Info",0);
				           

				} 
				   

			
		}
		
	}
}
