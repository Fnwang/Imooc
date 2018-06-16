package com.jms.queue;

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
/**
 * ������
 * @author qiuzhiwen
 *
 */
public class AppConsumer {
	
	private static final String URL="tcp://127.0.0.1:61616";
	private static final String QUEUE_NAME="queue_test";
	
	
	public static void main(String[] args) throws JMSException{
		ConnectionFactory connectionFactory=null;
		Connection conn=null;
		try {
			//1.����ActiveMQConnectionFactory����
			connectionFactory=new ActiveMQConnectionFactory(URL);
			//2.����Connection����
			conn=connectionFactory.createConnection();
			//3.��������
			conn.start();
			//4.����Session����
			Session session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5.����Destination
			Destination destination=session.createQueue(QUEUE_NAME);
			//6.����MessageConsumer����
			MessageConsumer consumer=session.createConsumer(destination);
			//7.����MessageListener
			consumer.setMessageListener(new MessageListener() {
				//8.������Ϣ
				public void onMessage(Message message) {
					TextMessage textMessage=(TextMessage) message;
					try {
						System.out.println("������Ϣ��"+textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//9.�ر�����
			//ע�⣺������Ϣ��һ���첽����
			if(conn!=null){
				//conn.close();
			}
		}
	}

}
