package peyton.training.rap.demoSecond.Views;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
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
import peyton.training.rap.demoSecond.DbUtils.DeviceModifyDAO;
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
    private Composite toolBarComposite;
    private ToolBar toolBarDetailItem;
    private GridLayout gridLayout;
    private ToolItem itemDetail, itemSaveClose, itemDeviceChange, itemShowDevice, itemSetting;;
    
    /** The Constant DVR_NVR_IMAGE. */
    public static final Image CCTV_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.CCTV_IMAGE_PATH).createImage();
    
    public final Image SAVE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin(
            Constant.PAKAGE_PATH, Constant.SAVE_IMAGE).createImage();
    
    public final Image SELECT_DEVICE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin(
            Constant.PAKAGE_PATH, Constant.SELECT_DEVICE_IMAGE).createImage();
    
    public final Image SAVE_AND_CLOSE = AbstractUIPlugin.imageDescriptorFromPlugin(
            Constant.PAKAGE_PATH, Constant.SAVE_AND_CLOSE_IMAGE).createImage();
    
    public final Image DEVICE_CHANGED = AbstractUIPlugin.imageDescriptorFromPlugin(
            Constant.PAKAGE_PATH, Constant.DEVICE_CHANGED).createImage();
    
    public final Image SHOW_DEVICE = AbstractUIPlugin.imageDescriptorFromPlugin(
            Constant.PAKAGE_PATH, Constant.SHOW_DEVICE).createImage();
    
    public final Image SETTING_DEVICE = AbstractUIPlugin.imageDescriptorFromPlugin(
            Constant.PAKAGE_PATH, Constant.SETTING_DEVICE).createImage();
    
    @Override
    public void createPartControl(Composite parent) {
        
        GridLayout layout = new GridLayout(1,false);
        parent.setLayout(layout);
        
        // Get Section from Device Table And active Page
        IWorkbenchWindow window = getSite().getWorkbenchWindow();
        window.getActivePage();
        
        //Create Toolbar
        createToolbar(parent);
        
        //Create Color
        Color darkColor = display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW);
        
        //Create subTab 
        tbFolder = new TabFolder(parent, SWT.NONE);
        tbFolder.setSize(100, 100);
        tbFolder.setForeground(darkColor);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        tbFolder.setLayoutData(gridData);
        
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
        txtName.addModifyListener(new ModifyListener() {
            
            private static final long serialVersionUID = 1L;

            @Override
            public void modifyText(ModifyEvent event) {
                itemDetail.setEnabled(true);
            }
        });
        
        //Create text manufacture
        toolkit.createLabel(scrollForm.getBody(), "Manufacturer:");
        txtManufact = toolkit.createText(scrollForm.getBody(), "");
        txtManufact.setLayoutData(layoutData);
        txtManufact.setEditable(false);
        txtManufact.setBackground(grayColor);
        
        //Create text ModelNumber
        toolkit.createLabel(scrollForm.getBody(), "Model Number:");
        txtModelNum = toolkit.createText(scrollForm.getBody(), "");
        txtModelNum.setLayoutData(layoutData);
        txtModelNum.addModifyListener(new ModifyListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void modifyText(ModifyEvent event) {
                itemDetail.setEnabled(true);
            }
        });

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
        txtNotes.addModifyListener(new ModifyListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void modifyText(ModifyEvent event) {
                itemDetail.setEnabled(true);
            }
        });
        
        //Set scroll Form to tab Item
        tbDetailItem.setControl(scrollForm);
    }
    
    public void createToolbar(Composite parent) {
    
        // Create composite Toolbar and set layout
        toolBarComposite = new Composite(parent, SWT.NONE);
        gridLayout = new GridLayout(1, false);
        gridLayout.marginTop = -10;
        gridLayout.marginBottom = -10;
        toolBarComposite.setLayout(gridLayout);
        GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        toolBarComposite.setLayoutData(gridData);

        // Create Toolbar
        toolBarDetailItem = new ToolBar(toolBarComposite, SWT.FLAT);
        gridData = new GridData(SWT.RIGHT, SWT.NONE, true, false);
        toolBarDetailItem.setLayoutData(gridData);

        // Create Item Save
        itemDetail = new ToolItem(toolBarDetailItem, SWT.PUSH);
        itemDetail.setImage(SAVE_IMAGE);
        itemDetail.setToolTipText("Save (Ctrl + S)");
        itemDetail.addSelectionListener(new SelectionAdapter() {
            private static final long serialVersionUID = -102212312093090431L;

            IWorkbenchWindow window = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow();

            @Override
            public void widgetSelected(SelectionEvent e) {
                Shell shell = window.getShell();
                MessageDialog dialog = new MessageDialog(shell,
                        "Save Resource", SELECT_DEVICE_IMAGE, " ' " + txtName.getText()
                        + " ' has been modifield. Save changes ?",SWT.ICON_QUESTION, new String[] { "YES", "NO", "CANCEL" }, 0);
               
                int result = dialog.open();
                
                if (deviceTemplateView.deviceTemplate != null) {
                    if (result == Window.OK) {
                        DeviceModifyDAO modifyDao = new DeviceModifyDAO();
                        deviceTemplateView.deviceTemplate.setName(txtName.getText());
                        deviceTemplateView.deviceTemplate.setModelNumber(txtModelNum.getText());
                        deviceTemplateView.deviceTemplate.setNote(txtNotes.getText());
                        modifyDao.updateDeviceTemplate(deviceTemplateView.deviceTemplate);
                        itemDetail.setEnabled(false);
                    }
                }
                
            }
            
        });
    
        // Create Item Save And Close
        itemSaveClose = new ToolItem(toolBarDetailItem, SWT.PUSH);
        itemSaveClose.setImage(SAVE_AND_CLOSE);

        // Create Item Device Changed
        itemDeviceChange = new ToolItem(toolBarDetailItem, SWT.PUSH);
        itemDeviceChange.setImage(DEVICE_CHANGED);

        // Create Item Show Device
        itemShowDevice = new ToolItem(toolBarDetailItem, SWT.PUSH);
        itemShowDevice.setImage(SHOW_DEVICE);

        // Create Item Setting

        itemSetting = new ToolItem(toolBarDetailItem, SWT.PUSH);
        itemSetting.setImage(SETTING_DEVICE);

        // refresh TreeViewer
        deviceTemplateView.treeViewer.refresh();
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
        txtName.setText(deviceTemplateView.deviceTemplate.getName());
        txtManufact.setText(deviceTemplateView.deviceTemplate.getManufacturer());
        txtModelNum.setText(deviceTemplateView.deviceTemplate.getModelNumber());
        lbTypes.setText(deviceTemplateView.deviceTemplate.getTypeDeviceTemplate().getName());
        lbDeviceDriver.setText(deviceTemplateView.deviceTemplate.getDeviceDriver());
        txtNotes.setText(deviceTemplateView.deviceTemplate.getNote());
        itemDetail.setEnabled(false);
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
