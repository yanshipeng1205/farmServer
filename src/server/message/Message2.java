package server.message;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.hibernate.manager.UsersManager;
import com.hibernate.manager.UsersManagerImpl;

import server.util.UserInfo;
/**
 * Message1: Register Message from pc client.
 * Purpose:	 register user.
 * Received Packet Structure:(only content after MSGID)
 * account(string)+password(string)+name(string)+[email(string)] []表示可有可无
 * Return Packet Structure: isOK(byte)
 * @author ysp
 *
 */
public class Message2 extends Message{
	private String account;
	private String password;
	private String name;
	private String email;
	private static byte msgID=2;
	private boolean isOK=false;
	public Message2(UserInfo userInfo,DataInputStream in)//解码 account+TOKENFLAG+password
	{
		super(userInfo);
		try {
System.out.println("Message 2 Constructor");
			byte[] buf=new byte[in.available()];
			in.read(buf);
			String tmp=new String(buf);
			Scanner scan=new Scanner(tmp);
			scan.useDelimiter(Message.TOKENFLAG);
			account = scan.next();
			password= scan.next();
			name	= scan.next();
			email	= scan.next();
		} 
		catch (NoSuchElementException e)
		{
			//email项是空的话会报出异常 先忽略这个
			email = "NULL";//?这样 可以么
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MsgSend handleMessage() {
		UsersManager userManager = new UsersManagerImpl();
		isOK=userManager.register(account, password, name, email);
		return messageEncap();
	}
	
	@Override
	MsgSend messageEncap()
	{
		EncapToBytes encap=new EncapToBytes();
		byte[] msgBytes=encap.getBytes(msgID,(byte)(isOK?1:0));
		MsgSend msgRet=getSendMsg(msgBytes,userInfo.sock);
		return msgRet;
	}
}
