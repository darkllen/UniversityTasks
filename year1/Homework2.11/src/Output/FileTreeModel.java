package Output;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FileTreeModel implements TreeModel {

    private Object root;
    public FileTreeModel(Object root) {
        this.root = root;
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        try {
            Word f = (Word) parent;
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(f.getHashMap().keySet());
            Pair pair = new Pair(f.getHashMap().get(arrayList.get(index)), (String) arrayList.get(index));
            return  pair;
        } catch (Exception e){
            ArrayList arrayList = (ArrayList)parent;
            return arrayList.get(index);
        }

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        try {
            Word par = (Word) parent;
            Pair ch = (Pair) child;
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(par.getHashMap().values());
            return arrayList.indexOf(ch.getNum());
        } catch (Exception e){
            ArrayList arrayList = (ArrayList) parent;
            Word ch = (Word) child;
            return arrayList.indexOf(ch);
        }

    }

    @Override
    public int getChildCount(Object parent) {
        try {
            return ((Word)parent).getHashMap().size();
        }catch (Exception e){
            return ((ArrayList)parent).size();
        }

    }

    @Override
    public boolean isLeaf(Object node) {
        try {
            Pair s = (Pair) node;
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }



    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }
}
