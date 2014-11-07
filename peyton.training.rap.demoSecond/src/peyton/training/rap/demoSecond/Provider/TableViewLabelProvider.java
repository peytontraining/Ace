package peyton.training.rap.demoSecond.Provider;

import java.awt.TextField;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import peyton.training.rap.demoSecond.Common.Constant;
import peyton.training.rap.demoSecond.Entites.Device;

public class TableViewLabelProvider extends LabelProvider implements
        ITableLabelProvider {
    private static final long serialVersionUID = 3997124367771097992L;

    /** The txt filter. */
    TextField txtFilter;
    /** The Constant IP_CAMERA_IMAGE. */
    public static final Image IP_CAMERA_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.CAMERA_IMAGE_PATH).createImage();
    /** The Constant FIREPLACE_CONTROLLER_IMAGE. */
    public static final Image FIREPLACE_CONTROLLER_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.FIREPLACE_IMAGE_PATH).createImage();
    /** The Constant DVR_NVR_IMAGE. */
    public static final Image DVR_NVR_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.CCTV_IMAGE_PATH).createImage();

    /**
     * The Class ViewContentProvider.
     */

    public TableViewLabelProvider() {
    }

    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        Device row = (Device) element;
        if (0 == columnIndex) {
            if (row.getAppModule().equals(Constant.CCTV)) {
                return IP_CAMERA_IMAGE;
            } else if (row.getAppModule().equals(Constant.FIRE_PLACE)) {
                return FIREPLACE_CONTROLLER_IMAGE;
            }
        }

        return null;
    }

    @Override
    public String getColumnText(Object element, int columnIndex) {
        Device row = (Device) element;
        if (columnIndex == 0) {
            return row.getDeviceType();
        } else if (columnIndex == 1) {
            return row.getName();
        } else if (columnIndex == 2) {
            return row.getRooms();
        } else if (columnIndex == 3) {
            return row.getAppModule();
        } else if (columnIndex == 4) {
            return row.getControlType();
        }
        return null;
    }
}
