/**
 * =============================================================================
 * @copyright    Copyright 2011
 * @filename     Table.java
 * @description  
 * @version      V001.00  
 * @history  	 ����      �޸���     �汾        ����
 *              2011-10-28  Slive     V001.00		�����ļ�
 *
 * ============================================================================= 
 */

package com.java.db.model;

import com.java.db.model.dbbeans.TBDatas;

/**
 * ������View,Table�ĳ���ģ��
 *
 */
public class AbstractTableModel<T> implements IBase<T>
{
	private String name;	// �����ǿ��޸ĵ�
	private T parent;
	private TBDatas datas;
	
	public AbstractTableModel(String name)
	{
		this.name = name;
		datas = new TBDatas();
	}
	
	public TBDatas getDatas()
	{
		return datas;
	}
	
	/**
	 * ���ֲ���Ϊ�գ����Ҳ����ǷǷ�ֵ
	 * @param name
	 */
	public void setName(String name)
	{
		if(name == null || name.isEmpty())
		{
			return;
		}
		this.name = name;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public T getParent()
	{
		return parent;
	}

	@Override
	public void setParent(T object)
	{
		parent = object;
	}

}
