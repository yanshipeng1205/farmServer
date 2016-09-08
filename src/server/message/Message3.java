package server.message;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.Map;

import com.hibernate.manager.HousesManager;
import com.hibernate.manager.HousesManagerImpl;

import server.util.UserInfo;


public class Message3 extends Message {

	private Map<String,Integer> farms;
	private static byte msgID=3;
	/**
	 * Msssage3�޾������ݣ����Թ��캯�����ž��У�������û��������캯����
	 * @param xSS
	 * @param in
	 */
	public Message3(UserInfo userInfo,DataInputStream in)
	{
		super(userInfo);
	}

	
	@Override
	public MsgSend handleMessage() {
		HousesManager housesManager = new HousesManagerImpl(userInfo);
		farms=housesManager.getFarms();
		return messageEncap();		
	}

	@Override
	MsgSend messageEncap() {
		EncapToBytes encap=new EncapToBytes();
		byte[] msgBytes=encap.getBytes(msgID,farms);
		MsgSend msgRet=getSendMsg(msgBytes,userInfo.sock);
		return msgRet;	
	}

}
