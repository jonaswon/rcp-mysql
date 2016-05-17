/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     IList.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-28   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model;

/**
 * �������ݿ�ʱ������Ľӿ�
 * @author llp585470
 * @param <T>
 */
public interface IList <T>
{
	/**
	 * ���
	 * @param t
	 * @return
	 */
	public boolean add(T t);
	
	/**
	 * ɾ��
	 * @param t
	 */
	public void remove(T t);
	
	/**
	 * ɾ������
	 */
	public void removeAll();
	
	/**
	 * ��ȡ����
	 * @param index
	 * @return
	 */
	public T get(int index);
	
	/**
	 * �ж��Ƿ����
	 * @return
	 */
	public boolean isExisted(T t);
	
	/**
	 * ��ȡ�ӽڵ�
	 */
	public T[] getChildren();
}
