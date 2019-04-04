import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class PurchaseTableModel extends AbstractTableModel {

	private String[] columns = {"Id", "Name", "Number","Price"}; 
	private ArrayList<ArrayList<Object>> data = new ArrayList<>();
	private ChoicesTableModel ctm;
	private ArrayList<JSpinner> currentSpinners = new ArrayList<>();
	private ArrayList<JSpinner> spinners = new ArrayList<>();

	/**
	 * @param ctm ChoiceTableModel
	 * @param totalLab JLabel where total price will be displayed
	 */
	public PurchaseTableModel(ChoicesTableModel ctm, JLabel totalLab){ 
		this.ctm = ctm;
		//create new spinner for each row of ChoiceTableModel
		for(int i=0; i<ctm.getRowCount(); i++) {
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(0, 0,((Integer)ctm.getValueAt(i, 2)).intValue(), 1));
			spinner.addChangeListener(new ChangeListener() {

    			public void stateChanged(ChangeEvent ce) {
    				//if value of spinner is changed
    				//change text in "Total" JLabel
    			  	Integer q = (Integer) spinner.getValue();
    				int ctmRow = spinners.indexOf(spinner);
    				int row = currentSpinners.indexOf(spinner);
    				if(row == -1) return;
    				Integer p = (Integer) ctm.getValueAt(ctmRow, 3);
    				setValueAt(q,row,2);
    				setValueAt(new Integer(q*p), row, 3);
    				int total =0;
    				for(int i=0; i<getRowCount();i++) {
    					total += ((Integer)getValueAt(i, 3)).intValue();
    				}
    				totalLab.setText("Total:"+total);
    			}
    	    	  
    	      });
			spinners.add(spinner);
		}
			
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return data.size();
	}


	public Object getValueAt(int row, int column) {
		return data.get(row).get(column);
	}
	
	public void setValueAt(Object value,
            int row,
            int column) {
		data.get(row).set(column,value);
		fireTableCellUpdated(row,column);
		}
	
	/**
	 * @param rowData
	 * @param row of ChoiceTableModel
	 * add row to PurchaseTableModel
	 * add new spinner to array of currently visible spinners
	 */
	public void addRow(ArrayList<Object> rowData,int ctmRow) {
		rowData.set(3, new Integer(0));
		currentSpinners.add(spinners.get(ctmRow));
		data.add(rowData);
		fireTableRowsInserted(data.size()-1,data.size()-1);
	}
	
	/**
	 * @param row
	 * remove row from PurchaseTableModel
	 * remove spinner from array of currently visible spinners
	 */
	public void removeRow(int row) {
		data.remove(row);
		currentSpinners.remove(row);
		fireTableRowsDeleted(row,row);
	}
	public String getColumnName(int column) {
		return columns[column];
		}
	public boolean isCellEditable(int row,int column){  
		if(column ==  2 )return true;
		return false;  
		}


	/**
	 * @param spinner
	 * remove spinner from array of currently visible spinners
	 */
	public void addSpinner(JSpinner spinner) {
		currentSpinners.add(spinner);
	}
	public void removeSpinner(int row) {
		currentSpinners.remove(row);
	}
	public JSpinner getCurrentSpinner(int row) {
		return currentSpinners.get(row);
	}

	/**
	 * @param row
	 * @param row of ChoiceTableModel
	 * determines max value of spinner in PurchaseTableModel
	 * based on value of "Number" column in specific row in ChoiceTableModel
	 */
	public void setSpinnerModel(int row, int ctmRow) {
		currentSpinners.get(row).setValue(0);
		currentSpinners.get(row).setModel(new SpinnerNumberModel(0, 0,((Integer)ctm.getValueAt(ctmRow, 2)).intValue(), 1));
	}

}
