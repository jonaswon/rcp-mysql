/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     MYSQLException.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-28   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.exception;

import java.sql.SQLException;


public class MYSQLException extends SQLException
{
	private static final long serialVersionUID = 4309102375324044511L;
	
	public MYSQLException()
	{
		super();
	}
	
	public MYSQLException(String msg)
	{
		super(msg);
	}
	
	public void tootipe()
	{
//		MessageBox mb = new MessageBox(activeShell);
//		mb.setText("��ʾ");
//		mb.setMessage("ɾ��ʧ��!");
//		mb.open();
//		getMessage();
	}
}
