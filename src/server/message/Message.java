package server.message;


import java.net.Socket;
import java.util.*;

import server.util.UserInfo;

import java.io.*;

public abstract class Message {
	protected static final String TOKENFLAG=String.format("%c", 0x11);
	protected UserInfo userInfo=null;
	public Message(UserInfo xUserInfo)
	{
		userInfo = xUserInfo;
	}
	
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
	public  abstract MsgSend handleMessage();
	abstract MsgSend messageEncap();
}
