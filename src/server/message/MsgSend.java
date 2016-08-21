package server.message;

import java.io.IOException;
import java.io.OutputStream;

public class MsgSend
{
	public MsgSend(byte[] xMsgBytes, OutputStream xOutput)
	{
		msgBytes  =xMsgBytes;
		output=xOutput;
	}
	
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
		return writeFlag;
	}
	public  byte[] msgBytes;
	public  OutputStream output;
	private boolean writeFlag=false;//what function does writeFlag have?
}
