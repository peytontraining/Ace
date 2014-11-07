package peyton.training.rap.demoSecond.Dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import peyton.training.rap.demoSecond.Common.Constant;
import peyton.training.rap.demoSecond.Entites.Version;
import peyton.training.rap.demoSecond.Views.TreeViewPart;

public class DeleteVersionDialog extends Dialog{
    
    private static final long serialVersionUID = 1L;
    
    Composite deleteComposite;
    
    public final Image DELETE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin(
            Constant.PAKAGE_PATH, Constant.DELETE_IMAGE).createImage();
    
    public DeleteVersionDialog(Shell parentShell) {
        super(parentShell);
    }
    
    @Override
    protected Control createDialogArea(Composite parent) {
        IWorkbenchWindow window = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow();
        
        IStructuredSelection structSelection = (IStructuredSelection) 
                window.getActivePage().getSelection(TreeViewPart.TREEVIEW_ID);
        
        Object element = structSelection.getFirstElement();
        Version version = (Version) element;
        
        deleteComposite = (Composite) super.createDialogArea(parent);
        deleteComposite.setLayout(new GridLayout(1, false));
        Label lbDelete = new Label(deleteComposite, SWT.CENTER);
        
        lbDelete.setText("Are you sure want to delete verion (" + version.getName() + ") ?");
        
        return deleteComposite;
    }
    
    @Override
    protected Point getInitialSize() {
        return new Point(320, 150);
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Delete Confirm");
        newShell.setImage(DELETE_IMAGE);
    }
    
    @Override
    protected void buttonPressed(int buttonId) {
        if (IDialogConstants.OK_ID == buttonId) {
            okPressed();
        } else if (IDialogConstants.CANCEL_ID == buttonId) {
            cancelPressed();
        }
    }
    
    @Override
    protected void okPressed() {
        super.okPressed();
    }

    @Override
    protected void cancelPressed() {
        setReturnCode(CANCEL);
        close();
    }
}
