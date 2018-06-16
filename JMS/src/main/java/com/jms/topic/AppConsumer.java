package com.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppConsumer {
	
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
			//3.����Connection
			connection.start();
			//4.����Session
			Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5.����Ŀ��Destination
			Destination destination=session.createTopic(TOPIC_NAME);
			//6.����������MessageConsumer
			MessageConsumer messageConsumer=session.createConsumer(destination);
			//7.������Ϣ������MessageListener
			messageConsumer.setMessageListener(new MessageListener() {
				
				public void onMessage(Message message) {
					//8.������Ϣ
					TextMessage textMessage=(TextMessage) message;
					try {
						System.out.println("������Ϣ"+textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(connection!=null){
				try {
					//9.�ر�����
					//connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
