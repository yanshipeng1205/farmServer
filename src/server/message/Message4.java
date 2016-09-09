package server.message;

import java.io.DataInputStream;
import java.io.IOException;

import com.hibernate.manager.FarmInfoManager;
import com.hibernate.manager.FarmInfoManagerImpl;

import server.util.FarmInfo;
import server.util.UserInfo;

public class Message4 extends Message {

	private int farmId;
	private byte msgID=4;
	FarmInfo farmInfo = null;
	public Message4(UserInfo userInfo,DataInputStream in) 
	{
		super(userInfo);
		try {
			byte[] buf=new byte[in.available()];
			in.read(buf);
			String farmIdStr=new String(buf);
			farmId=Integer.parseInt(farmIdStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MsgSend handleMessage() {
		FarmInfoManager farmStatusManager = new FarmInfoManagerImpl(farmId);
		farmInfo = farmStatusManager.getCurFarmStatus();

		return messageEncap();
	}

	@Override
	MsgSend messageEncap() {
		EncapToBytes encap=new EncapToBytes();
		byte[] msgBytes=encap.getBytes(msgID,farmInfo);
		MsgSend msgRet=getSendMsg(msgBytes,userInfo.sock);
		return msgRet;
	}

}
