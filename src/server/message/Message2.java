package server.message;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.hibernate.manager.UsersManager;
import com.hibernate.manager.UsersManagerImpl;

public class Message2 extends Message{
	private String account;
	private String password;
	private String name;
	private String email;
	
	public Message2(Socket xSS,DataInputStream in)//���� account+TOKENFLAG+password
	{
		try {
System.out.println("Message 2 Constructor");
			ss=xSS;
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
			//email���ǿյĻ��ᱨ���쳣 �Ⱥ������
			email = "NULL";//?���� ����ô
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MsgSend handleMessage() {
		UsersManager userManager = new UsersManagerImpl();
		boolean isOK=userManager.register(account, password, name, email);
		byte msgID=2;
		EncapToBytes encap=new EncapToBytes();
		byte[] msgBytes=encap.getBytes(msgID,(byte)(isOK?1:0));;
		MsgSend msgRet=getSendMsg(msgBytes,ss);
		return msgRet;
	}
}
