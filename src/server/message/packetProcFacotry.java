package server.message;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public class packetProcFacotry {
	
	private packetProcFacotry()
	{}
	/**
	 * 
	 * @param in
	 * @return
	 * msgID 0 means error
	 */
	private static int getMsgId(DataInputStream in) 
	{
		int msgID=0;
		try {
			msgID = in.readByte();
		} 
		catch (EOFException e)
		{
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return msgID;
	}
	
	public static Message getMsg(Socket sock,DataInputStream in) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException
	{
		int msgID = getMsgId(in);
//System.out.println("MSGID:"+msgID);
		String msgClassName 	= "server.message.Message" + msgID;
System.out.println(msgClassName);
		Class  msgClass 	 	= Class.forName(msgClassName);
		Constructor<?> msgCons = msgClass.getConstructor(Socket.class,DataInputStream.class);
		Message msg    =(Message)msgCons.newInstance(sock,in);
//System.out.println(msg);
		return msg;
	}
}
