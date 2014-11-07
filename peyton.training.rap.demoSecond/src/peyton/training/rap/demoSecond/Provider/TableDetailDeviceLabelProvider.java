package peyton.training.rap.demoSecond.Provider;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import peyton.training.rap.demoSecond.Entites.Device;
import peyton.training.rap.demoSecond.Entites.DeviceTableDetail;

public class TableDetailDeviceLabelProvider implements ITableLabelProvider {
    
    private static final long serialVersionUID = 1L;

    SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss.SSS");
    
    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    @Override
    public String getColumnText(Object element, int columnIndex) {
        for (int i = 0; i < columnIndex + 1; i++) {
            if (columnIndex == 0) {
                if (element instanceof Device) {
                    return ((Device) element).getName();
                }
            } else if (columnIndex == 1) {
                if (element instanceof DeviceTableDetail) {
                    return ((DeviceTableDetail) element).getValue();
                }
            } else if (columnIndex == 2) {
                if (element instanceof DeviceTableDetail) {
                    return "No";
                }
            } else if (columnIndex == 3) {
                if (element instanceof DeviceTableDetail) {
                    return ((DeviceTableDetail) element).getDescription();
                }
            } 
        }
        return null;
    }

    @Override
    public void addListener(ILabelProviderListener listener) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
    }
}
