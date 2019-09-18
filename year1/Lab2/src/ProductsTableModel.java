import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import model.*;

public class ProductsTableModel extends AbstractTableModel{
	Connect connect = new Connect();
	Database database = new Database(connect.connectToDB());
	private final String[] columns = {"Id","Name" ,"Group","Description","Producer","Number","Price","Total Price"};
	private ArrayList<Product> products;
	private ArrayList <Group> groups;
	public JLabel totalLab;
	private int productIdCounter;
	private int groupIdCounter;
	
	public ProductsTableModel(ArrayList<Product> data, ArrayList<Group> groups, JLabel total){
		this.products = data;
		this.groups = groups;
		this.totalLab = total;
		productIdCounter = products.get(products.size()-1).getId();
	}
	public ProductsTableModel(ArrayList<Group> groups, JLabel total){
		this(new ArrayList<Product>(), groups, total);
	}
	public ProductsTableModel(JLabel total){
		this(new ArrayList<Product>(), new ArrayList<Group>(), total);
	}
	
	public void setNumber(Integer number, int row) {
		Product product = products.get(row);
		product.setNumber(number);
		database.updateProductById(product.getId(),product.getGroup_id(),product.getName(),product.getDescription(),product.getProducer(),product.getNumber(),product.getCost());
		fireTableRowsUpdated(row, row);
	};
	private Product getProductByName(String productName) {
		for(Product product : products) {
			if(product.getName().equals(productName)) return product;
		}
		return null;
	}
	public int getRowIndexByName(String productName) {
		for(int i =0; i<products.size(); i++) {
			if(products.get(i).getName().equals(productName)) return i;
		}
		return -1;
	}
	
	public Class<?> getColumnClass(int column){
		if(products.size() == 0 || products.get(0) == null) return null;
		if(column == 0  || column == 5 || column == 6 || column == 7 ) return Integer.class;
		return String.class;
			}
	
	
	public String getColumnName(int column) {
		return columns[column];
		}
	
	public int getRowCount(){
		return products.size();
		}
	
	public int getColumnCount() {
		return columns.length;
		}
	
	/**
	 * @param product
	 * add row to the table
	 */
	private void addRow(Product product) {
		products.add(product);
		database.insertNewProduct(product.getGroup_id(),product.getName(),product.getDescription(),product.getProducer(),product.getNumber(),product.getCost());
		fireTableRowsInserted(products.size()-1,products.size()-1);
	}
	
	/**
	 * @param row
	 * remove row
	 */
	public void removeRow(int row) {
		database.deleteProduct(products.get(row).getId());
		products.remove(row);
		fireTableRowsDeleted(row,row);
	}
	// "Id","Name" ,"Group","Description","Producer","Number","Price","Total Price"
	public Object getValueAt(int row, int column) {
		Product product = products.get(row);
		switch(column) {
		case 0 : return product.getId();
		case 1 : return product.getName();
		case 2 : return ( getGroupById(product.getGroup_id()) ).getName();
		case 3 : return product.getDescription();
		case 4 : return product.getProducer();
		case 5 : return product.getNumber();
		case 6 : return product.getCost();
		case 7 : return product.getCost()*product.getNumber();
		default : return null;
		}
		}
	// "Id","Name" ,"Group","Description","Producer","Number","Price","Total Price"
		public Object getValueAt(int row, String columnName) {
			int column = -1;
			for(int i=0; i<columns.length; i++) {
				if(columns[i].equals(columnName)) column = i;
			}
			return getValueAt(row , column);
		}
	private Group getGroupById(Integer id) {
		for(Group group : groups) {
			if(group.getId().equals(id)) return group;
		}
		return null;
	}
//	/**
//	 * @param row
//	 * @param columnName
//	 * @return value
//	 */
//	public Object getValueAt(int row, String columnName) {
//		int column = -1;
//		for(int i=0; i<columns.size(); i++) {
//			if(columns.get(i).equals(columnName)) column = i;
//		}
//		return getValueAt(row,column);
//		}
	
	



//arr = {productName, groupName, description, producer, price, number}
	public void updateRowByName(String oldProductName, Object[] arr) {
		Product product = getProductByName(oldProductName);
		int row = getRowIndexByName(oldProductName);	
		Integer groupId = getGroupIdByName((String) arr[1]);
		//int id, int group_id, String name, String description, String producer, int number, int cost
		product.setName((String) arr[0]);
		product.setGroup_id(groupId);
		product.setDescription((String) arr[2]);
		product.setProducer((String) arr[3]);
		product.setCost((Integer) arr[4]);
		product.setNumber((Integer) arr[5]);

		database.updateProductById(product.getId(),groupId,(String) arr[0],(String) arr[2],(String)arr[3],(Integer) arr[4],(Integer) arr[5]);


		
		
		fireTableRowsUpdated(row,row);
	}
	//arr = {String name, String groupName, String description, String producer, Integer number, Integer cost}
	public void addRow(Object[] arr) {
		int id = ++productIdCounter;
		int groupId = getGroupIdByName((String) arr[1]);
		Product product = new Product(
				id, groupId,
				(String)arr[0], (String)arr[2], (String)arr[3],
				(Integer) arr[4], (Integer)arr[5]);
		addRow(product);
	}
	

	public Integer getGroupIdByName(String groupName) {
		for(Group group : groups) {
			if(group.getName().equals(groupName)) {
				return group.getId();
			}
		}
		return -1;
	}
	/**
	 * @param group name
	 * @return array of row indices of products of such group
	 */
	public int[] getRowIndicesByGroup(String groupName) {
		int[] r = new int[products.size()];
		int n = 0;
		Integer groupId = getGroupIdByName(groupName);
		for(int i=0; i<products.size(); i++) {
			if(products.get(i).getGroup_id().equals(groupId)) {
				r[n++] = i; 
			}
		}
		int res[] = new int[n];
		for(int i = 0; i<n; i++ ) res[i] = r[i];
		return  res;
	}
	/**
	 * @return total price of all products
	 */
	public int getTotalPrice() {
		int totalPrice =0;
		for(int i = 0; i<products.size(); i++) {
			totalPrice+= ((Integer)getValueAt(i,7)).intValue();
		}
		return totalPrice;
	}
	/**
	 * @param group
	 * @return total price of all products (number*price)
	 * set return value as text to JLabel totalGrPriceLab
	 */
	public int getTotalPriceOfGroup(String groupName) {
		int totalPrice =0;
		int[] rows = getRowIndicesByGroup(groupName);
		for(int row : rows) {
			totalPrice+= ((Integer)getValueAt(row,7)).intValue();
		}
		return totalPrice;
	}
	/**
	 * @param group
	 * remove all products of such group
	 */
	public void removeGroup(String groupName) {
		int[] rows = getRowIndicesByGroup(groupName);
		for(int row : rows) {
			removeRow(row);
		}
		int i = getGroupIndexByName(groupName);
		database.deleteGroup(groups.get(i).getId());
		groups.remove(i);
	}
	private int getGroupIndexByName(String groupName) {
		for(int i = 0; i<groups.size(); i++) {
			if(groups.get(i).getName().equals(groupName)) return i;
		}
		return -1;
	}

	/**
	 * get groups available for the table
	 */
	public ArrayList<String> getGroupNames() {
		ArrayList<String> groupNames = new ArrayList<>();
		for(Group group : groups) groupNames.add(group.getName());
		return groupNames;
	}

	/**
	 * @param group
	 * @param group
	 * @return description of group
	 */
	public String getGroupDescription(String groupName) {
		Group group = groups.get(getGroupIndexByName(groupName) );
		return group.getDescription();
	}


	/**
	 * @param group
	 * @param description
	 */
	public void setDescription(String groupName, String description) {
		Group group = groups.get(getGroupIndexByName(groupName) );
		group.setDescription(description);

		database.updateGroupyById(group.getId(), groupName, description);
	}

	/**
	 * @param group
	 * add group to groups available for the table
	 */
	public void addGroup(String groupName) {
		int id = ++groupIdCounter;
		Group group = new Group(id, groupName, "");
		groups.add(group);

		database.insertNewGroup(group.getName(),group.getDescription());
	}
	/**
	 * @param group
	 * @return true, if there is such group 
	 */
	public boolean groupExists(String group) {
		int i = getGroupIndexByName(group);
		if(i == -1 ) return false;
		return true;
	}
	/**
	 * @param object 
	 * @param product name
	 * @return true, if there is such product in the table
	 */
	public boolean productExist(String name, String newName) {
		if(getRowIndexByName(newName) == -1 ) return false;
		else if(name.equals(newName)) return false;
		return true;
	}
	/**
	 * @param object 
	 * @param product name
	 * @return true, if there is such product in the table
	 */
	public boolean productExist(String newName) {
		if(getRowIndexByName(newName) == -1 ) return false;
		return true;
	}


}

