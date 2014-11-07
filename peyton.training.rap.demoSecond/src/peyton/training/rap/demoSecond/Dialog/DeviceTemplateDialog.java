package peyton.training.rap.demoSecond.Dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import peyton.training.rap.demoSecond.Common.Constant;
import peyton.training.rap.demoSecond.DbUtils.DeviceTemplateDAO;
import peyton.training.rap.demoSecond.Entites.DeviceTemplate;
import peyton.training.rap.demoSecond.Entites.Version;
import peyton.training.rap.demoSecond.Provider.DeviceTemplateDialogContentProvider;
import peyton.training.rap.demoSecond.Provider.DeviceTemplateDialogLabelProvider;

public class DeviceTemplateDialog extends Dialog {
    private static final long serialVersionUID = 1L;

    private Version version;
    private DeviceTemplateDAO getAllData = new DeviceTemplateDAO();
    private FilteredTree filterTree;
    private TreeViewer treeViewer;
    private Composite deviceComposite;
    private Section section;
    private FormToolkit toolkit;
    private ScrolledForm scrollForm;
    private Combo cbOther;
    private GridData gridData;
    private DeviceTemplate deviceTemplate;
    
    public final Image SELECT_DEVICE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin(
            Constant.PAKAGE_PATH, Constant.SELECT_DEVICE_IMAGE).createImage();
    
    public DeviceTemplateDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        
        deviceComposite = (Composite) super.createDialogArea(parent);
        deviceComposite.setLayout(new GridLayout(2, false));

        //Create Form toolKit
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        Section section = toolkit.createSection(deviceComposite, Section.TITLE_BAR);
        section.setText("Device Templates");
        section.setLayout(new FillLayout());
        section.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
        
        // Create Content of tree
        filterTree = new FilteredTree(section, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL, new PatternFilter(), true);
        treeViewer = filterTree.getViewer();
        treeViewer.setContentProvider(new DeviceTemplateDialogContentProvider());
        treeViewer.setLabelProvider(new DeviceTemplateDialogLabelProvider());
        treeViewer.setInput(getAllData.getAllTreeColumn());
        treeViewer.setAutoExpandLevel(3);
        
        //Create TextBox Other
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        toolkit.createLabel(deviceComposite, "Communication Method: ");
        cbOther = new Combo(deviceComposite, SWT.READ_ONLY);
        cbOther.add("Other");
        cbOther.select(0);
        cbOther.setLayoutData(gridData);
        section.setClient(filterTree);
        return deviceComposite;
    }
    
    @Override
    protected boolean isResizable() {
      return true;
    }
    
    //Set init and show button "OK" or "Cancel"
    @Override
    protected Point getInitialSize() {
        return new Point(400, 500);
      }
    
    //Action Click Button OK
    @Override
    protected void buttonPressed(int buttonId) {
        if (IDialogConstants.OK_ID == buttonId) {
            okPressed();
        } else if (IDialogConstants.CANCEL_ID == buttonId) {
            cancelPressed();
        }
    }
    
    //Action click Button Cancel
    @Override
    protected void cancelPressed() {
        setReturnCode(CANCEL);
        close();
    }
    
    //Set Image && Text Title
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setImage(SELECT_DEVICE_IMAGE);
        newShell.setText("Select Template");
    }
    
    @Override
    protected void okPressed() {
        IStructuredSelection structSelection = (IStructuredSelection) treeViewer.getSelection();
        Object element = structSelection.getFirstElement();
            if(element != null && element instanceof DeviceTemplate){
                setDeviceTemplate((DeviceTemplate) element);
        }
        super.okPressed();
    }
    
    
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public FormToolkit getToolkit() {
        return toolkit;
    }

    public void setToolkit(FormToolkit toolkit) {
        this.toolkit = toolkit;
    }

    public ScrolledForm getScrollForm() {
        return scrollForm;
    }

    public void setScrollForm(ScrolledForm scrollForm) {
        this.scrollForm = scrollForm;
    }

    public DeviceTemplate getDeviceTemplate() {
        return deviceTemplate;
    }

    public void setDeviceTemplate(DeviceTemplate deviceTemplate) {
        this.deviceTemplate = deviceTemplate;
    }
    
}
