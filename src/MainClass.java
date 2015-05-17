
import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainClass {
	/**
	 * Launch the application.
	 */

	
	
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					JFrame frame = new JFrame();
					
					Window w = new Window(frame);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
