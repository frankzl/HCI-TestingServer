import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class KeyEventTesting{
	
	public static void main(String []args) throws AWTException{
		JFrame f = new JFrame();

		f.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k){
				System.out.println(k.getKeyCode());
				try {
					Robot robot = new Robot();
					
					// Simulate a key press
					robot.delay(3000);
					int code = KeyEvent.VK_LEFT;
					robot.keyPress(code);
					robot.keyRelease(code);
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		f.setVisible(true);
		System.out.println("yo");
	}

	
}
