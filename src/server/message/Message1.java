package server.message;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.hibernate.manager.UsersManager;
import com.hibernate.manager.UsersManagerImpl;

import server.util.UserInfo;

/**
 * Message1: Login Message from pc client.
 * Purpose:	 verify whether the login is right or not.
 * Received Packet Structure:(only content after MSGID)
 * account(string)+password(string)
 * Return Packet Structure: isRight(byte)
 * @author ysp
 *
 */
public class Message1 extends Message {

	private String  account;
	private String  passwd;
	private static byte msgID=1;
	private boolean isRight=false;
	
	public Message1(UserInfo userInfo,DataInputStream in)//½âÂë account+TOKENFLAG+password
	{
		super(userInfo);
		try {
			byte[] buf=new byte[in.available()];
			in.read(buf);
			String tmp=new String(buf);
			Scanner scan=new Scanner(tmp);
			scan.useDelimiter(Message.TOKENFLAG);
			account = scan.next();
			passwd  = scan.next();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * handle message according the received message
	 * @return return the message that we want to send
	 */
	public MsgSend handleMessage()
	{
		UsersManager userManager = new UsersManagerImpl();
		isRight=userManager.verify(account, passwd);
		if(isRight==true)
		{
			userInfo.account = account;
		}

		return messageEncap();
	}
	
	/**
	 * encapsulate our processed result to Class MsgSend
	 * @return return the message that we want to send
	 */
	MsgSend messageEncap()
	{
		EncapToBytes encap=new EncapToBytes();
		byte[] msgBytes=encap.getBytes(msgID,(byte)(isRight?1:0));
		MsgSend msgRet=getSendMsg(msgBytes,userInfo.sock);
		return msgRet;
	}
	
	public String toString()
	{
		return getClass().getName()+"[account:"+account+"] [password:"+passwd+"]";
	}
}
