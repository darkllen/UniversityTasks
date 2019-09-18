import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import model.*;
public class Data {

	
	
	public Data(ArrayList<Product> tableProducts, ArrayList<Group> tableGroups ) {
		
		
		ArrayList<String> groups = new ArrayList<>();
		groups.add("");
		for(Group group : tableGroups) groups.add(group.getName());
		JFrame frame = new JFrame();
		
		JMenuBar menu = new JMenuBar();
		JMenu addNew = new JMenu("Add New");
		JMenu purchase = new JMenu("Purchase");
		JMenu edit = new JMenu("Edit");
		JMenu delete = new JMenu("Delete");
		JMenu change = new JMenu("Change Quantity");
		
		JMenuItem addGroup = new JMenuItem("Add New Group");
		JMenuItem addProduct = new JMenuItem("Add New Product");
		
		JMenuItem newPurchase = new JMenuItem("Purchase");
		
		JMenuItem editGroup = new JMenuItem("Edit Group");
		JMenuItem editProduct = new JMenuItem("Edit Product");
		
		JMenuItem deleteGroup = new JMenuItem("Delete Group");
		JMenuItem deleteProduct = new JMenuItem("Delete Product");
		
		JMenuItem addGoods = new JMenuItem("Add");
		JMenuItem subGoods = new JMenuItem("Subtract");
		
		JLabel filterByProductLab = new JLabel("Filter by name");
		JTextField filterByName = new JTextField();
		filterByName.setColumns(10);
		JLabel filterByGroupLab = new JLabel("Filter by group");
		JComboBox<Object> filterByGroup = new JComboBox<>(groups.toArray());
		JButton editPrBut = new JButton("Edit Product");
		JButton editGrBut = new JButton("Edit Group");
		JButton delPrBut = new JButton("Delete Product");
		JButton delGrBut = new JButton("Delete Group");
		JButton addGoodsBut = new JButton("Add");
		JButton subGoodsBut = new JButton("Subtract");
		JLabel totalPriceLab = new JLabel("Total");
		JLabel totalGrPriceLab = new JLabel("Total group:");
		JLabel descGroupLab = new JLabel();
		
		
		JTable table = new JTable();
		ProductsTableModel ptm = new ProductsTableModel(tableProducts,tableGroups, totalPriceLab);
		table.setModel(ptm);
		table.getTableHeader().setReorderingAllowed(false);
		
	
	totalPriceLab.setText("Total price: "+ptm.getTotalPrice());	
	
		MenuListener ml = new MenuListener(frame,table,filterByGroup,groups);
		TableRowSorter<ProductsTableModel> sorter = new TableRowSorter<ProductsTableModel>(ptm);
		table.setRowSorter(sorter);
		JScrollPane sp = new JScrollPane(table);
		
		
		addProduct.addActionListener(ml);
		addGroup.addActionListener(ml);
		newPurchase.addActionListener(ml);
		editProduct.addActionListener(ml);
		editGroup.addActionListener(ml);
		deleteProduct.addActionListener(ml);
		deleteGroup.addActionListener(ml);
		addGoods.addActionListener(ml);
		subGoods.addActionListener(ml);
		editPrBut.addActionListener(ml);
		editGrBut.addActionListener(ml);
		delPrBut.addActionListener(ml);
		delGrBut.addActionListener(ml);
		addGoodsBut.addActionListener(ml);
		subGoodsBut.addActionListener(ml);
		
		addNew.add(addProduct);
		addNew.add(addGroup);
		purchase.add(newPurchase);
		edit.add(editProduct);
		edit.add(editGroup);
		delete.add(deleteProduct);
		delete.add(deleteGroup);
		change.add(addGoods);
		change.add(subGoods);
		
		menu.add(addNew);
		menu.add(purchase);
		menu.add(edit);
		
		menu.add(delete);
		menu.add(change);
		
		int kx = 30; 
		int ky = 10;
	

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		frame.setBounds(0,0,30*kx, 70*ky);
		filterByProductLab.setBounds(2*kx, ky, 3*kx, 2*ky);
		filterByName.setBounds(5*kx, ky, 5*kx, 2*ky);
		filterByGroupLab.setBounds(2*kx, 3*ky, 3*kx, 2*ky);
		filterByGroup.setBounds(5*kx, 3*ky, 5*kx, 2*ky);
		editPrBut.setBounds(12*kx, ky, 5*kx, 2*ky);
		delPrBut.setBounds(17*kx, ky, 5*kx, 2*ky);
		addGoodsBut.setBounds(22*kx, ky, 5*kx, 2*ky);
		editGrBut.setBounds(12*kx, 3*ky, 5*kx, 2*ky);
		delGrBut.setBounds(17*kx, 3*ky, 5*kx, 2*ky);
		subGoodsBut.setBounds(22*kx, 3*ky, 5*kx, 2*ky);
		sp.setBounds(2*kx, 7*ky, 25*kx, 43*ky);
		totalPriceLab.setBounds(2*kx, 54*ky, 10*kx, 2*ky);
		totalGrPriceLab.setBounds(2*kx, 56*ky, 6*kx, 2*ky);
		descGroupLab.setBounds(10*kx, 56*ky, 17*kx, 2*ky);
	
		frame.setJMenuBar(menu);
		frame.add(filterByProductLab);
		frame.add(filterByName);
		frame.add(editPrBut);
		frame.add(delPrBut);
		frame.add(addGoodsBut);
		frame.add(subGoodsBut);
		frame.add(delGrBut);
		frame.add(editGrBut);
		frame.add(filterByGroup);
		frame.add(filterByGroupLab);
		frame.add(sp);
		frame.add(totalPriceLab);
		frame.add(descGroupLab);
		frame.add(totalGrPriceLab);
		
		boolean buttonsAreVisible = false;
		editPrBut.setVisible(false);
		delPrBut.setVisible(false);
		addGoodsBut.setVisible(false);
		subGoodsBut.setVisible(false);
		delGrBut.setVisible(false);
		editGrBut.setVisible(false);
		
		frame.setVisible(true);
		filterByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//magic
				String text = filterByName.getText();
				if(text.length() ==0) sorter.setRowFilter(null);
				else sorter.setRowFilter(RowFilter.regexFilter(text, 1));
			}
		});
		filterByGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//magic
				String text = (String)filterByGroup.getSelectedItem();
				if(text.equals("")) sorter.setRowFilter(null);
				else sorter.setRowFilter(RowFilter.regexFilter(text, 2));
			}
		});
		
	
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1) return;
				Object group = ptm.getValueAt(row,2);
				totalGrPriceLab.setText("Total price of group: "+ptm.getTotalPriceOfGroup((String) group));
				descGroupLab.setText("Group description: "+ptm.getGroupDescription((String) group));
				
				if(!buttonsAreVisible) {
				editPrBut.setVisible(true);
				delPrBut.setVisible(true);
				addGoodsBut.setVisible(true);
				subGoodsBut.setVisible(true);
				delGrBut.setVisible(true);
				editGrBut.setVisible(true);
				}
			}
			
		});
	}
	
	
	
	
	public static void main(String[] args) {
		//groups
		Connect connect = new Connect();
		Database database = new Database(connect.connectToDB());
		ArrayList<Group>  groups = database.getGrpoups();

		ArrayList<Product> products =database.getProducts();

		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Data(products, groups);
			}
		});
	}
	
	
}


























