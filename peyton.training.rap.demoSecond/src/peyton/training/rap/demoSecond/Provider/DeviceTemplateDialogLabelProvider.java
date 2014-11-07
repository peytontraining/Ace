package peyton.training.rap.demoSecond.Provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import peyton.training.rap.demoSecond.Common.Constant;
import peyton.training.rap.demoSecond.Entites.DeviceTemplate;
import peyton.training.rap.demoSecond.Entites.TreeColumnParent;
import peyton.training.rap.demoSecond.Entites.TypeDeviceTemplate;

public class DeviceTemplateDialogLabelProvider extends LabelProvider {

    /** The Constant DVR_NVR_IMAGE. */
    public static final Image CCTV_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.CCTV_IMAGE_PATH).createImage();

    private static final long serialVersionUID = 1L;

    @Override
    public Image getImage(Object element) {
        if (element instanceof DeviceTemplate
                || element instanceof TreeColumnParent
                || element instanceof TypeDeviceTemplate) {
            return CCTV_IMAGE;
        }
        return null;
    }

    @Override
    public String getText(Object element) {
        if (element instanceof TreeColumnParent) {
            return ((TreeColumnParent) element).getName();
        } else if (element instanceof TypeDeviceTemplate) {
            return ((TypeDeviceTemplate) element).getName();
        } else if (element instanceof DeviceTemplate) {
            return ((DeviceTemplate) element).getName();
        }
        return null;
    }
}
