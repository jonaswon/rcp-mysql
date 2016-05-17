/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     TBModel.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-28   Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model;

import java.util.ArrayList;
import java.util.List;


/**
 * ��ģ�ͣ����Դ�Ŷ����view���洢���̼������ṹ
 * @param T ������Ҫ���������ģ��
 */
public abstract class AbstractDBModelManager<T> implements IList<T>,IBase<DBModel>
{
	private DBModel parent;			// ���ڵ�
	protected List<T> list;
	public AbstractDBModelManager()
	{
		list = new ArrayList<T>();
	}
	
	@Override
	public DBModel getParent()
	{
		return parent;
	}
	
	@Override
	public void setParent(DBModel parent)
	{
		this.parent = parent;
	}
	
	@Override
	public void remove(T t)
	{
		list.remove(t);
	}
	@Override
	public void removeAll()
	{
		list.clear();
	}

	@Override
	public T get(int index)
	{
		return list.get(index);
	}
	
}
