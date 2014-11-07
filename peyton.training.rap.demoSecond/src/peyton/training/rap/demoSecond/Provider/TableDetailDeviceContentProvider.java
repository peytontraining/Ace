package peyton.training.rap.demoSecond.Provider;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import peyton.training.rap.demoSecond.Entites.Device;
import peyton.training.rap.demoSecond.Entites.DeviceTableDetail;

public class TableDetailDeviceContentProvider implements ITreeContentProvider {
    
    private static final long serialVersionUID = 1L;

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
        if (element != null && element instanceof DeviceTableDetail) {
                return ((DeviceTableDetail) element).getDevice();
        }
        return new Object();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement != null) {
            if (parentElement instanceof List) {
                return ((List<?>) parentElement).toArray();
            } else if (parentElement instanceof Device) {
                return ((Device) parentElement).getDeviceTableDetail().toArray();
            } 
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        if (element != null) {
            if (element instanceof List) {
                return ((List<?>) element).size() > 0;
            } else if (element instanceof Device) {
                return ((Device) element).getDeviceTableDetail().size() > 0;
            } 
        }
        return false;
    }
}
