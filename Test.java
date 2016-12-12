import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.KeyStroke;

import eneter.messaging.diagnostic.EneterTrace;
import eneter.messaging.endpoints.typedmessages.*;
import eneter.messaging.messagingsystems.androidusbcablemessagingsystem.AndroidUsbCableMessagingFactory;
import eneter.messaging.messagingsystems.messagingsystembase.*;
import eneter.messaging.messagingsystems.tcpmessagingsystem.TcpMessagingSystemFactory;
import eneter.net.system.EventHandler;

public class Test {
	private static ISyncDuplexTypedMessageSender<String, String> mySender;

	public static void main(String[] args) {
		KeyStroke ks = KeyStroke.getKeyStroke('§', 0);
		System.out.println(ks.getKeyCode());

		//ServerBoard s = new ServerBoard();
		/*
		try (ServerSocket serverSocket = new ServerSocket(3001);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				out.println(inputLine);
			}
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port ");
			System.out.println(e.getMessage());
		}

		try {
			Robot robot = new Robot();

			// Simulate a mouse click
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);

			// Simulate a key press

			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);

		} catch (AWTException e) {
			e.printStackTrace();
		}
		/*
		 * try { // Use messaging via Android USB cable. IMessagingSystemFactory
		 * aMessaging = new AndroidUsbCableMessagingFactory();
		 * 
		 * // Specify that the android application will listen on the port 8090.
		 * // Note: adb (on PC) and adbd (on Android) will be configured to
		 * forward the // the communication to the port 8090.
		 * IDuplexOutputChannel anOutputChannel =
		 * aMessaging.createDuplexOutputChannel("8090");
		 * 
		 * // Create message sender. IDuplexTypedMessagesFactory aSenderFactory
		 * = new DuplexTypedMessagesFactory(); mySender =
		 * aSenderFactory.createSyncDuplexTypedMessageSender(String.class,
		 * String.class);
		 * 
		 * // Attach the output channel using Android USB cable and // be able
		 * to send messages and receive responses.
		 * mySender.attachDuplexOutputChannel(anOutputChannel);
		 * 
		 * 
		 * // Send request and wait for response. while(true){
		 * System.out.println("here"); String aResponse =
		 * mySender.sendRequestMessage("getKey"); System.out.println(
		 * "Android responded: " + aResponse); }
		 * 
		 * // Detach output channel and close the connection.
		 * //mySender.detachDuplexOutputChannel();
		 * 
		 * 
		 * } catch (Exception err) { EneterTrace.error("Error detected.", err);
		 * }
		 */
	}
	

}
