package server.message;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Message1 extends Message {

	private String  account;
	private String  passwd;
	private boolean isRight;
	public Message1(Socket xSS,DataInputStream in)//解码 account+TOKENFLAG+password
	{
		try {
			ss=xSS;
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
	//在handle中就应该把生成的MessageX进队了
	public MsgSend handleMessage()
	{
System.out.println("Message1:HandleMessage");
System.out.println("account:"+ account+ "password:"+ passwd);
//		boolean isRight=verify(account,passwd);
		boolean isRight=true;
		byte msgID=1;
		
		EncapToBytes encap=new EncapToBytes();
		byte[] msgBytes=encap.getBytes(msgID,(byte)(isRight?1:0));
//System.out.println(msgBytes);
		MsgSend msgRet=getSendMsg(msgBytes,ss);
//System.out.println(msgRet);
		return msgRet;
	}
	
	public String toString()
	{
		return getClass().getName()+"[account:"+account+"] [password:"+passwd+"]";
	}
}
