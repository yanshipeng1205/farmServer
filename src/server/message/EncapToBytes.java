package server.message;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


class EncapToBytes
{
	private static final int MSGHEADER=0xABCD0000;
//	private StringBuilder str = new StringBuilder();
	private ByteArrayOutputStream byteOut=new ByteArrayOutputStream();
	private DataOutputStream      dataOut=new DataOutputStream(byteOut);
	private int msgLen=0;
//	private boolean flag=false;
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
//			msgLen=2;
//			writeLen();
			dataOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
	}
	
	private void writeLen()
	{
		final int mask=0xff;//加上final为何不行了
		byte[] bytes=byteOut.toByteArray();
		//how to improve it
		bytes[4]=(byte)(msgLen>>>24 & mask);
		bytes[5]=(byte)(msgLen>>>16 & mask);
		bytes[6]=(byte)(msgLen>>>8  & mask);
		bytes[7]=(byte)(msgLen      & mask);
	}
}
