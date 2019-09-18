import java.awt.Component;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

//everything works fine
//do not correct anything
//please

class SpinnerRenderer implements TableCellRenderer {
private ChoicesTableModel ctm;
private  PurchaseTableModel ptm;
protected JSpinner renderer;

   public SpinnerRenderer(ChoicesTableModel ctm,PurchaseTableModel ptm) {
	   this.ctm = ctm;
	   this.ptm = ptm;
   }
   
   public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
	  renderer = ptm.getCurrentSpinner(row);
	  renderer.setOpaque(true);
      return renderer;
   }

}
   
class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
   protected JSpinner editor;
   private ChoicesTableModel ctm;
   private  PurchaseTableModel ptm;
   public SpinnerEditor(ChoicesTableModel ctm, PurchaseTableModel ptm) {
	  this.ctm = ctm;
      this.ptm = ptm;
   }
  
   public Component getTableCellEditorComponent(JTable table, Object value,
                    boolean isSelected, int row, int column) {
	   editor = ptm.getCurrentSpinner(row);
	   editor.setValue(value);
      return editor;
      
   }

public Object getCellEditorValue() {
	return editor.getValue();
}
  
}