package peyton.training.rap.demoSecond.Views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import peyton.training.rap.demoSecond.Common.Constant;
import peyton.training.rap.demoSecond.Entites.DeviceTemplate;

public class DeviceTemplatesDetailView extends ViewPart {

    public static String VIEW_ID = "peyton.training.rap.demoSecond.Views.deviceTemplateDetail";
    private FormToolkit toolkit;
    private Section section;
    private Button ckCertifield, set, remove, editTypes, selectDriver;
    private GridData layoutData;
    private Text txtName, txtManufact, txtModelNum, txtNotes;
    private Label lbDeviceDriver, lbTypes, lbIcon;
    private TabFolder tbFolder;
    private TabItem tbDetailItem, tbConfigItem;
    private ScrolledForm scrollForm;
    private Composite driver, types;
    private Display display = Display.getCurrent();
    public DeviceTemplatesView deviceTemplateView;
    
    /** The Constant DVR_NVR_IMAGE. */
    public static final Image CCTV_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.CCTV_IMAGE_PATH).createImage();
    
    @Override
    public void createPartControl(Composite parent) {
        //Create Color
        Color darkColor = display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW);
        //Create subTab 
        tbFolder = new TabFolder(parent, SWT.NONE);
        tbFolder.setSize(100, 100);
        tbFolder.setForeground(darkColor);
        
        //Create tbItem
        createDetailTbItem(parent);
        
        //Create Configure tbItem
        createConfigureTbItem(parent);
    }
    
    public void createDetailTbItem(Composite parent){
        // Get Section from Device Template And active Page
        IWorkbenchWindow window = getSite().getWorkbenchWindow();
        window.getActivePage();

        //Create Color
        Color grayColor = display.getSystemColor(SWT.COLOR_GRAY);
        
        //Create Tab Item Detail
        tbDetailItem = new TabItem(tbFolder, SWT.BORDER);
        tbDetailItem.setText("Details");
        tbDetailItem.setToolTipText("Detail Device Template");
        
        //Crreate Form Toolkit
        toolkit = new FormToolkit(parent.getDisplay());
        
        //Create Scroll Form
        scrollForm = toolkit.createScrolledForm(tbFolder);
        scrollForm.getBody().setLayout(new GridLayout(4, false));
        
        //Create Checkbox Certifield
        layoutData = new GridData(SWT.LEFT, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Certified:");
        ckCertifield = toolkit
                .createButton(scrollForm.getBody(), "", SWT.CHECK);
        ckCertifield.setLayoutData(layoutData);
        
        //Create button set and Remove 
        layoutData = new GridData(SWT.LEFT, SWT.NONE, false, false, 1, 1);
        toolkit.createLabel(scrollForm.getBody(), "Icon:");
        lbIcon = toolkit.createLabel(scrollForm.getBody(), "");
        // lbIcon.setImage(CCTV_IMAGE);
        set = toolkit.createButton(scrollForm.getBody(), "Set...", SWT.PUSH);
        set.setLayoutData(layoutData);
        remove = toolkit.createButton(scrollForm.getBody(), "Remove", SWT.NONE);
        remove.setLayoutData(layoutData);
        
        //Create text Name 
        layoutData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Name:");
        txtName = toolkit.createText(scrollForm.getBody(), "");
        txtName.setLayoutData(layoutData);
        
        //Create text manufacture
        toolkit.createLabel(scrollForm.getBody(), "Manufacturer:");
        txtManufact = toolkit.createText(scrollForm.getBody(), "");
        txtManufact.setLayoutData(layoutData);

        //Create text ModelNumber
        toolkit.createLabel(scrollForm.getBody(), "Model Number:");
        txtModelNum = toolkit.createText(scrollForm.getBody(), "");
        txtModelNum.setLayoutData(layoutData);

        //Create text Types
        toolkit.createLabel(scrollForm.getBody(), "Types:");
        types = toolkit.createComposite(scrollForm.getBody(), SWT.BORDER);
        types.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
        types.setLayout(new GridLayout(2, false));
        types.setBackground(grayColor);
        
        //Create button Edit Types
        lbTypes = toolkit.createLabel(types, "");
        lbTypes.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        lbTypes.setBackground(grayColor);
        editTypes = toolkit.createButton(types, "Edit Types", SWT.PUSH);
        editTypes.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, true, false));

        //Create Text Area Device Driver
        toolkit.createLabel(scrollForm.getBody(), "Device Driver:");
        driver = toolkit.createComposite(scrollForm.getBody(), SWT.BORDER);
        driver.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
        driver.setLayout(new GridLayout(2, false));
        
        //Create button select Driver
        lbDeviceDriver = toolkit.createLabel(driver, "");
        lbDeviceDriver.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true,
                false));
        selectDriver = toolkit.createButton(driver, "Select Driver", SWT.PUSH);
        selectDriver.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, true,
                false));
        
        //Create Text area Note
        layoutData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Notes:");
        txtNotes = toolkit.createText(scrollForm.getBody(), "", SWT.MULTI);
        layoutData.heightHint = txtNotes.getLineHeight() * 5;
        txtNotes.setLayoutData(layoutData);
        
        //Set scroll Form to tab Item
        tbDetailItem.setControl(scrollForm);
    }
    
    public void createConfigureTbItem(Composite parent){
        
        //Create Tab Item Detail
        tbConfigItem = new TabItem(tbFolder, SWT.BORDER);
        tbConfigItem.setText("Configure");
        tbConfigItem.setToolTipText("Configure");
        
        //Crreate Form Toolkit
        toolkit = new FormToolkit(parent.getDisplay());
        
        //Create Scroll Form
        scrollForm = toolkit.createScrolledForm(tbFolder);
        scrollForm.getBody().setLayout(new GridLayout(4, false));
        
        //Set scroll Form to tab Item
        tbConfigItem.setControl(scrollForm);
    }
    
    public void setDataDeviceTemplate(DeviceTemplate deviceTemplate) {
        lbIcon.setImage(CCTV_IMAGE);
        txtName.setText(deviceTemplate.getName());
        txtManufact.setText(deviceTemplate.getManufacturer());
        txtModelNum.setText(deviceTemplate.getModelNumber());
        lbTypes.setText(deviceTemplate.getTypeDeviceTemplate().getName());
        lbDeviceDriver.setText(deviceTemplate.getDeviceDriver());
        txtNotes.setText(deviceTemplate.getNote());
    }
    
    /* Change Title Name View Part - Start */
    @Override
    public void init(IViewSite site) throws PartInitException {
        deviceTemplateView = (DeviceTemplatesView) PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage()
                .findView(DeviceTemplatesView.VIEW_ID);
        setSite(site);
        setPartName("");
    }
    
    @Override
    protected void setPartName(String partName) {
        partName = deviceTemplateView.deviceTemplate.getName();
        super.setPartName(partName);
    }
     /*Change Title Name View Part - End */
    
    @Override
    public void setFocus() {
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
