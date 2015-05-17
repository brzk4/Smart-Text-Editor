
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

public class Engine {

	private boolean isSaved = false; // check if file is saved
	private JFileChooser chooser = new JFileChooser(); // file chooser

	private Window win;
	private JTextPane textPane;
	public Engine(Window window,JTextPane textp)
	{
		win = window;
		textPane = textp;
		
		
	}
	
	/**
	 * ask if want to save file and clean whole text
	 */
	void newFile() {

		
		
		if (!textPane.getText().isEmpty()) {
			int x = JOptionPane.showConfirmDialog(null,
					"Do you want to save your changes?");
			switch (x) {
			case 0:
				save();
				break;

			case 1:
				textPane.setText("");
				break;

			case 2:
				break;
			}
		}

	}

	/**
	 * saves the state of file, if not saved, saves to new .txt file
	 */
	void save() {
		if (!isSaved) {
			saveAs();
		}
		try {

			PrintWriter zapis = new PrintWriter(new File(chooser
					.getSelectedFile().getAbsolutePath()) + ".txt");
			zapis.println(textPane.getText());
			zapis.close();
		} catch (FileNotFoundException a) {
			System.out.println("nie dziala");
			JOptionPane.showMessageDialog(null, "Some error with saving");
		} catch (java.lang.NullPointerException a) {
			System.out.println("NullPointerExceptiom when saving");
		}

		isSaved = true;

	}

	/**
	 * saves whole text to .txt file
	 *
	 */
	void saveAs() {

		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				".txt files", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {

				PrintWriter zapis = new PrintWriter(new File(chooser
						.getSelectedFile().getAbsolutePath()));
				zapis.println(textPane.getText());
				zapis.close();
			} catch (FileNotFoundException a) {
				System.out.println("nie dziala");
				JOptionPane.showMessageDialog(null, "ERROR 707");
			}

		}

	}

	/**
	 * load text from text file
	 */
	void open() {

		if (!textPane.getText().isEmpty()) {
			int x = JOptionPane.showConfirmDialog(null,
					"Do you want to save your changes?");
			if (x == 0) {
				save();
			}
		}

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				".txt files", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			textPane.setText("");
			try {

				BufferedReader reader = new BufferedReader(new FileReader(
						chooser.getSelectedFile()));
				String line;
				while ((line = reader.readLine()) != null) {
					textPane.setText(textPane.getText() + "\n"
							+ line);

				}
				reader.close();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Some problem occurred trying to read ");

			}
		}
	}

	/**
	 * copy selected text
	 */

	void copy() {
		StringSelection stringSelection = new StringSelection(
				textPane.getText());
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}

	/**
	 * pasting text from system clipboard
	 */
	void paste() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		TransferHandler transferHandler = textPane.getTransferHandler();
		transferHandler
				.importData(textPane, clipboard.getContents(null));
	}

	/**
	 * cuting selected text
	 */

	void cut() {
		copy();
		textPane.replaceSelection("");
	}

	/**
	 * find word
	 */
	
	void find()
	{
		
		try {
			String word = JOptionPane.showInputDialog("Find:");
			String wholeText = textPane.getText();
			
			for (int index = 0; index + word.length() < wholeText.length(); index++) {
				String match = textPane.getText(index, word.length());
				if (word.equals(match)) {

					textPane.select(index, index+word.length());
					
					System.out.print(".");
				}
			}
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	/**
	 * Colour special words
	 */
	public void searchLine() {
		String wholeText = textPane.getText();
		char[] chars = wholeText.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			try {

				if (chars[i] == ' ' && chars[i + 1] == 'c'
						&& chars[i + 2] == 'l' && chars[i + 3] == 'a'
						&& chars[i + 4] == 's' && chars[i + 5] == 's'
						&& chars[i + 6] == ' ') {

				}

			} catch (ArrayIndexOutOfBoundsException a) {
				System.out.println("SearchLine: Out of the board");
			}
		}
	}

}
