    package server;
import server.message.*;
import server.util.UserInfo;

import java.io.*;
import java.net.*;
import java.util.*;

public class farmServer {

	private final int serverPort=12345;
	private static final int VERIFYHEADER = 0xABCD0000;
	private static final int VERIFYMASK	  = 0xFFFF0000;
	private static Queue<MsgSend> sendMsgQ=new LinkedList<MsgSend>();
	
	
	public static void main(String[] args) throws IOException {
		new farmServer();
	}
	
	public farmServer()  {
		
		try
		{
			ServerSocket serverSock = new ServerSocket(serverPort);
			sendThread sT=new sendThread();
			while(true)
			{
				Socket   sock = serverSock.accept();//这应该对应的是TCP三次握手机制=>raise Q
System.out.println(sock);
					Terminal term = new Terminal(sock);
					//ip(MAC)->terminal ID对应表
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private class Terminal implements Runnable
	{
		private int type;//type 0 TQ2440 type 1 PC 
		private Socket sock=null;
		private DataInputStream dataIn = null;
		private UserInfo userInfo=new UserInfo();
		public Terminal(Socket xSock) 
		{
			userInfo.sock = xSock;	
			try {
				dataIn = new DataInputStream(userInfo.sock.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
			startThread();
		}
		public void run() 
		{
			termConnInit();
			while(true)
			{
				try {
					readPacket();					
					Message msg=packetProcFacotry.getMsg(userInfo,dataIn);
					MsgSend waitSendMsg=msg.handleMessage();
					sendMsgQ.add(waitSendMsg);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		//生成发送消息 分解发送消息
		private void startThread()
		{			
			Thread threadTmp = new Thread(this);
			threadTmp.start();
		}
		
		private void termConnInit()
		{
			try {
				userInfo.sock.setKeepAlive(true);//保持长连接
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}
		
		private void readPacket()
		{
//System.out.println("Packet Length:");
			final int sleepMS = 10;
			
			int packetFlag=0;
			try {
System.out.println(dataIn);
//至少在未解决如何判断客户端连接终端之前，这句判断当前是否有数据是必要的
				while(dataIn.available()==0)Thread.sleep(20);
				while(((packetFlag=dataIn.readInt())&VERIFYMASK) !=  VERIFYHEADER);
				int packLen = dataIn.readInt();
				while(dataIn.available()<packLen) 
					Thread.sleep(sleepMS);
			} 
			catch (EOFException e)
			{
			}
//			catch (NullPointerException e)
//			{
//				System.out.println("NullPointerException");
////				e.printStackTrace();
//			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 给各个终端发送消息，消息是由MessageX处理所接收的消息后得来的
	 * 
	 * @author ysp
	 */
	private class sendThread implements Runnable
	{
		private Thread sendTh=null; 
		/**
		 * Class constructor: create a thread to send message and start it.
		 */
		public sendThread()
		{
			sendTh = new Thread(this);
			sendTh.start();
		}
	
		/**
		 * 
		 */
		public void run()
		{
//System.out.println("Sending Thread is working");
			while(true)
			{
				while(sendMsgQ.isEmpty())
				{
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				MsgSend sM=sendMsgQ.poll();
				sM.sendMsgToClient();
			}
		}
	}
}
