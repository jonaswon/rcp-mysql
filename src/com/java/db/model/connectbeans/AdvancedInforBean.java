/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     AdvancedInforBean.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-26   Slive      V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model.connectbeans;

import java.io.Serializable;

/*********************
 * Advanced Information ���ñ���·�� ���� �������Ӽ�� (��) ʹ�� MySQL �ַ��� ʹ��ѹ�� �Զ����� ʹ�ø߼�����
 **********************/
public class AdvancedInforBean implements Serializable
{
	/**
	 * general VersionID
	 */
	private static final long serialVersionUID = 7176219491142823310L;

	/*********************
	 * Advanced Information
	 * ���ñ���·�� ���� �������Ӽ�� (��) ʹ�� MySQL �ַ��� ʹ��ѹ�� �Զ����� ʹ�ø߼�����
	 **********************/
	private String savePath;		// ����·��
	private String characterCode;	// �ַ�����
	private boolean isUseMySQLCode; // ʹ��SQL���룬���ַ��������
	private int keepConnTime;		// �������Ӽ�����룩
	private boolean isCompress;		// �Ƿ�ѹ��
	private boolean isAutoConn;		// �Ƿ��Զ�����
	private boolean isUserAdvancedConn; // �Ƿ�ʹ�ø߼�����
	
	/**
	 * @description ���캯������ʼ��
	 */
	public AdvancedInforBean()
	{
		savePath = "C:/MySQL";
		characterCode = "65001 (UTF-8)";
		isUseMySQLCode = false;
		keepConnTime = -1;
		isCompress = false;
		isAutoConn = false;
		isUserAdvancedConn = false;
	}

	
	public String getSavePath()
	{
		return savePath;
	}

	
	public void setSavePath(String savePath)
	{
		this.savePath = savePath;
	}

	
	public String getCharacterCode()
	{
		return characterCode;
	}

	
	public void setCharacterCode(String characterCode)
	{
		this.characterCode = characterCode;
	}

	
	public boolean isUseMySQLCode()
	{
		return isUseMySQLCode;
	}

	
	public void setUseMySQLCode(boolean isUseMySQLCode)
	{
		this.isUseMySQLCode = isUseMySQLCode;
	}

	
	public int getKeepConnTime()
	{
		return keepConnTime;
	}
	
	public void setKeepConnTime(int keepConnTime)
	{
		this.keepConnTime = keepConnTime;
	}

	public boolean isCompress()
	{
		return isCompress;
	}
	
	public void setCompress(boolean isCompress)
	{
		this.isCompress = isCompress;
	}

	
	public boolean isAutoConn()
	{
		return isAutoConn;
	}

	
	public void setAutoConn(boolean isAutoConn)
	{
		this.isAutoConn = isAutoConn;
	}
	
	public boolean isUserAdvancedConn()
	{
		return isUserAdvancedConn;
	}
	
	public void setUserAdvancedConn(boolean isUserAdvancedConn)
	{
		this.isUserAdvancedConn = isUserAdvancedConn;
	}
}
