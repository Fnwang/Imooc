package com.jms.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.jms.service.ProducerService;

public class ProducerServiceImpl implements ProducerService{

	@Autowired
	private JmsTemplate jmsTemplate;
	
	//@Resource(name="queueDetination")//ָ������ģʽĿ�ĵ�
	@Resource(name="topicDetination")//ָ������ģʽĿ�ĵ�
	private Destination destination;
	
	public void sendMessage(final String message) {
		//ʹ��JmsTemplate������Ϣ
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage=session.createTextMessage(message);
				return textMessage;
			}
		});
		System.out.println("������Ϣ��"+message);
	}

}
