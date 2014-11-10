package peyton.training.rap.demoSecond.Views;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import peyton.training.rap.demoSecond.Common.Constant;
import peyton.training.rap.demoSecond.DbUtils.DeviceTemplateDAO;
import peyton.training.rap.demoSecond.Entites.DeviceTemplate;
import peyton.training.rap.demoSecond.Provider.DeviceTemplatesContentProvider;
import peyton.training.rap.demoSecond.Provider.DeviceTemplatesLabelProvider;

public class DeviceTemplatesView extends ViewPart implements
        IDoubleClickListener {
    
    public static final String VIEW_ID = "peyton.training.rap.demoSecond.DeviceTemplates";
    
    /** The Constant UP_IMAGE. */
    public static final Image UP_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.UP_IMAGE_PATH).createImage();

    /** The Constant DOWN_IMAGE. */
    public static final Image DOWN_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.DOWN_IMAGE_PATH).createImage();

    private static final String[] COLUMN_PROPERTIES = { "Name",
            "Last Modified", "Manufacture", "Model Number", "Version" };

    SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss.SSS");

    public TreeViewer treeViewer;
    
    private Text txtFilter, txtFind;

    private Button btnUp, btnDown;

    private Filter filter;
    
    public DeviceTemplate deviceTemplate;

    @Override
    public void createPartControl(Composite parent) {
        //Create Layout Parent
        GridLayout layout = new GridLayout(9, false);
        parent.setLayout(layout);
        
        //Create Filter
        createFilter(parent);

        //Create Find
        createFind(parent);

        // Create Layout for tree
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 9, 1);
        int style = SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL
                | SWT.V_SCROLL;
        Tree tree = new Tree(parent, style);
        tree.setLayoutData(layoutData);

        tree.setHeaderVisible(true);
        tree.setLinesVisible(true);

        // Create columns for tree.
        createColumns(tree);

        // Create a TreeViewer.
        treeViewer = new TreeViewer(tree);

        // Set ContentProvider and LabelProvider for treeViewer.
        treeViewer.setContentProvider(new DeviceTemplatesContentProvider());
        treeViewer.setLabelProvider(new DeviceTemplatesLabelProvider());

        // Set data for treeViewer.
        DeviceTemplateDAO getAllData = new DeviceTemplateDAO();
        treeViewer.setInput(getAllData.getAllTreeColumn());

        // Create and set filter for treeViewer.
        filter = new Filter();
        treeViewer.addFilter(filter);
        
        treeViewer.addDoubleClickListener(this);

        getSite().setSelectionProvider(treeViewer);
    }
    
    private void createFilter(Composite parent) {
        Label label = new Label(parent, SWT.NONE);
        label.setText("Filter:");
        filter = new Filter();
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, false, 2,1);
        txtFilter = new Text(parent, SWT.BORDER | SWT.SEARCH);
        txtFilter.setLayoutData(layoutData);
        txtFilter.addModifyListener(new ModifyListener() {
            private static final long serialVersionUID = 4906617449904102933L;
            @Override
            public void modifyText(ModifyEvent event) {
                filter.setPattern(txtFilter.getText());
                treeViewer.refresh();
            }
        });
    }
    
    class Filter extends PatternFilter {
        private static final long serialVersionUID = 1L;
        @Override
        protected boolean isLeafMatch(Viewer viewer, Object element) {
            TreeViewer treeViewer = (TreeViewer) viewer;
            int numberOfColumns = treeViewer.getTree().getColumnCount();
            ITableLabelProvider labelProvider = (ITableLabelProvider) treeViewer.getLabelProvider();
            boolean isMatch = false;
            for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
                String labelText = labelProvider.getColumnText(element, columnIndex);
                isMatch |= wordMatches(labelText);
            }
            return isMatch;
        }
    }
    
    private void createFind(Composite parent) {
        
        GridData layoutData = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
        Label label = new Label(parent, SWT.NONE);
        label.setText("Find:");
        label.setLayoutData(layoutData);
        layoutData = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);
        int style = SWT.BORDER | SWT.SEARCH;
        txtFind = new Text(parent, style);
        txtFind.setLayoutData(layoutData);
        btnUp = new Button(parent, SWT.NONE);
        btnUp.setImage(UP_IMAGE);
        btnDown = new Button(parent, SWT.NONE);
        btnDown.setImage(DOWN_IMAGE);
    }
    
    private void createColumns(Tree parent) {
        for (String title : COLUMN_PROPERTIES) {
            TreeColumn column = new TreeColumn(parent, SWT.NONE);
            column.setText(title);
            column.setWidth(100);
        }
    }

    @Override
    public void doubleClick(DoubleClickEvent event) {
        IWorkbenchWindow iWindow = getSite().getWorkbenchWindow();
        
        IWorkbenchPage page = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage();
        
        ISelection selection = event.getSelection();
        IStructuredSelection iSelection = (IStructuredSelection) selection;
        Object firstElement = iSelection.getFirstElement();
        
        if (firstElement instanceof DeviceTemplate) {
            try {
                deviceTemplate = (DeviceTemplate) firstElement;
                int secondaryId = deviceTemplate.getId();
                iWindow.getActivePage().showView(DeviceTemplatesDetailView.VIEW_ID,String.valueOf(secondaryId),IWorkbenchPage.VIEW_VISIBLE);
                
                ((DeviceTemplatesDetailView) page.findViewReference(DeviceTemplatesDetailView.VIEW_ID,
                        String.valueOf(secondaryId)).getView(true)).setDataDeviceTemplate((DeviceTemplate) firstElement);
                
            } catch (PartInitException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setFocus() {
        treeViewer.getTree().setFocus();
    }
}
