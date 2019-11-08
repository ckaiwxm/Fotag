package View.UtilWidget;

import java.awt.*;

// Researched on tutorial https://tips4java.wordpress.com/2008/11/06/wrap-layout/#comments
public class SmartLayout extends FlowLayout
{

    public SmartLayout(int align)
    {
        super(align);
    }

    @Override
    public Dimension preferredLayoutSize(Container ctr)
    {
        return getResizableLayout(ctr);
    }

    @Override
    public Dimension minimumLayoutSize(Container ctr)
    {
        Dimension min = getResizableLayout(ctr);
        min.width -= (getHgap() + 1);
        return min;
    }

    private Dimension getResizableLayout(Container ctr) {

        synchronized (ctr.getTreeLock()) {
            int curWidth = 0;
            int curHeight = 0;
            int retWidth = 0;
            int retHeight = 0;
            int hgap = getHgap();
            int vgap = getVgap();
            int widthWhiteSpace = ctr.getInsets().left + ctr.getInsets().right + (hgap * 2);
            int heightWhiteSpace = ctr.getInsets().top + ctr.getInsets().bottom + vgap * 2;
            int maxWidth = 0;

            int ctrWidth = 0;
            if (ctr.getWidth() > 0) {
                ctrWidth = ctr.getWidth();
            } else {
                Container parentCtr = ctr;
                while (ctrWidth == 0) {
                    parentCtr = parentCtr.getParent();
                    if (parentCtr != null) {
                        ctrWidth = parentCtr.getWidth();
                    } else {
                        break;
                    }
                }
                ctrWidth = ctrWidth == 0? 1000 : ctrWidth;
            }
            maxWidth = ctrWidth - widthWhiteSpace;

            for (Component comp : ctr.getComponents()) {
                if (comp.isVisible()) {
                    int compWidth = comp.getPreferredSize().width;
                    int compHeight = comp.getPreferredSize().height;
                    if (curWidth + compWidth <= maxWidth) {
                        if (curWidth > 0) {
                            curWidth += hgap;
                        }
                        curWidth += compWidth;
                        curHeight = Math.max(curHeight, compHeight);
                    } else {
                        retWidth = Math.max(retWidth, curWidth);
                        if (retHeight > 0)
                        {
                            retHeight += vgap;
                        }
                        retHeight += curHeight;
                        curWidth = compWidth;
                        curHeight = compHeight;
                    }
                }
            }
            retWidth = Math.max(retWidth, curWidth);
            retHeight += vgap + curHeight + heightWhiteSpace;
            retWidth += widthWhiteSpace - (hgap + 1);

            return new Dimension(retWidth, retHeight);
        }
    }
}

