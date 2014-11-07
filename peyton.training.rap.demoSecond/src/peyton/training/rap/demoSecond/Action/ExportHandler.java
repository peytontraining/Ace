package peyton.training.rap.demoSecond.Action;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The Class ExportHandler.
 * 
 * @author Ace
 */
public class ExportHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
	IWorkbenchWindow window = HandlerUtil
		.getActiveWorkbenchWindowChecked(event);
	MessageDialog.openInformation(window.getShell(), "Export",
		"Export Dialog");
	return null;
    }

}
