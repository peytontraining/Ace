package peyton.training.rap.demoSecond.Action;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import peyton.training.rap.demoSecond.Dialog.DeviceTemplateDialog;


public class ShowDeviceTemplateAdd extends AbstractHandler{
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        Shell shell = window.getShell();
        DeviceTemplateDialog getDevice = new DeviceTemplateDialog(shell);
        getDevice.create();
        getDevice.open();
        
        return null;
    }
    
}
