/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     GeneralInforBean.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-26   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model.connectbeans;

import java.io.Serializable;


public class GeneralInforBean implements Serializable
{
	/**
	 * general VersionID
	 */
	private static final long serialVersionUID = 1L;
	/*********************
	 * General Information 
	 * ���������� ������ �������� IP ��ַ �˿� �û��� ����״̬ �Ƿ񱣴��û�����
	 ********************/
	private String DBType;			// ����������
	private String connName;		// ������
	private String hostName;		// �������� IP ��ַ
	private String userName;		// �û���
	private String pwd;				// �û�����
	private boolean isSavePwd;		// �Ƿ񱣴��û�����
	private int port;				// �˿�
	
	/**
	 * @description ���캯������ʼ����������
	 */
	public GeneralInforBean()
	{
		// ��ʼ
		DBType = "";
		connName = "";
		hostName = "";
		userName = "";
		port = -1;
	}
	
	public String getDBType()
	{
		return DBType;
	}
	
	public void setDBType(String type)
	{
		DBType = type;
	}
	
	public String getConnName()
	{
		return connName;
	}
	
	public void setConnName(String connName)
	{
		this.connName = connName;
	}
	
	public String getHostName()
	{
		return hostName;
	}
	
	public void setHostName(String hostName)
	{
		this.hostName = hostName;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public int getPort()
	{
		return port;
	}
	
	public void setPort(int port)
	{
		this.port = port;
	}
	
	public boolean isSavePwd()
	{
		return isSavePwd;
	}
	
	public void setSavePwd(boolean isSavePwd)
	{
		this.isSavePwd = isSavePwd;
	}

	
	public String getPwd()
	{
		return pwd;
	}
	
	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}
	
}
