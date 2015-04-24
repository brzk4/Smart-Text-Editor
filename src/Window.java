

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.undo.UndoManager;

public class Window {

	public static JTextPane textPane = new JTextPane(); // whole text
	public static JFrame frame; // main frame
	public static JLabel lblNewLabel = new JLabel(); // position of caret label
	public static int caretPosition = 0; // current position of caret
	Engine engine = new Engine(); // all functionality(save,open, cut, paste
									// etc)
	UndoManager manager = new UndoManager(); // undo manager
	Action undoAction = new UndoAction(manager);
	Action redoAction = new RedoAction(manager);

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();

		frame.getContentPane().add(textPane, BorderLayout.CENTER);
		textPane.setText("");
		textPane.setForeground(Color.BLACK);
		textPane.setBackground(Color.WHITE);
		lblNewLabel.setText("Position: " + caretPosition);

		textPane.getDocument().addDocumentListener(new MyDocumentListener());
		textPane.getDocument().addUndoableEditListener(manager);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 452, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);

		panel_1.add(lblNewLabel);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				engine.newFile();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Save as");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.saveAs();
			}
		});

		JMenuItem mntmNewMenuItem2 = new JMenuItem("Save");
		mntmNewMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.save();
			}
		});

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Open");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.open();
			}
		});
		mntmNewMenuItem_8.setActionCommand("open");
		mnNewMenu.add(mntmNewMenuItem_8);
		mnNewMenu.add(mntmNewMenuItem2);
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);

		JMenuItem menu_item_undo = new JMenuItem("Undo");

		menu_item_undo.addActionListener(undoAction);

		JMenuItem menu_item_redo = new JMenuItem("Redo");

		menu_item_redo.addActionListener(redoAction);

		JMenuItem menu_item_cut = new JMenuItem("Cut");

		menu_item_cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.cut();
			}
		});

		JMenuItem menu_item_copy = new JMenuItem("Copy");

		menu_item_copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				engine.copy();
			}
		});

		JMenuItem menu_item_find = new JMenuItem("Find");

		menu_item_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				engine.find();
			}
		});

		JMenuItem menu_item_paste = new JMenuItem("Paste");

		menu_item_paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				engine.paste();

			}
		});

		mnNewMenu_1.add(menu_item_undo);
		mnNewMenu_1.add(menu_item_redo);
		mnNewMenu_1.add(menu_item_cut);
		mnNewMenu_1.add(menu_item_copy);
		mnNewMenu_1.add(menu_item_paste);
		mnNewMenu_1.add(menu_item_find);

		JMenu mnNewMenu_2 = new JMenu("View");
		mnNewMenu_2.setActionCommand("Nfd");
		menuBar.add(mnNewMenu_2);

		JMenu mnNewMenu_4 = new JMenu("Help");
		menuBar.add(mnNewMenu_4);

		JMenuItem mnNewMenu_5 = new JMenuItem("About");
		mnNewMenu_4.add(mnNewMenu_5);
		mnNewMenu_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Jakub Brzoza\n2015");

			}
		});

		JMenu mnNewMenu_3 = new JMenu("Style");
		mnNewMenu_2.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Night");
		mnNewMenu_3.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textPane.setForeground(Color.GREEN);
				textPane.setBackground(Color.BLACK);
				System.out.println("Changed to NIGHT");
			}
		});

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Day");
		mnNewMenu_3.add(mntmNewMenuItem_9);
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textPane.setForeground(Color.BLACK);
				textPane.setBackground(Color.WHITE);
				System.out.println("Changed to DAY");
			}
		});

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
	}

}
