/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     DBBean.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-26   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model.dbbeans;


public class DBBean
{
	private String DBName;			// ���ݿ�����
	private String characterCode;	// �ַ���
	private String sortRule;	    // �������
	
	public DBBean(String dbName)
	{
		DBName = dbName;
		characterCode = "";
		sortRule = "";
	}

	
	public String getCharacterCode()
	{
		return characterCode;
	}

	
	public void setCharacterCode(String characterCode)
	{
		this.characterCode = characterCode;
	}

	
	public String getSortRule()
	{
		return sortRule;
	}

	
	public void setSortRule(String sortRule)
	{
		this.sortRule = sortRule;
	}

	
	public String getDBName()
	{
		return DBName;
	}
	
	
}
