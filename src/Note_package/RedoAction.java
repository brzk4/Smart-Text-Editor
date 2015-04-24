package Note_package;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;
 
public class RedoAction extends AbstractAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedoAction(UndoManager manager) {
      this.manager = manager;
    }

    public void actionPerformed(ActionEvent evt) {
      try {
        manager.redo();
      } catch (CannotRedoException e) {
        Toolkit.getDefaultToolkit().beep();
      }
    }

    private UndoManager manager;
  }