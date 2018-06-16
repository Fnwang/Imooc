package com.java.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {
	
	/**
	 * ��ʵ������
	 */
	@Test
	public void test1(){
		//1.����IP�Ͷ˿�
		Jedis jedis=new Jedis("192.168.2.119", 6379);
		//2.��������
		jedis.set("name", "Jasper");
		//3.��ȡ����
		String value=jedis.get("name");
		System.out.println(value);
		System.out.println(jedis.get("foo"));
		//4.�ͷ���Դ
		jedis.close();
	}
	
	/**
	 * ���ӳط�ʽ����
	 */
	@SuppressWarnings("resource")
	@Test
	public void test2(){
		//�������ӳ����ö���
		JedisPoolConfig config=new JedisPoolConfig();
		//�������������
		config.setMaxTotal(30);
		//����������������
		config.setMaxIdle(10);
		//������ӳ�
		JedisPool jedisPool=new JedisPool(config, "192.168.2.119", 6379);
		//��ú��Ķ���
		Jedis jedis=null;
		try {
			//ͨ�����ӳػ�ȡ����
			jedis=jedisPool.getResource();
			//��������
			jedis.set("age", "100");
			//��ȡ����
			System.out.println(jedis.get("age"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//�ر���Դ
			jedis.close();
		}
	}

}
