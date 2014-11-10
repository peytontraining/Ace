package peyton.training.rap.demoSecond.Views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

public class AreaTemplateView extends ViewPart {

    // Declare
    private Combo combobox;
    private FormToolkit toolkit;
    private GridLayout grid;
    private GridData gridData;

    @Override
    public void createPartControl(Composite parent) {
        createAreaForm(parent);
    }

    private void createAreaForm(Composite parent) {

        grid = new GridLayout(2, false);
        parent.setLayout(grid);

        toolkit = new FormToolkit(parent.getDisplay());
        toolkit.createLabel(parent, "Template:");
        combobox = new Combo(parent, SWT.DROP_DOWN);
        combobox.add("Projects");
        combobox.add("Drivers");
        combobox.add("Areas Templates");
        combobox.select(0);

        gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        combobox.setLayoutData(gridData);

        toolkit.createLabel(parent, "Name:");
        toolkit.createText(parent, "").setLayoutData(gridData);

        toolkit.createLabel(parent, "Notes:");
        toolkit.createText(parent, "", SWT.MULTI).setLayoutData(gridData);

        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1);
        toolkit.createText(parent, "").setLayoutData(gridData);

    }

    @Override
    public void setFocus() {
    }

}
