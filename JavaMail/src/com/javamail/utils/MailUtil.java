package com.javamail.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * �ʼ����͹�����
 * @author qiuzhiwen
 *
 */
public class MailUtil {
	
	public static void sendMail(String to,String code){
		Properties props=new Properties();
		//props.setProperty("host", "");
		//1.�������Ӷ������ӵ����������
		Session session=Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@qzw.com", "123");
			}
		});
		//2.�����ʼ�����
		Message message=new MimeMessage(session);
		try {
			//���÷�����
			message.setFrom(new InternetAddress("service@qzw.com"));
			//�����ռ���
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			//�����ʼ�������
			message.setSubject("����XXX��վ�ļ����ʼ�");
			//�����ʼ�������
			message.setContent("<h1>����XXX��վ�ļ����ʼ�,���������������ӣ�</h1><br/><h3><a href='http://localhost:8080/JavaMail/ActiveServlet?code="+code+"'>http://localhost:8080/JavaMail/ActiveServlet?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			//3.���ͼ����ʼ�
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
