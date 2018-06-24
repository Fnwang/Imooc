package com.javamail.service.impl;

import com.javamail.dao.UserDAO;
import com.javamail.dao.impl.UserDAOImpl;
import com.javamail.service.UserService;
import com.javamail.utils.MailUtil;
import com.javamail.vo.UserVO;

public class UserServiceImpl implements UserService{

	@Override
	public void regist(UserVO user) {
		
		//�����ݴ������ݿ�
		UserDAO userDAO=new UserDAOImpl();
		userDAO.regist(user);
		
		//���ͼ����ʼ�
		MailUtil.sendMail(user.getEmail(), user.getCode());
	}

	@Override
	public UserVO findUserByCode(String code) {
		UserDAO userDAO=new UserDAOImpl();
		return userDAO.findUserByCode(code);
	}

	@Override
	public void updateUser(UserVO user) {
		UserDAO userDAO=new UserDAOImpl();
		userDAO.updateUser(user);
		
	}

}
