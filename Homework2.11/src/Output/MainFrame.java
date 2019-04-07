package Output;

import org.netbeans.swing.outline.DefaultOutlineModel;
import org.netbeans.swing.outline.Outline;
import org.netbeans.swing.outline.OutlineModel;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private JScrollPane scrollPane = new JScrollPane();

        public MainFrame(Object root, int wordsNum, int collectionSize, long resultSize) throws HeadlessException {
        initFrame(this);
        TreeModel treeModel = new FileTreeModel(root);
        OutlineModel model = DefaultOutlineModel.createOutlineModel(treeModel, new FileRowModel(),true,"Words");
        Outline outline = new Outline();
        outline.setModel(model);
        outline.setRenderDataProvider(new RenderData(collectionSize,resultSize,wordsNum));
        scrollPane.setViewportView(outline);
    }


    private void initFrame(JFrame jFrame){
        jFrame.setSize(500,400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(scrollPane);
        jFrame.setVisible(true);
    }
}

