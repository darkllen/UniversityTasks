import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ChoicesTableModel extends AbstractTableModel{
	private String[] columns = {"Id", "Name", "Number","Price"}; //use ArrayList to apply indexOf()
	private ArrayList<ArrayList<Object>> data = new ArrayList<>();
	private ArrayList<Boolean> selectedRows = new ArrayList<>();
	/**
	 * @param ptm ProductTableModel
	 * set column names to "Index", "Name", "Number","Price"
	 */
	public ChoicesTableModel(ProductsTableModel ptm){
		for(int r= 0; r<ptm.getRowCount(); r++) {
			ArrayList<Object> newRow = new ArrayList<>();
			int newC = 0;
			for(int c = 0; c<ptm.getColumnCount(); c++) {
				if(c ==0 || c==1 || c==5 || c== 6) 
					newRow.add(newC++, ptm.getValueAt(r, c));
			}
			this.data.add(newRow);
			
		}
		//no rows are in PurchaseTableModel
		for(int i =0; i<ptm.getRowCount(); i++) selectedRows.add(false);
	}

	public void setValueAt(Object value,
            int row,
            int column) {
		data.get(row).set(column,value);
		fireTableCellUpdated(row,column);
		}
	
	public boolean isCellEditable(int rowIndex,
            int columnIndex) {  return false;  }
				
	public Class<?> getColumnClass(int columnIndex){
		if(data.size() == 0 || data.get(0) == null) return null;
		return Object.class;
			}
	

	public String getColumnName(int column) {
		return columns[column];
		}
	
	public int getRowCount(){
		return data.size();
		}
	
	public int getColumnCount() {
		return columns.length;
		}

	public Object getValueAt(int row,
            int column) {
	return data.get(row).get(column);
		}
	private void updateRow(int row, ArrayList<Object> arr) {
			data.set(row, arr);
			fireTableRowsUpdated(row,row);
		}
	
	/**
	 * @param name
	 * @param arr
	 */
	public void updateRowByName(Object name, ArrayList<Object> arr) {
		for(int i=0; i<data.size(); i++) {
			if(data.get(i).get(0).equals(name)) {
				updateRow(i,arr);
				break;
			}
		}
		
	}
	/**
	 * @param name of product
	 * @return index of row of product with this name,
	 * -1 if no row was found
	 */
	public int getRowIndexByName(Object name) {
		for(int i=0; i<data.size(); i++) {
			if(data.get(i).get(1).equals(name)) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * @param row index
	 * @return row to add to PurchaseTableModel
	 */
	public ArrayList<Object> getRow(int row){
		return data.get(row);
	}
	/**
	 * @param row index
	 * indicates that selected row can no longer be added to PurchaseTableModel
	 */
	public void setRowSelected(int row) {
		selectedRows.set(row, true);
	}
	/**
	 * @param row index
	 * @return
	 * determines if selected row is already in PurchaseTableModel
	 */
	public boolean wasRowSelected(int row) {
		return selectedRows.get(row);
	}

	/**
	 * @param row index
	 selected row was removed from PurchaseTableModel
	 indicates that selected row can be added again to PurchaseTableModel
	 */
	public void setRowDiselected(int row) {
		if(row==-1)return;
		selectedRows.set(row, false);
	}

}