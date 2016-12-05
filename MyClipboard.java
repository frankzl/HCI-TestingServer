import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.concurrent.TimeUnit;

public class MyClipboard {
	
	static String sysClipboard;
	static boolean isStored = false;
	
	public static void storeClipboard(){
		sysClipboard = getSysClipboardText();
		isStored = true;
	}
	
	public static void restoreClipboard(){
		setSysClipboardText(sysClipboard);
	}
	
	public static void setMyClipboardText(String myString){
		if(!isStored)
			storeClipboard();
		setSysClipboardText(myString);
	}
	
	
	
	public static void setSysClipboardText(String myString){
		StringSelection str = new StringSelection(myString);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents((str), null);
	}
	

	private static String getSysClipboardText() {
	        String ret = "";
	        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();

	        Transferable clipTf = sysClip.getContents(null);

	        if (clipTf != null) {

	            if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
	                try {
	                    ret = (String) clipTf
	                            .getTransferData(DataFlavor.stringFlavor);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return ret;
	    }
	
}
