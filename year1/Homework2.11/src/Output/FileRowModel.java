package Output;

import org.netbeans.swing.outline.RowModel;

import java.io.File;
import java.util.Date;

public class FileRowModel implements RowModel {
    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return Integer.class;
            default:
                assert false;
        }
        return null;
    }
    @Override
    public int getColumnCount() {
        return 1;
    }
    @Override
    public String getColumnName(int column) {
        return "Number";
    }
    @Override
    public Object getValueFor(Object node, int column) {
        try {
            Word word = (Word)node;
            return word.sum;
        }catch (Exception e){
            try {
                return ((Pair)node).getNum();
            }catch (Exception e1){
                return 0;
            }

        }

    }
    @Override
    public boolean isCellEditable(Object node, int column) {
        return false;
    }
    @Override
    public void setValueFor(Object node, int column, Object value) {
        //do nothing for now
    }
}