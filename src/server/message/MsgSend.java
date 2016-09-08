package server.message;

import java.io.IOException;
import java.io.OutputStream;

public class MsgSend
{
	public  byte[] msgBytes;
	public  OutputStream output;
	private boolean writeFlag=false;//what function does writeFlag have?
	
	public MsgSend(byte[] xMsgBytes, OutputStream xOutput)
	{
		msgBytes  =xMsgBytes;
		output=xOutput;
	}
	
	/**
	 * Send Message to Client(Maybe user pc terminal or TQ2440 embedded terminal)
	 * @return indicating whether sending message is successful or not
	 */
	public  boolean sendMsgToClient()
	{
System.out.println("message is sent.");
String tmp=new String(msgBytes);
System.out.println("msgBytes:"+tmp);
		if(writeFlag==true)
			return false;
		try {
			output.write(msgBytes);
			output.flush();
			writeFlag=true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
