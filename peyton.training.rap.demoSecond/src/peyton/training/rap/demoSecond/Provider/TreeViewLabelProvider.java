package peyton.training.rap.demoSecond.Provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import peyton.training.rap.demoSecond.Common.Constant;
import peyton.training.rap.demoSecond.Entites.Machine;
import peyton.training.rap.demoSecond.Entites.Type;
import peyton.training.rap.demoSecond.Entites.Version;

public class TreeViewLabelProvider extends LabelProvider {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3411700484032680305L;

    /** The type image. */
    public final Image TYPE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin(
            Constant.PAKAGE_PATH, Constant.COMPANY_GROUP).createImage();

    /** The machine image. */
    public final Image MACHINE_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.PROJECT_IMAGE).createImage();

    /** The version images. */
    public final Image VERSION_IMAGES = AbstractUIPlugin
            .imageDescriptorFromPlugin(Constant.PAKAGE_PATH,
                    Constant.VERSION_IMAGE).createImage();

    @Override
    public Image getImage(Object element) {
        if (element instanceof Type) {
            return TYPE_IMAGE;
        } else if (element instanceof Machine) {
            return MACHINE_IMAGE;
        } else if (element instanceof Version) {
            return VERSION_IMAGES;
        }
        return null;
    }

    @Override
    public String getText(Object element) {
        if (element instanceof Type) {
            return ((Type) element).getName();
        } else if (element instanceof Machine) {
            return ((Machine) element).getName();
        } else if (element instanceof Version) {
            return ((Version) element).getName();
        }
        return null;
    }
}
