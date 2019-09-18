package Output;

import org.netbeans.swing.outline.RenderDataProvider;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class RenderData implements RenderDataProvider {
    int collectionSize;
    long resultSize;
    int woeldNumber;

    public RenderData(int collectionSize, long resultSize, int woeldNumber) {
        this.collectionSize = collectionSize;
        this.resultSize = resultSize;
        this.woeldNumber = woeldNumber;
    }

    @Override
    public String getDisplayName(Object o) {
        if (o.getClass()==Word.class){
            return ((Word)o).getName();
        }
        if (o.getClass()== ArrayList.class){
            return "Words number: " + woeldNumber+", Collection size: " +collectionSize+", Result size: "+resultSize;
        }
        return o.toString();
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
        return null;
    }

    @Override
    public String getTooltipText(Object o) {
        return null;
    }

    @Override
    public Icon getIcon(Object o) {
        return null;
    }
}
