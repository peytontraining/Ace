package peyton.training.rap.demoSecond.Views;

import java.text.SimpleDateFormat;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import peyton.training.rap.demoSecond.Common.Constant;
import peyton.training.rap.demoSecond.DbUtils.DeviceModifyDAO;
import peyton.training.rap.demoSecond.DbUtils.TreeViewDAO;
import peyton.training.rap.demoSecond.Entites.Device;
import peyton.training.rap.demoSecond.Provider.TableDetailDeviceContentProvider;
import peyton.training.rap.demoSecond.Provider.TableDetailDeviceLabelProvider;

public class DeviceModifyDetailView extends ViewPart implements ISaveablePart {

    public static String VIEW_ID = "peyton.training.rap.demoSecond.views.DeviceModifyDetail";

    private TabFolder tbFolder;
    private TabItem tbDetailItem, tbConfigItem, tbTestItem;
    private FormToolkit toolkit;
    private ScrolledForm scrollForm;
    private Display display = Display.getCurrent();
    private GridData gridData;
    private GridLayout gridLayout;
    private Text txtName, txtManufacture, txtModelNumber, txtTypes, txtNotes,txtVersion, txtLastModifield;
    private Label icon;
    private Hyperlink masterTemplate;
    private Device device;
    private Section section;
    private ToolBar toolBarDetailItem;
    private ToolItem itemDetail, itemSaveClose, itemDeviceChange, itemShowDevice, itemSetting;
    private Composite toolBarComposite;
    public DeviceTableSelectionView deviceTable;
    private boolean isDirty;
    private boolean saveAsAllowed;
    private boolean saveNeeded;

    SimpleDateFormat formatDate = new SimpleDateFormat(Constant.DATE);

    /* Declare Varialbe for tree */
    private TreeViewer treeViewer;

    // Create IMAGE
    public final Image SAVE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin(
            Constant.PAKAGE_PATH, Constant.SAVE_IMAGE).createImage();

    public static final Image CCTV_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.CCTV_IMAGE_PATH).createImage();
    
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
    
    //Create Header Content Of Column
    private static final String[] COLUMN_PROPERTIES = { "Name", "Value",
            "Mandatory", "Description" };

    @Override
    public void createPartControl(Composite parent) {
    	
    	GridLayout layout = new GridLayout(1,false);
    	parent.setLayout(layout);
        
        // Get Section from Device Table And active Page
        IWorkbenchWindow window = getSite().getWorkbenchWindow();
        window.getActivePage();

        // Get Device Table Selection View
        deviceTable = (DeviceTableSelectionView) PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage()
                .findView(DeviceTableSelectionView.VIEW_ID);
        
        // Create Color
        Color darkColor = display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW);
        
        //Crate Toolbar
        createToolbar(parent);
      
		// Create tab Folder
		tbFolder = new TabFolder(parent, SWT.NONE);
		tbFolder.setSize(100, 100);
		tbFolder.setForeground(darkColor);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tbFolder.setLayoutData(gridData);
      
        // Create tbItem
        createDeviceDetailTbItem(parent);

        // Create Configure tbItem
        createConfigureTbItem(parent);

        // Create Test tbItem
        createTestTbItem(parent);
        
    }
    
    public void createDeviceDetailTbItem(Composite parent) {
        Color grayColor = display.getSystemColor(SWT.COLOR_GRAY);

        tbDetailItem = new TabItem(tbFolder, SWT.BORDER);
        tbDetailItem.setText("Details");
        tbDetailItem.setToolTipText("Detail Device Modify");

        // Crreate Form Toolkit
        toolkit = new FormToolkit(parent.getDisplay());

        // Create Scroll Form
        scrollForm = toolkit.createScrolledForm(tbFolder);
        scrollForm.getBody().setLayout(new GridLayout(4, false));

        // Create Icon Image
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Icon:");
        icon = toolkit.createLabel(scrollForm.getBody(), "");
        icon.setLayoutData(gridData);

        // Create txtName
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Name:");
        txtName = toolkit.createText(scrollForm.getBody(), "");
        txtName.setLayoutData(gridData);

        // Create txtManufacture
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Manufacture:");
        txtManufacture = toolkit.createText(scrollForm.getBody(), "",
                SWT.READ_ONLY);
        txtManufacture.setLayoutData(gridData);
        txtManufacture.setBackground(grayColor);

        // Create txtModelNumber
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Model Number:");
        txtModelNumber = toolkit.createText(scrollForm.getBody(), "",
                SWT.READ_ONLY);
        txtModelNumber.setLayoutData(gridData);
        txtModelNumber.setBackground(grayColor);

        // Create Hyperlink Master Template
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Master Template:");
        masterTemplate = toolkit.createHyperlink(scrollForm.getBody(), "",
                SWT.NONE);
        masterTemplate.setLayoutData(gridData);

        // Create Types
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Types:");
        txtTypes = toolkit.createText(scrollForm.getBody(), "", SWT.READ_ONLY);
        txtTypes.setLayoutData(gridData);
        txtTypes.setBackground(grayColor);

        // Create Notes
        gridData = new GridData(SWT.FILL, SWT.MULTI, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Notes");
        txtNotes = toolkit.createText(scrollForm.getBody(), "");
        gridData.heightHint = txtNotes.getLineHeight() * 5;
        txtNotes.setLayoutData(gridData);

        // Create Version
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Version:");
        txtVersion = toolkit
                .createText(scrollForm.getBody(), "", SWT.READ_ONLY);
        txtVersion.setLayoutData(gridData);
        txtVersion.setBackground(grayColor);

        // Create Last Modify
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(scrollForm.getBody(), "Last Modified:");
        txtLastModifield = toolkit.createText(scrollForm.getBody(), "",
                SWT.READ_ONLY);
        txtLastModifield.setLayoutData(gridData);
        txtLastModifield.setBackground(grayColor);

        // Set scroll Form to tbItemDetail
        tbDetailItem.setControl(scrollForm);
        
        //Create Modify Listener
        txtName.addModifyListener(new ModifyListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void modifyText(ModifyEvent event) {
                itemDetail.setEnabled(true);
                setDirty(true);
            }
        });

        txtNotes.addModifyListener(new ModifyListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void modifyText(ModifyEvent event) {
                itemDetail.setEnabled(true);
                setDirty(true);
            }
        });
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
                
                itemDetail.setEnabled(true);
                Shell shell = window.getShell();
                MessageDialog dialog = new MessageDialog(shell, "Save Resource", SELECT_DEVICE_IMAGE, 
                     " ' " + txtName.getText() + " ' has been modifield. Save changes ?" , 
                     SWT.ICON_QUESTION, new String[] {
                             "YES", "NO", "CANCEL" }, 0);
                int result = dialog.open();
                
                if (deviceTable.device != null) {
                    if(result == Window.OK){
                        DeviceModifyDAO modifyDao = new DeviceModifyDAO();
                        deviceTable.device.setName(txtName.getText());
                        deviceTable.device.setNotes(txtNotes.getText());
                        modifyDao.updateDevice(deviceTable.device);
                        itemDetail.setEnabled(false);
                        setDirty(false);
                    }
                }
                setSaveAsAllowed(itemDetail.getSelection());
            }

        });
        
        //Create Item Save And Close
        itemSaveClose = new ToolItem(toolBarDetailItem, SWT.PUSH);
        itemSaveClose.setImage(SAVE_AND_CLOSE);
         
        //Create Item Device Changed 
        itemDeviceChange = new ToolItem(toolBarDetailItem, SWT.PUSH);
        itemDeviceChange.setImage(DEVICE_CHANGED);
        
        //Create Item Show Device
        itemShowDevice = new ToolItem(toolBarDetailItem, SWT.PUSH);
        itemShowDevice.setImage(SHOW_DEVICE);
        
        //Create Item Setting
        
        itemSetting = new ToolItem(toolBarDetailItem, SWT.PUSH);
        itemSetting.setImage(SETTING_DEVICE);
        
        //refresh Table
        deviceTable.tableViewer.refresh();
    }

    public void createConfigureTbItem(Composite parent) {

        // Create Tab Item Detail
        tbConfigItem = new TabItem(tbFolder, SWT.BORDER);
        tbConfigItem.setText("Configure");
        tbConfigItem.setToolTipText("Configure");

        // Create toolKit && section
        toolkit = new FormToolkit(tbFolder.getDisplay());
        int sectionStyle = Section.TITLE_BAR;
        section = toolkit.createSection(tbFolder, sectionStyle);
        section.setLayout(new FillLayout());
        section.setText("Configuration Properties");

        /* Start Create TreeColum */
        // Create Layout for tree
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1);
        int style = SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL
                | SWT.V_SCROLL;
        Tree tree = new Tree(section, style);
        tree.setLayoutData(layoutData);

        tree.setHeaderVisible(true);
        tree.setLinesVisible(true);

        // Create columns for tree.
        createColumns(tree);

        // Create a TreeViewer.
        treeViewer = new TreeViewer(tree);

        // Set ContentProvider and LabelProvider for treeViewer.
        treeViewer.setContentProvider(new TableDetailDeviceContentProvider());
        treeViewer.setLabelProvider(new TableDetailDeviceLabelProvider());

        // Set data for treeViewer.
        TreeViewDAO getAllData = new TreeViewDAO();
        treeViewer.setInput(getAllData.getAllDevice());
        /* End Create Tree Colum */

        // Set tree to client
        section.setClient(tree);

        // Set section to tbConfigItem
        tbConfigItem.setControl(section);
    }

    public void createTestTbItem(Composite parent) {

        // Create Tab Item Detail
        tbTestItem = new TabItem(tbFolder, SWT.BORDER);
        tbTestItem.setText("Test");
        tbTestItem.setToolTipText("Test");

        // Create toolKit && section
        toolkit = new FormToolkit(tbFolder.getDisplay());
        int sectionStyle = Section.TITLE_BAR;
        section = toolkit.createSection(tbFolder, sectionStyle);
        section.setLayout(new FillLayout());
        section.setText("Test Properties");

        // Set section to tbConfigItem
        tbTestItem.setControl(section);
    }

    private void createColumns(Tree parent) {
        for (String title : COLUMN_PROPERTIES) {
            TreeColumn column = new TreeColumn(parent, SWT.NONE);
            column.setText(title);
            column.setWidth(150);
        }
    }

    // Set Data to device
    public void setDataDevice(Device device) {
        icon.setImage(CCTV_IMAGE);
        txtName.setText(device.getName());
        txtManufacture.setText(device.getManufacture());
        txtModelNumber.setText(device.getModelNumber());
        masterTemplate.setText(device.getName() + " " + "Change Device Template");
        txtTypes.setText(device.getDeviceType());
        txtNotes.setText(device.getNotes());
        txtVersion.setText(device.getVersionContent());
        txtLastModifield.setText(formatDate.format(device.getLastModifield()));
        itemDetail.setEnabled(false);
        setDirty(false);
    }
    
    /* Change Title Name View Part - Start */
    
    @Override
    public void init(IViewSite site) throws PartInitException {
        deviceTable = (DeviceTableSelectionView) PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage()
                .findView(DeviceTableSelectionView.VIEW_ID);
        setSite(site);
        setPartName("");
    }
    
    @Override
    protected void setPartName(String partName) {
        partName = deviceTable.device.getName();
        super.setPartName(partName);
    }
    /* Change Title Name View Part - End */
    
    @Override
    public void setFocus() {
    }

    // Create "*" While modifying Devide Detail
    @Override
    public void doSave(IProgressMonitor monitor) {
    }

    @Override
    public void doSaveAs() {
    }

    @Override
    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public boolean isSaveOnCloseNeeded() {
        return saveNeeded;
    }

    public boolean isSaveAsAllowed() {
        return saveAsAllowed;
    }

    public void setDirty(boolean isDirty) {
        this.isDirty = isDirty;
        firePropertyChange(PROP_DIRTY);
    }

    public void setSaveAsAllowed(boolean isSaveAsAllowed) {
        this.saveAsAllowed = isSaveAsAllowed;
    }

    public void setSaveNeeded(boolean isSaveOnCloseNeeded) {
        this.saveNeeded = isSaveOnCloseNeeded;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

}
