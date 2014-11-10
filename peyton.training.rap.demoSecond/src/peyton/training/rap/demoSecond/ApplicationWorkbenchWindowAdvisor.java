package peyton.training.rap.demoSecond;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import peyton.training.rap.demoSecond.Common.Constant;

/**
 * Configures the initial size and appearance of a workbench window.
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    /**
     * Instantiates a new application workbench window advisor.
     * 
     * @param configurer
     *            the configurer
     */
    public ApplicationWorkbenchWindowAdvisor(
            IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    @Override
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setShowMenuBar(false);
        configurer.setShowStatusLine(false);
        configurer.setShowPerspectiveBar(true);
        configurer.setShellStyle(SWT.NO_TRIM);

        IPreferenceStore prefStore = PlatformUI.getPreferenceStore();
        prefStore.setValue(IWorkbenchPreferenceConstants.SHOW_OPEN_ON_PERSPECTIVE_BAR, false);
        prefStore.setValue(IWorkbenchPreferenceConstants.PERSPECTIVE_BAR_EXTRAS,
                          Constant.PROJECT_PERPECTIVE + ","
                        + Constant.TEMPLATE_PERPECTIVE + ","
                        + Constant.SYSTEM_PERPECTIVE + ","
                        + Constant.USER_PERPECTIVE + ","
                        + Constant.LOGOUT_PERPECTIVE);
    }
    
    
    @Override
    public void postWindowOpen() {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
                .setFullScreen(true);
    }

    @Override
    public boolean isDurableFolder(String perspectiveId, String folderId) {
        if("peyton.training.rap.demoSecond.perspective".equals(perspectiveId) &&"bottom".equals(folderId)){
            return true;
        }else if("peyton.training.rap.demoSecond.templates".equals(perspectiveId) && "bottom".equals(folderId)){
            return true;
        }
        return super.isDurableFolder(perspectiveId, folderId);
    }
}
