package peyton.training.rap.demoSecond;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import peyton.training.rap.demoSecond.Common.Constant;

/**
 * Configures the perspective layout. This class is contributed through the
 * plugin.xml.
 */

public class DeviceTemplatesPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(false);
        layout.setFixed(true);
        
        // Top Left WinDow
        IFolderLayout topLeft = layout.createFolder(Constant.TOP_LEFT,
                IPageLayout.LEFT, 0.33f, editorArea);
        topLeft.addView(Constant.AREAS);

        // Bottom WinDow
        @SuppressWarnings("unused")
        IFolderLayout bottom = layout.createFolder(Constant.BOTTOM,
                IPageLayout.BOTTOM, 0.50f, editorArea);
        
        // Top Right WinDow
        IFolderLayout topRight = layout.createFolder(Constant.TOP_RIGHT,
                IPageLayout.RIGHT, 0.70f, editorArea);
        topRight.addView(Constant.DEVICE_TEMPLATES);
        
    }
}
