package server;
import server.message.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class farmServer {

	private final int serverPort=12345;
	ServerSocket serverSock = new ServerSocket(serverPort);
	private static final int VERIFYHEADER = 0xABCD0000;
	private static final int VERIFYMASK	  = 0xFFFF0000;
	private static Queue<MsgSend> sendMsgQ=new LinkedList<MsgSend>();
	
	
	public static void main(String[] args) throws IOException {
		new farmServer();
	}
	
	public farmServer() throws IOException {
		
		sendThread sT=new sendThread();
		while(true)
		{
			Socket   sock = serverSock.accept();//这应该对应的是TCP三次握手机制=>raise Q
System.out.println(sock);
			Terminal term = new Terminal(sock);
			//ip(MAC)->terminal ID对应表
			
		}
	}
	/**
	 * 
	 * process corresponding Messages(需要一个消息处理器）
	 */
	private class Terminal implements Runnable
	{
		private int type;//type 0 TQ2440 type 1 PC 
		private Socket sock=null;
		private DataInputStream dataIn = null;
		public Terminal(Socket xSock)
		{
			startThread();
			sock = xSock;	
			//保持连接并且确认对方在线
			try {
				dataIn = new DataInputStream(
							new BufferedInputStream(sock.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void run() 
		{
			termConnInit();
//System.out.println("New Connection");
			while(true)
			{
				try {
					readPacket();					
					Message msg=packetProcFacotry.getMsg(sock,dataIn);
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
				sock.setKeepAlive(true);//保持长连接
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
				while(dataIn.available()==0);
				while(((packetFlag=dataIn.readInt())&VERIFYMASK) !=  VERIFYHEADER);
				int packLen = dataIn.readInt();
				while(dataIn.available()<packLen) 
					Thread.sleep(10);
			} 
			catch (EOFException e)
			{
			}
			catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private class sendThread implements Runnable
	{
		private Thread sendTh=null; 
		public sendThread()
		{
			sendTh = new Thread(this);
			sendTh.start();
		}
	
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
