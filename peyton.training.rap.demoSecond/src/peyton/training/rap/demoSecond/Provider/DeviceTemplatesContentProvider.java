package peyton.training.rap.demoSecond.Provider;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import peyton.training.rap.demoSecond.Entites.DeviceTemplate;
import peyton.training.rap.demoSecond.Entites.TreeColumnParent;
import peyton.training.rap.demoSecond.Entites.TypeDeviceTemplate;

public class DeviceTemplatesContentProvider implements ITreeContentProvider {
    private static final long serialVersionUID = -833288932787462667L;

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    @Override
    public Object getParent(Object element) {
        if (element != null) {
            if (element instanceof TypeDeviceTemplate) {
                return ((TypeDeviceTemplate) element).getTreeColumnParent();
            } else if (element instanceof DeviceTemplate) {
                return ((DeviceTemplate) element).getTypeDeviceTemplate();
            }
        }
        return null;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement != null) {
            if (parentElement instanceof List) {
                return ((List<?>) parentElement).toArray();
            } else if (parentElement instanceof TreeColumnParent) {
                return ((TreeColumnParent) parentElement)
                        .getTypeDeviceTemplates().toArray();
            } else if (parentElement instanceof TypeDeviceTemplate) {
                return ((TypeDeviceTemplate) parentElement)
                        .getDeviceTemplates().toArray();
            }
        }
        return new Object[0];
    }

    @Override
    public boolean hasChildren(Object element) {
        if (element != null) {
            if (element instanceof List) {
                return ((List<?>) element).size() > 0;
            } else if (element instanceof TreeColumnParent) {
                return ((TreeColumnParent) element).getTypeDeviceTemplates()
                        .size() > 0;
            } else if (element instanceof TypeDeviceTemplate) {
                return ((TypeDeviceTemplate) element).getDeviceTemplates()
                        .size() > 0;
            }
        }
        return false;
    }
}
