package peyton.training.rap.demoSecond.Provider;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import peyton.training.rap.demoSecond.Entites.Project;
import peyton.training.rap.demoSecond.Entites.Type;
import peyton.training.rap.demoSecond.Entites.Version;

public class TreeViewContentProvider implements ITreeContentProvider {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5778736282315203042L;

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return ((List<?>) inputElement).toArray();
    }

    @Override
    public Object getParent(Object element) {
        if (element != null && element instanceof Project) {
            return ((Project) element).getType();
        } else if (element != null && element instanceof Version) {
            return ((Version) element).getProject();
        }
        return null;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement != null && parentElement instanceof Type) {
            return ((Type) parentElement).getMachines().toArray();
        } else if (parentElement != null && parentElement instanceof Project) {
            return ((Project) parentElement).getVersions().toArray();
        }

        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        if (element != null && element instanceof Type) {
            return ((Type) element).getMachines().size() > 0;
        } else if (element != null && element instanceof Project) {
            return ((Project) element).getVersions().size() > 0;
        }
        return false;
    }
}
