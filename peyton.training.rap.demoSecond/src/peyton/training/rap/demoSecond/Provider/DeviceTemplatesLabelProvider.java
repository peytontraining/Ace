package peyton.training.rap.demoSecond.Provider;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import peyton.training.rap.demoSecond.Common.Constant;
import peyton.training.rap.demoSecond.Entites.DeviceTemplate;
import peyton.training.rap.demoSecond.Entites.TreeColumnParent;
import peyton.training.rap.demoSecond.Entites.TypeDeviceTemplate;

public class DeviceTemplatesLabelProvider implements ITableLabelProvider {

    private static final long serialVersionUID = -889220201270235236L;

    /** The Constant DVR_NVR_IMAGE. */
    public static final Image CCTV_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.CCTV_IMAGE_PATH).createImage();

    SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss.SSS");

    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        if (columnIndex == 0) {
            if (element instanceof TreeColumnParent) {
                if (((TreeColumnParent) element).getName().intern() == Constant.CCTV) {
                    return CCTV_IMAGE;
                }
            }
            if (element instanceof TypeDeviceTemplate) {
                if ((((TypeDeviceTemplate) element).getName().intern() == Constant.IP_CAMERAS)
                        || ((((TypeDeviceTemplate) element).getName().intern() == Constant.DVR_NVR))) {
                    return CCTV_IMAGE;
                }
            }
            if (element instanceof DeviceTemplate) {
                if (((DeviceTemplate) element).getManufacturer().intern() == Constant.AXIS) {
                    return CCTV_IMAGE;
                }
            }
        }
        return null;
    }

    @Override
    public String getColumnText(Object element, int columnIndex) {
        for (int i = 0; i < columnIndex + 1; i++) {
            if (columnIndex == 0) {
                if (element instanceof TreeColumnParent) {
                    return ((TreeColumnParent) element).getName();
                }
                if (element instanceof TypeDeviceTemplate) {
                    return ((TypeDeviceTemplate) element).getName();
                }
                if (element instanceof DeviceTemplate) {
                    return ((DeviceTemplate) element).getName();
                }
            } else if (columnIndex == 1) {
                if (element instanceof DeviceTemplate) {
                    return dateFormat.format(((DeviceTemplate) element)
                            .getLastModifield());
                }
            } else if (columnIndex == 2) {
                if (element instanceof DeviceTemplate) {
                    return ((DeviceTemplate) element).getManufacturer();
                }
            } else if (columnIndex == 3) {
                if (element instanceof DeviceTemplate) {
                    return ((DeviceTemplate) element).getModelNumber();
                }
            } else if (columnIndex == 4) {
                if (element instanceof DeviceTemplate) {
                    return ((DeviceTemplate) element).getVersion();
                }
            }
        }
        return null;
    }

    /**
     * Adds a listener to this label provider. Has no effect if an identical
     * listener is already registered.
     * <p>
     * Label provider listeners are informed about state changes that affect the
     * rendering of the viewer that uses this label provider.
     * </p>
     * 
     * @param listener a label provider listener
     */
    @Override
    public void addListener(ILabelProviderListener listener) {

    }

    /**
     * Disposes of this label provider. When a label provider is attached to a
     * viewer, the viewer will automatically call this method when the viewer is
     * being closed. When label providers are used outside of the context of a
     * viewer, it is the client's responsibility to ensure that this method is
     * called when the provider is no longer needed.
     */
    @Override
    public void dispose() {

    }

    /**
     * Returns whether the label would be affected by a change to the given
     * property of the given element. This can be used to optimize a
     * non-structural viewer update. If the property mentioned in the update
     * does not affect the label, then the viewer need not update the label.
     * 
     * @param element the element
     * @param property the property
     * @return <code>true</code> if the label would be affected, and
     *         <code>false</code> if it would be unaffected
     */
    @Override
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    /**
     * Removes a listener to this label provider. Has no effect if an identical
     * listener is not registered.
     * 
     * @param listenera label provider listener
     */
    @Override
    public void removeListener(ILabelProviderListener listener) {
    }
}
