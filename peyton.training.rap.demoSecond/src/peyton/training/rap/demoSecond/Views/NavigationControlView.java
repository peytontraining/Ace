package peyton.training.rap.demoSecond.Views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import peyton.training.rap.demoSecond.Common.Constant;
import peyton.training.rap.demoSecond.DbUtils.TreeViewDAO;
import peyton.training.rap.demoSecond.DbUtils.VersionDAO;
import peyton.training.rap.demoSecond.Dialog.DeleteVersionDialog;
import peyton.training.rap.demoSecond.Entites.Device;
import peyton.training.rap.demoSecond.Entites.Machine;
import peyton.training.rap.demoSecond.Entites.Type;
import peyton.training.rap.demoSecond.Entites.Version;

public class NavigationControlView extends ViewPart {

    public final static String VIEW_ID = "peyton.training.rap.demoSecond.Views.NavigationForm";

    // Version properties
    /** The txt version. */
    public Text txtVersion;

    /** The txt project. */
    private Text txtProject;

    /** The txt deploy time. */
    private Text txtDeployTime;

    /** The txt deploy source. */
    private Text txtDeploySource;

    /** The txt save time. */
    private Text txtSaveTime;

    /** The txt target version. */
    private Text txtTargetVersion;

    // Project properties
    public Text txtName;
    private Button radioUUID, radioHostPost;
    private Text txtGateHost;
    private Combo cbGatePort;
    private Text txtUuid;
    private Combo cbLicense;
    private Button ckDeploy;
    private Text txtEditNote;

    // Common
    public TreeViewPart tree;
    private FormToolkit toolkit;
    private Section sectionVersion, sectionProject;
    private ScrolledForm scrollForm;
    private GridData gridData;
    private Composite versionComposite, projectComposite;
    private ToolItem itemVersion, itemProject;
    private ToolBar toolBarVersion, toolBarProject;
    private Version version;
    private Machine project;
    SimpleDateFormat formatDate = new SimpleDateFormat(Constant.DATE);

    public final Image SAVE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin(
            Constant.PAKAGE_PATH, Constant.SAVE_IMAGE).createImage();

    public static final ImageDescriptor NEW_PROJECT_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.NEW_PROJECT_IMAGE);

    public static final ImageDescriptor NEW_VERSION_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.NEW_VERSION_IMAGE);

    public static final ImageDescriptor SAVE_AS_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.SAVE_IMAGE);

    public static final ImageDescriptor DELETE_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.DELETE_IMAGE);

    @SuppressWarnings("static-access")
    @Override
    public void createPartControl(Composite parent) {
        tree = (TreeViewPart) PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage()
                .findView(tree.TREEVIEW_ID);

        parent.setLayout(new StackLayout());
        createSelectionListener();
        createVersionForm(parent);
        createProjectForm(parent);
        this.initContextMenu();
    }

    @Override
    public void setFocus() {

    }

    private void createSelectionListener() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        ISelectionService selectionService = window.getSelectionService();
        selectionService.addSelectionListener(new ISelectionListener() {

            @Override
            public void selectionChanged(IWorkbenchPart part,
                    ISelection selection) {
                versionComposite.setVisible(false);
                projectComposite.setVisible(false);
                IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                Object firstElement = structuredSelection.getFirstElement();
                if (firstElement != null) {
                    if (firstElement instanceof Version) {
                        Date date = new Date();
                        Calendar cal = Calendar.getInstance();
                        date = cal.getTime();
                        version = (Version) firstElement;
                        version.setDeployTime(date);
                        version.setSaveTime(date);
                        setDataVersion(version);
                        itemVersion.setEnabled(false);
                        versionComposite.setVisible(true);
                    } else if (firstElement instanceof Machine) {
                        project = (Machine) firstElement;
                        projectComposite.setVisible(true);
                        itemProject.setEnabled(false);
                        setDataProject(project);
                        itemProject.setEnabled(false);
                        projectComposite.setVisible(true);
                    }
                }
            }
        });
    }

    private void initContextMenu() {
        final MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.setVisible(true);
        menuMgr.addMenuListener(new IMenuListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void menuAboutToShow(IMenuManager manager) {

                IWorkbenchWindow window = PlatformUI.getWorkbench()
                        .getActiveWorkbenchWindow();
                ISelectionService selection = window.getSelectionService();
                IStructuredSelection structSelection = (IStructuredSelection) selection
                        .getSelection();
                Object element = structSelection.getFirstElement();

                IAction newProjectAction = new Action() {
                    private static final long serialVersionUID = 1L;

                    IWorkbenchWindow window = PlatformUI.getWorkbench()
                            .getActiveWorkbenchWindow();
                    IStructuredSelection selection = (IStructuredSelection) window
                            .getActivePage().getSelection(
                                    TreeViewPart.TREEVIEW_ID);
                    Object firstElement = selection.getFirstElement();

                    @Override
                    public void run() {
                        if (firstElement != null) {
                            if (firstElement instanceof Machine) {
                                Machine project = (Machine) firstElement;
                                // create New Version
                                Machine newProject = createNewProject(project);

                                // Create New Version
                                Version newVersion = createNewVersion(
                                        newProject, "1.0.0 *");
                                List<Version> versions = new ArrayList<>();
                                versions.add(newVersion);
                                newProject.setVersions(versions);

                                // Add new version to project
                                project.getType().getMachines()
                                        .add(0, newProject);

                                tree.treeviewer.refresh();

                            }
                        }
                    }
                };

                IAction newVersionAction = new Action() {

                    private static final long serialVersionUID = 1L;

                    IWorkbenchWindow window = PlatformUI.getWorkbench()
                            .getActiveWorkbenchWindow();
                    ISelectionService selection = window.getSelectionService();
                    IStructuredSelection structSelection = (IStructuredSelection) selection
                            .getSelection();
                    Object element = structSelection.getFirstElement();

                    @Override
                    public void run() {
                        if (element != null) {
                            if (element instanceof Version) {

                                String newName = getNewVersionName(version);

                                // Create new version.
                                Version newVersion = createNewVersion(
                                        version.getMachine(), newName);

                                // Add new version to project
                                version.getMachine().getVersions()
                                        .add(0, newVersion);

                                // Refresh TreeViewer
                                tree.treeviewer.refresh(project);

                            } else if (element instanceof Machine) {

                                version = project.getVersions().get(0);

                                String newName = getNewVersionName(version);

                                // Create new version.
                                Version newVersion = createNewVersion(project,
                                        newName);

                                // Add new version to project pointer.
                                project.getVersions().add(0, newVersion);

                                // Refresh TreeViewer
                                tree.treeviewer.refresh();
                            }
                        }
                    }

                };

                IAction saveAsAction = new Action() {

                    private static final long serialVersionUID = 1L;

                    IWorkbenchWindow window = PlatformUI.getWorkbench()
                            .getActiveWorkbenchWindow();
                    IStructuredSelection selection = (IStructuredSelection) window
                            .getActivePage().getSelection(
                                    TreeViewPart.TREEVIEW_ID);
                    Object element = selection.getFirstElement();

                    @Override
                    public void run() {
                        if (element != null) {
                            if (element instanceof Version) {
                                VersionDAO vsDao = new VersionDAO();
                                Version version = (Version) element;
                                Machine project = version.getMachine();
                                version.setMachine(project);
                                Version newVersion = createNewVersion(
                                        ((Version) element).getMachine(),
                                        "1.0.29");

                                List<Device> devices = ((Version) element)
                                        .getDevices();

                                List<Device> copyDevices = new ArrayList<>();

                                // for (Device device : devices) {
                                // Device d = new Device();
                                // d.setAppModule("App module");
                                // d.setId(0);
                                // d.setDeviceType("Device type");
                                // d.setManufacture("Manufacture");
                                // d.setName("Name Device");
                                // d.setPhysicalLocation("Physical Location");
                                // d.setVersion(newVersion);
                                // copyDevices.add(d);
                                // }
                                newVersion.setDevices(copyDevices);
                                version.setDevices(devices);
                                version.setTargetVersion(version
                                        .getTargetVersion());
                                version.setDeploySource(version
                                        .getDeploySource());
                                project.getVersions().add(version);
                                // Insert new version to DB
                                vsDao.AddnewVersion(version);
                                tree.treeviewer.refresh(project);
                            }
                        }
                    }
                };

                IAction deleteAction = new Action() {
                    private static final long serialVersionUID = 1L;

                    IWorkbenchWindow window = PlatformUI.getWorkbench()
                            .getActiveWorkbenchWindow();

                    IStructuredSelection structSelection = (IStructuredSelection) window
                            .getActivePage().getSelection(
                                    TreeViewPart.TREEVIEW_ID);

                    Object element = structSelection.getFirstElement();

                    @Override
                    public void run() {
                        Shell shell = window.getShell();
                        DeleteVersionDialog deleteDialog = new DeleteVersionDialog(
                                shell);
                        if (element != null && element instanceof Version) {
                            if (deleteDialog.open() == Window.OK) {
                                VersionDAO vsDao = new VersionDAO();
                                version = (Version) element;
                                vsDao.deleteVersion(version);
                                version.getMachine().removeVersion(version);
                                tree.treeviewer.refresh();
                            }
                        }
                    }
                };
                // Create Action Menu
                newProjectAction.setText("New Project");
                newProjectAction.setImageDescriptor(NEW_PROJECT_IMAGE);
                newVersionAction.setText("New Version");
                newVersionAction.setImageDescriptor(NEW_VERSION_IMAGE);
                saveAsAction.setText("Save As");
                saveAsAction.setImageDescriptor(SAVE_AS_IMAGE);
                deleteAction.setText("Delete");
                deleteAction.setImageDescriptor(DELETE_IMAGE);

                // set enable dependence on right mouse click
                if (element instanceof Type) {
                    newProjectAction.setEnabled(false);
                    newVersionAction.setEnabled(false);
                    saveAsAction.setEnabled(false);
                    deleteAction.setEnabled(false);
                } else if (element instanceof Machine) {
                    saveAsAction.setEnabled(false);
                    deleteAction.setEnabled(false);
                }
                // Add All action to menu
                menuMgr.add(newProjectAction);
                menuMgr.add(newVersionAction);
                menuMgr.add(saveAsAction);
                menuMgr.add(deleteAction);
            }
        });

        Menu menu = menuMgr.createContextMenu(tree.treeviewer.getTree());
        tree.treeviewer.getTree().setMenu(menu);
        getSite().registerContextMenu(menuMgr, tree.treeviewer);
    }

    /*
     * Initial new version. Set project data for new Version.
     */
    private Version createNewVersion(Machine project, String newName) {
        Version newVersion = new Version();
        newVersion.setDeploySource("");
        newVersion.setDeployTime(new Date());
        newVersion.setDevices(null);
        newVersion.setName(newName);
        newVersion.setMachine(project);
        newVersion.setSaveTime(new Date());
        newVersion.setTargetVersion("2.x");
        return newVersion;
    }

    private void createVersionForm(Composite parent) {

        versionComposite = new Composite(parent, SWT.BORDER);
        versionComposite.setLayout(new FillLayout());

        // Set Color to text Box
        Display display = Display.getCurrent();
        Color grayColor = display.getSystemColor(SWT.COLOR_GRAY);

        // Create Section
        toolkit = new FormToolkit(versionComposite.getDisplay());
        sectionVersion = toolkit.createSection(versionComposite,
                ExpandableComposite.TITLE_BAR);
        sectionVersion.setLayout(new FillLayout());
        sectionVersion.setText("Version Properties");

        /* Start Create Form */
        scrollForm = toolkit.createScrolledForm(sectionVersion);
        scrollForm.setExpandHorizontal(true);
        toolkit.decorateFormHeading(scrollForm.getForm());
        scrollForm.getBody().setLayout(new GridLayout(2, false));
        /* Grid Data */
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1);

        // Version
        toolkit.createLabel(scrollForm.getBody(), Constant.VERSION);
        txtVersion = toolkit.createText(scrollForm.getBody(), "", SWT.NONE);
        txtVersion.setLayoutData(gridData);

        // Project
        toolkit.createLabel(scrollForm.getBody(), Constant.PROJECT);
        txtProject = toolkit
                .createText(scrollForm.getBody(), "", SWT.READ_ONLY);
        txtProject.setLayoutData(gridData);
        txtProject.setBackground(grayColor);
        toolkit.createLabel(scrollForm.getBody(), Constant.DEPLOY_TIME);

        // Deploy Time
        txtDeployTime = toolkit.createText(scrollForm.getBody(), "",
                SWT.READ_ONLY);
        txtDeployTime.setLayoutData(gridData);
        txtDeployTime.setBackground(grayColor);

        // Deploy Source
        toolkit.createLabel(scrollForm.getBody(), Constant.DEPLOY_SOURCE);
        txtDeploySource = toolkit.createText(scrollForm.getBody(), "",
                SWT.READ_ONLY);
        txtDeploySource.setLayoutData(gridData);
        txtDeploySource.setBackground(grayColor);

        // Save Time
        toolkit.createLabel(scrollForm.getBody(), Constant.SAVE_TIME);
        txtSaveTime = toolkit.createText(scrollForm.getBody(), "",
                SWT.READ_ONLY);
        txtSaveTime.setLayoutData(gridData);
        txtSaveTime.setBackground(grayColor);

        // Target Version
        toolkit.createLabel(scrollForm.getBody(), Constant.TARGET_VERSION);
        txtTargetVersion = toolkit.createText(scrollForm.getBody(), "",
                SWT.READ_ONLY);
        txtTargetVersion.setLayoutData(gridData);
        txtTargetVersion.setBackground(grayColor);

        // Set scroll Form to section
        sectionVersion.setClient(scrollForm);

        // Create toolbar
        /* Start Create ToolBar And Item */
        toolBarVersion = new ToolBar(sectionVersion, SWT.FLAT | SWT.HORIZONTAL);
        itemVersion = new ToolItem(toolBarVersion, SWT.PUSH);
        itemVersion.setImage(SAVE_IMAGE);
        itemVersion.setToolTipText("Save (Ctrl + S)");
        itemVersion.setEnabled(false);
        itemVersion.addSelectionListener(new SelectionAdapter() {
            private static final long serialVersionUID = -102212312093090431L;
            
            IWorkbenchWindow window = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow();
            
            @Override
            public void widgetSelected(SelectionEvent e) {

                itemVersion.setEnabled(true);
                // Get Window
                Shell shell = window.getShell();
                MessageDialog dialog = new MessageDialog(shell,
                        " Confirm Save As", SAVE_IMAGE,
                        "Do you want to save version (" + txtVersion.getText() + ") ?", MessageDialog.CONFIRM, new String[] {
                                "YES", "NO", "CANCEL" }, 0);
                int result = dialog.open();
                
                if (version != null) {
                    if (result == Window.OK) {
                        VersionDAO versionDao = new VersionDAO();
                        version.setName(txtVersion.getText());
                        versionDao.updateVersion(version);
                        itemVersion.setEnabled(false);
                        tree.treeviewer.refresh();
                    }
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        
        txtVersion.addModifyListener(new ModifyListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void modifyText(ModifyEvent event) {
                itemVersion.setEnabled(true);
            }
        });
        
        sectionVersion.setTextClient(toolBarVersion);

        // Set Invisible Version Composite
        versionComposite.setVisible(false);
    }

    private void createProjectForm(final Composite parent) {
        projectComposite = new Composite(parent, SWT.BORDER);
        projectComposite.setLayout(new FillLayout());

        // Create Color
        Display display = Display.getCurrent();
        Color grayColor = display.getSystemColor(SWT.COLOR_GRAY);

        // Create Form ToolKit
        toolkit = new FormToolkit(projectComposite.getDisplay());

        // Creating the Screen
        sectionProject = toolkit.createSection(projectComposite,
                Section.TITLE_BAR);
        sectionProject.setText("Project Properties");
        sectionProject.setLayout(new FillLayout());

        // Create Scroll form inside section
        scrollForm = toolkit.createScrolledForm(sectionProject);
        scrollForm.setExpandHorizontal(true);
        scrollForm.getBody().setLayout(new GridLayout(2, false));

        // create Text Name
        toolkit.createLabel(scrollForm.getBody(), "Name: ");
        txtName = toolkit.createText(scrollForm.getBody(), "");
        txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // Create GateWay Users
        toolkit.createLabel(scrollForm.getBody(), "Gateway Users: ");
        Composite groupRadio = new Composite(scrollForm.getBody(), SWT.NONE);
        groupRadio.setLayout(new FillLayout());
        radioUUID = toolkit.createButton(groupRadio, "UUID", SWT.RADIO);
        radioUUID.setSelection(true);
        setRadioHostPost(toolkit.createButton(groupRadio, "Host/Port",
                SWT.RADIO));

        // Create Gateway Host
        toolkit.createLabel(scrollForm.getBody(), "Gateway Host:");
        txtGateHost = toolkit.createText(scrollForm.getBody(), "");
        txtGateHost.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // Create Gateway Port
        toolkit.createLabel(scrollForm.getBody(), "Gateway Post:");
        cbGatePort = new Combo(scrollForm.getBody(), SWT.READ_ONLY);
        cbGatePort.add("8080");
        cbGatePort.select(0);
        cbGatePort.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // Create Gateway UUID
        toolkit.createLabel(scrollForm.getBody(), "Gateway UUID:");
        txtUuid = toolkit.createText(scrollForm.getBody(), "");
        txtUuid.setLayoutData(new GridData(GridData.FILL_BOTH));

        // Create Licence
        toolkit.createLabel(scrollForm.getBody(), "Licence:");
        cbLicense = new Combo(scrollForm.getBody(), SWT.READ_ONLY);
        cbLicense.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // Create Checkbox Deployment Locked
        setCkDeploy(toolkit.createButton(scrollForm.getBody(),
                "Deployment locked", SWT.CHECK));
        toolkit.createLabel(scrollForm.getBody(), "");

        // Create TextArea Edit Notes
        txtEditNote = toolkit.createText(scrollForm.getBody(), "", SWT.MULTI
                | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
        gridData = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1);
        txtEditNote.setLayoutData(gridData);
        gridData.heightHint = txtEditNote.getLineHeight() * 5;
        txtEditNote.setBackground(grayColor);

        // Set client for scroll Form
        sectionProject.setClient(scrollForm);

        // Create Toolbar for Item Save
        /* Start Create ToolBar And Item */
        toolBarProject = new ToolBar(sectionProject, SWT.FLAT | SWT.HORIZONTAL);
        itemProject = new ToolItem(toolBarProject, SWT.PUSH);
        itemProject.setImage(SAVE_IMAGE);
        itemProject.setToolTipText("Save (Ctrl + S)");
        itemProject.setEnabled(false);
        itemProject.addSelectionListener(new SelectionAdapter() {
            private static final long serialVersionUID = -102212312093090431L;

            IWorkbenchWindow window = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow();
            @Override
            public void widgetSelected(SelectionEvent e) {
                itemProject.setEnabled(true);
                Shell shell = window.getShell();
                MessageDialog dialog = new MessageDialog(shell,
                        " Confirm Save As", SAVE_IMAGE,
                        "Do you want to save name project (" + txtName.getText() + ") ?", MessageDialog.CONFIRM, new String[] {
                                "OK", "CANCEL" }, 0);
                int result = dialog.open();
                if (project != null) {
                    if(result == Window.OK){
                        TreeViewDAO treeDAO = new TreeViewDAO();
                        project.setName(txtName.getText());
                        treeDAO.updateMachine(project);
                        itemProject.setEnabled(false);
                        tree.treeviewer.refresh();
                    }
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        txtName.addModifyListener(new ModifyListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void modifyText(ModifyEvent event) {
                itemProject.setEnabled(true);
            }
        });

        // Set tooBar to section
        sectionProject.setTextClient(toolBarProject);

        // Set invisible project Composite
        projectComposite.setVisible(false);
    }

    private void setDataVersion(Version version) {
        txtVersion.setText(version.getName());
        txtProject.setText(version.getMachine().getName());
        txtDeployTime.setText(formatDate.format(version.getDeployTime()));
        txtDeploySource.setText(version.getDeploySource());
        txtSaveTime.setText(formatDate.format(version.getSaveTime()));
        txtTargetVersion.setText(version.getTargetVersion());
    }

    private void setDataProject(Machine project) {
        txtName.setText(project.getName());
    }

    private String getNewVersionName(Version version) {
        Version latestVersion = version.getMachine().getVersions().get(0);
        String name = latestVersion.getName().split("\\.")[2];
        int number = Integer.valueOf(name);
        name = String.valueOf(++number);
        return "1.0.".concat(name).concat(" *");
    }

    private Machine createNewProject(Machine machine) {
        Machine newProject = new Machine();
        newProject.setName("UNKNOWN *");
        newProject.setType(machine.getType());
        newProject.setId(-1);
        return newProject;
    }

    public Button getCkDeploy() {
        return ckDeploy;
    }

    public void setCkDeploy(Button ckDeploy) {
        this.ckDeploy = ckDeploy;
    }

    public Button getRadioHostPost() {
        return radioHostPost;
    }

    public void setRadioHostPost(Button radioHostPost) {
        this.radioHostPost = radioHostPost;
    }
}