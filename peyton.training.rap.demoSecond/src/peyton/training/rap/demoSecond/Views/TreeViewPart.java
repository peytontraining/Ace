package peyton.training.rap.demoSecond.Views;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.ViewPart;

import peyton.training.rap.demoSecond.Common.Constant;
import peyton.training.rap.demoSecond.DbUtils.TreeViewDAO;
import peyton.training.rap.demoSecond.Provider.TreeViewContentProvider;
import peyton.training.rap.demoSecond.Provider.TreeViewLabelProvider;

/**
 * View with a tree viewer. This class is contributed through the plugin.xml.
 */
public class TreeViewPart extends ViewPart implements IDoubleClickListener {

    public final static String TREEVIEW_ID = "peyton.training.rap.demoSecond.Projects";

    TreeViewDAO treeData = new TreeViewDAO();

    /** The Treeviewer. */
    public TreeViewer treeviewer;

    /** The filter tree. */
    private FilteredTree filterTree;
    
    @Override
    public void createPartControl(final Composite parent) {

        filterTree = new FilteredTree(parent, SWT.MULTI | SWT.H_SCROLL
                | SWT.V_SCROLL, new PatternFilter(), true);
        treeviewer = filterTree.getViewer();
        treeviewer.setContentProvider(new TreeViewContentProvider());
        treeviewer.setLabelProvider(new TreeViewLabelProvider());
        treeviewer.setInput(treeData.getAllType());
        treeviewer.addDoubleClickListener(this);
        getSite().setSelectionProvider(treeviewer);
    }

    // Double Click Event
    public void doubleClick(DoubleClickEvent event) {
        String msg = Constant.MSG + event.getSelection().toString();
        Shell shell = treeviewer.getTree().getShell();
        MessageDialog.openInformation(shell, Constant.TREE_VIEWER, msg);
    }
    
    @Override
    public void setFocus() {
        treeviewer.getControl().setFocus();
    }
}