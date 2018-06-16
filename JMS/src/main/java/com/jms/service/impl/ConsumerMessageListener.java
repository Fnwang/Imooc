package com.jms.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
/**
 * ��������Ϣ������
 * @author qiuzhiwen
 *
 */
public class ConsumerMessageListener implements MessageListener{

	public void onMessage(Message message) {
		TextMessage textMessage=(TextMessage) message;
		
		try {
			System.out.println("������Ϣ��"+textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
