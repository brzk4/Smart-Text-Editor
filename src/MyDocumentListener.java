
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

class MyDocumentListener implements DocumentListener {
	String newline = "\n";
	Window win;
	int caretPosition;
	JLabel lblNewLabel;
	JTextPane textPane;
	
	public MyDocumentListener(Window window, int caretPos,JLabel label,JTextPane textp)
	{
		win = window;
		caretPosition = caretPos;
		lblNewLabel = label;
		textPane = textp;
	}

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

		caretPosition = textPane.getCaretPosition();
		lblNewLabel.setText("Position: " + caretPosition);

		try {
			String find = " class ";
			String wholeText = textPane.getText();
			for (int index = 0; index + find.length() < wholeText.length(); index++) {
				String match = textPane.getText(index, find.length());
				if (find.equals(match)) {

					System.out.print(".");
				}
			}
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
	}
}