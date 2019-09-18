import org.netbeans.swing.outline.DefaultOutlineModel;
import org.netbeans.swing.outline.Outline;
import org.netbeans.swing.outline.OutlineModel;
import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {

    private JScrollPane scrollPane = new JScrollPane();
    private File root;

        private MainFrame(File root) throws HeadlessException {
        initFrame(this);

        TreeModel treeModel = new FileTreeModel(root);
        OutlineModel model = DefaultOutlineModel.createOutlineModel(treeModel, new FileRowModel(),true,"File system");
        Outline outline = new Outline();
        outline.setModel(model);
        outline.setRenderDataProvider(new RenderData());
        scrollPane.setViewportView(outline);
    }

    public static void main(String[] args) {
            File[] file = File.listRoots();
            for (File f:file) {
                new MainFrame(f);
            }

    }

    private void initFrame(JFrame jFrame){
        jFrame.setSize(500,400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(scrollPane);
        jFrame.setVisible(true);
    }
}

