import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


// l = line of code
//try to delete dialog.add(new JLabel()) and see what will happen

public class MenuListener implements ActionListener{
	private JFrame frame;
	private ProductsTableModel ptm;
	private int kx, ky;
	private JComboBox<Object> filter;
	private JTable table;
	
	public MenuListener(JFrame frame, JTable table,  JComboBox<Object> filterByGroup,ArrayList<String> groups) {
		this(frame,table,filterByGroup,20,20);
	}
	
	/**
	 * @param frame 
	 * @param main table
	 * @param filterByGroup (to edit items of filter combo box in main frame)
	 * @param kx width coefficient, default = 20
	 * @param ky height coefficient, default = 20
	 */
	public MenuListener(JFrame frame, JTable table, JComboBox<Object> filterByGroup, int kx,int ky) {
		this.frame = frame;
		this.table = table;	
		this.ptm = (ProductsTableModel)table.getModel();
		this.filter = filterByGroup;
		this.kx = kx; 
		this.ky = ky; 
	}

	
	public void actionPerformed(ActionEvent ae) {
		String action = ((AbstractButton) ae.getSource()).getActionCommand();
		// true to manage products, false to manage groups
		boolean productAction = true; 
		switch(action) {
		case "Add New Group" :  productAction = false;  break;
		case "Edit Group" :  	productAction = false; break;
		case "Delete Group" : 	productAction = false; break;
		default: break;
		}
		//if button was clicked
		if(ae.getSource() instanceof JMenuItem ) {
			if(productAction)setComboBoxForProductsWithMenu(action);
			else setComboBoxForGroupsWithMenu(action);
		}
		// if menu item was selected
		else {
			if(productAction)setComboBoxForProductsWithButton(action);
			else setComboBoxForGroupsWithButton(action);

		}
	}
	//make combobox that includes all products, (after menu item was selected)
	private void setComboBoxForProductsWithMenu(String action) {
		JComboBox<String> products = new JComboBox<String>();
		for(int i = 0; i<ptm.getRowCount(); i++) 
		products.addItem((String) ptm.getValueAt(i, "Name"));
		chooseAction(action,products);
	}
	//make combobox that includes only selected product, (after button was clicked)
	private void setComboBoxForProductsWithButton(String action) {
		JComboBox<String> products = new JComboBox<String>();
		int row = table.getSelectedRow();
		String item = (String) ptm.getValueAt(row, "Name");
		products.addItem(item);
		chooseAction(action,products);
	}
	//make combobox that includes all products` groups, (after menu item was selected)
	private void setComboBoxForGroupsWithMenu(String action) {
		Object[] obj =  ptm.getGroupNames().toArray();
		String[] groups = new String[obj.length];
		for(int i =0; i <obj.length; i++) groups[i] = (String)obj[i];
		JComboBox<String> group = new JComboBox<>(groups);
		chooseAction(action, group);
	}
	//make combobox that includes only selected product`s group, (after button was clicked)
	private void setComboBoxForGroupsWithButton(String action) {
		JComboBox<String> group = new JComboBox<String>();
		int row = table.getSelectedRow();
		String item = (String) ptm.getValueAt(row, "Group");
		group.addItem(item);
		chooseAction(action, group);
	}
	
	private void chooseAction(String action, JComboBox<String> products) {
		switch(action) {
		case "Add New Product": addNewProductWithMenu(); break;
		case "Purchase" : makePurchase(); break;
		case "Add" : increaseNumberOfProduct(products); break;
		case "Subtract" : decreaseNumberOfProduct(products); break;
		case "Edit Product" : editProduct(products); break;
		case "Delete Product" : deleteProduct(products); break;
		case "Add New Group" : addNewGroupWithMenu(); break;
		case "Edit Group" : editGroup(products); break;
		case "Delete Group" : deleteGroup(products); break;
		default : break;
		}
	}

	private void increaseNumberOfProduct(JComboBox<String> products) {
		JDialog dialog = new JDialog(frame);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel header = new JLabel("Add"); 
		JLabel numberLab = new JLabel("Number: ");
		JButton submit = new JButton("Confirm");
		
		//create text format to allow only positive integers
				NumberFormat format = NumberFormat.getNumberInstance();
				NumberFormatter formatter = new NumberFormatter(format);
				formatter.setMinimum(0);
				
				JFormattedTextField number = new JFormattedTextField(formatter);
				number.setValue(0);
				
		setChangeNumberOfProductDialog(dialog, header, products, numberLab, number, submit);
		
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Integer a = ((Number)number.getValue()).intValue();
				String name = (String) products.getSelectedItem();
				int row = ptm.getRowIndexByName(name);
				Integer b = (Integer) ptm.getValueAt(row, "Number");
				ptm.setNumber(a+b, row);
				System.out.println("sas");
				dialog.dispose();
			}
	
		});
	}
	private void decreaseNumberOfProduct(JComboBox<String> products) {
		JDialog dialog = new JDialog(frame);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel header = new JLabel("Write down"); 
		JLabel numberLab = new JLabel("Number: ");
		
		JButton submit = new JButton("Confirm");
		
		//create text format to allow only positive integers
		NumberFormat format = NumberFormat.getNumberInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setMinimum(0);
		
		JFormattedTextField number = new JFormattedTextField(formatter);
		number.setValue(0);
		
		setChangeNumberOfProductDialog(dialog, header, products, numberLab, number, submit);//l 164
		calculateMaxNumber(products,formatter); //l 158
		
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				//after button was pressed, calculate new number of product 
				Integer a = ((Number)number.getValue()).intValue();
				String name = (String) products.getSelectedItem();
				int row = ptm.getRowIndexByName(name);
				Integer b = (Integer) ptm.getValueAt(row, "Number");
				ptm.setNumber(b-a, row);
				formatter.setMaximum(b-a);
				number.setValue(0);
			}
	
		});
	}
	private void calculateMaxNumber(JComboBox<String> products,NumberFormatter formatter ) {
		String name = (String) products.getSelectedItem();
		int row = ptm.getRowIndexByName(name);
		int max = ((Integer)ptm.getValueAt(row, "Number")).intValue();
		formatter.setMaximum(max);
	}
	private void setChangeNumberOfProductDialog(
			JDialog dialog, JLabel header, JComboBox<String> products, 
			JLabel numberLab, JFormattedTextField number, JButton submit
			) {
	
		
		dialog.setBounds(100,100 , 20*kx, 12*ky);
		header.setBounds(4*kx,ky,10*kx,ky);
		products.setBounds(4*kx,3*ky,10*kx,ky);
		numberLab.setBounds(4*kx,5*ky,4*kx,ky);
		number.setBounds(8*kx,5*ky,6*kx,ky);
		submit.setBounds(7*kx, 7*ky, 7*kx, ky);
		
		dialog.add(header);
		dialog.add(products);
		dialog.add(numberLab);
		dialog.add(number);
		dialog.add(submit);
		dialog.add(new JLabel());
		dialog.setVisible(true);
	}
	
	private void deleteProduct(JComboBox<String> products2) {
		JDialog dialog = new JDialog(frame);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel header = new JLabel("Delete product"); 
		JComboBox products = new JComboBox(new DefaultComboBoxModel<Object>());
		for(int i = 0; i<products2.getItemCount(); i++ )products.addItem(products2.getItemAt(i));
		JButton submit = new JButton("Confirm");
		
		setEditDialog(dialog,header, products ,submit); //l 247
		
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				String name = (String) products.getSelectedItem();
				//remove chosen product from table
				ptm.removeRow(ptm.getRowIndexByName(name));
				//remove chosen product from combobox
				//(only combobox with Default..Model allows to remove elements )
			((DefaultComboBoxModel<Object>)products.getModel()).removeElement(name);
			dialog.dispose();
			}
	
		});
	}

	private void deleteGroup(JComboBox<String> products) {
		JDialog dialog = new JDialog(frame);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel header = new JLabel("Delete group"); 
		JButton submit = new JButton("Confirm");
		
		setEditDialog(dialog,header, products ,submit); //l 247
		
		dialog.add(header);
		dialog.add(products);
		dialog.add(submit);
		dialog.add(new JLabel());
		dialog.setVisible(true);
		
		submit.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {
				Object item = products.getSelectedItem();	
				//remove chosen group from table
					ptm.removeGroup((String) item);
					//remove chosen group from combobox
					//(only combobox with Default..Model allows to remove elements )
					((DefaultComboBoxModel<String>)products.getModel()).removeElement(item);
					//remove one of groups in filterByName in main frame
					filter.removeItem(item);
				dialog.dispose();
			}
	
		});
	}
	private void setEditDialog(JDialog dialog,JLabel header, JComboBox<String> products,JButton submit) {
		dialog.setBounds(100,100 , 20*kx, 9*ky);
		header.setBounds(4*kx,ky,10*kx,ky);
		products.setBounds(4*kx,3*ky,10*kx,ky);
		submit.setBounds(9*kx, 5*ky, 7*kx, ky);
		
		dialog.add(header);
		dialog.add(products);
		dialog.add(submit);
		dialog.add(new JLabel());
		dialog.setVisible(true);
	}
	
	private void editGroup(JComboBox<String> products) {
			JDialog dialog = new JDialog(frame);
			dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				JLabel header = new JLabel("Edit group"); 
				JLabel groupLab = new JLabel("Group:");
				JLabel editDescLab = new JLabel("Edit Description: ");
				JTextField desc = new JTextField();
				JButton submit = new JButton("Confirm");
				
				showGroupDescription(products,desc); //l 311
				
				dialog.setBounds(100,100 , 20*kx, 14*ky);
				header.setBounds(5*kx,ky , 10*kx, ky);
				groupLab.setBounds(2*kx, 3*ky, 5*kx, ky);
				products.setBounds(7*kx, 3*ky, 10*kx, ky);
				editDescLab.setBounds(2*kx, 5*ky, 5*kx, ky);
				desc.setBounds(2*kx, 7*ky, 15*kx, ky);
				submit.setBounds(10*kx, 9*ky, 7*kx, ky);
				
				dialog.add(header);
				dialog.add(groupLab);
				dialog.add(products);
				dialog.add(editDescLab);
				dialog.add(desc);
				dialog.add(submit);

				dialog.add(new JLabel());
				
				dialog.setVisible(true);
				
				products.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent ie) {
						if(ie.getStateChange() == ItemEvent.DESELECTED) return;
						showGroupDescription(products,desc);//l 311
					}
					
				});
				submit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String description = desc.getText();
						Object groupName = products.getSelectedItem();
					
						ptm.setDescription((String) groupName,description);
						dialog.dispose();
					}
					
				});
	}
	private void showGroupDescription(JComboBox<String> products,JTextField desc) {
		Object groupName = products.getSelectedItem();
		String description = ptm.getGroupDescription((String) groupName);
		desc.setText(description);
	}
	
	private void addNewGroupWithMenu() {
		JDialog dialog = new JDialog(frame);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel header = new JLabel("Create new group"); 
		JTextField group = new JTextField("");
		JButton submit = new JButton("Add New Group");
		
		dialog.setBounds(100,100 , 20*kx, 9*ky);
		header.setBounds(4*kx,ky,10*kx,ky);
		group.setBounds(4*kx,3*ky,10*kx,ky);
		submit.setBounds(9*kx, 5*ky, 7*kx, ky);
		
		dialog.add(header);
		dialog.add(group);
		dialog.add(submit);
		dialog.add(new JLabel());
		dialog.setVisible(true);
		
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String groupName = group.getText();	
				if(groupName.equals("")) return;
					if(ptm.groupExists(groupName)) {
						JDialog warn = new JDialog(dialog,"This product is already exist");
						warn.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						warn.setBounds(200,200,299,0);
						warn.setVisible(true);
						return;
					}
					//add group to filterByName in main frame
					filter.addItem(groupName);
					ptm.addGroup(groupName);
				
			}
	
		});
	}

	private  void editProduct(JComboBox<String> products) {
		JDialog dialog = new JDialog(frame);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			JLabel header = new JLabel("Edit product"); 
			JLabel productLab = new JLabel("Product: ");
			JLabel newNameLab = new JLabel("New name: ");
			JTextField newName = new JTextField();
			JLabel groupLab = new JLabel("Group");
			JComboBox<Object> group = new JComboBox<>(ptm.getGroupNames().toArray());
			JLabel numLab = new JLabel("Number: ");
			
			//create text format to allow only positive integers
			NumberFormat format = NumberFormat.getNumberInstance();
			NumberFormatter formatter = new NumberFormatter(format);
			formatter.setMinimum(0);
			
			JFormattedTextField number = new JFormattedTextField(formatter);
			number.setValue(0);
			JLabel priceLab = new JLabel("Price: ");
			JFormattedTextField price = new JFormattedTextField(formatter);
			JLabel descLab = new JLabel("Description: ");
			JTextField desc = new JTextField("");
			JLabel prodLab = new JLabel("Producer: ");
			JTextField producer = new JTextField("");
			JButton submit = new JButton("Confirm");
			
			setEditProductDialog(
			ptm.getRowIndexByName((String) products.getSelectedItem()),newName, group, desc, producer, number, price	); //l 444
			
			dialog.setBounds(100,100 , 20*kx, 22*ky);
		
			header.setBounds(2*kx, ky, 15*kx,2*ky);
			
			productLab.setBounds(2*kx,3*ky,5*kx,ky);
			newNameLab.setBounds(2*kx, 5*ky, 5*kx,ky);
			groupLab.setBounds(2*kx, 7*ky, 5*kx,ky);
			priceLab.setBounds(2*kx, 9*ky, 5*kx,ky);
			numLab.setBounds(2*kx, 11*ky, 5*kx,ky);
			descLab.setBounds(2*kx, 13*ky, 5*kx,ky);
			prodLab.setBounds(2*kx, 15*ky, 5*kx,ky);
			
			products.setBounds(7*kx,3*ky,10*kx,ky);
			newName.setBounds(7*kx,5*ky,10*kx,ky);
			group.setBounds(7*kx,7*ky,10*kx,ky);
			price.setBounds(7*kx,9*ky,10*kx,ky);
			number.setBounds(7*kx,11*ky,10*kx,ky);
			desc.setBounds(7*kx,13*ky,10*kx,ky);
			producer.setBounds(7*kx,15*ky,10*kx,ky);

			submit.setBounds(10*kx, 17*ky, 7*kx, ky);
			
			dialog.add(header);
			dialog.add(productLab);
			dialog.add(products);
			dialog.add(newNameLab);
			dialog.add(newName);
			dialog.add(groupLab);
			dialog.add(group);
			dialog.add(descLab);
			dialog.add(desc);
			dialog.add(prodLab);
			dialog.add(producer);
			dialog.add(numLab);
			dialog.add(number);
			dialog.add(priceLab);
			dialog.add(price);
			dialog.add(submit);
			dialog.add(new JLabel());
			
			dialog.setVisible(true);
			
			submit.addActionListener(new ActionListener() {


				@Override
				public void actionPerformed(ActionEvent ae) {
					if(ptm.productExist((String) products.getSelectedItem(),newName.getText())) {

						JDialog warn = new JDialog(dialog,"This product is already exist");
						warn.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						warn.setBounds(200,200,300,0);
						warn.setVisible(true);
						return;
					}
					//changes main table row, chosen by name
					Object[] arr = { 
					        		newName.getText(),
					        		(String)group.getSelectedItem(),
					        		desc.getText(),
					        		producer.getText(),
					        		((Number)price.getValue()).intValue(),
					        		((Number)number.getValue()).intValue()
					};
						
					ptm.updateRowByName((String)products.getSelectedItem(), arr);
					dialog.dispose();
				}
				
			});
			products.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ie) {
					if(ie.getStateChange() == ItemEvent.DESELECTED) return;
					int row = ptm.getRowIndexByName((String) ie.getItem());
					setEditProductDialog(row, newName,group, desc, producer, number, price);//l 444
				}});
			
		}
	/**
	 * change info depending on which product was chosen in combobox
	 */
	private void setEditProductDialog(
			int row, JTextField newName,
			JComboBox<Object> group, JTextField desc,
			JTextField producer,JFormattedTextField number, JFormattedTextField price
			) {
		newName.setText((String)ptm.getValueAt(row, "Name"));
		group.setSelectedItem(ptm.getValueAt(row, "Group"));
		desc.setText((String)ptm.getValueAt(row, "Description"));
		producer.setText((String) ptm.getValueAt(row, "Producer"));
		number.setValue(ptm.getValueAt(row, "Number"));
		price.setValue(ptm.getValueAt(row, "Price"));
	}
	
	
	private void addNewProductWithMenu() {
		JDialog dialog = new JDialog(frame);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			JLabel header = new JLabel("Create new product"); 
			JLabel nameLab = new JLabel("Name: ");
			JTextField name = new JTextField("");
			JLabel groupLab = new JLabel("Group");
			JComboBox<Object> group = new JComboBox<>(ptm.getGroupNames().toArray());
			JLabel numLab = new JLabel("Number: ");
			JFormattedTextField number = new JFormattedTextField(NumberFormat.getNumberInstance());
			JLabel priceLab = new JLabel("Price: ");
			JFormattedTextField price = new JFormattedTextField(NumberFormat.getNumberInstance());
			JLabel descLab = new JLabel("Description: ");
			JTextField desc = new JTextField("");
			JLabel prodLab = new JLabel("Producer: ");
			JTextField producer = new JTextField("");
			JButton submit = new JButton("Add New Product");
			number.setValue(0);
			price.setValue(0);
			
			dialog.setBounds(100,100 , 20*kx, 22*ky);
		
			header.setBounds(2*kx, ky, 15*kx,2*ky);
			
			nameLab.setBounds(2*kx,3*ky,5*kx,ky);
			groupLab.setBounds(2*kx, 5*ky, 5*kx,ky);
			numLab.setBounds(2*kx, 7*ky, 5*kx,ky);
			priceLab.setBounds(2*kx, 9*ky, 5*kx,ky);
			descLab.setBounds(2*kx, 12*ky, 5*kx,ky);
			prodLab.setBounds(2*kx, 15*ky, 5*kx,ky);
			
			name.setBounds(7*kx,3*ky,10*kx,ky);
			group.setBounds(7*kx,5*ky,10*kx,ky);
			number.setBounds(7*kx,7*ky,10*kx,ky);
			price.setBounds(7*kx,9*ky,10*kx,ky);
			desc.setBounds(7*kx,12*ky,10*kx,ky);
			producer.setBounds(7*kx,15*ky,10*kx,ky);

			submit.setBounds(10*kx, 17*ky, 7*kx, ky);
			
			dialog.add(header);
			dialog.add(nameLab);
			dialog.add(name);
			dialog.add(groupLab);
			dialog.add(group);
			dialog.add(descLab);
			dialog.add(desc);
			dialog.add(prodLab);
			dialog.add(producer);
			dialog.add(numLab);
			dialog.add(number);
			dialog.add(priceLab);
			dialog.add(price);
			dialog.add(submit);
			dialog.add(new JLabel());
			
			dialog.setVisible(true);
			
			submit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent ae) {
					if(ptm.productExist(name.getText())) {
						JDialog warn = new JDialog(dialog,"This product is already exist");
						warn.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						warn.setBounds(200,200,301,0);
						warn.setVisible(true);
						return;
					}
					Object[] arr = {
					        		name.getText(),
					        		(String)group.getSelectedItem(),
					        		desc.getText(),
					        		producer.getText(),
					        		((Number)price.getValue()).intValue(),
					        		((Number)number.getValue()).intValue()
					};
						
					ptm.addRow(arr);
	
				}
				
			});
		}
	/**
	 * 
	 */
	private void makePurchase() {
		JDialog dialog = new JDialog(frame);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel header = new JLabel("Make purchase");
		JLabel custInfoLab = new JLabel("Customer info:");
		JLabel nameLab = new JLabel("Name:");
		JTextField name = new JTextField("");
		JLabel telLab = new JLabel("Telephone:");
		JFormattedTextField tel = new JFormattedTextField();
		try {
			MaskFormatter mf2 = new MaskFormatter("+38(###) ###-####");
			 tel = new JFormattedTextField(mf2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		JLabel chooseLab = new JLabel("Choose products");
	
		JLabel totalLab = new JLabel();
		ChoicesTableModel ctm = new ChoicesTableModel(ptm);
		PurchaseTableModel putm = new PurchaseTableModel(ctm,totalLab);
		
		JTable chooseTab = new JTable(ctm);
		TableRowSorter<ChoicesTableModel> sorter = new TableRowSorter<ChoicesTableModel>(ctm);
		chooseTab.setRowSorter(sorter);
		JLabel filterByNameLab = new JLabel("Filter by name");
		JTextField filterByName = new JTextField();
		JButton addRowBut = new JButton("Add row");
		JLabel yourPurchaseLab = new JLabel("Youre purchase:");
		JTable purchaseTab = new JTable(putm);
		JButton removeRowBut = new JButton("Remove row");
		chooseTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		
		JButton submit = new JButton("Confirm");
		JScrollPane scp1 = new JScrollPane(chooseTab);
		JScrollPane scp2 = new JScrollPane(purchaseTab);
		
		dialog.setBounds(100, 100, 21*kx, 32*ky);
		header.setBounds(5*kx,ky,10*kx,ky);
		
		custInfoLab.setBounds(2*kx,3*ky,5*kx,ky);
		nameLab.setBounds(2*kx, 5*ky, 5*kx, ky);
		telLab.setBounds(2*kx, 7*ky, 5*kx, ky);
		chooseLab.setBounds(2*kx, 9*ky, 5*kx, ky);
		
		name.setBounds(7*kx, 5*ky, 10*kx, ky);
		tel.setBounds(7*kx, 7*ky, 10*kx, ky);
		
		scp1.setBounds(2*kx, 11*ky, 16*kx, 4*ky);
		filterByNameLab.setBounds(2*kx, 16*ky, 5*kx, ky);
		filterByName.setBounds(7*kx, 16*ky, 5*kx, ky);
		addRowBut.setBounds(12*kx, 16*ky, 6*kx, ky);
		
		yourPurchaseLab.setBounds(2*kx, 18*ky, 5*kx, ky);
		scp2.setBounds(2*kx, 20*ky, 16*kx, 4*ky);
		removeRowBut.setBounds(2*kx, 25*ky, 6*kx, ky);
		
		totalLab.setBounds(2*kx, 27*ky, 7*kx, ky);
		submit.setBounds(11*kx, 27*ky, 7*kx, ky);
		
		dialog.add(header);
		dialog.add(custInfoLab);
		dialog.add(nameLab);
		dialog.add(name);
		dialog.add(telLab);
		dialog.add(tel);
		dialog.add(chooseLab);
		dialog.add(filterByNameLab);
		dialog.add(filterByName);
		dialog.add(scp1);
		dialog.add(addRowBut);
		dialog.add(yourPurchaseLab);
		dialog.add(scp2);
		dialog.add(removeRowBut);
		dialog.add(totalLab);
		dialog.add(submit);
		dialog.add(new JLabel(""));
		dialog.setVisible(true);

		chooseTab.setUpdateSelectionOnSort(true);

		
		addRowBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {

				//add seleted from first table row to second table
				int r = chooseTab.getSelectedRow();
				//if no row was selected, return
				if(r==-1) return;
				//if selected row is already in second table, return
				if(ctm.wasRowSelected(r)) return;
				//create copy of selected row to change number only in second row
				ArrayList<Object> rowData = (ArrayList<Object>) ctm.getRow(ctm.getRowIndexByName(chooseTab.getValueAt(r,1))).clone();
				putm.addRow(rowData,r);
				//selected row cannot be added to second table more than once
				ctm.setRowSelected(r);


				System.out.println(	chooseTab.getValueAt(r,1));
			}
			
		});
		
		purchaseTab.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor(ctm,putm));
		purchaseTab.getColumnModel().getColumn(2).setCellRenderer(new SpinnerRenderer(ctm,putm));

		removeRowBut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int r = purchaseTab.getSelectedRow();
				//if no row was selected, return
				if(r==-1) return;
				//allow row to be again selected from first table
				int ctmRow = ctm.getRowIndexByName(putm.getValueAt(r, 1));
				ctm.setRowDiselected(ctmRow);
				putm.removeRow(r);
			}
			
		});
		
		filterByName.addActionListener(new ActionListener() {
			//magic happens here
			public void actionPerformed(ActionEvent ae) {

				String text = filterByName.getText();
				if(text.length() ==0) sorter.setRowFilter(null);
				else {
					sorter.setRowFilter(RowFilter.regexFilter(text, 1));
					System.out.println(chooseTab.getSelectedRow());
				}



			}
		});
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent al) {
				//TODO save info about purchase(person, telephone, bought products) somewhere and do something with it
				while(putm.getRowCount()!=0) {
					int i=0;
					Integer ptmNumber = (Integer) putm.getValueAt(i, 2);
					int ctmRow = ctm.getRowIndexByName(putm.getValueAt(i, 1));
					Integer ctmNumber = (Integer) ctm.getValueAt(ctmRow, 2);
					
					Integer value = ctmNumber - ptmNumber;
					ctm.setValueAt(value, ctmRow, 2);
					ptm.setNumber(value, ctmRow);
					putm.setSpinnerModel(i, ctmRow);
					ctm.setRowDiselected(ctmRow);
					putm.removeRow(i);
					dialog.dispose();
				}
				
			}
			
		});
		
	}
	

	
}