package server.message;


import java.net.Socket;
import java.util.*;
import java.io.*;

public abstract class Message {
	Socket ss;
	protected static final String TOKENFLAG=String.format("%c", 0x11);
	public  abstract MsgSend handleMessage();
	protected MsgSend getSendMsg(byte[] msgBytes,Socket sock)
	{
		MsgSend msgRet=null;
		try {
			msgRet=new MsgSend(msgBytes, sock.getOutputStream());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return msgRet;
	}
}
