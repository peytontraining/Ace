package peyton.training.rap.demoSecond.Provider;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import peyton.training.rap.demoSecond.Entites.DeviceTemplate;
import peyton.training.rap.demoSecond.Entites.TreeColumnParent;
import peyton.training.rap.demoSecond.Entites.TypeDeviceTemplate;

public class DeviceTemplateDialogContentProvider implements ITreeContentProvider {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5778736282315203042L;

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return ((List<?>) inputElement).toArray();
    }

    @Override
    public Object getParent(Object element) {
        if (element != null && element instanceof TypeDeviceTemplate) {
            return ((TypeDeviceTemplate) element).getTreeColumnParent();
        } else if (element != null && element instanceof DeviceTemplate) {
            return ((DeviceTemplate) element).getTypeDeviceTemplate();
        }
        return null;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement != null && parentElement instanceof TreeColumnParent) {
            return ((TreeColumnParent) parentElement).getTypeDeviceTemplates().toArray();
        } else if (parentElement != null && parentElement instanceof TypeDeviceTemplate) {
            return ((TypeDeviceTemplate) parentElement).getDeviceTemplates().toArray();
        }

        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        if (element != null && element instanceof TreeColumnParent) {
            return ((TreeColumnParent) element).getTypeDeviceTemplates().size() > 0;
        } else if (element != null && element instanceof TypeDeviceTemplate) {
            return ((TypeDeviceTemplate) element).getDeviceTemplates().size() > 0;
        }
        return false;
    }
}
