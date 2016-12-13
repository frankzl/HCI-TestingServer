import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.KeyStroke;

public class MessageListener implements TCPServer.OnMessageReceived{

	Robot robot;
	
	public MessageListener() throws AWTException{
		robot = new Robot();
	}
	
	public enum MsgType{
		VK_KEY, UNICODE, ASCII, FUNC_KEY, SYMBOL, SEQUENCE
	}
	
	@Override
	public void messageReceived(String message) {
		try {
			System.out.println(message);
			if(getType(message) == MsgType.SEQUENCE){
				pressKeySequence(message.substring(2));
			}
			processMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pressKeySequence(String queue) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		String[] elements = queue.split(";");
		for(int i = 0; i < elements.length; i++){
			processMessage(elements[i]);
		}
	}
	
	public void processMessage(String message) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		if(message.charAt(0) == '1'){
			pressKey(message.substring(1));
		}else if(message.charAt(0) == '0'){
			releaseKey(message.substring(1));QQ
		}
	}
	
	public void pressKey(String msg) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		int keyEvent = 0;
		System.out.println("dis");
		switch(getType(msg)){
			case VK_KEY:
				msg = msg.toUpperCase();
				System.out.println("is");
				Field f = KeyEvent.class.getField(msg);
				keyEvent = f.getInt(null);
				robot.keyPress(keyEvent);
				break;
			case UNICODE:
				MyClipboard.setUnicodeText(msg);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				break;
			case SYMBOL:
				MyClipboard.setSymbolText(msg);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				break;
			default: break;
		}
	}
	
	public void releaseKey(String msg) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		int keyEvent = 0;
		if(getType(msg) == MsgType.VK_KEY){
			msg = msg.toUpperCase();
			Field f = KeyEvent.class.getField(msg);
			keyEvent = f.getInt(null);
			robot.keyRelease(keyEvent);
		}
		else if(getType(msg) == MsgType.UNICODE || getType(msg) == MsgType.SYMBOL){
			MyClipboard.restoreClipboard();
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
		}
	}
	
	public MsgType getType(String msg){
		switch(msg.charAt(0)){
			case 'V': return MsgType.VK_KEY;
			case 'F': return MsgType.FUNC_KEY; 
			case 'S': return MsgType.SYMBOL;
			case 'Q': return MsgType.SEQUENCE;
			default: return MsgType.UNICODE;
		}
	}

}
