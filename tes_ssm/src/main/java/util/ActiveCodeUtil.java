package util;

import java.util.Random;

public class ActiveCodeUtil {
	
	public static String generateActiveCode() {
		String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb=new StringBuilder(4);
		for(int i=0;i<4;i++)
		{
		char ch=str.charAt(new Random().nextInt(str.length()));
		sb.append(ch);
		}
		return sb.toString();
		
	}

}
