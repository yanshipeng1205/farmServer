package server.message;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;


class EncapToBytes
{
	private static final int MSGHEADER=0xABCD0000;
	private ByteArrayOutputStream byteOut=new ByteArrayOutputStream();
	private DataOutputStream      dataOut=new DataOutputStream(byteOut);
	private  final String TOKENFLAG=String.format("%c", 0x11);

	private int msgLen=0;
	public EncapToBytes()
	{
//		try {
////			dataOut.writeInt(MSGHEADER);
////			dataOut.writeInt(msgLen);
////			str.append(MSGHEADER);
////			str.append(msgLen);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	public byte[] getBytes(byte msgID, byte val)
	{
		try {
			msgLen=2;
			dataOut.writeInt(MSGHEADER);
			dataOut.writeInt(msgLen);
			dataOut.write(msgID);//大小端在哪设置 默认的是大端么
			dataOut.write(val);
			dataOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
	}
	
	public byte[] getBytes(byte msgID, Map<String,Integer> farms)
	{
		try {
			byte[] msgBytes=mergeMapStrInt(farms);
			
			msgLen=msgBytes.length+1;//需要把MSGID算进去
			dataOut.writeInt(MSGHEADER);
			dataOut.writeInt(msgLen);
			dataOut.write(msgID);
			dataOut.write(msgBytes);
			dataOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
	}
	
	public byte[] mergeMapStrInt(Map<String, Integer> farms)
	{
		StringBuilder msg = new StringBuilder();
		for(Map.Entry<String, Integer> entry:farms.entrySet())
		{
System.out.println("Key:"+entry.getKey()+"  value："+entry.getValue().toString());
			msg.append(entry.getKey()+TOKENFLAG);
			msg.append(entry.getValue().toString()+TOKENFLAG);
		}
		if(msg.length()>0)
		{
			msg.deleteCharAt(msg.length()-1);
		}
System.out.println("StringBuilder:"+msg);
		return msg.toString().getBytes();	
		
	}
}

