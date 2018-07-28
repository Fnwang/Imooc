package com.java.security.des;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class DESTest {
	
	private String text="Jasper is a good student!";
	
	@Test
	public void testJDKDES(){
		try {
			//����Key
			KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
			keyGenerator.init(56);//JDKDESʵ��--��Կ����Ĭ����56λ
			SecretKey secretKey=keyGenerator.generateKey();
			byte[] bytesKey=secretKey.getEncoded();
			
			//Keyת��
			DESKeySpec desKeySpec=new DESKeySpec(bytesKey);
			SecretKeyFactory factory=SecretKeyFactory.getInstance("DES");
			SecretKey convertSecretKey=factory.generateSecret(desKeySpec);
			
			//����
			Cipher cipher=Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result=cipher.doFinal(text.getBytes());
			System.out.println("���ܣ�"+Hex.encodeHexString(result));
			
			//����
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result=cipher.doFinal(result);
			System.out.println("���ܣ�"+new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
