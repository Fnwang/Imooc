package com.java.security.des;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class My3DESTest {
	
private String text="Jasper is a good student!";
	
	@Test
	public void testJDK3DES(){
		try {
			//����Key
			KeyGenerator keyGenerator=KeyGenerator.getInstance("DESede");
			//keyGenerator.init(56);//JDKDESʵ��--��Կ����Ĭ����56λ
			keyGenerator.init(new SecureRandom());//���ݼ��ܷ�ʽ�������һ������
			SecretKey secretKey=keyGenerator.generateKey();
			byte[] bytesKey=secretKey.getEncoded();
			
			//Keyת��
			DESedeKeySpec desKeySpec=new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory=SecretKeyFactory.getInstance("DESede");
			SecretKey convertSecretKey=factory.generateSecret(desKeySpec);
			
			//����
			Cipher cipher=Cipher.getInstance("DESede/ECB/PKCS5Padding");
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
