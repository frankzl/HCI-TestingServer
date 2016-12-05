import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import javax.swing.KeyStroke;

public class MessageListener implements TCPServer.OnMessageReceived{

	Robot robot;
	
	public MessageListener() throws AWTException{
		robot = new Robot();
	}
	
	public enum MsgType{
		VK_KEY, UNICODE, ASCII
	}
	
	@Override
	public void messageReceived(String message) {
		
		try {
			System.out.println(message);
			int keyEvent = 0;
			message = message.toUpperCase();
			
			String pureMessage = message.substring(1);
			if(pureMessage.length() > 1){
				if(pureMessage.charAt(0) == 'V'){
					Field f = KeyEvent.class.getField(pureMessage);
			        keyEvent = f.getInt(null);
				}
			}else{
				KeyStroke ks = KeyStroke.getKeyStroke(pureMessage.charAt(0), 0);
				System.out.println(ks.getKeyCode());
				keyEvent = ks.getKeyCode();
			}
			
			
			if(message.charAt(0) == '1'){
				robot.keyPress(keyEvent);
			}else{
				robot.keyRelease(keyEvent);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pressKey(String msg) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		int keyEvent = 0;
		if(getType(msg) == MsgType.VK_KEY){
			Field f = KeyEvent.class.getField(msg);
			keyEvent = f.getInt(null);
			robot.keyPress(keyEvent);
		}
		else if(getType(msg) == MsgType.UNICODE){
			MyClipboard.setMyClipboardText(msg);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
		}
	}
	
	public void releaseKey(String msg) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		int keyEvent = 0;
		if(getType(msg) == MsgType.VK_KEY){
			Field f = KeyEvent.class.getField(msg);
			keyEvent = f.getInt(null);
			robot.keyPress(keyEvent);
		}
		else if(getType(msg) == MsgType.UNICODE){
			MyClipboard.setMyClipboardText(msg);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
		}
	}
	
	
	
	public MsgType getType(String msg){
		switch(msg.charAt(0)){
			case 'V': return MsgType.VK_KEY;
			default: return MsgType.UNICODE;
		}
	}

}
