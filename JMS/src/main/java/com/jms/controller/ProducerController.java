package com.jms.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jms.service.ProducerService;

public class ProducerController {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//����xml�����ļ�
		ApplicationContext context=new ClassPathXmlApplicationContext("producer.xml");
		//��ȡbean����
		ProducerService producerService=context.getBean(ProducerService.class);
		for(int i=10;i<20;i++){
			//������Ϣ
			producerService.sendMessage("test"+i);
		}
	}

}
