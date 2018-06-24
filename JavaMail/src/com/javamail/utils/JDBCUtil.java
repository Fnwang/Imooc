package com.javamail.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * ���õ���ģʽʵ��JDBC�������ݿ�
 * @author qiuzhiwen
 *
 */
public class JDBCUtil {
	//���������������
	private static String url=null;
	private static String user=null;
	private static String password=null;
	public static Connection conn=null;
	
	//1.������������
	private static JDBCUtil instance=null;
	//2.˽�л����캯��
	private JDBCUtil(){
		
	}
	//3.������ȡ��������ķ���
	public static JDBCUtil getInstance(){
		if(instance==null){
			synchronized (JDBCUtil.class) {//���ǲ������
				if(instance==null){
					instance=new JDBCUtil();
				}
			}
		}
		return instance;
	}
	
	//ͨ����̬�����ע�����ݿ���������֤ע��ִֻ��һ��
	static{
		try {
			//�������ݿ������ļ�
			Properties prop=new Properties();
			/**
			* ʹ����·���Ķ�ȡ��ʽ
			* / : б�ܱ�ʾclasspath�ĸ�Ŀ¼
			* ��java��Ŀ�£�classpath�ĸ�Ŀ¼��binĿ¼��ʼ
			* ��web��Ŀ�£�classpath�ĸ�Ŀ¼��WEB-INF/classesĿ¼��ʼ
			*/
			prop.load(JDBCUtil.class.getResourceAsStream("/db.properties"));
			//�������ݿ�����
			Class.forName(prop.getProperty("dataSource.driverClass"));
			url=prop.getProperty("dataSource.url");
			user=prop.getProperty("dataSource.user");
			password=prop.getProperty("dataSource.password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ���ݿ����Ӷ���
	public Connection getConnection(){
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//�ر���Դ
	public void close(ResultSet rs,Statement st,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(st!=null){
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						if(conn!=null){
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			JDBCUtil jdbcUtil=JDBCUtil.getInstance();
			conn=jdbcUtil.getConnection();
			String sql="select * from regist";
			st=conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs!=null&&rs.next()){
				String username=rs.getString("username");
				String nickname=rs.getString("nickname");
				System.out.println(username+"--"+nickname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
