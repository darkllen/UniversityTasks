import org.netbeans.swing.outline.RenderDataProvider;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RenderData implements RenderDataProvider {
    @Override
    public String getDisplayName(Object o) {
        if (((File)o).getParent()!=null)
        return ((File)o).getName();
        return ((File)o).getPath();
    }

    @Override
    public boolean isHtmlDisplayName(Object o) {
        return false;
    }

    @Override
    public Color getBackground(Object o) {
        return null;
    }

    @Override
    public Color getForeground(Object o) {
        File f = (File) o;
        if (!f.isDirectory() && !f.canWrite()) {
            return UIManager.getColor("controlShadow");
        }
        return null;
    }

    @Override
    public String getTooltipText(Object o) {
        File f = (File) o;
        return f.getAbsolutePath();
    }

    @Override
    public Icon getIcon(Object o) {
        return null;
    }
}
