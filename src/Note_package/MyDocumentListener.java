package Note_package;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

class MyDocumentListener implements DocumentListener {
	String newline = "\n";

	public void insertUpdate(DocumentEvent e) {
		updateLog(e, "inserted into");
	}

	public void removeUpdate(DocumentEvent e) {
		updateLog(e, "removed from");
	}

	public void changedUpdate(DocumentEvent e) {
		// Plain text components do not fire these events
	}

	public void updateLog(DocumentEvent e, String action) {

		Window.caretPosition = Window.textPane.getCaretPosition();
		Window.lblNewLabel.setText("Position: " + Window.caretPosition);

		try {
			String find = " class ";
			String wholeText = Window.textPane.getText();
			for (int index = 0; index + find.length() < wholeText.length(); index++) {
				String match = Window.textPane.getText(index, find.length());
				if (find.equals(match)) {

					System.out.print(".");
				}
			}
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
	}
}