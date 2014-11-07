package peyton.training.rap.demoSecond.Action;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The Class AboutAction.
 * 
 * @author Ace
 */
public class AboutAction extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
	IWorkbenchWindow window = HandlerUtil
		.getActiveWorkbenchWindowChecked(event);
	String title = "About RAP";
	String msg = "RAP Mail template created by PDE.\n\n"
		+ "You can learn more about RAP at:\n\n"
		+ "http://www.eclipse.org/rap";
	MessageDialog.openInformation(window.getShell(), title, msg);
	return null;
    }

}
