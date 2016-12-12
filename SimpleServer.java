import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import javax.swing.KeyStroke;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class SimpleServer {
	
	public static void main(String[] args) throws AWTException{
		System.out.println("yo");
		TCPServer mServer = new TCPServer(new MessageListener());
		mServer.start();
	}
}
