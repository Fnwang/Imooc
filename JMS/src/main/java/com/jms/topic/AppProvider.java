package com.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppProvider {
	
	private static final String URL="tcp://127.0.0.1:61616";
	private static final String TOPIC_NAME="topic_test";
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory=null;
		Connection connection=null;
		try {
			//1.����ConnectionFactory
			connectionFactory=new ActiveMQConnectionFactory(URL);
			//2.����Connection
			connection=connectionFactory.createConnection();
			//3.��������
			connection.start();
			//4.����Session
			Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5.����Destination
			Destination destination=session.createTopic(TOPIC_NAME);
			//6.����������MessageProducer
			MessageProducer messageProducer=session.createProducer(destination);
			
			for(int i=0;i<10;i++){
				//7.������ϢTextMessage
				TextMessage textMessage=session.createTextMessage("topic-test"+i);
				//8.������Ϣ
				messageProducer.send(textMessage);
				System.out.println("������Ϣ��"+textMessage.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(connection!=null){
				try {
					//9.�ر�����
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
