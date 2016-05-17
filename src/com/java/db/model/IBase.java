/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     IBase.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-28   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model;


public interface IBase<T>
{
	/**
	 * ��ȡ����
	 */
	public String getName();
	
	/**
	 * ��ȡ�ĸ��ڵ�
	 * @return
	 */
	public T getParent();
	
	/**
	 * ���ø���
	 * @param object
	 */
	public void setParent(T object);
}
